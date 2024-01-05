import { ReadMovie } from '../movie/ReadMovie.model';

export interface ReadSeance {
  readonly id: string;
  readonly movie: ReadMovie;
  readonly room: string;
  readonly date: string;
  readonly time: string;
}
