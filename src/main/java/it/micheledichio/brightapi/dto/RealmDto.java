package it.micheledichio.brightapi.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RealmDto {

    private Long id;
    @NotNull(message = "InvalidRealmName")
    private String name;
    private String description;
    private String key;

}
