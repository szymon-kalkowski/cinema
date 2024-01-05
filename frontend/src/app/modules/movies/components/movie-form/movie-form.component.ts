/* eslint-disable complexity */
/* eslint-disable indent */
import { Component, Input, OnChanges, SimpleChanges } from '@angular/core';
import { FormArray, FormControl, FormGroup, Validators } from '@angular/forms';
import {
  ActorForm,
  DirectorForm,
  MovieForm,
} from '../../models/MovieForm.model';
import { WriteMovie } from '../../../../features/dto/movie/WriteMovie.model';
import { WriteActor } from '../../../../features/dto/actor/WriteActor.mode';
import { WriteDirector } from '../../../../features/dto/director/WriteDirector.mode';
import { MoviesService } from '../../../../features/services/movies/movies.service';
import { ReadMovie } from '../../../../features/dto/movie/ReadMovie.model';
import { ReadDirector } from '../../../../features/dto/director/ReadDirector.model';
import { ReadActor } from '../../../../features/dto/actor/ReadActor.model';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-movie-form',
  templateUrl: './movie-form.component.html',
  styleUrl: './movie-form.component.scss',
})
export class MovieFormComponent implements OnChanges {
  @Input() public movie: ReadMovie | null = null;

  public movieForm: FormGroup<MovieForm> = new FormGroup<MovieForm>(
    {} as MovieForm
  );

  public genres: string[] = [];

  public constructor(private moviesService: MoviesService) {
    this.initializeForm();
    this.moviesService.getGenres().subscribe((genres: string[]) => {
      this.genres = genres;
    });
  }

  public ngOnChanges(changes: SimpleChanges): void {
    if ('movie' in changes && this.movie !== null) {
      this.initializeForm();
    }
  }

  private initializeForm(): void {
    this.movieForm = new FormGroup<MovieForm>({
      title: new FormControl<string | null>(
        this.movie ? this.movie.title : null,
        [Validators.required]
      ),
      description: new FormControl<string | null>(
        this.movie ? this.movie.description : null,
        [Validators.required]
      ),
      genres: new FormControl<string[] | null>(
        this.movie ? this.movie.genres : null,
        [Validators.required]
      ),
      duration: new FormControl<number | null>(
        this.movie ? this.movie.duration : null,
        [Validators.required, Validators.min(1)]
      ),
      year: new FormControl<number | null>(
        this.movie ? this.movie.year : null,
        [
          Validators.required,
          Validators.min(1888),
          Validators.max(new Date().getFullYear()),
        ]
      ),
      directors: new FormArray<FormGroup<DirectorForm>>(
        this.movie?.directors
          ? this.movie.directors.map((director: ReadDirector) => {
              return new FormGroup<DirectorForm>({
                name: new FormControl<string | null>(director.name, [
                  Validators.required,
                ]),
                birthYear: new FormControl<number | null>(director.birthYear, [
                  Validators.required,
                  Validators.max(new Date().getFullYear()),
                ]),
              });
            })
          : []
      ),
      actors: new FormArray<FormGroup<ActorForm>>(
        this.movie?.actors
          ? this.movie.actors.map((actor: ReadActor) => {
              return new FormGroup<ActorForm>({
                name: new FormControl<string | null>(actor.name, [
                  Validators.required,
                ]),
                birthYear: new FormControl<number | null>(actor.birthYear, [
                  Validators.required,
                  Validators.max(new Date().getFullYear()),
                ]),
              });
            })
          : []
      ),
      imageURL: new FormControl<string | null>(
        this.movie ? this.movie.imageURL : null,
        [Validators.required]
      ),
    });
  }

  public addActor(): void {
    this.movieForm.controls.actors.push(
      new FormGroup<ActorForm>({
        name: new FormControl<string | null>(null, [Validators.required]),
        birthYear: new FormControl<number | null>(null, [
          Validators.required,
          Validators.max(new Date().getFullYear()),
        ]),
      })
    );
  }

  public addDirector(): void {
    this.movieForm.controls.directors.push(
      new FormGroup<DirectorForm>({
        name: new FormControl<string | null>(null, [Validators.required]),
        birthYear: new FormControl<number | null>(null, [
          Validators.required,
          Validators.max(new Date().getFullYear()),
        ]),
      })
    );
  }

  public removeActor(index: number): void {
    this.movieForm.controls.actors.removeAt(index);
  }

  public removeDirector(index: number): void {
    this.movieForm.controls.directors.removeAt(index);
  }

  public get actors(): FormArray<FormGroup<ActorForm>> {
    return this.movieForm.controls.actors as FormArray<FormGroup<ActorForm>>;
  }

  public get directors(): FormArray<FormGroup<DirectorForm>> {
    return this.movieForm.controls.directors as FormArray<
      FormGroup<DirectorForm>
    >;
  }

  public onSubmit(): void {
    const newMovie: WriteMovie = {
      title: this.movieForm.controls.title.value as string,
      description: this.movieForm.controls.description.value as string,
      genres: this.movieForm.controls.genres.value as string[],
      duration: this.movieForm.controls.duration.value as number,
      year: this.movieForm.controls.year.value as number,
      actors: this.movieForm.controls.actors.value as WriteActor[],
      directors: this.movieForm.controls.directors.value as WriteDirector[],
      imageURL: this.movieForm.controls.imageURL.value as string,
    };

    if (this.movie) {
      this.moviesService
        .update(this.movie.id, newMovie)
        .subscribe((movie: ReadMovie) => {
          Swal.fire({
            icon: 'success',
            title: 'Success!',
            text: `Movie "${movie.title}" has been updated.`,
            background: '#191e24',
            buttonsStyling: false,
            customClass: {
              confirmButton: 'btn btn-primary',
            },
            color: '#ffffff',
          });
        });
    } else {
      this.moviesService.add(newMovie).subscribe((movie: ReadMovie) => {
        Swal.fire({
          icon: 'success',
          title: 'Success!',
          text: `Movie "${movie.title}" has been added.`,
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
    this.movieForm.reset();
  }
}
