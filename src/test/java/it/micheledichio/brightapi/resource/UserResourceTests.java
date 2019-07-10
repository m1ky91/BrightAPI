package it.micheledichio.brightapi.resource;

import it.micheledichio.brightapi.dto.Error;
import it.micheledichio.brightapi.dto.RealmDto;
import it.micheledichio.brightapi.service.RealmService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
public class UserResourceTests {

    private RealmDto realm, realmToCreate;

    @Mock
    private RealmService realmService;

    @InjectMocks
    private UserResource userResource;

    private RealmDto createValidDto() {
        RealmDto realm = new RealmDto();
        realm.setId(1L);
        realm.setName("test");
        return realm;
    }

    private RealmDto createValidDtoToCreate() {
        RealmDto realm = new RealmDto();
        realm.setName("testTwo");
        return realm;
    }

    @BeforeEach
    public void initTests() {
        realm = createValidDto();
        realmToCreate = createValidDtoToCreate();
    }

    @Test
    @DisplayName("Get a realm by id from the service and check result")
    public void getUserRealm_byId() {
        Optional<RealmDto> opRealmDto = Optional.ofNullable(realm);
        when(realmService.getById(realm.getId())).thenReturn(opRealmDto);
        ResponseEntity<?> userRealm = userResource.getUserRealm(realm.getId());
        assertEquals(userRealm, new ResponseEntity<>(realm, HttpStatus.OK));
    }

    @Test
    @DisplayName("Create a user realm and check result")
    public void createUserRealm() {
        when(realmService.create(realmToCreate)).thenReturn(Optional.ofNullable(realmToCreate));
        ResponseEntity<?> userRealm = userResource.createUserRealm(realmToCreate);
        assertEquals(userRealm, new ResponseEntity<>(realmToCreate, HttpStatus.CREATED));
    }

    @Test
    @DisplayName("Get an error representation if the id does not identify an existing realm")
    public void getUserRealm_idDoesNotExists() {
        when(realmService.getById(10L)).thenReturn(Optional.empty());
        ResponseEntity<?> userRealm = userResource.getUserRealm(10L);
        assertEquals(userRealm, new ResponseEntity<>(new Error("RealmNotFound"), HttpStatus.NOT_FOUND));
    }

    @Test
    @DisplayName("Get an error if create a user realm with an existing realm name")
    public void createUserRealm_existingRealm() {
        when(realmService.create(realmToCreate)).thenReturn(Optional.empty());
        ResponseEntity<?> userRealm = userResource.createUserRealm(realmToCreate);
        assertEquals(userRealm, new ResponseEntity<>(new Error("DuplicateRealmName"), HttpStatus.BAD_REQUEST));
    }

}
