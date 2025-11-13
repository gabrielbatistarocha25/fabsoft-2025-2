import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Manufacturer } from '../model/manufacturer';
import { ManufacturerService } from '../service/manufacturer.service';

@Component({
  selector: 'app-form-manufacturer',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './form-manufacturer.component.html',
  styleUrls: ['./form-manufacturer.component.css']
})
export class FormManufacturerComponent implements OnInit {
  manufacturer: Manufacturer = new Manufacturer();
  
  constructor(
    private service: ManufacturerService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.service.getManufacturerById(+id).subscribe(data => this.manufacturer = data);
    }
  }
  salvar() {
    this.service.saveManufacturer(this.manufacturer).subscribe(() => {
      this.router.navigate(['/manufacturers']);
    });
  }
  cancelar() { this.router.navigate(['/manufacturers']); }
}