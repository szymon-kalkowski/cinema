import { Component, Input } from '@angular/core';
import { ActorForm } from '../../models/MovieForm.model';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'app-actor-form',
  templateUrl: './actor-form.component.html',
  styleUrl: './actor-form.component.scss',
})
export class ActorFormComponent {
  @Input() public actorForm!: FormGroup<ActorForm>;
}
