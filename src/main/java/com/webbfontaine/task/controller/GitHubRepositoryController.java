package com.webbfontaine.task.controller;

import com.webbfontaine.task.model.CommitResult;
import com.webbfontaine.task.model.Contributor;
import com.webbfontaine.task.model.Home;
import com.webbfontaine.task.model.Repository;
import com.webbfontaine.task.model.SearchResult;
import com.webbfontaine.task.service.impl.GitHubRepositoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/api/v1/home", produces = MediaType.APPLICATION_JSON_VALUE)
public class GitHubRepositoryController {

    private static final String template = "Simple data analysis for github repositories!";

    private final AtomicLong counter = new AtomicLong();

    @Autowired
    private GitHubRepositoryServiceImpl gitHubService;

    @GetMapping
    public ResponseEntity<Home> welcome() {
        return new ResponseEntity<>(new Home(counter.incrementAndGet(), String.format(template)), HttpStatus.OK);
    }

    @GetMapping("/repositories")
    public List<Repository> getAllRepositories() {
        return gitHubService.getAllRepositories();
    }

    @GetMapping("/search/repositories/**")
    public ResponseEntity<SearchResult> searchGitRepositories(HttpServletRequest request) {
        return new ResponseEntity<>(gitHubService.searchRepositories(extractRepositoryName(request)), HttpStatus.OK);
    }

    @GetMapping("/contributors/**")
    public List<Contributor> getContributors(HttpServletRequest request) {
        return gitHubService.getContributors(extractRepositoryName(request));
    }

    @GetMapping("/commits/**")
    public List<CommitResult> getCommits(HttpServletRequest request) {
        return gitHubService.getCommits(extractRepositoryName(request));
    }

    private String extractRepositoryName(HttpServletRequest request) {
        String path = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
        String bestMatchPattern = (String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
        return new AntPathMatcher().extractPathWithinPattern(bestMatchPattern, path);
    }
}
