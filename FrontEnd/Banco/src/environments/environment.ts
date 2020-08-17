// This file can be replaced during build by using the `fileReplacements` array.
// `ng build --prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.
const BASEURL: string = 'http://localhost:8080/';
export const environment = {
  production: false,

  saveAdmin: BASEURL + 'saveAdministrador',
  updateAdmin: BASEURL + 'updateAdministrador',
  deleteAdmin: BASEURL + 'deleteAdministrador',
  getAdmins: BASEURL + 'getAdministrador',
  getAdminById: BASEURL + 'getIdAdministrador',
  getAdministradorLogin: BASEURL + 'getAdministradorLogin',

  saveCliente: BASEURL + 'saveCliente',
  updateCliente: BASEURL + 'updateCliente',
  deleteCliente: BASEURL + 'deleteCliente',
  getClientes: BASEURL + 'getCliente',
  getClienteById: BASEURL + 'getClienteById',
  getClientesByEstado: BASEURL + 'getClienteByEstado',

  saveCuenta: BASEURL + 'saveCuenta',
  updateCuenta: BASEURL + 'updateCuenta',
  deleteCuenta: BASEURL + 'deleteCuenta',
  getCuentas: BASEURL + 'getCuenta',
  getCuentaById: BASEURL + 'getCuentaById',
  getCuentasByEstado: BASEURL + 'getCuentaByEstado',
  getCuentasBySaldo: BASEURL + 'getCuentaBySaldo',
  getCuentaByCliente: BASEURL + 'getCuentaByCliente',

  saveReporte: BASEURL + 'saveReportes',
  updateReporte: BASEURL + 'updateReportes',
  deleteReporte: BASEURL + 'deleteReportes',
  getReportes: BASEURL + 'getReportes',
  getReportesById: BASEURL + 'getIdReportesById',
  getReportesByMovimiento: BASEURL + 'getReportesByMovimiento',

  saveTipoCuenta: BASEURL + 'saveTipoCuenta',
  updateTipoCuenta: BASEURL + 'updateTipoCuenta',
  deleteTipoCuenta: BASEURL + 'deleteTipoCuenta',
  getTiposCuentas: BASEURL + 'getTipoCuenta',
  getTipoCuentaById: BASEURL + 'getTipoCuentaById'
};

/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/dist/zone-error';  // Included with Angular CLI.
