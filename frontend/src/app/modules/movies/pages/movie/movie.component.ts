import { Component } from '@angular/core';
import { MoviesService } from '../../../../features/services/movies/movies.service';
import { ReadMovie } from '../../../../features/dto/movie/ReadMovie.model';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-movie',
  templateUrl: './movie.component.html',
  styleUrl: './movie.component.scss',
})
export class MovieComponent {
  public movie: ReadMovie = {} as ReadMovie;
  private id: string = '';

  public constructor(
    private moviesService: MoviesService,
    route: ActivatedRoute
  ) {
    this.id = route.snapshot.paramMap.get('id') as string;
    this.moviesService.get(this.id).subscribe((movie: ReadMovie) => {
      this.movie = movie;
    });
  }
}
