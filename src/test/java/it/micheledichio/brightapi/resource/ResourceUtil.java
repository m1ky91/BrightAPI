package it.micheledichio.brightapi.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;

public class ResourceUtil {

    public static final MediaType APPLICATION_JSON_UTF8 = MediaType.APPLICATION_JSON_UTF8;

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
