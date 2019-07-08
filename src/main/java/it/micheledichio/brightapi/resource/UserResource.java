package it.micheledichio.brightapi.resource;

import it.micheledichio.brightapi.dto.RealmDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("service/user")
public class UserResource {

    @GetMapping(value = "/realm/{id}")
    public ResponseEntity<RealmDto> getUser(@PathVariable("id") Long id) {
        return new ResponseEntity<>(new RealmDto(), HttpStatus.OK);
    }

    @PostMapping(value = "/realm")
    public ResponseEntity<RealmDto> addEmployee (@RequestBody RealmDto realm) {
        return new ResponseEntity<>(new RealmDto(), HttpStatus.CREATED);
    }

}
