import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Vlan } from '../model/vlan';

@Injectable({
  providedIn: 'root'
})
export class VlanService {

  private readonly apiURL = 'https://glowing-bassoon-v65944vpw9j73x9g-8080.app.github.dev/api/vlans';

  constructor(private http: HttpClient) {}

  getVlans(): Observable<Vlan[]> {
    return this.http.get<Vlan[]>(this.apiURL);
  }

  getVlanById(id: number): Observable<Vlan> {
    return this.http.get<Vlan>(`${this.apiURL}/${id}`);
  }

  saveVlan(vlan: Vlan): Observable<Vlan> {
    if (vlan.id) {
      return this.http.put<Vlan>(`${this.apiURL}/${vlan.id}`, vlan);
    }
    return this.http.post<Vlan>(this.apiURL, vlan);
  }

  deleteVlan(id: number): Observable<any> {
    return this.http.delete(`${this.apiURL}/${id}`);
  }

  getVlansBySite(siteId: number): Observable<Vlan[]> {
    return this.http.get<Vlan[]>(`${this.apiURL}/by-site/${siteId}`);
  }
}