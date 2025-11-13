import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Vlan } from '../model/vlan';
import { VlanService } from '../service/vlan.service';
import { Site } from '../model/site';
import { SiteService } from '../service/site.service';

@Component({
  selector: 'app-form-vlan',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './form-vlan.component.html',
  styleUrls: ['./form-vlan.component.css']
})
export class FormVlanComponent implements OnInit {
  vlan: Vlan = new Vlan();
  sites: Site[] = [];
  
  constructor(
    private vlanService: VlanService,
    private siteService: SiteService, 
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.siteService.getSites().subscribe(data => this.sites = data);

    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.vlanService.getVlanById(+id).subscribe(data => this.vlan = data);
    }
  }
  salvar() {
    this.vlanService.saveVlan(this.vlan).subscribe(() => {
      this.router.navigate(['/vlans']);
    });
  }
  cancelar() { this.router.navigate(['/vlans']); }
}