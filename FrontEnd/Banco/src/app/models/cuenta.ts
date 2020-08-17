import { TipoCuenta } from './tipo-cuenta';
import { Cliente } from './cliente';

export class Cuenta {
  id: number;
  tipoDocumento: string;
  documento: string;
  idTipoCuenta: string;
  saldo: number;
  estado: boolean;
  tipoCuenta?: TipoCuenta;
  cliente?: Cliente;
}
