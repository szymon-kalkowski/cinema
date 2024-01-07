import { Component, Input } from '@angular/core';
import { ReadOrder } from '../../../../features/dto/client/ReadOrder';

@Component({
  selector: 'app-order-card',
  templateUrl: './order-card.component.html',
  styleUrl: './order-card.component.scss',
})
export class OrderCardComponent {
  @Input() public order: ReadOrder = {} as ReadOrder;
}
