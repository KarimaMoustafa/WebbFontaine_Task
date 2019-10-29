import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {RepositoryService} from "../services/repository.service";
import {AuthenticationService} from "../services/authentication.service";
import {Repository} from "../models/repository";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {SearchResult} from "../models/search-result";
import {Contributor} from "../models/contributor";
import {CommitResult} from "../models/commit-result";

@Component({
  selector: 'app-hello-world',
  templateUrl: './repository.component.html',
  styleUrls: ['./repository.component.css']
})
export class RepositoryComponent implements OnInit {

  welcomeMessage = '';
  searchForm: FormGroup;
  filteredRepositories: Repository[];
  searchResult: SearchResult;
  cols: any[];
  showTable: boolean;
  contributors: Contributor[];
  commitResults: CommitResult[];
  progressBarValue: number = 0;
  showProgressBar: boolean;

  constructor(private fb: FormBuilder, private route: ActivatedRoute, private app: AuthenticationService,
              private router: Router, private homeService: RepositoryService) {
  }

  ngOnInit() {
    this.homeService.executeHelloWorldService().subscribe((res) => {
      this.welcomeMessage = res.content;
    });
    this.initForm();
  }

  initForm(): void {
    this.searchForm = this.fb.group({
      repository: new FormControl('', Validators.required)
    });
  }

  private filterRepository(event) {
    this.showTable = false;
    this.homeService.getAll().subscribe(repositories => {
      this.filteredRepositories = RepositoryComponent.searchRepositories(event.query, repositories);
      if (this.filteredRepositories.length === 0) {
        this.searchForm.get('repository').setValue('');
      }
    });

  }

  private static searchRepositories(query, repositories: any[]): any[] {
    const filtered: any[] = [];
    for (const item of repositories) {
      const repo = item;
      if (repo.name.toLowerCase().indexOf(query.toLowerCase()) === 0) {
        filtered.push(repo);
      }
    }

    return filtered;
  }

  searchRepository() {
    const selectedRepository = (this.searchForm.get('repository').value) as Repository;
    this.homeService.getRepository(selectedRepository.full_name).subscribe(searchResult => {
      this.searchResult = searchResult;
      this.progressBarValue = 40;
      this.homeService.getContributors(selectedRepository.full_name).subscribe(contributors => {
        this.contributors = contributors;
        this.progressBarValue = 80;
        this.homeService.getCommits(selectedRepository.full_name).subscribe(commitResults => {
          this.commitResults = commitResults;
          this.progressBarValue = 100;
          this.showProgressBar = false;
          this.showTable = true;
        });
      });
    });
  }
}
