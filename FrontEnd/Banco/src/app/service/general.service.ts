import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Administrador } from '../models/administrador';
import { environment } from 'src/environments/environment';
import { Cliente } from '../models/cliente';
import { Cuenta } from '../models/cuenta';
import { Reportes } from '../models/reportes';
import { TipoCuenta } from '../models/tipo-cuenta';

@Injectable({
  providedIn: 'root'
})
export class GeneralService {
  constructor(private http: HttpClient) {}

  //* ADMIN */
  saveAdmin(admin: Administrador): Observable<string> {
    const body = new HttpParams()
      .set('id', admin.id + '')
      .set('nombre', admin.nombre)
      .set('tipoDocumento', admin.tipoDocumento)
      .set('documento', admin.documento)
      .set('clave', admin.clave);
    return this.http.post<string>(environment.saveAdmin, body);
  }

  updateAdmin(admin: Administrador): Observable<string> {
    const body = new HttpParams()
      .set('id', admin.id + '')
      .set('nombre', admin.nombre)
      .set('tipodocumento', admin.tipoDocumento)
      .set('documento', admin.documento)
      .set('clave', admin.clave);
    return this.http.put<string>(environment.updateAdmin, body);
  }

  deleteAdmin(id: number): Observable<string> {
    const param = new HttpParams().set('id', id + '');
    return this.http.delete<string>(environment.deleteAdmin, { params: param });
  }

  getAllAdmins(): Observable<Administrador[]> {
    return this.http.get<Administrador[]>(environment.getAdmins);
  }

  getAdminById(id: number): Observable<Administrador[]> {
    const param = new HttpParams().set('id', id + '');
    return this.http.get<Administrador[]>(environment.getAdminById, {
      params: param
    });
  }

  getAdminLogin(tipoDocumento: string, documento: string): Observable<Administrador> {
    const param = new HttpParams().set('tipoDocumento', tipoDocumento).set('documento', documento);
    return this.http.get<Administrador>(environment.getAdministradorLogin, {
      params: param
    });
  }

  //* CUENTA */
  saveCuenta(cuenta: Cuenta): Observable<string> {
    const body = new HttpParams()
      .set('id', cuenta.id + '')
      .set('saldo', cuenta.saldo + '')
      .set('estado', cuenta.estado + '')
      .set('documento', cuenta.documento + '')
      .set('tipoDocumento', cuenta.tipoDocumento + '')
      .set('idTipoCuenta', cuenta.idTipoCuenta + '');
    return this.http.post<string>(environment.saveCuenta, body);
  }

  updateCuenta(cuenta: Cuenta): Observable<string> {
    const body = new HttpParams()
      .set('id', cuenta.id + '')
      .set('saldo', cuenta.saldo + '')
      .set('estado', cuenta.estado + '')
      .set('documento', cuenta.documento + '')
      .set('tipoDocumento', cuenta.tipoDocumento + '')
      .set('idTipoCuenta', cuenta.idTipoCuenta + '');
    return this.http.put<string>(environment.updateCuenta, body);
  }

  deleteCuenta(id: number): Observable<string> {
    const param = new HttpParams().set('id', id + '');
    return this.http.delete<string>(environment.deleteCuenta, {
      params: param
    });
  }

  getAllCuentas(): Observable<Cuenta[]> {
    return this.http.get<Cuenta[]>(environment.getCuentas);
  }

  getCuentaById(id: number): Observable<Cuenta[]> {
    const param = new HttpParams().set('id', id + '');
    return this.http.get<Cuenta[]>(environment.getCuentaById, {
      params: param
    });
  }

  getCuentaByEstado(estado: boolean): Observable<Cuenta[]> {
    const param = new HttpParams().set('estado', estado + '');
    return this.http.get<Cuenta[]>(environment.getCuentasByEstado, { params: param });
  }

  getCuentasBySaldo(saldo: number): Observable<Cuenta[]> {
    const param = new HttpParams().set('saldo', saldo + '');
    return this.http.get<Cuenta[]>(environment.getCuentasBySaldo, { params: param });
  }

  getCuentaByCliente(tipoDocumento: string, documento: string): Observable<Cuenta> {
    const param = new HttpParams().set('tipoDocumento', tipoDocumento).set('documento', documento);
    return this.http.get<Cuenta>(environment.getCuentaByCliente, { params: param });
  }

