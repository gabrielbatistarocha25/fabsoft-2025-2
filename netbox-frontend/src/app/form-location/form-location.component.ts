import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { Location } from '../model/location';
import { LocationService } from '../service/location.service';

@Component({
  selector: 'app-form-location',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './form-location.component.html',
  styleUrls: ['./form-location.component.css']
})
export class FormLocationComponent {
  location: Location = new Location();

  constructor(private service: LocationService, private router: Router) {}

  salvar() {
    this.service.saveLocation(this.location).subscribe(() => {
      this.router.navigate(['/locations']);
    });
  }

  cancelar() {
      this.router.navigate(['/locations']);
  }
}