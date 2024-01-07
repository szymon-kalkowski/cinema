import { Component } from '@angular/core';
import { ReadSeance } from '../../../../features/dto/seance/ReadSeance.model';
import { ActivatedRoute, Router } from '@angular/router';
import { RepertoireService } from '../../../../features/services/repertoire/repertoire.service';
import { AuthService } from '@auth0/auth0-angular';
import Swal, { SweetAlertResult } from 'sweetalert2';
import { Id } from '../../../../features/dto/id/id.model';
import {
  Seat,
  WriteOrder,
} from '../../../../features/dto/order/WriteOrder.model';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { OrderForm } from '../../models/orderForm.model';
import { ReadOrder } from '../../../../features/dto/client/ReadOrder';

@Component({
  selector: 'app-seance',
  templateUrl: './seance.component.html',
  styleUrl: './seance.component.scss',
})
export class SeanceComponent {
  public seance: ReadSeance | null = null;
  private id: string = '';
  public selectedSeats: Seat[] = [];
  public orderForm: FormGroup<OrderForm> = new FormGroup<OrderForm>(
    {} as OrderForm
  );

  public constructor(
    private repertoireService: RepertoireService,
    private route: ActivatedRoute,
    public auth: AuthService,
    private router: Router
  ) {
    this.id = route.snapshot.paramMap.get('id') as string;
    this.repertoireService
      .getSeance(this.id)
      .subscribe((seance: ReadSeance) => {
        this.seance = seance;
      });
    this.orderForm = new FormGroup<OrderForm>({
      name: new FormControl<string | null>(null, [Validators.required]),
      email: new FormControl<string | null>(null, [
        Validators.required,
        Validators.email,
      ]),
    });
  }

  public addSeat(row: number, column: number): void {
    this.selectedSeats = [...this.selectedSeats, { row, column }];
  }

  public removeSeat(row: number, column: number): void {
    this.selectedSeats = this.selectedSeats.filter(
      (seat: Seat) => seat.row !== row || seat.column !== column
    );
  }

  public isSeatSelected(row: number, column: number): boolean {
    return this.selectedSeats.some(
      (seat: Seat) => seat.row === row && seat.column === column
    );
  }

  public toggleSeat(row: number, column: number): void {
    if (this.isSeatSelected(row, column)) {
      this.removeSeat(row, column);
    } else {
      this.addSeat(row, column);
    }
  }

  public deleteSeance(): void {
    Swal.fire({
      title: 'Are you sure?',
      text: "You won't be able to revert this!",
      icon: 'warning',
      showCancelButton: true,
      color: '#ffffff',
      buttonsStyling: false,
      customClass: {
        confirmButton: 'btn btn-primary mx-2',
        cancelButton: 'btn btn-error mx-2',
      },
      iconColor: '#7480ff',
      background: '#191e24',
      confirmButtonText: 'Yes, delete it!',
    }).then((result: SweetAlertResult) => {
      if (result.isConfirmed) {
        this.repertoireService.deleteSeance(this.id).subscribe((_id: Id) => {
          Swal.fire({
            title: 'Deleted!',
            text: 'Seance has been deleted.',
            icon: 'success',
            color: '#ffffff',
            background: '#191e24',
            buttonsStyling: false,
            customClass: {
              confirmButton: 'btn btn-primary mx-2',
              cancelButton: 'btn btn-error mx-2',
            },
          }).then((_result: SweetAlertResult) => {
            this.router.navigate(['/repertoire']);
          });
        });
      }
    });
  }

  public onSubmit(): void {
    Swal.fire({
      title: 'Make payment',
      text: `Total price: $${this.selectedSeats.length * 10}`,
      background: '#191e24',
      buttonsStyling: false,
      showCancelButton: true,
      color: '#ffffff',
      confirmButtonText: 'Pay',
      customClass: {
        confirmButton: 'btn btn-primary mx-2',
        cancelButton: 'btn btn-error mx-2',
      },
    }).then((result: SweetAlertResult) => {
      if (result.isConfirmed) {
        const newOrder: WriteOrder = {
          name: this.orderForm.value.name as string,
          email: this.orderForm.value.email as string,
          seats: this.selectedSeats,
        };

        this.repertoireService
          .addOrder(this.id, newOrder)
          .subscribe((order: ReadOrder) => {
            Swal.fire({
              icon: 'success',
              title: 'Order completed!',
              text: `Order id: ${order.id}`,
              html: `<div>Movie: ${order.seance.movie.title}</div>
                <div>Room: ${order.seance.room}</div>
                <div>Date: ${order.seance.date} ${order.seance.time}</div>
                <div>Seats: ${order.seats.join(', ')}</div>`,
              background: '#191e24',
              buttonsStyling: false,
              customClass: {
                confirmButton: 'btn btn-primary',
              },
              color: '#ffffff',
            }).then((_result: SweetAlertResult) => {
              this.router.navigate(['/']);
            });
          });
      }
    });
  }
}
