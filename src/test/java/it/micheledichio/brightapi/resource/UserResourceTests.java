package it.micheledichio.brightapi.resource;

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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
public class UserResourceTests {

    private RealmDto realm;

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

    @BeforeEach
    public void initTests() {
        realm = createValidDto();
    }

    @Test
    @DisplayName("Get a realm by id from the service and check result")
    public void getUserRealm_byId() {
        when(realmService.getById(realm.getId())).thenReturn(realm);
        userResource = new UserResource(realmService);
        ResponseEntity<RealmDto> userRealm = userResource.getUserRealm(realm.getId());
        assertEquals(userRealm, new ResponseEntity<>(realm, HttpStatus.OK));
    }

}
