package it.micheledichio.brightapi.service;

import it.micheledichio.brightapi.dto.RealmDto;
import it.micheledichio.brightapi.model.Realm;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@ActiveProfiles("test")
public class RealmMapperTests {

    private RealmMapper mapper
            = Mappers.getMapper(RealmMapper.class);

    @Test
    @DisplayName("Mapper from entity to dto works correctly")
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
    @DisplayName("Mapper from dto to entity works correctly")
    public void givenDtoToEntity_whenMaps_thenCorrect() {
        RealmDto dto = new RealmDto();
        dto.setName("name");
        dto.setDescription("description");
        Realm enity = mapper.toEntity(dto);
        assertEquals(dto.getName(), enity.getName());
        assertEquals(dto.getDescription(),
                enity.getDescription());
    }

    @Test
    @DisplayName("Mapper from null entity to dto")
    public void givenEntityNull() {
        Realm enity = null;
        RealmDto dto = mapper.toDto(enity);

        assertNull(dto);
    }

    @Test
    @DisplayName("Mapper from null dto to entity")
    public void givenDtoNull() {
        RealmDto dto = null;
        Realm enity = mapper.toEntity(dto);

        assertNull(enity);
    }

}
