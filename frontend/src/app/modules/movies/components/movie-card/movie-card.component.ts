import { Component, Input } from '@angular/core';
import { ReadMovie } from '../../../../features/dto/movie/ReadMovie.model';

@Component({
  selector: 'app-movie-card',
  templateUrl: './movie-card.component.html',
  styleUrl: './movie-card.component.scss',
})
export class MovieCardComponent {
  @Input() public movie: ReadMovie = {} as ReadMovie;
}
