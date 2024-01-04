import { Component } from '@angular/core';
import { ReadSeance } from '../../../../features/dto/repertoire/ReadSeance.model';
import { ActivatedRoute } from '@angular/router';
import { RepertoireService } from '../../../../features/services/repertoire/repertoire.service';

@Component({
  selector: 'app-seance',
  templateUrl: './seance.component.html',
  styleUrl: './seance.component.scss',
})
export class SeanceComponent {
  public seance: ReadSeance = { movie: {} } as ReadSeance;
  private id: string = '';

  public constructor(
    private repertoireService: RepertoireService,
    private route: ActivatedRoute
  ) {
    this.id = route.snapshot.paramMap.get('id') as string;
    this.repertoireService
      .getSeance(this.id)
      .subscribe((seance: ReadSeance) => {
        this.seance = seance;
      });
  }
}
