import { Component, ElementRef, ViewChild, OnInit } from '@angular/core';
import { Site } from '../model/site';
import { SiteService } from '../service/site.service';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import * as bootstrap from 'bootstrap';

@Component({
  selector: 'app-site',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './site.component.html',
  styleUrls: ['./site.component.css']
})
export class SiteComponent implements OnInit {
  lista: Site[] = [];
  selecionado!: Site;
  @ViewChild('deleteModal') modalElement!: ElementRef;
  modal!: bootstrap.Modal;

  constructor(private service: SiteService, private router: Router) {}
  
  ngOnInit(): void {
    this.carregarLista();
  }

  ngAfterViewInit(): void {
    this.modal = new bootstrap.Modal(this.modalElement.nativeElement);
  }

  carregarLista() { 
    this.service.getSites().subscribe(data => this.lista = data); 
  }
  novo() { this.router.navigate(['/sites/novo']); }
  alterar(item: Site) { this.router.navigate(['/sites/alterar', item.id]); }
  abrirConfirmacao(item: Site) { this.selecionado = item; this.modal.show(); }
  fecharConfirmacao() { this.modal.hide(); }
  
  confirmarExclusao() {
    this.service.deleteSite(this.selecionado.id).subscribe(() => {
      this.carregarLista();
      this.fecharConfirmacao();
    });
  }
}