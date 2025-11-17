import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Manufacturer } from '../model/manufacturer';

@Injectable({
  providedIn: 'root'
})
export class ManufacturerService {

  private readonly apiURL = 'https://glowing-bassoon-v65944vpw9j73x9g-8080.app.github.dev/api/manufacturers';

  constructor(private http: HttpClient) {}

  getManufacturers(): Observable<Manufacturer[]> {
    return this.http.get<Manufacturer[]>(this.apiURL);
  }

  getManufacturerById(id: number): Observable<Manufacturer> {
    return this.http.get<Manufacturer>(`${this.apiURL}/${id}`);
  }

  saveManufacturer(manufacturer: Manufacturer): Observable<Manufacturer> {
    if (manufacturer.id) {
      return this.http.put<Manufacturer>(`${this.apiURL}/${manufacturer.id}`, manufacturer);
    }
    return this.http.post<Manufacturer>(this.apiURL, manufacturer);
  }

  deleteManufacturer(id: number): Observable<any> {
    return this.http.delete(`${this.apiURL}/${id}`);
  }
}