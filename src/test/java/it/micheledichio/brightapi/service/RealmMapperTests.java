package it.micheledichio.brightapi.service;

import it.micheledichio.brightapi.dto.RealmDto;
import it.micheledichio.brightapi.model.Realm;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
public class RealmMapperTests {

    private RealmMapper mapper
            = Mappers.getMapper(RealmMapper.class);

    @Test
    public void givenEntityToDto_whenMaps_thenCorrect() {
        Realm enity = new Realm();
        enity.setName("name");
        enity.setDescription("description");
        RealmDto dto = mapper.toDto(enity);

        assertEquals(enity.getName(), dto.getName());
        assertEquals(enity.getDescription(),
                dto.getDescription());
    }

    @Test
    public void givenDtoToEntity_whenMaps_thenCorrect() {
        RealmDto dto = new RealmDto();
        dto.setName("name");
        dto.setDescription("description");
        Realm enity = mapper.toEntity(dto);
        assertEquals(dto.getName(), enity.getName());
        assertEquals(dto.getDescription(),
                enity.getDescription());
    }

}
