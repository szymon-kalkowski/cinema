import { ReadSeance } from '../seance/ReadSeance.model';
import { ReadPerson } from './ReadPerson';

export interface ReadOrder {
  readonly id: string;
  readonly seance: ReadSeance;
  readonly person: ReadPerson;
  readonly seats: string[];
}
