import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Skill } from 'src/app/model/hys';
import { SkillService } from 'src/app/service/hys.service';


@Component({
  selector: 'app-editskill',
  templateUrl: './edit-hys.component.html',
  styleUrls: ['./edit-hys.component.css']
})
export class EditHysComponent implements OnInit {
  skill: Skill = null;
  constructor(private sHys: SkillService, private activatedRouter: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    const id = this.activatedRouter.snapshot.params['id'];
    this.sHys.detail(id).subscribe(
      data =>{
        this.skill = data;
      }, err =>{
        alert("Error al modificar la skill!");
        this.router.navigate(['']);
      }
    )
  }

  onUpdate(): void{
    const id = this.activatedRouter.snapshot.params['id'];
    this.sHys.update(id, this.skill).subscribe(
      data => {
        this.router.navigate(['']);
      }, err =>{
         alert("Error al modificar la skill!");
         this.router.navigate(['']);
      }
    )
  }

}