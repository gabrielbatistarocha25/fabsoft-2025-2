import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Location } from '../model/location';

@Injectable({
  providedIn: 'root'
})
export class LocationService {
  // URL da sua API backend
  private readonly apiURL = 'http://localhost:8080/api/organization/locations';

  constructor(private http: HttpClient) {}

  getLocations(): Observable<Location[]> {
    return this.http.get<Location[]>(this.apiURL);
  }

  saveLocation(location: Location): Observable<Location> {
    if (location.id) {
      return this.http.put<Location>(`${this.apiURL}/${location.id}`, location);
    }
    return this.http.post<Location>(this.apiURL, location);
  }

  // Nota: Adicionei este método caso precise editar. Se sua API não tiver, precisará criar no backend.
  getLocationById(id: number): Observable<Location> {
     // Supondo que sua API suporte GET /api/organization/locations/{id}
    return this.http.get<Location>(`${this.apiURL}/${id}`);
  }

  // Nota: Se sua API não tiver DELETE implementado para locations, isso dará erro ao chamar.
  deleteLocation(id: number): Observable<any> {
    return this.http.delete(`${this.apiURL}/${id}`);
  }
}