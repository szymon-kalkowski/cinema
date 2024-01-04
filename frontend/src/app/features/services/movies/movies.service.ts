import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ReadMovie } from '../../dto/movie/ReadMovie.model';
import { WriteMovie } from '../../dto/movie/WriteMovie.model';

@Injectable({
  providedIn: 'root',
})
export class MoviesService {
  private url: string = 'movies';

  public constructor(private http: HttpClient) {}

  public getAll(): Observable<ReadMovie[]> {
    return this.http.get<ReadMovie[]>(this.url);
  }

  public get(id: string): Observable<ReadMovie> {
    return this.http.get<ReadMovie>(`${this.url}/${id}`);
  }

  public add(movie: WriteMovie): Observable<ReadMovie> {
    return this.http.post<ReadMovie>(this.url, movie);
  }

  public update(id: string, movie: WriteMovie): Observable<ReadMovie> {
    return this.http.put<ReadMovie>(`${this.url}/${id}`, movie);
  }

  public getGenres(): Observable<string[]> {
    return this.http.get<string[]>('genres');
  }
}
