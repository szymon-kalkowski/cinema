import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ReadStatistics } from '../../dto/statistics/ReadStatistics.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AdminService {
  public constructor(private http: HttpClient) {}

  public getStatistics(date: string): Observable<ReadStatistics> {
    return this.http.get<ReadStatistics>(`statistics/${date}`);
  }
}
