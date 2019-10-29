package com.webbfontaine.task.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

/**
 * Committer.
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Committer {

    private String name;

    private String email;

    private Instant date;

    @Override
    public String toString() {
        return "Committer{" +
                "name='" + name + '\'' +
                ", email=" + email + '\'' +
                ", date=" + date +
                '}';
    }


}
