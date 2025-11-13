import { Component, ElementRef, ViewChild, OnInit } from '@angular/core';
import { Manufacturer } from '../model/manufacturer';
import { ManufacturerService } from '../service/manufacturer.service';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import * as bootstrap from 'bootstrap';

@Component({
  selector: 'app-manufacturer',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './manufacturer.component.html',
  styleUrls: ['./manufacturer.component.css']
})
export class ManufacturerComponent implements OnInit {
  lista: Manufacturer[] = [];
  selecionado!: Manufacturer;
  @ViewChild('deleteModal') modalElement!: ElementRef;
  modal!: bootstrap.Modal;

  constructor(private service: ManufacturerService, private router: Router) {}
  
  ngOnInit(): void { this.carregarLista(); }
  ngAfterViewInit(): void { this.modal = new bootstrap.Modal(this.modalElement.nativeElement); }
  carregarLista() { this.service.getManufacturers().subscribe(data => this.lista = data); }
  novo() { this.router.navigate(['/manufacturers/novo']); }
  alterar(m: Manufacturer) { this.router.navigate(['/manufacturers/alterar', m.id]); }
  abrirConfirmacao(m: Manufacturer) { this.selecionado = m; this.modal.show(); }
  fecharConfirmacao() { this.modal.hide(); }
  confirmarExclusao() {
    this.service.deleteManufacturer(this.selecionado.id).subscribe(() => {
      this.carregarLista();
      this.fecharConfirmacao();
    });
  }
}