package com.webbfontaine.task.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

/**
 * Commit Result.
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CommitResult {

    private Commit commit;

    private String sha;

    private Stats stats;

    @Override
    public String toString() {
        return "Commit{" +
                "commit='" + commit + '\'' +
                "stats='" + stats + '\'' +
                ", sha=" + sha +
                '}';
    }
}
