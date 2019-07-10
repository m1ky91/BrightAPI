package it.micheledichio.brightapi.service;

import it.micheledichio.brightapi.dto.RealmDto;
import it.micheledichio.brightapi.model.Realm;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RealmMapper {

    RealmDto toDto(Realm source);
    Realm toEntity(RealmDto destination);

}
