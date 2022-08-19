
package com.portfolio.nb.Dto;

import javax.validation.constraints.NotBlank;


public class dtoHys {
    @NotBlank
    private String img;
    @NotBlank
    private int progreso;

    public dtoHys() {
    }

    public dtoHys(String img, int progreso) {
        this.img = img;
        this.progreso = progreso;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getProgreso() {
        return progreso;
    }

    public void setProgreso(int progreso) {
        this.progreso = progreso;
    }
 
    
    
}
