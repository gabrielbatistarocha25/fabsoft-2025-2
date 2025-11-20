import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Prefix } from '../model/prefix';

@Injectable({
  providedIn: 'root'
})
export class PrefixService {
  private readonly apiURL = 'https://glowing-bassoon-v65944vpw9j73x9g-8080.app.github.dev/api/ipam/prefixes';

  constructor(private http: HttpClient) {}

  getPrefixes(): Observable<Prefix[]> {
    return this.http.get<Prefix[]>(this.apiURL);
  }

  getPrefixById(id: number): Observable<Prefix> {
    return this.http.get<Prefix>(`${this.apiURL}/${id}`);
  }

  savePrefix(prefix: Prefix): Observable<Prefix> {
    if (prefix.id) {
      return this.http.put<Prefix>(`${this.apiURL}/${prefix.id}`, prefix);
    }
    return this.http.post<Prefix>(this.apiURL, prefix);
  }

  deletePrefix(id: number): Observable<any> {
    return this.http.delete(`${this.apiURL}/${id}`);
  }
}