import { Component } from '@angular/core';
import { ReadOrder } from '../../../../features/dto/client/ReadOrder';
import { dateToString } from '../../../../shared/utils/dateToString';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { DateForm } from '../../../repertoire/models/dateForm.model';
import { ActivatedRoute } from '@angular/router';
import { AdminService } from '../../../../features/services/admin/admin.service';
import { ReadSeance } from '../../../../features/dto/seance/ReadSeance.model';
import { RepertoireService } from '../../../../features/services/repertoire/repertoire.service';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrl: './orders.component.scss',
})
export class OrdersComponent {
  public date: string = dateToString(new Date());
  public orders: ReadOrder[] | null = null;
  public filteredOrders: ReadOrder[] | null = [];
  public displayedOrders: ReadOrder[] | null = [];
  public seances: ReadSeance[] = [];
  public searchBy: string | null = null;

  public currentPage: number = 1;
  public itemsPerPage: number = 2;
  public pages: number[] = [];

  public dateForm: FormGroup<DateForm> = new FormGroup<DateForm>({
    date: new FormControl<string | null>(this.date, [Validators.required]),
  });

  public constructor(
    private route: ActivatedRoute,
    private adminService: AdminService,
    private repertoireService: RepertoireService
  ) {
    this.date = this.route.snapshot.paramMap.get('date') || this.date;
    this.repertoireService
      .getSeancesByDate(this.date)
      .subscribe((seances: ReadSeance[]) => {
        this.seances = seances;
      });
    this.adminService.getOrders(this.date).subscribe((orders: ReadOrder[]) => {
      this.orders = orders;
      this.prepareOrders();
    });
  }

  public changePage(page: number): void {
    this.currentPage = page;
    this.displayedOrders = this.filteredOrders!.slice(
      this.itemsPerPage * (this.currentPage - 1),
      this.itemsPerPage * this.currentPage
    );
  }

  public onSubmit(): void {
    this.date = this.dateForm.value.date as string;
    this.repertoireService
      .getSeancesByDate(this.date)
      .subscribe((seances: ReadSeance[]) => {
        this.seances = seances;
      });
    this.searchBy = null;
    this.adminService.getOrders(this.date).subscribe((orders: ReadOrder[]) => {
      this.orders = orders;
      this.prepareOrders();
    });
  }

  public filter(): void {
    if (!this.searchBy) {
      this.filteredOrders = this.orders;

      return;
    }
    this.filteredOrders = this.orders!.filter((order: ReadOrder) => {
      return order.seance.id === this.searchBy;
    });
  }

  public prepareOrders(): void {
    this.currentPage = 1;
    this.filter();
    this.pages = [
      ...Array(
        Math.ceil(this.filteredOrders!.length / this.itemsPerPage)
      ).keys(),
    ].map((i: number) => i + 1);
    this.changePage(this.currentPage);
  }
}
