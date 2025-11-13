import { Component, ElementRef, ViewChild, OnInit } from '@angular/core';
import { Device } from '../model/device';
import { DeviceService } from '../service/device.service';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import * as bootstrap from 'bootstrap';

@Component({
  selector: 'app-device',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './device.component.html',
  styleUrls: ['./device.component.css']
})
export class DeviceComponent implements OnInit {
  lista: Device[] = [];
  selecionado!: Device;
  @ViewChild('deleteModal') modalElement!: ElementRef;
  modal!: bootstrap.Modal;

  constructor(private service: DeviceService, private router: Router) {}
  
  ngOnInit(): void { this.carregarLista(); }
  ngAfterViewInit(): void { this.modal = new bootstrap.Modal(this.modalElement.nativeElement); }
  carregarLista() { this.service.getDevices().subscribe(data => this.lista = data); }
  novo() { this.router.navigate(['/devices/novo']); }
  alterar(item: Device) { this.router.navigate(['/devices/alterar', item.id]); }
  abrirConfirmacao(item: Device) { this.selecionado = item; this.modal.show(); }
  fecharConfirmacao() { this.modal.hide(); }
  confirmarExclusao() {
    this.service.deleteDevice(this.selecionado.id).subscribe(() => {
      this.carregarLista();
      this.fecharConfirmacao();
    });
  }
}