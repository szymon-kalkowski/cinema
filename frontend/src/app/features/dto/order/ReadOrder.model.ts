import { ReadPerson } from '../client/ReadPerson';
import { ReadSeance } from '../seance/ReadSeance.model';

export interface ReadOrder {
  readonly id: string;
  readonly seance: ReadSeance;
  readonly person: ReadPerson;
  readonly seats: string[];
}
