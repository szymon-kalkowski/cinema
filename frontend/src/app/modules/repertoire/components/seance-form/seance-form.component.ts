import { Component, Input, OnChanges, SimpleChanges } from '@angular/core';
import { ReadSeance } from '../../../../features/dto/repertoire/ReadSeance.model';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { SeanceForm } from '../../models/seanceForm.model';
import { RepertoireService } from '../../../../features/services/repertoire/repertoire.service';
import { MoviesService } from '../../../../features/services/movies/movies.service';
import { ReadMovie } from '../../../../features/dto/movie/ReadMovie.model';
import { WriteSeance } from '../../../../features/dto/seance/WriteSeance.model';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-seance-form',
  templateUrl: './seance-form.component.html',
  styleUrl: './seance-form.component.scss',
})
export class SeanceFormComponent implements OnChanges {
  @Input() public seance: ReadSeance | null = null;

  public seanceForm: FormGroup<SeanceForm> = new FormGroup<SeanceForm>(
    {} as SeanceForm
  );

  public movies: ReadMovie[] = [];

  public constructor(
    private repertoireService: RepertoireService,
    private moviesService: MoviesService
  ) {
    this.initializeForm();
    this.moviesService.getAll().subscribe((movies: ReadMovie[]) => {
      this.movies = movies;
    });
  }

  public ngOnChanges(changes: SimpleChanges): void {
    if ('seance' in changes && this.seance !== null) {
      this.initializeForm();
    }
  }

  private initializeForm(): void {
    this.seanceForm = new FormGroup<SeanceForm>({
      movieId: new FormControl<string | null>(
        this.seance?.movie ? this.seance.movie.id : null,
        [Validators.required]
      ),
      room: new FormControl<number | null>(
        this.seance ? this.seance.room : null,
        [Validators.required, Validators.min(1)]
      ),
      date: new FormControl<string | null>(
        this.seance ? this.seance.date : null,
        [Validators.required]
      ),
      time: new FormControl<string | null>(
        this.seance ? this.seance.time : null,
        [Validators.required]
      ),
    });
  }

  public onSubmit(): void {
    const newSeance: WriteSeance = {
      movieId: this.seanceForm.value.movieId as string,
      room: this.seanceForm.value.room as number,
      date: this.seanceForm.value.date as string,
      time: this.seanceForm.value.time as string,
    };

    if (this.seance) {
      this.repertoireService
        .updateSeacne(this.seance.id, newSeance)
        .subscribe((seance: ReadSeance) => {
          Swal.fire({
            icon: 'success',
            title: 'Success!',
            text: `Seance "${seance.movie.title}" has been updated.`,
            background: '#191e24',
            buttonsStyling: false,
            customClass: {
              confirmButton: 'btn btn-primary',
            },
            color: '#ffffff',
          });
        });
    } else {
      this.repertoireService
        .addSeance(newSeance)
        .subscribe((seance: ReadSeance) => {
          Swal.fire({
            icon: 'success',
            title: 'Success!',
            text: `Seance "${seance.movie.title}" has been added.`,
            background: '#191e24',
            buttonsStyling: false,
            customClass: {
              confirmButton: 'btn btn-primary',
            },
            color: '#ffffff',
          });
        });

      this.reset();
    }
  }

  private reset(): void {
    this.seanceForm.reset();
  }
}
