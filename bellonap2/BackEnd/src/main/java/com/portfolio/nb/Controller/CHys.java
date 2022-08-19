/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.nb.Controller;

import com.portfolio.nb.Dto.dtoHys;
import com.portfolio.nb.Entity.Hys;
import com.portfolio.nb.Security.Controller.Mensaje;
import com.portfolio.nb.Service.SHys;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/skill")
@CrossOrigin(origins = "http://localhost:4200")
public class CHys {

    @Autowired
    SHys sHys;

    @GetMapping("/lista")
    public ResponseEntity<List<Hys>> list() {
        List<Hys> list = sHys.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Hys> getById(@PathVariable("id") int id) {
        if (!sHys.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe la ID!"), HttpStatus.BAD_REQUEST);
        }

        Hys skill = sHys.getOne(id).get();
        return new ResponseEntity(skill, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!sHys.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe la ID!"), HttpStatus.NOT_FOUND);
        }
        sHys.delete(id);
        return new ResponseEntity(new Mensaje("Skill eliminada!"), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoHys dtoskill) {
        if (StringUtils.isBlank(dtoskill.getImg())) {
            return new ResponseEntity(new Mensaje("El link es obligatorio!"), HttpStatus.BAD_REQUEST);
        }

        Hys skill = new Hys(
                dtoskill.getImg(), dtoskill.getProgreso()
        );
        sHys.save(skill);
        return new ResponseEntity(new Mensaje("Skill creada!"), HttpStatus.OK);

    }
    //@PreAuthorize("hasRole('ADMIN', 'USER')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoHys dtoskill) {
        if (!sHys.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe la ID!"), HttpStatus.NOT_FOUND);
        }
        
        if (StringUtils.isBlank(dtoskill.getImg())) {
            return new ResponseEntity(new Mensaje("El link no puede estar vacio!"), HttpStatus.BAD_REQUEST);
        }

        Hys skill = sHys.getOne(id).get();

        skill.setImg(dtoskill.getImg());
        skill.setProgreso(dtoskill.getProgreso());

        sHys.save(skill);

        return new ResponseEntity(new Mensaje("Skill actualizada!"), HttpStatus.OK);
    }
}