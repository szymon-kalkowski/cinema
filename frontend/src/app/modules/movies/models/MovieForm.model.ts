import { FormArray, FormControl, FormGroup } from '@angular/forms';

export interface ActorForm {
  readonly name: FormControl<string | null>;
  readonly birthYear: FormControl<number | null>;
}

export interface DirectorForm {
  readonly name: FormControl<string | null>;
  readonly birthYear: FormControl<number | null>;
}

export interface MovieForm {
  readonly title: FormControl<string | null>;
  readonly description: FormControl<string | null>;
  readonly genres: FormControl<string[] | null>;
  readonly duration: FormControl<number | null>;
  readonly year: FormControl<number | null>;
  readonly actors: FormArray<FormGroup<ActorForm>>;
  readonly directors: FormArray<FormGroup<DirectorForm>>;
  // eslint-disable-next-line @typescript-eslint/naming-convention
  readonly imageURL: FormControl<string | null>;
}
