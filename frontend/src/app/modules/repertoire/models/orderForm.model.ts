import { FormControl } from '@angular/forms';

export interface OrderForm {
  readonly name: FormControl<string | null>;
  readonly email: FormControl<string | null>;
}
