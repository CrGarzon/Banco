import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-cliente',
  templateUrl: './cliente.component.html',
  styleUrls: ['./cliente.component.scss'],
})
export class ClienteComponent implements OnInit {
  step = 0;

  constructor() {}

  ngOnInit(): void {}

  setStep(index: number) {
    this.step = index;
  }
}
