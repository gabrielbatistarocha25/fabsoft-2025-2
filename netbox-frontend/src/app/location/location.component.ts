import { Component, ElementRef, ViewChild, OnInit } from '@angular/core';
import { Location } from '../model/location';
import { LocationService } from '../service/location.service';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import * as bootstrap from 'bootstrap';

@Component({
  selector: 'app-location',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './location.component.html',
  styleUrls: ['./location.component.css']
})
export class LocationComponent implements OnInit {
  lista: Location[] = [];
  selecionado!: Location;
  @ViewChild('deleteModal') modalElement!: ElementRef;
  modal!: bootstrap.Modal;

  constructor(private service: LocationService, private router: Router) {}
  
  ngOnInit(): void {
    this.carregarLista();
  }

  // CORREÇÃO: Mova a inicialização do modal para esta função
  ngAfterViewInit(): void {
    this.modal = new bootstrap.Modal(this.modalElement.nativeElement);
  }

  carregarLista() { this.service.getLocations().subscribe(data => this.lista = data); }
  novo() { this.router.navigate(['/locations/novo']); }
  alterar(item: Location) { this.router.navigate(['/locations/alterar', item.id]); }
  abrirConfirmacao(item: Location) { this.selecionado = item; this.modal.show(); }
  fecharConfirmacao() { this.modal.hide(); }
  confirmarExclusao() {
    this.service.deleteLocation(this.selecionado.id).subscribe(() => {
      this.carregarLista();
      this.fecharConfirmacao();
    });
  }
}