  //* CLIENTE */
  saveCliente(cliente: Cliente): Observable<string> {
    const body = new HttpParams()
      .set('tipodocumento', cliente.tipoDocumento)
      .set('documento', cliente.documento)
      .set('clave', cliente.clave)
      .set('estado', cliente.estado + '');
    return this.http.post<string>(environment.saveCliente, body);
  }

  updateCliente(cliente: Cliente): Observable<string> {
    const body = new HttpParams()
      .set('tipodocumento', cliente.tipoDocumento)
      .set('documento', cliente.documento)
      .set('clave', cliente.clave)
      .set('estado', cliente.estado + '');
    return this.http.put<string>(environment.updateCliente, body);
  }

  deleteCliente(tipoDocumento: string, documento: string): Observable<string> {
    const param = new HttpParams().set('tipoDocumento', tipoDocumento).set('documento', documento);
    return this.http.delete<string>(environment.deleteCliente, {
      params: param
    });
  }

  getAllClientes(): Observable<Cliente[]> {
    return this.http.get<Cliente[]>(environment.getClientes);
  }

  getClienteById(tipoDocumento: string, documento: string): Observable<Cliente> {
    const param = new HttpParams().set('tipoDocumento', tipoDocumento).set('documento', documento);
    return this.http.get<Cliente>(environment.getClienteById, {
      params: param
    });
  }

  getClientesByEstado(estado: boolean): Observable<Cliente[]> {
    const param = new HttpParams().set('estado', estado + '');
    return this.http.get<Cliente[]>(environment.getClientesByEstado, { params: param });
  }

  //* Reportes */
  saveReporte(reporte: Reportes): Observable<string> {
    const body = new HttpParams()
      .set('id', reporte.id + '')
      .set('fecha', reporte.fecha + '')
      .set('tipoDocumento', reporte.tipoDocumento)
      .set('documento', reporte.documento)
      .set('tipoMovimimiento', reporte.tipoMovimiento)
      .set('cantidad', reporte.cantidad + '');
    return this.http.post<string>(environment.saveReporte, body);
  }

  updateReporte(reporte: Reportes): Observable<string> {
    const body = new HttpParams()
      .set('id', reporte.id + '')
      .set('fecha', reporte.fecha + '')
      .set('tipoDocumento', reporte.tipoDocumento)
      .set('documento', reporte.documento)
      .set('tipoMovimimiento', reporte.tipoMovimiento)
      .set('cantidad', reporte.cantidad + '');
    return this.http.put<string>(environment.updateReporte, body);
  }

  deleteReporte(id: number): Observable<string> {
    const param = new HttpParams().set('id', id + '');
    return this.http.delete<string>(environment.deleteReporte, { params: param });
  }

  getAllReportes(): Observable<Reportes[]> {
    return this.http.get<Reportes[]>(environment.getReportes);
  }

  getReporteById(id: number): Observable<Reportes[]> {
    const param = new HttpParams().set('id', id + '');
    return this.http.get<Reportes[]>(environment.getReportesById, {
      params: param
    });
  }

  getReportesByMovimiento(movimiento: string): Observable<Reportes[]> {
    const param = new HttpParams().set('tipoMovimiento', movimiento);
    return this.http.get<Reportes[]>(environment.getReportesByMovimiento, {
      params: param
    });
  }

  //* TipoCuenta */
  saveTipoCuenta(tipoCuenta: TipoCuenta): Observable<string> {
    const body = new HttpParams().set('id', tipoCuenta.id + '').set('nombre', tipoCuenta.nombre);
    return this.http.post<string>(environment.saveTipoCuenta, body);
  }

  updateTipoCuenta(tipoCuenta: TipoCuenta): Observable<string> {
    const body = new HttpParams().set('id', tipoCuenta.id + '').set('nombre', tipoCuenta.nombre);
    return this.http.put<string>(environment.updateTipoCuenta, body);
  }

  deleteTipoCuenta(id: number): Observable<string> {
    const param = new HttpParams().set('id', id + '');
    return this.http.delete<string>(environment.deleteTipoCuenta, { params: param });
  }

  getAllTipoCuentas(): Observable<TipoCuenta[]> {
    return this.http.get<TipoCuenta[]>(environment.getTiposCuentas);
  }

  getTipoCuentaById(id: number): Observable<TipoCuenta[]> {
    const param = new HttpParams().set('id', id + '');
    return this.http.get<TipoCuenta[]>(environment.getTipoCuentaById, {
      params: param
    });
  }
}
