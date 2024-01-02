import { ReadActor } from '../actor/ReadActor.model';
import { ReadDirector } from '../director/ReadDirector.model';

export interface ReadMovie {
  readonly id: string;
  readonly title: string;
  readonly description: string;
  readonly genres: string[];
  readonly duration: number;
  readonly year: number;
  readonly directors: ReadDirector[];
  readonly actors: ReadActor[];
  // eslint-disable-next-line @typescript-eslint/naming-convention
  readonly imageURL: string;
}
