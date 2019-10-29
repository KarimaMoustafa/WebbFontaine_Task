package com.webbfontaine.task.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Stats.
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Stats {

    @JsonProperty(value = "total")
    private Long changes;

    private Long additions;

    private Long deletions;

}
