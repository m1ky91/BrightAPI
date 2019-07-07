package it.micheledichio.brightapi.resource;

import it.micheledichio.brightapi.BrightApiApplication;
import it.micheledichio.brightapi.dao.UserDto;
import it.micheledichio.brightapi.resource.UserResource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserResource.class)
public class UserResourceIT {

    private UserDto user;

    @Autowired
    private MockMvc mockMvc;

    public UserDto createDto() {
        UserDto user = new UserDto();
        user.setId(1L);
        return user;
    }

    @BeforeEach
    public void initTest() {
        user = createDto();
    }

    @Test
    public void getUserRealm() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/service/user/realm/{id}", user.getId()))
                .andExpect(status().isOk());
    }

}
