import { Component, ElementRef, ViewChild, OnInit } from '@angular/core';
import { Vlan } from '../model/vlan';
import { VlanService } from '../service/vlan.service';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import * as bootstrap from 'bootstrap';

@Component({
  selector: 'app-vlan',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './vlan.component.html',
  styleUrls: ['./vlan.component.css']
})
export class VlanComponent implements OnInit {
  lista: Vlan[] = [];
  selecionado!: Vlan;
  @ViewChild('deleteModal') modalElement!: ElementRef;
  modal!: bootstrap.Modal;

  constructor(private service: VlanService, private router: Router) {}
  
  ngOnInit(): void { this.carregarLista(); }
  ngAfterViewInit(): void { this.modal = new bootstrap.Modal(this.modalElement.nativeElement); }
  
  carregarLista() { 
    this.service.getVlans().subscribe(data => this.lista = data); 
  }
  
  novo() { this.router.navigate(['/vlans/novo']); }
  alterar(item: Vlan) { this.router.navigate(['/vlans/alterar', item.id]); }
  abrirConfirmacao(item: Vlan) { this.selecionado = item; this.modal.show(); }
  fecharConfirmacao() { this.modal.hide(); }
  
  confirmarExclusao() {
    this.service.deleteVlan(this.selecionado.id).subscribe(() => {
      this.carregarLista();
      this.fecharConfirmacao();
    });
  }
}