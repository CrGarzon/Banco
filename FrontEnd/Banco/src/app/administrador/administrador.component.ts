import { Component, OnInit } from '@angular/core';
import * as CryptoJS from 'crypto-js';
import { GeneralService } from '../service/general.service';
import { MatSnackBarHorizontalPosition, MatSnackBarVerticalPosition, MatSnackBar } from '@angular/material/snack-bar';
import { Cliente } from '../models/cliente';
import { Administrador } from '../models/administrador';
import { Cuenta } from '../models/cuenta';
import { Reportes } from '../models/reportes';

@Component({
  selector: 'app-administrador',
  templateUrl: './administrador.component.html',
  styleUrls: ['./administrador.component.scss']
})
export class AdministradorComponent implements OnInit {
  step = 0;
  horizontalPosition: MatSnackBarHorizontalPosition = 'start';
  verticalPosition: MatSnackBarVerticalPosition = 'bottom';
  logged: boolean = false;

  tipoDocumento: string = '';
  documento: string = '';
  clave: string = '';

  tipoDocumentoNVc: string = '';
  documentoNVc: string = '';
  claveNVc: string = '';

  tipoDocumentoNVa: string = '';
  documentoNVa: string = '';
  claveNVa: string = '';

  tipoCuentaNV: number;
  tipoDocumentoNVk: string = '';
  documentoNVk: string = '';

  administradores: Administrador[] = [];
  colsAdmin: string[] = ['tipoDocumento', 'documento', 'nombre'];
  cuentas: Cuenta[] = [];
  cuentasEstadoAct: Cuenta[] = [];
  cuentasEstadoIna: Cuenta[] = [];
  colsCuentas: string[] = ['tipoCuenta', 'documento', 'saldo', 'estado'];
  clientes: Cliente[] = [];
  clientesEstadoAct: Cliente[] = [];
  clientesEstadoIna: Cliente[] = [];
  ColsClientes: string[] = ['tipoDocumento', 'documento', 'estado'];

  reportesIngresos: Reportes[] = [];
  reportesEgresos: Reportes[] = [];
  colsReportes: string[] = ['tipoMovimiento', 'fecha', 'documento', 'cantidad'];

  constructor(private services: GeneralService, private _snackBar: MatSnackBar) {}

  ngOnInit(): void {
    if (localStorage.getItem('admin')) {
      this.logged = true;
      const admin = JSON.parse(localStorage.getItem('admin'));
    }
    this.getAll();
  }

  login() {
    const claveEncript = CryptoJS.MD5(this.clave).toString();
    this.services.getAdminLogin(this.tipoDocumento, this.documento).subscribe(resp => {
      if (resp.clave == claveEncript) {
        localStorage.setItem('admin', JSON.stringify(resp));
        location.reload();
      } else {
        this._snackBar.open('Usuario o clave incorrectos', 'Cerrar', {
          duration: 4500,
          horizontalPosition: this.horizontalPosition,
          verticalPosition: this.verticalPosition
        });
      }
    });
  }
  agregar(tipo: number) {
    if (tipo == 1) {
      //Agrega cliente
      const claveEncript = CryptoJS.MD5(this.claveNVc).toString();
      const cliente: Cliente = {
        tipoDocumento: this.tipoDocumentoNVc,
        documento: this.documentoNVc,
        clave: claveEncript,
        estado: true
      };
      this.services.saveCliente(cliente).subscribe(
        resp => {
          this._snackBar.open(resp, 'Cerrar', {
            duration: 4500,
            horizontalPosition: this.horizontalPosition,
            verticalPosition: this.verticalPosition
          });
          if (resp == 'Se registro el usurio') {
            this.tipoDocumentoNVc = '';
            this.documentoNVc = '';
            this.claveNVc = '';
          }
        },
        error => {
          this._snackBar.open(error.error.text, 'Cerrar', {
            duration: 4500,
            horizontalPosition: this.horizontalPosition,
            verticalPosition: this.verticalPosition
          });
          if (error.error.text == 'Se registro el usurio') {
            this.tipoDocumentoNVc = '';
            this.documentoNVc = '';
            this.claveNVc = '';
          }
        }
      );
    } else if (tipo == 2) {
      //Agrega Cuenta
    } else {
      //Agrega Admin
      const claveEncript = CryptoJS.MD5(this.claveNVa).toString();
      const id = Math.floor(Math.random() * 100000);
      const admin: Administrador = {
        id: id,
        tipoDocumento: this.tipoDocumentoNVa,
        documento: this.documentoNVa,
        nombre: 'admin' + id,
        clave: claveEncript
      };
      this.services.saveAdmin(admin).subscribe(
        resp => {
          this._snackBar.open(resp, 'Cerrar', {
            duration: 4500,
            horizontalPosition: this.horizontalPosition,
            verticalPosition: this.verticalPosition
          });
          if (resp == 'Se registro el Administrador') {
            this.tipoDocumentoNVa = '';
            this.documentoNVa = '';
            this.claveNVa = '';
          }
        },
        error => {
          this._snackBar.open(error.error.text, 'Cerrar', {
            duration: 4500,
            horizontalPosition: this.horizontalPosition,
            verticalPosition: this.verticalPosition
          });
          if (error.error.text == 'Se registro el Administrador') {
            this.tipoDocumentoNVa = '';
            this.documentoNVa = '';
            this.claveNVa = '';
          }
        }
      );
    }
  }

  action(action, element) {
    console.log(action, element);
  }

  logout() {
    localStorage.removeItem('admin');
    location.reload();
  }

  getAll() {
    this.services.getAllAdmins().subscribe(resp => (this.administradores = resp));
    this.services.getAllCuentas().subscribe(resp => (this.cuentas = resp));
    this.services.getAllClientes().subscribe(resp => (this.clientes = resp));

    this.services.getCuentaByEstado(true).subscribe(resp => (this.cuentasEstadoAct = resp));
    this.services.getCuentaByEstado(false).subscribe(resp => (this.cuentasEstadoIna = resp));

    this.services.getClientesByEstado(true).subscribe(resp => (this.clientesEstadoAct = resp));
    this.services.getClientesByEstado(false).subscribe(resp => (this.clientesEstadoIna = resp));

    this.services.getReportesByMovimiento('INGRESO').subscribe(resp => (this.reportesIngresos = resp));
    this.services.getReportesByMovimiento('EGRESO').subscribe(resp => (this.reportesEgresos = resp));
  }

  setStep(index: number) {
    this.step = index;
  }
}
