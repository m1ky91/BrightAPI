package it.micheledichio.brightapi.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "realm")
public class RealmDto {

    @XmlAttribute
    private Long id;
    @XmlAttribute
    @NotNull(message = "InvalidRealmName")
    private String name;
    private String description;
    private String key;

}
