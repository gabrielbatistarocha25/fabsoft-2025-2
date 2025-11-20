import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Prefix } from '../model/prefix';
import { PrefixService } from '../service/prefix.service';
import { Site } from '../model/site';
import { SiteService } from '../service/site.service';
import { Vlan } from '../model/vlan';
import { VlanService } from '../service/vlan.service';

@Component({
  selector: 'app-form-prefix',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './form-prefix.component.html'
})
export class FormPrefixComponent implements OnInit {
  prefix: Prefix = new Prefix();
  sites: Site[] = [];
  vlans: Vlan[] = [];
  
  constructor(
    private service: PrefixService,
    private siteService: SiteService,
    private vlanService: VlanService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.siteService.getSites().subscribe(data => this.sites = data);
    this.vlanService.getVlans().subscribe(data => this.vlans = data);

    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.service.getPrefixById(+id).subscribe(data => this.prefix = data);
    }
  }
  salvar() {
    this.service.savePrefix(this.prefix).subscribe(() => {
      this.router.navigate(['/prefixes']);
    });
  }
  cancelar() { this.router.navigate(['/prefixes']); }
}