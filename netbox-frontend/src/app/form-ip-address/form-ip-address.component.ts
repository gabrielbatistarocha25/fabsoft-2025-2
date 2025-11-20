import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { IpAddress } from '../model/ip-address';
import { IpAddressService } from '../service/ip-address.service';
import { Device } from '../model/device';
import { DeviceService } from '../service/device.service';

@Component({
  selector: 'app-form-ip-address',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './form-ip-address.component.html'
})
export class FormIpAddressComponent implements OnInit {
  ip: IpAddress = new IpAddress();
  devices: Device[] = [];
  
  constructor(
    private service: IpAddressService,
    private deviceService: DeviceService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.deviceService.getDevices().subscribe(data => this.devices = data);

    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.service.getIpAddressById(+id).subscribe(data => this.ip = data);
    }
  }
  salvar() {
    this.service.saveIpAddress(this.ip).subscribe(() => {
      this.router.navigate(['/ip-addresses']);
    });
  }
  cancelar() { this.router.navigate(['/ip-addresses']); }
}