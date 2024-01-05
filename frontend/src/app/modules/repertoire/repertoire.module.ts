import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RepertoireComponent } from './pages/repertoire/repertoire.component';
import { RepertoireRoutingModule } from './repertoire-routing.module';
import { SharedModule } from '../../shared/shared.module';
import { SeanceComponent } from './pages/seance/seance.component';
import { MoviesModule } from '../movies/movies.module';
import { ReactiveFormsModule } from '@angular/forms';
import { AddSeanceComponent } from './pages/add-seance/add-seance.component';
import { UpdateSeanceComponent } from './pages/update-seance/update-seance.component';
import { SeanceFormComponent } from './components/seance-form/seance-form.component';

@NgModule({
  declarations: [RepertoireComponent, SeanceComponent, AddSeanceComponent, UpdateSeanceComponent, SeanceFormComponent],
  imports: [
    CommonModule,
    RepertoireRoutingModule,
    SharedModule,
    MoviesModule,
    ReactiveFormsModule,
  ],
})
export class RepertoireModule {}
