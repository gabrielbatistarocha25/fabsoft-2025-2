import { Component, ElementRef, ViewChild, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import * as bootstrap from 'bootstrap';
import { Prefix } from '../model/prefix';
import { PrefixService } from '../service/prefix.service';

@Component({
  selector: 'app-prefix',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './prefix.component.html'
})
export class PrefixComponent implements OnInit {
  lista: Prefix[] = [];
  selecionado!: Prefix;
  @ViewChild('deleteModal') modalElement!: ElementRef;
  modal!: bootstrap.Modal;

  constructor(private service: PrefixService, private router: Router) {}
  
  ngOnInit(): void { this.carregarLista(); }
  ngAfterViewInit(): void { this.modal = new bootstrap.Modal(this.modalElement.nativeElement); }
  
  carregarLista() { this.service.getPrefixes().subscribe(data => this.lista = data); }
  novo() { this.router.navigate(['/prefixes/novo']); }
  alterar(item: Prefix) { this.router.navigate(['/prefixes/alterar', item.id]); }
  abrirConfirmacao(item: Prefix) { this.selecionado = item; this.modal.show(); }
  fecharConfirmacao() { this.modal.hide(); }
  
  confirmarExclusao() {
    this.service.deletePrefix(this.selecionado.id).subscribe(() => {
      this.carregarLista();
      this.fecharConfirmacao();
    });
  }
}