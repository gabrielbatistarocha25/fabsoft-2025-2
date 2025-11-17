import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Device } from '../model/device';

@Injectable({
  providedIn: 'root'
})
export class DeviceService {

  private readonly apiURL = 'https://glowing-bassoon-v65944vpw9j73x9g-8080.app.github.dev/api/devices';
  private readonly siteApiURL = 'https://glowing-bassoon-v65944vpw9j73x9g-8080.app.github.dev/api/sites';

  constructor(private http: HttpClient) {}

  getDevices(): Observable<Device[]> {
    return this.http.get<Device[]>(this.apiURL);
  }

  getDeviceById(id: number): Observable<Device> {
    return this.http.get<Device>(`${this.apiURL}/${id}`);
  }

  saveDevice(device: Device): Observable<Device> {
    if (device.id) {
      return this.http.put<Device>(`${this.apiURL}/${device.id}`, device);
    }
    return this.http.post<Device>(this.apiURL, device);
  }

  deleteDevice(id: number): Observable<any> {
    return this.http.delete(`${this.apiURL}/${id}`);
  }

  getDevicesBySite(siteId: number): Observable<Device[]> {
    return this.http.get<Device[]>(`${this.siteApiURL}/${siteId}/devices`);
  }
}