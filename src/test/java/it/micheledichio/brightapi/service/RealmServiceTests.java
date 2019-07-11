package it.micheledichio.brightapi.service;

import it.micheledichio.brightapi.dto.RealmDto;
import it.micheledichio.brightapi.model.Realm;
import it.micheledichio.brightapi.repository.RealmRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.DigestUtils;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
public class RealmServiceTests {

    private Realm realm;
    private RealmDto realmDto;

    @Mock
    private RealmRepository realmRepository;

    @Mock
    private RealmMapper realmMapper;

    @InjectMocks
    private RealmService realmService;

    private Realm createValidEnity() {
        Realm realm = new Realm();
        realm.setId(1L);
        realm.setName("test");
        return realm;
    }

    private RealmDto createValidDto() {
        RealmDto realm = new RealmDto();
        realm.setId(1L);
        realm.setName("test");
        return realm;
    }

    @BeforeEach
    public void initTests() {
        realm = createValidEnity();
        realmDto = createValidDto();
    }

    @Test
    @DisplayName("Get by id works as expected and return an optional dto after mapper action")
    public void getById(){
        when(realmRepository.findById(realmDto.getId())).thenReturn(Optional.of(realm));
        when(realmMapper.toDto(realm)).thenReturn(realmDto);
        assertEquals(Optional.of(realmDto), realmService.getById(realmDto.getId()));
    }

    @Test
    @DisplayName("Get by id works as expected and return an empty optional dto if the repository returns an empty entity")
    public void getById_whenEmptyEntity(){
        when(realmRepository.findById(realmDto.getId())).thenReturn(Optional.empty());
        when(realmMapper.toDto(realm)).thenReturn(null);
        assertEquals(Optional.empty(), realmService.getById(realmDto.getId()));
    }

    @Test
    @DisplayName("Create works as expected and return an optional dto")
    public void create(){
        when(realmRepository.findByName(realmDto.getName())).thenReturn(Optional.empty());
        String digest = DigestUtils.md5DigestAsHex(realmDto.getName().getBytes());
        realmDto.setKey(digest);
        realm.setKey(digest);
        when(realmMapper.toEntity(realmDto)).thenReturn(realm);
        when(realmRepository.save(realm)).thenReturn(realm);
        when(realmMapper.toDto(realm)).thenReturn(realmDto);
        assertEquals(Optional.of(realmDto), realmService.create(realmDto));
    }

    @Test
    @DisplayName("Create works as expected and return an empty optional dto if the realm name already exists")
    public void create_whenExistingName(){
        when(realmRepository.findByName(realmDto.getName())).thenReturn(Optional.of(realm));
        assertEquals(Optional.empty(), realmService.create(realmDto));
    }

}
