import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { StatisticsComponent } from './pages/statistics/statistics.component';
import { adminGuard } from '../../core/guards/admin.guard';
import { OrdersComponent } from './pages/orders/orders.component';

const routes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    redirectTo: 'statistics',
  },
  {
    path: 'statistics',
    component: StatisticsComponent,
    canActivate: [adminGuard],
  },
  {
    path: 'orders',
    component: OrdersComponent,
    canActivate: [adminGuard],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class AdminRoutingModule {}
