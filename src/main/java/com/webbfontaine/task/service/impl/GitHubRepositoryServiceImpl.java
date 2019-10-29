package com.webbfontaine.task.service.impl;

import com.webbfontaine.task.exception.ServiceException;
import com.webbfontaine.task.model.CommitResult;
import com.webbfontaine.task.model.Contributor;
import com.webbfontaine.task.model.Repository;
import com.webbfontaine.task.model.SearchResult;
import com.webbfontaine.task.service.GitHubRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class GitHubRepositoryServiceImpl implements GitHubRepositoryService {

    private static final String SEARCH_REPOSITORY_URL_PREFIX = "https://api.github.com/search/repositories?";

    private static final String ALL_REPOSITORIES_URL_PREFIX = "https://api.github.com/repositories";

    private static final String REPOSITORY_URL_PREFIX = "https://api.github.com/repos/";

    public static final String GIT_TOKEN = "e6a4c6f0f816783dc12fa095a1d3cd1730ccf672";

    @Autowired
    private RestTemplate restTemplate;

    /**
     *
     * @param repositoryName repository name to find it
     * @return exist repository with provided full name
     * @throws ServiceException
     */
    @Override
    public SearchResult searchRepositories(final String repositoryName) throws ServiceException {
        try {
            return restTemplate.getForObject(buildSearchRepositoriesURL(repositoryName), SearchResult.class);
        } catch (final Exception e) {
            throw new ServiceException("Generic Error", e.getMessage());
        }
    }

    /**
     *
     * @return list of all public repositories
     * @throws ServiceException
     */
    @Override
    public List<Repository> getAllRepositories() throws ServiceException {
        try {
            ResponseEntity<Repository[]> forEntity = restTemplate.exchange(
                    ALL_REPOSITORIES_URL_PREFIX,
                    HttpMethod.GET,
                    new HttpEntity<>(getHeaders()),
                    Repository[].class
            );
            return Arrays.asList(forEntity.getBody());
        } catch (final Exception e) {
            throw new ServiceException("Generic Error", e.getMessage(), e.getCause());
        }
    }

    /**
     *
     * @param repositoryName repository name to find its contributors
     * @return list of contributors
     * @throws ServiceException
     */
    @Override
    public List<Contributor> getContributors(final String repositoryName) throws ServiceException {
        try {
            ResponseEntity<Contributor[]> forEntity = restTemplate.exchange(
                    buildGetContributorsURL(repositoryName),
                    HttpMethod.GET,
                    new HttpEntity<>(getHeaders()),
                    Contributor[].class
            );
            return Arrays.asList(forEntity.getBody());
        } catch (final Exception e) {
            throw new ServiceException("Generic Error", e.getMessage(), e.getCause());
        }
    }

    /**
     *
     * @param repositoryName repository name to find its commits and statistics as well
     * @return list of commits per user and statistics
     * @throws ServiceException
     */
    @Override
    public List<CommitResult> getCommits(final String repositoryName) throws ServiceException {
        try {
            ResponseEntity<CommitResult[]> forEntity = restTemplate.exchange(
                    buildGetCommitsURL(repositoryName),
                    HttpMethod.GET,
                    new HttpEntity<>(getHeaders()),
                    CommitResult[].class
            );
            List<CommitResult> commits = Arrays.asList(forEntity.getBody());
            return setCommitStats(repositoryName, commits);
        } catch (final Exception e) {
            e.printStackTrace();
            throw new ServiceException("Generic Error", e.getMessage(), e.getCause());
        }
    }

    private List<CommitResult> setCommitStats(String repositoryName, List<CommitResult> commits) {
        List<CommitResult> finalResult = new ArrayList<>();
        commits.forEach(commitResult ->
                finalResult.add(restTemplate.exchange(buildGetStatsURL(repositoryName, commitResult.getSha()),
                        HttpMethod.GET, new HttpEntity<>(getHeaders()), CommitResult.class).getBody()));
        return finalResult;
    }

    private String buildSearchRepositoriesURL(String repositoryName) {
        return SEARCH_REPOSITORY_URL_PREFIX + "q=repo:" + repositoryName;
    }

    private String buildGetContributorsURL(String repositoryName) {
        return REPOSITORY_URL_PREFIX + repositoryName + "/contributors?anon=1";
    }

    private String buildGetCommitsURL(String repositoryName) {
        return REPOSITORY_URL_PREFIX + repositoryName + "/commits";
    }

    private String buildGetStatsURL(String repositoryName, String sha) {
        return REPOSITORY_URL_PREFIX + repositoryName + "/commits/" + sha;
    }

    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + GIT_TOKEN);
        return headers;
    }

}
