import { Component, Input } from '@angular/core';
import { DirectorForm } from '../../models/MovieForm.model';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'app-director-form',
  templateUrl: './director-form.component.html',
  styleUrl: './director-form.component.scss',
})
export class DirectorFormComponent {
  @Input() public directorForm!: FormGroup<DirectorForm>;
}
