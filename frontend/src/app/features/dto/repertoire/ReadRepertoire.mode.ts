import { ReadSeance } from './ReadSeance.model';

export interface ReadRepertoire {
  readonly [title: string]: ReadSeance[];
}
