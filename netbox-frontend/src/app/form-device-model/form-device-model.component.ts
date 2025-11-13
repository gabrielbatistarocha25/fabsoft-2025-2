import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { DeviceModel } from '../model/device-model';
import { DeviceModelService } from '../service/device-model.service';
import { Manufacturer } from '../model/manufacturer';
import { ManufacturerService } from '../service/manufacturer.service';

@Component({
  selector: 'app-form-device-model',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './form-device-model.component.html',
  styleUrls: ['./form-device-model.component.css']
})
export class FormDeviceModelComponent implements OnInit {
  deviceModel: DeviceModel = new DeviceModel();
  manufacturers: Manufacturer[] = [];
  
  constructor(
    private deviceModelService: DeviceModelService,
    private manufacturerService: ManufacturerService, 
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.manufacturerService.getManufacturers().subscribe(data => this.manufacturers = data);

    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.deviceModelService.getDeviceModelById(+id).subscribe(data => this.deviceModel = data);
    }
  }
  salvar() {
    this.deviceModelService.saveDeviceModel(this.deviceModel).subscribe(() => {
      this.router.navigate(['/device-models']);
    });
  }
  cancelar() { this.router.navigate(['/device-models']); }
}