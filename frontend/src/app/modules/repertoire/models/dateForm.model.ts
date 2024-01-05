import { FormControl } from '@angular/forms';

export interface DateForm {
  readonly date: FormControl<string | null>;
}
