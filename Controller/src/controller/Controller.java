/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import javafx.stage.Stage;


/**
 *
 * @author David
 */
public class Controller {
    
    
    public static void main(String[] args){
        ChoiceInterface c = new ChoiceInterface();
        Stage g = new Stage();
        c.start(g);
    }
}
