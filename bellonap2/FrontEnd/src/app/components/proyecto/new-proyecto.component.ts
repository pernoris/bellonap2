import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Proyecto } from 'src/app/model/proyecto';
import { ProyectoService } from 'src/app/service/proyecto.service';


@Component({
  selector: 'app-new-proyecto',
  templateUrl: './new-proyecto.component.html',
  styleUrls: ['./new-proyecto.component.css']
})
export class NewProyectoComponent implements OnInit {
  nombreP: string;
  descripcionP: string;
  img: string;

  constructor(private proyecto:ProyectoService, private router: Router) { }

  ngOnInit(): void {
  }

  onCreate():void{
    const proyecto = new Proyecto(this.nombreP, this.descripcionP, this.img);
    this.proyecto.save(proyecto).subscribe(
      data=>{
        alert("Proyecto añadido exitosamente!");
        this.router.navigate(['']);
      }, err=>{
        alert("Error al añadir proyecto");
        this.router.navigate(['']);
      }
    )
  }
}