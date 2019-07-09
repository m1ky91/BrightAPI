package it.micheledichio.brightapi.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.http.MediaType;

public class ResourceUtil {

    public static final MediaType APPLICATION_JSON_UTF8 = MediaType.APPLICATION_JSON_UTF8;
    public static final MediaType APPLICATION_XML = MediaType.APPLICATION_XML;

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String asXmlString(final Object obj) {
        try {
            return new XmlMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
