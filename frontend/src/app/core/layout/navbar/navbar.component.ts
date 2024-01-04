import { DOCUMENT } from '@angular/common';
import { Component, Inject } from '@angular/core';
import { AuthService } from '@auth0/auth0-angular';

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
  public constructor(
    public auth: AuthService,
    @Inject(DOCUMENT) public document: Document
  ) {}

  public links: RouterLink[] = [
    { label: 'Movies', path: '/movies' },
    { label: 'Repertoire', path: '/repertoire' },
  ];

  public adminLinks: RouterLink[] = [
    { label: 'Add movie', path: '/movies/add' },
    { label: 'Add seance', path: '/repertoire/add' },
    { label: 'Statistics', path: '/statistics' },
    { label: 'Orders', path: '/orders' },
  ];
}
