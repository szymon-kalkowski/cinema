import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MainComponent } from './pages/main/main.component';
import { MovieComponent } from './pages/movie/movie.component';
import { AddMovieComponent } from './pages/add-movie/add-movie.component';
import { adminGuard } from '../../core/guards/admin.guard';
import { UpdateMovieComponent } from './pages/update-movie/update-movie.component';

const routes: Routes = [
  {
    path: '',
    component: MainComponent,
  },
  {
    path: 'add',
    component: AddMovieComponent,
    canActivate: [adminGuard],
  },
  {
    path: 'update/:id',
    component: UpdateMovieComponent,
    canActivate: [adminGuard],
  },
  {
    path: ':id',
    component: MovieComponent,
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class MoviesRoutingModule {}
