import { Component } from '@angular/core';
import { MoviesService } from '../../../../features/services/movies/movies.service';
import { ReadMovie } from '../../../../features/dto/movie/ReadMovie.model';

@Component({
  selector: 'app-movies-list',
  templateUrl: './movies-list.component.html',
  styleUrl: './movies-list.component.scss',
})
export class MoviesListComponent {
  public movies: ReadMovie[] = [];

  public constructor(private moviesService: MoviesService) {
    this.moviesService.getAll().subscribe((movies: ReadMovie[]) => {
      this.movies = movies;
    });
  }
}
