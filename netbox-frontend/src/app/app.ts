import { Component } from '@angular/core';
import { RouterOutlet, RouterLink, RouterLinkActive } from '@angular/router';
import * as bootstrap from 'bootstrap';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, RouterLink, RouterLinkActive],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  title = 'netbox-frontend';

  fecharMenu() {
    const menu = document.getElementById('navbarNav');
    if (menu && menu.classList.contains('show')) {
      const bsCollapse = bootstrap.Collapse.getOrCreateInstance(menu);
      bsCollapse.hide();
    }
  }
}