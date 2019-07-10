package it.micheledichio.brightapi.service;

import it.micheledichio.brightapi.dto.RealmDto;
import it.micheledichio.brightapi.model.Realm;
import it.micheledichio.brightapi.repository.RealmRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Optional;

@AllArgsConstructor
@Service
public class RealmService {

    @Autowired
    private RealmRepository realmRepository;

    @Autowired
    private RealmMapper realmMapper;

    public Optional<RealmDto> getById(Long id) {
        return realmRepository.findById(id)
                .flatMap(r -> Optional.ofNullable(realmMapper.toDto(r)));
    }

    public Optional<RealmDto> create(RealmDto realmToCreate) {
        Optional<Realm> realmFindByName = realmRepository.findByName(realmToCreate.getName());

        if (realmFindByName.isPresent()) {
            return Optional.empty();
        } else {
            String digest = DigestUtils.md5DigestAsHex(realmToCreate.getName().getBytes());
            realmToCreate.setKey(digest);
            Realm realmSaved = realmRepository.save(realmMapper.toEntity(realmToCreate));
            return Optional.of(realmMapper.toDto(realmSaved));
        }
    }
}
