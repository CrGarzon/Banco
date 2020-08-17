import { Component, OnInit } from '@angular/core';
import { GeneralService } from '../service/general.service';
import { Cliente } from '../models/cliente';
import { MatSnackBar, MatSnackBarHorizontalPosition, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';
import * as CryptoJS from 'crypto-js';
import { Cuenta } from '../models/cuenta';
import { Reportes } from '../models/reportes';

@Component({
  selector: 'app-cliente',
  templateUrl: './cliente.component.html',
  styleUrls: ['./cliente.component.scss']
})
export class ClienteComponent implements OnInit {
  step = 0;
  horizontalPosition: MatSnackBarHorizontalPosition = 'start';
  verticalPosition: MatSnackBarVerticalPosition = 'bottom';

  logged: boolean = false;
  solicitarC: boolean = false;
  infoCuenta: boolean = false;

  tipoDocumento: string = '';
  documento: string = '';
  clave: string = '';

  tipoCuenta: number;
  nombretipocuenta: string = '';
  saldo: number = 0;
  idcuenta: number;

  cantidad: number;

  constructor(private services: GeneralService, private _snackBar: MatSnackBar) {}

  ngOnInit(): void {
    if (localStorage.getItem('cliente')) {
      this.logged = true;
      const cliente = JSON.parse(localStorage.getItem('cliente'));
      this.services.getCuentaByCliente(cliente.id.tipoDocumento, cliente.id.documento).subscribe(resp => {
        console.log(resp);
        if (resp == null) this.solicitarC = true;
        else {
          this.infoCuenta = true;
          this.nombretipocuenta = resp.tipoCuenta.nombre ? resp.tipoCuenta.nombre : '';
          this.saldo = resp.saldo;
          this.idcuenta = resp.id;
        }
      });
    }
  }

  login() {
    const claveEncript = CryptoJS.MD5(this.clave).toString();
    console.log(claveEncript);
    this.services.getClienteById(this.tipoDocumento, this.documento).subscribe(
      resp => {
        if (resp.clave == claveEncript) {
          localStorage.setItem('cliente', JSON.stringify(resp));
          location.reload();
        }
        this._snackBar.open('Usuario o clave incorrectos', 'Cerrar', {
          duration: 4500,
          horizontalPosition: this.horizontalPosition,
          verticalPosition: this.verticalPosition
        });
      },
      error => {
        console.log(error.error);
      }
    );
  }

  logout() {
    localStorage.removeItem('cliente');
    location.reload();
  }

  registrarse() {
    const claveEncript = CryptoJS.MD5(this.clave).toString();

    const cliente: Cliente = {
      tipoDocumento: this.tipoDocumento,
      documento: this.documento,
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
          this.tipoDocumento = '';
          this.documento = '';
          this.clave = '';
        }
      },
      error => {
        this._snackBar.open(error.error.text, 'Cerrar', {
          duration: 4500,
          horizontalPosition: this.horizontalPosition,
          verticalPosition: this.verticalPosition
        });
        if (error.error.text == 'Se registro el usurio') {
          this.tipoDocumento = '';
          this.documento = '';
          this.clave = '';
        }
      }
    );
  }

  solicitarCuenta() {
    const cliente = JSON.parse(localStorage.getItem('cliente'));
    this.services.getCuentaByCliente(cliente.id.tipoDocumento + '', cliente.id.documento + '').subscribe(respGet => {
      if (respGet == null) {
        this.services.getAllCuentas().subscribe(respAllC => {
          const cuenta: Cuenta = {
            id: respAllC.length + 1,
            tipoDocumento: cliente.id.tipoDocumento,
            documento: cliente.id.documento,
            idTipoCuenta: this.tipoCuenta + '',
            saldo: 0,
            estado: true
          };
          this.services.saveCuenta(cuenta).subscribe(
            res => {
              this._snackBar.open(res, 'Cerrar', {
                duration: 4500,
                horizontalPosition: this.horizontalPosition,
                verticalPosition: this.verticalPosition
              });
              location.reload();
            },
            error => {
              this._snackBar.open(error.error.text, 'Cerrar', {
                duration: 4500,
                horizontalPosition: this.horizontalPosition,
                verticalPosition: this.verticalPosition
              });
              location.reload();
            }
          );
        });
      }
    });
  }

  operaciones(tipo: number) {
    if (tipo == 1) this.cantidad *= 1;
    else this.cantidad *= -1;

    if (!this.cantidad) {
      this._snackBar.open('Por favor ingrese un valor', 'Cerrar', {
        duration: 4500,
        horizontalPosition: this.horizontalPosition,
        verticalPosition: this.verticalPosition
      });
      return;
    }

    if (this.cantidad + this.saldo < 0) {
      this._snackBar.open('Su saldo no puede ser menos de 0', 'Cerrar', {
        duration: 4500,
        horizontalPosition: this.horizontalPosition,
        verticalPosition: this.verticalPosition
      });
      this.cantidad = 0;
      return;
    }

    const cliente = JSON.parse(localStorage.getItem('cliente'));
    const cuenta: Cuenta = {
      id: this.idcuenta,
      tipoDocumento: cliente.id.tipoDocumento,
      documento: cliente.id.documento,
      idTipoCuenta: this.tipoCuenta + '',
      saldo: this.saldo + this.cantidad,
      estado: true
    };
    this.services.updateCuenta(cuenta).subscribe(
      resp => {
        this._snackBar.open('Operacion exitosa', 'Cerrar', {
          duration: 4500,
          horizontalPosition: this.horizontalPosition,
          verticalPosition: this.verticalPosition
        });
        this.guardarReporte(tipo, cliente, this.cantidad);
      },
      error => {
        this._snackBar.open('Operacion exitosa', 'Cerrar', {
          duration: 4500,
          horizontalPosition: this.horizontalPosition,
          verticalPosition: this.verticalPosition
        });
        this.guardarReporte(tipo, cliente, this.cantidad);
      }
    );
  }

  guardarReporte(tipoMov: number, cliente, saldo: number) {
    const reporte: Reportes = {
      id: Math.floor(Math.random() * 100000),
      fecha: new Date(),
      tipoDocumento: cliente.id.tipoDocumento,
      documento: cliente.id.documento,
      tipoMovimiento: tipoMov == 1 ? 'INGRESO' : 'EGRESO',
      cantidad: Math.abs(saldo)
    };
    this.services.saveReporte(reporte).subscribe(
      resp => {
        console.log(resp);
        location.reload();
      },
      error => {
        console.log(error.error.text);
        location.reload();
      }
    );
  }

  setStep(index: number) {
    this.step = index;
  }
}
