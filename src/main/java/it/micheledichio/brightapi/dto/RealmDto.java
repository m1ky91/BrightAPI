package it.micheledichio.brightapi.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RealmDto {

    private Long id;
    private String name;
    private String description;
    private String key;

}
