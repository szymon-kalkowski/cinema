import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MoviesListComponent } from './components/movies-list/movies-list.component';
import { MovieCardComponent } from './components/movie-card/movie-card.component';
import { MainComponent } from './pages/main/main.component';
import { MoviesRoutingModule } from './movies-routing.module';
import { HttpClientModule } from '@angular/common/http';
import { SharedModule } from '../../shared/shared.module';
import { MovieComponent } from './pages/movie/movie.component';
import { MovieDetailsComponent } from './components/movie-details/movie-details.component';
import { AddMovieComponent } from './pages/add-movie/add-movie.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ActorFormComponent } from './components/actor-form/actor-form.component';
import { DirectorFormComponent } from './components/director-form/director-form.component';
import { MovieFormComponent } from './components/movie-form/movie-form.component';
import { UpdateMovieComponent } from './pages/update-movie/update-movie.component';

@NgModule({
  declarations: [
    MoviesListComponent,
    MovieCardComponent,
    MainComponent,
    MovieComponent,
    MovieDetailsComponent,
    AddMovieComponent,
    ActorFormComponent,
    DirectorFormComponent,
    MovieFormComponent,
    UpdateMovieComponent,
  ],
  imports: [
    CommonModule,
    MoviesRoutingModule,
    HttpClientModule,
    SharedModule,
    ReactiveFormsModule,
    FormsModule,
  ],
  exports: [MovieDetailsComponent],
})
export class MoviesModule {}
