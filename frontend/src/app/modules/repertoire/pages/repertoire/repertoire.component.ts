import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { RepertoireService } from '../../../../features/services/repertoire/repertoire.service';
import { ReadRepertoire } from '../../../../features/dto/repertoire/ReadRepertoire.mode';
import { dateToString } from '../../../../shared/utils/dateToString';

@Component({
  selector: 'app-repertoire',
  templateUrl: './repertoire.component.html',
  styleUrl: './repertoire.component.scss',
})
export class RepertoireComponent {
  public date: string = dateToString(new Date());
  public repertoire: ReadRepertoire = {} as ReadRepertoire;

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
}
