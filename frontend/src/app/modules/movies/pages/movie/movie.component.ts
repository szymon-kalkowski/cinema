import { Component } from '@angular/core';
import { MoviesService } from '../../../../features/services/movies/movies.service';
import { ReadMovie } from '../../../../features/dto/movie/ReadMovie.model';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from '@auth0/auth0-angular';
import Swal, { SweetAlertResult } from 'sweetalert2';
import { Id } from '../../../../features/dto/id/id.model';

@Component({
  selector: 'app-movie',
  templateUrl: './movie.component.html',
  styleUrl: './movie.component.scss',
})
export class MovieComponent {
  public movie: ReadMovie | null = null;
  private id: string = '';

  public constructor(
    private moviesService: MoviesService,
    private route: ActivatedRoute,
    private router: Router,
    public auth: AuthService
  ) {
    this.id = route.snapshot.paramMap.get('id') as string;
    this.moviesService.get(this.id).subscribe((movie: ReadMovie) => {
      this.movie = movie;
    });
  }

  public deleteMovie(): void {
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
        this.moviesService.delete(this.id).subscribe((_id: Id) => {
          Swal.fire({
            title: 'Deleted!',
            text: 'Movie has been deleted.',
            icon: 'success',
            color: '#ffffff',
            background: '#191e24',
            buttonsStyling: false,
            customClass: {
              confirmButton: 'btn btn-primary mx-2',
              cancelButton: 'btn btn-error mx-2',
            },
          }).then((_result: SweetAlertResult) => {
            this.router.navigate(['/movies']);
          });
        });
      }
    });
  }
}
