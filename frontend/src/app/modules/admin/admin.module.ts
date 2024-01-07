import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { StatisticsComponent } from './pages/statistics/statistics.component';
import { OrdersComponent } from './pages/orders/orders.component';
import { AdminRoutingModule } from './admin-routing.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { OrderCardComponent } from './components/order-card/order-card.component';
import { SharedModule } from '../../shared/shared.module';

@NgModule({
  declarations: [StatisticsComponent, OrdersComponent, OrderCardComponent],
  imports: [
    CommonModule,
    AdminRoutingModule,
    ReactiveFormsModule,
    SharedModule,
    FormsModule,
  ],
  exports: [StatisticsComponent, OrdersComponent],
})
export class AdminModule {}
