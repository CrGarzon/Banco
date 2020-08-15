import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-administrador',
  templateUrl: './administrador.component.html',
  styleUrls: ['./administrador.component.scss'],
})
export class AdministradorComponent implements OnInit {
  step = 0;

  constructor() {}

  ngOnInit(): void {}

  setStep(index: number) {
    this.step = index;
  }
}
