package it.micheledichio.brightapi.resource;

import it.micheledichio.brightapi.dto.Error;
import it.micheledichio.brightapi.dto.RealmDto;
import it.micheledichio.brightapi.service.RealmService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("service/user")
public class UserResource {

    @Autowired
    private RealmService realmService;

    @GetMapping(value = "/realm/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> getUserRealm(@PathVariable("id") Long id) {
        Optional<RealmDto> realmDto = realmService.getById(id);

        if (realmDto.isPresent()) {
            return new ResponseEntity<>(realmDto.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new Error("RealmNotFound"), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(
            value = "/realm",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> createUserRealm (@RequestBody @Valid RealmDto realm) {
        Optional<RealmDto> realmDto = realmService.create(realm);

        if (realmDto.isPresent()) {
            return new ResponseEntity<>(realmDto.get(), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(new Error("DuplicateRealmName"), HttpStatus.BAD_REQUEST);
        }
    }

}
