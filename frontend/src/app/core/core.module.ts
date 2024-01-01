import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NavbarComponent } from './layout/navbar/navbar.component';
import { FooterComponent } from './layout/footer/footer.component';
import { NoPageFoundComponent } from './layout/no-page-found/no-page-found.component';
import { RouterModule } from '@angular/router';

@NgModule({
  declarations: [NavbarComponent, FooterComponent, NoPageFoundComponent],
  imports: [CommonModule, RouterModule],
  exports: [NavbarComponent, FooterComponent, NoPageFoundComponent],
})
export class CoreModule {}
