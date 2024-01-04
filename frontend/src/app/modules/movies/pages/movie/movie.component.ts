import { Component } from '@angular/core';
import { MoviesService } from '../../../../features/services/movies/movies.service';
import { ReadMovie } from '../../../../features/dto/movie/ReadMovie.model';
import { ActivatedRoute } from '@angular/router';
import { AuthService } from '@auth0/auth0-angular';

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
    private route: ActivatedRoute,
    public auth: AuthService
  ) {
    this.id = route.snapshot.paramMap.get('id') as string;
    this.moviesService.get(this.id).subscribe((movie: ReadMovie) => {
      this.movie = movie;
    });
  }

  public deleteMovie(): void {
    // eslint-disable-next-line no-console
    console.log('delete');
  }
}
