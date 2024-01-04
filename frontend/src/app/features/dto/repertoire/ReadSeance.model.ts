import { ReadPerson } from '../client/ReadPerson';
import { ReadMovie } from '../movie/ReadMovie.model';

type Seat = ReadPerson | null;

export interface ReadSeance {
  readonly id: string;
  readonly movie: ReadMovie;
  readonly room: number;
  readonly date: string;
  readonly time: string;
  readonly hall: Seat[][];
}
