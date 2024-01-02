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

@NgModule({
  declarations: [MoviesListComponent, MovieCardComponent, MainComponent, MovieComponent, MovieDetailsComponent],
  imports: [CommonModule, MoviesRoutingModule, HttpClientModule, SharedModule],
})
export class MoviesModule {}
