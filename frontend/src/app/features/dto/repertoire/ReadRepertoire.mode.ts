import { ReadSeance } from '../seance/ReadSeance.model';

export interface ReadRepertoire {
  readonly [title: string]: ReadSeance[];
}
