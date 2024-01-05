import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { RepertoireService } from '../../../../features/services/repertoire/repertoire.service';
import { ReadRepertoire } from '../../../../features/dto/repertoire/ReadRepertoire.mode';
import { dateToString } from '../../../../shared/utils/dateToString';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { DateForm } from '../../models/dateForm.model';

@Component({
  selector: 'app-repertoire',
  templateUrl: './repertoire.component.html',
  styleUrl: './repertoire.component.scss',
})
export class RepertoireComponent {
  public date: string = dateToString(new Date());
  public repertoire: ReadRepertoire = {} as ReadRepertoire;
  public dateForm: FormGroup<DateForm> = new FormGroup<DateForm>({
    date: new FormControl<string | null>(this.date, [Validators.required]),
  });

  public constructor(
    private route: ActivatedRoute,
    private repertoireService: RepertoireService
  ) {
    this.date = this.route.snapshot.paramMap.get('date') || this.date;
    this.repertoireService
      .getRepertoire(this.date)
      .subscribe((repertoire: ReadRepertoire) => {
        this.repertoire = repertoire;
      });
  }

  public onSubmit(): void {
    this.date = this.dateForm.value.date as string;
    this.repertoireService
      .getRepertoire(this.date)
      .subscribe((repertoire: ReadRepertoire) => {
        this.repertoire = repertoire;
      });
  }
}
