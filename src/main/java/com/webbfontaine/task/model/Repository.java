package com.webbfontaine.task.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Repository model
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Repository {

    private Long id;

    private String name;

    @JsonProperty(value = "full_name")
    private String fullName;

    @JsonProperty(value = "commits_url")
    private String commitsURL;
}
