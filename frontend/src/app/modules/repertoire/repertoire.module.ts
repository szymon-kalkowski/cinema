import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RepertoireComponent } from './pages/repertoire/repertoire.component';
import { RepertoireRoutingModule } from './repertoire-routing.module';
import { SharedModule } from '../../shared/shared.module';
import { SeanceComponent } from './pages/seance/seance.component';
import { MoviesModule } from '../movies/movies.module';

@NgModule({
  declarations: [RepertoireComponent, SeanceComponent],
  imports: [CommonModule, RepertoireRoutingModule, SharedModule, MoviesModule],
})
export class RepertoireModule {}
