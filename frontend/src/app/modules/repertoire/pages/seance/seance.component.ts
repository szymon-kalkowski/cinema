import { Component } from '@angular/core';
import { ReadSeance } from '../../../../features/dto/repertoire/ReadSeance.model';
import { ActivatedRoute, Router } from '@angular/router';
import { RepertoireService } from '../../../../features/services/repertoire/repertoire.service';
import { AuthService } from '@auth0/auth0-angular';
import Swal, { SweetAlertResult } from 'sweetalert2';
import { Id } from '../../../../features/dto/id/id.model';

@Component({
  selector: 'app-seance',
  templateUrl: './seance.component.html',
  styleUrl: './seance.component.scss',
})
export class SeanceComponent {
  public seance: ReadSeance | null = null;
  private id: string = '';

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
}
