import { Component } from '@angular/core';
import { ReadStatistics } from '../../../../features/dto/statistics/ReadStatistics.model';
import { dateToString } from '../../../../shared/utils/dateToString';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { DateForm } from '../../../repertoire/models/dateForm.model';
import { ActivatedRoute } from '@angular/router';
import { AdminService } from '../../../../features/services/admin/admin.service';

@Component({
  selector: 'app-statistics',
  templateUrl: './statistics.component.html',
  styleUrl: './statistics.component.scss',
})
export class StatisticsComponent {
  public date: string = dateToString(new Date());
  public statistics: ReadStatistics = {} as ReadStatistics;
  public dateForm: FormGroup<DateForm> = new FormGroup<DateForm>({
    date: new FormControl<string | null>(this.date, [Validators.required]),
  });

  public constructor(
    private route: ActivatedRoute,
    private adminService: AdminService
  ) {
    this.date = this.route.snapshot.paramMap.get('date') || this.date;
    this.adminService
      .getStatistics(this.date)
      .subscribe((statistics: ReadStatistics) => {
        this.statistics = statistics;
      });
  }

  public onSubmit(): void {
    this.date = this.dateForm.value.date as string;
    this.adminService
      .getStatistics(this.date)
      .subscribe((statistics: ReadStatistics) => {
        this.statistics = statistics;
      });
  }
}
