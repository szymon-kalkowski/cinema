import { Component } from '@angular/core';
import { ReadMovie } from '../../../../features/dto/movie/ReadMovie.model';
import { MoviesService } from '../../../../features/services/movies/movies.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-update-movie',
  templateUrl: './update-movie.component.html',
  styleUrl: './update-movie.component.scss',
})
export class UpdateMovieComponent {
  public movie: ReadMovie = {
    id: '',
    title: '',
    description: '',
    genres: [],
    duration: 0,
    year: 0,
    actors: [],
    directors: [],
    imageURL: '',
  };
  private id: string = '';

  public constructor(
    private moviesService: MoviesService,
    private route: ActivatedRoute
  ) {
    this.id = route.snapshot.paramMap.get('id') as string;
    this.moviesService.get(this.id).subscribe((movie: ReadMovie) => {
      this.movie = movie;
    });
  }
}
