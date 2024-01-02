import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MoviesModule } from './modules/movies/movies.module';
import { CoreModule } from './core/core.module';
import { AuthModule } from '@auth0/auth0-angular';
import { provideHttpClient, withInterceptors } from '@angular/common/http';
import { apiInterceptor } from './core/interceptors/api.interceptor';
import { authInterceptor } from './core/interceptors/auth.interceptor';
import { SharedModule } from './shared/shared.module';

@NgModule({
  declarations: [AppComponent],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MoviesModule,
    CoreModule,
    SharedModule,
    AuthModule.forRoot({
      domain: 'szymon-kalkowski.eu.auth0.com',
      clientId: 'GNYIjWhLUdMf7PvAU4A23wM5DdR0YoQ4',
      authorizationParams: {
        redirect_uri: window.location.origin,
        audience: 'cinema',
      },
    }),
  ],
  providers: [
    provideHttpClient(withInterceptors([apiInterceptor, authInterceptor])),
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
