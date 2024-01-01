import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MoviesListComponent } from './components/movies-list/movies-list.component';
import { MovieCardComponent } from './components/movie-card/movie-card.component';
import { MainComponent } from './pages/main/main.component';
import { MoviesRoutingModule } from './movies-routing.module';

@NgModule({
  declarations: [MoviesListComponent, MovieCardComponent, MainComponent],
  imports: [CommonModule, MoviesRoutingModule],
})
export class MoviesModule {}
