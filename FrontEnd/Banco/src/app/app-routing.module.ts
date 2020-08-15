import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { MainPageComponent } from './main-page/main-page.component';
import { AdministradorComponent } from './administrador/administrador.component';
import { ClienteComponent } from './cliente/cliente.component';

const routes: Routes = [
  { path: '', redirectTo: '/cliente', pathMatch: 'full' },
  // { path: 'home', component: MainPageComponent },
  { path: 'administrador', component: AdministradorComponent },
  { path: 'cliente', component: ClienteComponent },
  { path: '**', component: MainPageComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
