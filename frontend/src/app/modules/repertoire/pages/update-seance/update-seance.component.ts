import { Component } from '@angular/core';
import { ReadSeance } from '../../../../features/dto/repertoire/ReadSeance.model';
import { RepertoireService } from '../../../../features/services/repertoire/repertoire.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-update-seance',
  templateUrl: './update-seance.component.html',
  styleUrl: './update-seance.component.scss',
})
export class UpdateSeanceComponent {
  public seance: ReadSeance = {} as ReadSeance;
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
