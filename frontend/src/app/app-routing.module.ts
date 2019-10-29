import {RouterModule, Routes} from "@angular/router";
import {RepositoryComponent} from "./home/repository.component";
import {ModuleWithProviders, NgModule} from "@angular/core";
import {LoginComponent} from "./login/login.component";

const routes: Routes = [
  {path: 'login', component: LoginComponent},
  {path: '', component: LoginComponent},
  {path: 'home', component: RepositoryComponent},
  {path: 'logout', component: LoginComponent}
];

export const appRoutes: ModuleWithProviders = RouterModule.forRoot(routes);
