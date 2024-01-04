import { WriteActor } from '../actor/WriteActor.mode';
import { WriteDirector } from '../director/WriteDirector.mode';

export interface WriteMovie {
  readonly title: string;
  readonly description: string;
  readonly genres: string[];
  readonly duration: number;
  readonly year: number;
  readonly directors: WriteDirector[];
  readonly actors: WriteActor[];
  // eslint-disable-next-line @typescript-eslint/naming-convention
  readonly imageURL: string;
}
