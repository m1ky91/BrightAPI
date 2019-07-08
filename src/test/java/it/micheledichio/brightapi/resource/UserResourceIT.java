package it.micheledichio.brightapi.resource;

import it.micheledichio.brightapi.dto.RealmDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserResource.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
public class UserResourceIT {

    private RealmDto realm;

    @Autowired
    private MockMvc mockMvc;

    public RealmDto createDto() {
        RealmDto realm = new RealmDto();
        realm.setId(1L);
        return realm;
    }

    @BeforeAll
    public void initTests() {
        realm = createDto();
    }

    @Test
    public void getUserRealm() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/service/user/realm/{id}", realm.getId()))
                .andExpect(status().isOk());
    }

    @Test
    public void createUserRealm() throws Exception {
        mockMvc.perform( MockMvcRequestBuilders
                .post("/service/user/realm")
                .content(ResourceUtil.asJsonString(realm))
                .contentType(ResourceUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isCreated());
    }

}
