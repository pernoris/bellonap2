import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Skill } from 'src/app/model/hys';
import { SkillService } from 'src/app/service/hys.service';

@Component({
  selector: 'app-newskill',
  templateUrl: './new-hys.component.html',
  styleUrls: ['./new-hys.component.css']
})
export class NewHysComponent implements OnInit {
  progreso: number;
  img:string;

  constructor(private skillS:SkillService, private router: Router) { }

  ngOnInit(): void {
  }

  onCreate():void{
    const skill = new Skill(this.img, this.progreso);
    this.skillS.save(skill).subscribe(
      data=>{
        alert("Skill añadida de forma exitosa!");
        this.router.navigate(['']);
      }, err=>{
        alert("Error al añadir skill");
        this.router.navigate(['']);
      }
    )
  }
}