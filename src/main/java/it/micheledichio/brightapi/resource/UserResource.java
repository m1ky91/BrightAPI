package it.micheledichio.brightapi.resource;

import it.micheledichio.brightapi.dao.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("service/user")
public class UserResource {

    @GetMapping(value = "/realm/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable("id") Long id) {
        return new ResponseEntity<>(new UserDto(), HttpStatus.OK);
    }

    @PostMapping(value = "/realm")
    public ResponseEntity<UserDto> addEmployee (@RequestBody UserDto user) {
        return new ResponseEntity<>(new UserDto(), HttpStatus.CREATED);
    }

}
