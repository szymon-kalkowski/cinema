import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ReadMovie } from '../../dto/movie/ReadMovie.model';

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
}
