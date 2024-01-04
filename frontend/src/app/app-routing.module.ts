/* eslint-disable @typescript-eslint/typedef */

import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    redirectTo: 'movies',
  },
  {
    path: 'movies',
    loadChildren: () =>
      import('./modules/movies/movies.module').then((m) => m.MoviesModule),
  },
  {
    path: 'repertoire',
    loadChildren: () =>
      import('./modules/repertoire/repertoire.module').then(
        (m) => m.RepertoireModule
      ),
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
