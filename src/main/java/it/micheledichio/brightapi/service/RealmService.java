package it.micheledichio.brightapi.service;

import it.micheledichio.brightapi.dto.RealmDto;
import org.springframework.stereotype.Service;

@Service
public class RealmService {

    public RealmDto getById(Long id) {
        return new RealmDto(1L, "test", null, null);
    }

}
