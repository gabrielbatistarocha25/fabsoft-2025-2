import { Component, ElementRef, ViewChild, OnInit } from '@angular/core';
import { Rack } from '../model/rack';
import { RackService } from '../service/rack.service';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import * as bootstrap from 'bootstrap';

@Component({
  selector: 'app-rack',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './rack.component.html',
  styleUrls: ['./rack.component.css']
})
export class RackComponent implements OnInit {
  lista: Rack[] = [];
  selecionado!: Rack;
  @ViewChild('deleteModal') modalElement!: ElementRef;
  modal!: bootstrap.Modal;

  constructor(private service: RackService, private router: Router) {}
  
  ngOnInit(): void { 
    this.carregarLista(); 
  }

  ngAfterViewInit(): void { 
    this.modal = new bootstrap.Modal(this.modalElement.nativeElement); 
  }

  carregarLista() { this.service.getRacks().subscribe(data => this.lista = data); }
  novo() { this.router.navigate(['/racks/novo']); }
  alterar(item: Rack) { this.router.navigate(['/racks/alterar', item.id]); }
  abrirConfirmacao(item: Rack) { this.selecionado = item; this.modal.show(); }
  fecharConfirmacao() { this.modal.hide(); }
  
  confirmarExclusao() {
    this.service.deleteRack(this.selecionado.id).subscribe(() => {
      this.carregarLista();
      this.fecharConfirmacao();
    });
  }
}