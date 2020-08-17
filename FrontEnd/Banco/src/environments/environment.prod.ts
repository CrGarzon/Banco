const BASEURL: string = 'http://localhost:8080/';
export const environment = {
  production: true,

  saveAdmin: BASEURL + 'saveAdministradorSQL',
  updateAdmin: BASEURL + 'updateAdministradorSQL',
  deleteAdmin: BASEURL + 'deleteAdministradorSQL',
  getAdmins: BASEURL + 'getAdministradorSQL',
  getAdminById: BASEURL + 'getIdAdministradorSQL',

  saveCliente: BASEURL + 'saveClienteSQL',
  updateCliente: BASEURL + 'updateClienteSQL',
  deleteCliente: BASEURL + 'deleteClienteSQL',
  getClientes: BASEURL + 'getClienteSQL',
  getClienteById: BASEURL + 'getClienteByIdSQL',
  getClientesByEstado: BASEURL + 'getClienteByEstadoSQL',

  saveCuenta: BASEURL + 'saveCuentaSQL',
  updateCuenta: BASEURL + 'updateCuentaSQL',
  deleteCuenta: BASEURL + 'deleteCuentaSQL',
  getCuentas: BASEURL + 'getCuentaSQL',
  getCuentaById: BASEURL + 'getCuentaByIdSQL',
  getCuentasByEstado: BASEURL + 'getCuentaByEstadoSQL',
  getCuentasBySaldo: BASEURL + 'getCuentaBySaldoSQL',

  saveReporte: BASEURL + 'saveReportesSQL',
  updateReporte: BASEURL + 'updateReportesSQL',
  deleteReporte: BASEURL + 'deleteReportesSQL',
  getReportes: BASEURL + 'getReportesSQL',
  getReportesById: BASEURL + 'getIdReportesByIdSQL',

  saveTipoCuenta: BASEURL + 'saveTipoCuentaSQL',
  updateTipoCuenta: BASEURL + 'updateTipoCuentaSQL',
  deleteTipoCuenta: BASEURL + 'deleteTipoCuentaSQL',
  getTiposCuentas: BASEURL + 'getTipoCuentaSQL',
  getTipoCuentaById: BASEURL + 'getTipoCuentaByIdSQL'
};
