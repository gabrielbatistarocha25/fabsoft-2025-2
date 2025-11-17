import { Component, ElementRef, ViewChild, OnInit } from '@angular/core';
import { DeviceModel } from '../model/device-model';
import { DeviceModelService } from '../service/device-model.service';
import { CommonModule } from '@angular/common'; 
import { Router } from '@angular/router';
import * as bootstrap from 'bootstrap';

@Component({
  selector: 'app-device-model',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './device-model.component.html',
  styleUrls: ['./device-model.component.css']
})
export class DeviceModelComponent implements OnInit {
  lista: DeviceModel[] = [];
  selecionado!: DeviceModel;
  @ViewChild('deleteModal') modalElement!: ElementRef;
  modal!: bootstrap.Modal;

  constructor(private service: DeviceModelService, private router: Router) {}
  
  ngOnInit(): void { 
    this.carregarLista(); 
  }
  
  ngAfterViewInit(): void { 
    this.modal = new bootstrap.Modal(this.modalElement.nativeElement); 
  }

  carregarLista() { 
    this.service.getDeviceModels().subscribe(data => this.lista = data); 
  }
  novo() { this.router.navigate(['/device-models/novo']); }
  alterar(item: DeviceModel) { this.router.navigate(['/device-models/alterar', item.id]); }
  abrirConfirmacao(item: DeviceModel) { this.selecionado = item; this.modal.show(); }
  fecharConfirmacao() { this.modal.hide(); }
  
  confirmarExclusao() {
    this.service.deleteDeviceModel(this.selecionado.id).subscribe(() => {
      this.carregarLista();
      this.fecharConfirmacao();
    });
  }
}