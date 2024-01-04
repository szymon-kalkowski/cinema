import { FormArray, FormControl, FormGroup } from '@angular/forms';

export interface ActorForm {
  name: FormControl<string | null>;
  birthYear: FormControl<number | null>;
}

export interface DirectorForm {
  name: FormControl<string | null>;
  birthYear: FormControl<number | null>;
}

export interface MovieForm {
  title: FormControl<string | null>;
  description: FormControl<string | null>;
  genres: FormControl<string[] | null>;
  duration: FormControl<number | null>;
  year: FormControl<number | null>;
  actors: FormArray<FormGroup<ActorForm>>;
  directors: FormArray<FormGroup<DirectorForm>>;
  // eslint-disable-next-line @typescript-eslint/naming-convention
  imageURL: FormControl<string | null>;
}
