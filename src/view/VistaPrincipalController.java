/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.SportMania;

/**
 *
 * @author dam
 */
public class VistaPrincipalController {
    //Referencia a la clase principal

    private SportMania sportmania;

    //Es llamada por la clase Principal para tener una referencia de vuelta de si misma
    public void setSportMania(SportMania sportmania) {
        this.sportmania = sportmania;
    }
}
