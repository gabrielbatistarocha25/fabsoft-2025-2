import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Site } from '../model/site';
import { SiteService } from '../service/site.service';
import { Location } from '../model/location';
import { LocationService } from '../service/location.service';

@Component({
  selector: 'app-form-site',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './form-site.component.html',
  styleUrls: ['./form-site.component.css']
})
export class FormSiteComponent implements OnInit {
  site: Site = new Site();
  locations: Location[] = [];
  
  constructor(
    private siteService: SiteService,
    private locationService: LocationService, 
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.locationService.getLocations().subscribe(data => this.locations = data);

    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.siteService.getSiteById(+id).subscribe(data => this.site = data);
    }
  }
  salvar() {
    this.siteService.saveSite(this.site).subscribe(() => {
      this.router.navigate(['/sites']);
    });
  }
  cancelar() { this.router.navigate(['/sites']); }
}