package it.micheledichio.brightapi.resource;

import it.micheledichio.brightapi.dto.RealmDto;
import it.micheledichio.brightapi.service.RealmService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("service/user")
public class UserResource {

    @Autowired
    private RealmService realmService;

    @GetMapping(value = "/realm/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<RealmDto> getUserRealm(@PathVariable("id") Long id) {
        return new ResponseEntity<>(realmService.getById(id), HttpStatus.OK);
    }

    @PostMapping(
            value = "/realm",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<RealmDto> createUserRealm (@RequestBody @Valid RealmDto realm) {
        return new ResponseEntity<>(new RealmDto(), HttpStatus.CREATED);
    }

}
