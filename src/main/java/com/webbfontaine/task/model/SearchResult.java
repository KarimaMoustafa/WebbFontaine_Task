package com.webbfontaine.task.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Search repositories result
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchResult {

    @JsonProperty(value = "total_count")
    private Long totalCount;

    private List<Repository> items;
}
