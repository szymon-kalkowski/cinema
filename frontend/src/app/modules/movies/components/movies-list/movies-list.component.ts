import { Component } from '@angular/core';
import { MoviesService } from '../../../../features/services/movies/movies.service';
import { ReadMovie } from '../../../../features/dto/movie/ReadMovie.model';
import { FormControl, FormGroup } from '@angular/forms';
import { SearchForm } from '../../models/SearchForm.model';

type SortBy = 'titleAsc' | 'titleDesc' | 'yearAsc' | 'yearDesc' | null;

@Component({
  selector: 'app-movies-list',
  templateUrl: './movies-list.component.html',
  styleUrl: './movies-list.component.scss',
})
export class MoviesListComponent {
  public movies: ReadMovie[] | null = null;
  public filteredMovies: ReadMovie[] = [];
  public displayedMovies: ReadMovie[] = [];
  public currentPage: number = 1;
  public itemsPerPage: number = 2;
  public pages: number[] = [];
  public genres: string[] = [];
  public searchBy: string | null = null;
  public filterBy: string | null = null;
  public sortBy: SortBy = null;
  public searchForm: FormGroup<SearchForm> = new FormGroup<SearchForm>(
    {} as SearchForm
  );

  public constructor(private moviesService: MoviesService) {
    this.moviesService.getGenres().subscribe((genres: string[]) => {
      this.genres = genres;
      this.searchForm = new FormGroup<SearchForm>({
        title: new FormControl<string | null>(null),
        genre: new FormControl<string | null>(null),
      });
    });
    this.moviesService.getAll().subscribe((movies: ReadMovie[]) => {
      this.movies = movies;
      this.filteredMovies = movies;
      this.prepareMovies();
    });
  }

  public changePage(page: number): void {
    this.currentPage = page;
    this.displayedMovies = this.filteredMovies!.slice(
      this.itemsPerPage * (this.currentPage - 1),
      this.itemsPerPage * this.currentPage
    );
  }

  public filter(): void {
    this.filteredMovies = this.movies!.filter((movie: ReadMovie) => {
      return (
        (!this.searchBy ||
          movie.title.toLowerCase().search(this.searchBy.toLowerCase())) !==
          -1 &&
        (!this.filterBy || movie.genres.includes(this.filterBy))
      );
    });
  }

  public sort(): void {
    if (this.sortBy === null) {
      return;
    }
    this.filteredMovies.sort((a: ReadMovie, b: ReadMovie) => {
      if (this.sortBy === 'titleAsc') {
        return a.title.toLowerCase() < b.title.toLowerCase() ? -1 : 1;
      } else if (this.sortBy === 'titleDesc') {
        return a.title.toLowerCase() > b.title.toLowerCase() ? -1 : 1;
      } else if (this.sortBy === 'yearAsc') {
        return a.year - b.year;
      }

      return b.year - a.year;
    });
  }

  public prepareMovies(): void {
    this.currentPage = 1;
    this.filter();
    this.sort();
    this.pages = [
      ...Array(
        Math.ceil(this.filteredMovies.length / this.itemsPerPage)
      ).keys(),
    ].map((i: number) => i + 1);
    this.changePage(this.currentPage);
  }

  public onSubmit(): void {
    this.searchBy = this.searchForm.get('title')!.value;
    this.filterBy = this.searchForm.get('genre')!.value;
    this.prepareMovies();
    this.searchForm.reset();
  }

  public clearSearch(): void {
    this.searchBy = null;
    this.prepareMovies();
  }

  public clearFilter(): void {
    this.filterBy = null;
    this.prepareMovies();
  }
}
