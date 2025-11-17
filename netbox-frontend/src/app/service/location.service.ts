import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Location } from '../model/location';

@Injectable({
  providedIn: 'root'
})
export class LocationService {
  
  private readonly apiURL = 'https://glowing-bassoon-v65944vpw9j73x9g-8080.app.github.dev/api/organization/locations';

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

  getLocationById(id: number): Observable<Location> {
    return this.http.get<Location>(`${this.apiURL}/${id}`);
  }

  deleteLocation(id: number): Observable<any> {
    return this.http.delete(`${this.apiURL}/${id}`);
  }
}