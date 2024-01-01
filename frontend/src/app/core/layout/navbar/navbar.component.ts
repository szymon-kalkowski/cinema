import { Component } from '@angular/core';

interface RouterLink {
  label: string;
  path: string;
}

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.scss',
})
export class NavbarComponent {
  public links: RouterLink[] = [
    { label: 'Movies', path: '/movies' },
    { label: 'Reperoire', path: '/reperoire' },
  ];
}
