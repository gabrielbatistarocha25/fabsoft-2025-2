import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Router } from '@angular/router';
import { Location } from '../model/location';
import { LocationService } from '../service/location.service';

@Component({
  selector: 'app-location',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './location.component.html',
  styleUrls: ['./location.component.css']
})
export class LocationComponent implements OnInit {
  listaLocations: Location[] = [];

  constructor(private service: LocationService, private router: Router) {}

  ngOnInit(): void {
    this.findAll();
  }

  findAll() {
    this.service.getLocations().subscribe(data => {
      this.listaLocations = data;
    });
  }

  novo() {
    this.router.navigate(['/locations/novo']);
  }
  
  // Métodos de alterar/excluir podem ser adicionados aqui seguindo a lógica do professor
}