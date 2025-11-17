import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Rack } from '../model/rack';

@Injectable({
  providedIn: 'root'
})
export class RackService {

  private readonly apiURL = 'https://glowing-bassoon-v65944vpw9j73x9g-8080.app.github.dev/api/racks';

  constructor(private http: HttpClient) {}

  getRacks(): Observable<Rack[]> {
    return this.http.get<Rack[]>(this.apiURL);
  }

  getRackById(id: number): Observable<Rack> {
    return this.http.get<Rack>(`${this.apiURL}/${id}`);
  }

  saveRack(rack: Rack): Observable<Rack> {
    if (rack.id) {
      return this.http.put<Rack>(`${this.apiURL}/${rack.id}`, rack);
    }
    return this.http.post<Rack>(this.apiURL, rack);
  }

  deleteRack(id: number): Observable<any> {
    return this.http.delete(`${this.apiURL}/${id}`);
  }
}