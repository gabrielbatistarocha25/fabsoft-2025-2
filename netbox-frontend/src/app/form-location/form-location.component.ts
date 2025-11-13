import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Location } from '../model/location';
import { LocationService } from '../service/location.service';

@Component({
  selector: 'app-form-location',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './form-location.component.html',
  styleUrls: ['./form-location.component.css']
})
export class FormLocationComponent implements OnInit {
  location: Location = new Location();
  
  constructor(
    private service: LocationService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.service.getLocationById(+id).subscribe(data => this.location = data);
    }
  }
  salvar() {
    this.service.saveLocation(this.location).subscribe(() => {
      this.router.navigate(['/locations']);
    });
  }
  cancelar() { this.router.navigate(['/locations']); }
}