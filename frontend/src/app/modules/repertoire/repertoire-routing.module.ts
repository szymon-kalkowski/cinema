import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RepertoireComponent } from './pages/repertoire/repertoire.component';
import { SeanceComponent } from './pages/seance/seance.component';
import { AddSeanceComponent } from './pages/add-seance/add-seance.component';
import { UpdateSeanceComponent } from './pages/update-seance/update-seance.component';

const routes: Routes = [
  {
    path: '',
    component: RepertoireComponent,
  },
  {
    path: 'seances/add',
    component: AddSeanceComponent,
  },
  {
    path: 'seances/update/:id',
    component: UpdateSeanceComponent,
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
