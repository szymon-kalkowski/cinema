import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { StatisticsComponent } from './pages/statistics/statistics.component';
import { OrdersComponent } from './pages/orders/orders.component';
import { AdminRoutingModule } from './admin-routing.module';
import { ReactiveFormsModule } from '@angular/forms';

@NgModule({
  declarations: [StatisticsComponent, OrdersComponent],
  imports: [CommonModule, AdminRoutingModule, ReactiveFormsModule],
  exports: [StatisticsComponent, OrdersComponent],
})
export class AdminModule {}
