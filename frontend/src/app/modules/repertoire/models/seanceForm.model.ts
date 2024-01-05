import { FormControl } from '@angular/forms';

export interface SeanceForm {
  readonly movieId: FormControl<string | null>;
  readonly room: FormControl<number | null>;
  readonly date: FormControl<string | null>;
  readonly time: FormControl<string | null>;
}
