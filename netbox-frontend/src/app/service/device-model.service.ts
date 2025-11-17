import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { DeviceModel } from '../model/device-model';

@Injectable({
  providedIn: 'root'
})
export class DeviceModelService {

  private readonly apiURL = 'https://glowing-bassoon-v65944vpw9j73x9g-8080.app.github.dev/api/device-models';

  constructor(private http: HttpClient) {}

  getDeviceModels(): Observable<DeviceModel[]> {
    return this.http.get<DeviceModel[]>(this.apiURL);
  }

  getDeviceModelById(id: number): Observable<DeviceModel> {
    return this.http.get<DeviceModel>(`${this.apiURL}/${id}`);
  }

  saveDeviceModel(deviceModel: DeviceModel): Observable<DeviceModel> {
    if (deviceModel.id) {
      return this.http.put<DeviceModel>(`${this.apiURL}/${deviceModel.id}`, deviceModel);
    }
    return this.http.post<DeviceModel>(this.apiURL, deviceModel);
  }

  deleteDeviceModel(id: number): Observable<any> {
    return this.http.delete(`${this.apiURL}/${id}`);
  }
}