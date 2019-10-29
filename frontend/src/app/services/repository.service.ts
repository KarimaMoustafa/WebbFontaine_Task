import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Message} from "../models/message";
import {Observable} from "rxjs";
import {Repository} from "../models/repository";
import {SearchResult} from "../models/search-result";
import {Contributor} from "../models/contributor";
import {CommitResult} from "../models/commit-result";

@Injectable({
  providedIn: 'root'
})
export class RepositoryService {

  protected API_ENDPOINT: string = "http://localhost:8080/api/v1/home";

  constructor(private http: HttpClient) {
  }

  executeHelloWorldService() {
    return this.http.get<Message>(`${this.API_ENDPOINT}`);
  }

  getAll(): Observable<Repository[]> {
    return this.http.get<Repository[]>(`${this.API_ENDPOINT}/repositories`);
  }

  getContributors(repositoryName: string): Observable<Contributor[]> {
    return this.http.get<Contributor[]>(`${this.API_ENDPOINT}/contributors/${repositoryName}`);
  }

  getCommits(repositoryName: string): Observable<CommitResult[]> {
    return this.http.get<CommitResult[]>(`${this.API_ENDPOINT}/commits/${repositoryName}`);
  }

  getRepository(repositoryName: string): Observable<SearchResult> {
    return this.http.get<SearchResult>(`${this.API_ENDPOINT}/search/repositories/${repositoryName}`);
  }
}
