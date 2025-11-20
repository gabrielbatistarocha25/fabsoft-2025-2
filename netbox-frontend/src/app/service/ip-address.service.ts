import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { IpAddress } from '../model/ip-address';

@Injectable({
  providedIn: 'root'
})
export class IpAddressService {
  private readonly apiURL = 'https://glowing-bassoon-v65944vpw9j73x9g-8080.app.github.dev/api/ipam/ip-addresses';

  constructor(private http: HttpClient) {}

  getIpAddresses(): Observable<IpAddress[]> {
    return this.http.get<IpAddress[]>(this.apiURL);
  }

  getIpAddressById(id: number): Observable<IpAddress> {
    return this.http.get<IpAddress>(`${this.apiURL}/${id}`);
  }

  saveIpAddress(ip: IpAddress): Observable<IpAddress> {
    if (ip.id) {
      return this.http.put<IpAddress>(`${this.apiURL}/${ip.id}`, ip);
    }
    return this.http.post<IpAddress>(this.apiURL, ip);
  }

  deleteIpAddress(id: number): Observable<any> {
    return this.http.delete(`${this.apiURL}/${id}`);
  }
}