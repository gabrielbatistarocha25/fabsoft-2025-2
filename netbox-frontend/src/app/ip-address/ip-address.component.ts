import { Component, ElementRef, ViewChild, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import * as bootstrap from 'bootstrap';
import { IpAddress } from '../model/ip-address';
import { IpAddressService } from '../service/ip-address.service';

@Component({
  selector: 'app-ip-address',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './ip-address.component.html'
})
export class IpAddressComponent implements OnInit {
  lista: IpAddress[] = [];
  selecionado!: IpAddress;
  @ViewChild('deleteModal') modalElement!: ElementRef;
  modal!: bootstrap.Modal;

  constructor(private service: IpAddressService, private router: Router) {}
  
  ngOnInit(): void { this.carregarLista(); }
  ngAfterViewInit(): void { this.modal = new bootstrap.Modal(this.modalElement.nativeElement); }
  
  carregarLista() { this.service.getIpAddresses().subscribe(data => this.lista = data); }
  novo() { this.router.navigate(['/ip-addresses/novo']); }
  alterar(item: IpAddress) { this.router.navigate(['/ip-addresses/alterar', item.id]); }
  abrirConfirmacao(item: IpAddress) { this.selecionado = item; this.modal.show(); }
  fecharConfirmacao() { this.modal.hide(); }
  
  confirmarExclusao() {
    this.service.deleteIpAddress(this.selecionado.id).subscribe(() => {
      this.carregarLista();
      this.fecharConfirmacao();
    });
  }
}