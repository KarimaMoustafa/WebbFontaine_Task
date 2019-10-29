package com.webbfontaine.task.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Contributor
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Contributor {

    @JsonProperty("login")
    @JsonAlias("name")
    private String name;

    private String email;

    private Long id;

    private Long contributions;

}
