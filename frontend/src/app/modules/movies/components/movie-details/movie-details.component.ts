import { Component, Input } from '@angular/core';
import { ReadMovie } from '../../../../features/dto/movie/ReadMovie.model';

@Component({
  selector: 'app-movie-details',
  templateUrl: './movie-details.component.html',
  styleUrl: './movie-details.component.scss',
})
export class MovieDetailsComponent {
  @Input() public movie: ReadMovie = {} as ReadMovie;
}
