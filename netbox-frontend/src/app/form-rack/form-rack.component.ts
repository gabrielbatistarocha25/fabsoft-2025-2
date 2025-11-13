import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Rack } from '../model/rack';
import { RackService } from '../service/rack.service';
import { Site } from '../model/site';
import { SiteService } from '../service/site.service';

@Component({
  selector: 'app-form-rack',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './form-rack.component.html',
  styleUrls: ['./form-rack.component.css']
})
export class FormRackComponent implements OnInit {
  rack: Rack = new Rack();
  sites: Site[] = [];
  
  constructor(
    private rackService: RackService,
    private siteService: SiteService, 
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.siteService.getSites().subscribe(data => this.sites = data);

    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.rackService.getRackById(+id).subscribe(data => this.rack = data);
    }
  }
  salvar() {
    this.rackService.saveRack(this.rack).subscribe(() => {
      this.router.navigate(['/racks']);
    });
  }
  cancelar() { this.router.navigate(['/racks']); }
}