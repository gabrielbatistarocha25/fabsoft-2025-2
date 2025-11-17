import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Device } from '../model/device';
import { DeviceService } from '../service/device.service';
import { Site } from '../model/site';
import { SiteService } from '../service/site.service';
import { Rack } from '../model/rack';
import { RackService } from '../service/rack.service';
import { DeviceModel } from '../model/device-model';
import { DeviceModelService } from '../service/device-model.service';

@Component({
  selector: 'app-form-device',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './form-device.component.html',
  styleUrls: ['./form-device.component.css']
})
export class FormDeviceComponent implements OnInit {
  
  device: Device = new Device();
  sites: Site[] = [];
  racks: Rack[] = [];
  deviceModels: DeviceModel[] = [];
  
  constructor(
    private deviceService: DeviceService,
    private siteService: SiteService,
    private rackService: RackService,
    private deviceModelService: DeviceModelService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.siteService.getSites().subscribe(data => this.sites = data);
    this.rackService.getRacks().subscribe(data => this.racks = data);
    this.deviceModelService.getDeviceModels().subscribe(data => this.deviceModels = data);

    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.deviceService.getDeviceById(+id).subscribe(data => {
        this.device = data;
        if (!this.device.rack) {
          this.device.rack = new Rack();
        }
      });
    }
  }

  salvar() {
    if (this.device.rack && !this.device.rack.id) {
      this.device.rack = null as any; 
    }
    
    this.deviceService.saveDevice(this.device).subscribe(() => {
      this.router.navigate(['/devices']);
    });
  }
  cancelar() { this.router.navigate(['/devices']); }
}