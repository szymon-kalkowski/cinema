import { ReadPerson } from '../client/ReadPerson';
import { ReadSeance } from '../repertoire/ReadSeance.model';

export interface ReadOrder {
  readonly id: string;
  readonly seance: ReadSeance;
  readonly person: ReadPerson;
  readonly seats: string[];
}
