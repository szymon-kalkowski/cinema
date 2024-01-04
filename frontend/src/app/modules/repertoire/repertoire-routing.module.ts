import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RepertoireComponent } from './pages/repertoire/repertoire.component';
import { SeanceComponent } from './pages/seance/seance.component';

const routes: Routes = [
  {
    path: '',
    component: RepertoireComponent,
  },
  {
    path: 'seances/:id',
    component: SeanceComponent,
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class RepertoireRoutingModule {}
