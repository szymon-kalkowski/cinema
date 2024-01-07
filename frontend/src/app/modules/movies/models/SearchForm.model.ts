import { FormControl } from '@angular/forms';

export interface SearchForm {
  readonly title: FormControl<string | null>;
  readonly genre: FormControl<string | null>;
}
