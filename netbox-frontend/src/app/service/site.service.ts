import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Site } from '../model/site';

@Injectable({
  providedIn: 'root'
})
export class SiteService {

  private readonly apiURL = 'https://glowing-bassoon-v65944vpw9j73x9g-8080.app.github.dev/api/organization/sites';

  constructor(private http: HttpClient) {}

  getSites(): Observable<Site[]> {
    return this.http.get<Site[]>(this.apiURL);
  }

  getSiteById(id: number): Observable<Site> {
    return this.http.get<Site>(`${this.apiURL}/${id}`);
  }

  saveSite(site: Site): Observable<Site> {
    if (site.id) {
      return this.http.put<Site>(`${this.apiURL}/${site.id}`, site);
    }
    return this.http.post<Site>(this.apiURL, site);
  }

  deleteSite(id: number): Observable<any> {
    return this.http.delete(`${this.apiURL}/${id}`);
  }
}