import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ReadRepertoire } from '../../dto/repertoire/ReadRepertoire.mode';
import { Observable } from 'rxjs';
import { ReadSeance } from '../../dto/seance/ReadSeance.model';
import { WriteSeance } from '../../dto/seance/WriteSeance.model';
import { Id } from '../../dto/id/id.model';
import { WriteOrder } from '../../dto/order/WriteOrder.model';
import { ReadOrder } from '../../dto/client/ReadOrder';

@Injectable({
  providedIn: 'root',
})
export class RepertoireService {
  private url: string = 'repertoire';

  public constructor(private http: HttpClient) {}

  public getRepertoire(date: string): Observable<ReadRepertoire> {
    return this.http.get<ReadRepertoire>(`${this.url}/${date}`);
  }

  public getSeance(id: string): Observable<ReadSeance> {
    return this.http.get<ReadSeance>(`${this.url}/seances/${id}`);
  }

  public addSeance(seance: WriteSeance): Observable<ReadSeance> {
    return this.http.post<ReadSeance>(`${this.url}/seances`, seance);
  }

  public updateSeacne(id: string, seance: WriteSeance): Observable<ReadSeance> {
    return this.http.put<ReadSeance>(`${this.url}/seances/${id}`, seance);
  }

  public addOrder(seanceId: string, order: WriteOrder): Observable<ReadOrder> {
    return this.http.post<ReadOrder>(
      `${this.url}/seances/${seanceId}/orders`,
      order
    );
  }

  public deleteSeance(id: string): Observable<Id> {
    return this.http.delete<Id>(`${this.url}/seances/${id}`);
  }
}
