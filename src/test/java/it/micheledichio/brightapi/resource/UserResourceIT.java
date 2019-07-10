package it.micheledichio.brightapi.resource;

import it.micheledichio.brightapi.dto.Error;
import it.micheledichio.brightapi.dto.RealmDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
public class UserResourceIT {

    private RealmDto realm, realmNotValid;

    @Autowired
    private MockMvc mockMvc;

    private RealmDto createValidDto() {
        RealmDto realm = new RealmDto();
        realm.setId(1L);
        realm.setName("test");
        return realm;
    }

    private RealmDto createNotValidDto() {
        RealmDto realm = new RealmDto();
        return realm;
    }

    @BeforeEach
    public void initTests() {
        realm = createValidDto();
        realmNotValid = createNotValidDto();
    }

    @Test
    @DisplayName("Get request for a user realm is performed correcly for Content-Type JSON")
    public void getUserRealm_contentTypeJSON() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/service/user/realm/{id}", realm.getId())
                    .accept(ResourceUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().string(ResourceUtil.asJsonString(realm)));;
    }

    @Test
    @DisplayName("Get request for a user realm is performed correcly for Content-Type XML")
    public void getUserRealm_contentTypeXML() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/service/user/realm/{id}", realm.getId())
                    .accept(ResourceUtil.APPLICATION_XML))
                .andExpect(status().isOk())
                .andExpect(content().string(ResourceUtil.asXmlString(realm)));
    }

    @Test
    @DisplayName("Bad request after a get request if the requested realm id is not an integer value")
    public void getUserRealm_idIsNotAnIntegerValue() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/service/user/realm/{id}", realm.getName())
                        .accept(ResourceUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(ResourceUtil.asJsonString(new Error("InvalidArgument"))));
    }

    @Test
    @DisplayName("Post request for a user realm is performed correcly for Content-Type JSON")
    public void createUserRealm_contentTypeJSON() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/service/user/realm")
                    .content(ResourceUtil.asJsonString(realm))
                    .accept(ResourceUtil.APPLICATION_JSON_UTF8)
                    .contentType(ResourceUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Bad request after a post request if the mandatory realm name is not supplied")
    public void createUserRealm_withoutRealmName() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/service/user/realm")
                    .content(ResourceUtil.asJsonString(realmNotValid))
                    .accept(ResourceUtil.APPLICATION_JSON_UTF8)
                    .contentType(ResourceUtil.APPLICATION_JSON_UTF8))
                .andExpect(content().string(ResourceUtil.asJsonString(new Error("InvalidRealmName"))))
                .andExpect(status().isBadRequest());
    }

}
