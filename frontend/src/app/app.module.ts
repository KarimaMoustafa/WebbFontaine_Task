import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {RepositoryComponent} from './home/repository.component';
import {MenuComponent} from './menu/menu.component';
import {RouterModule} from "@angular/router";
import {appRoutes} from "./app-routing.module";
import {RepositoryService} from "./services/repository.service";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {LoginComponent} from './login/login.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {AuthInterceptor} from "./services/auth.interceptor.service";
import {LogoutComponent} from './logout/logout.component';
import {AutoCompleteModule} from "primeng/autocomplete";
import {MessageModule} from "primeng/message";
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {ButtonModule} from "primeng/button";
import {FieldsetModule} from "primeng/fieldset";
import {DialogModule} from "primeng/dialog";
import {ProgressBarModule} from "primeng/primeng";

@NgModule({
  declarations: [
    AppComponent,
    RepositoryComponent,
    MenuComponent,
    LoginComponent,
    LogoutComponent
  ],
  imports: [
    BrowserModule,
    appRoutes,
    RouterModule,
    HttpClientModule,
    FormsModule,
    AutoCompleteModule,
    MessageModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    ButtonModule,
    FieldsetModule,
    DialogModule,
    ProgressBarModule
  ],
  providers: [
    RepositoryService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {}
