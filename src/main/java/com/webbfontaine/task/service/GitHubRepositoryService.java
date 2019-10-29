package com.webbfontaine.task.service;

import com.webbfontaine.task.exception.ServiceException;
import com.webbfontaine.task.model.CommitResult;
import com.webbfontaine.task.model.Contributor;
import com.webbfontaine.task.model.Repository;
import com.webbfontaine.task.model.SearchResult;

import java.util.List;

public interface GitHubRepositoryService {

    SearchResult searchRepositories(String repositoryName);

    List<Repository> getAllRepositories() throws ServiceException;

    List<Contributor> getContributors(String repositoryName);

    List<CommitResult> getCommits(String repositoryName);

}
