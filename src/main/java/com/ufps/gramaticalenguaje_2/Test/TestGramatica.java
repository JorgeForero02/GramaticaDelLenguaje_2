/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufps.gramaticalenguaje_2.Test;

import com.ufps.gramaticalenguaje_2.Model.Gramatica;
import com.ufps.gramaticalenguaje_2.Util.seed.ListaCD;
import java.util.Set;

/**
 *
 * @author Juan David Ortiz Cano - 1152298
 * @author Jorge Andres Forero Serrano - 1152328
 */
public class TestGramatica {

    public static void main(String[] args) {

        Gramatica gramatica1 = new Gramatica("G1");

        gramatica1.agregarTerminales("1,2,3");
        gramatica1.agregarNoTerminales("B,C,D,E");
        gramatica1.setVariableInicial('B');
        gramatica1.agregarProducciones('B', "CD1/1");
        gramatica1.agregarProducciones('C', "ABA/D2/*D1/4/TCD");
        gramatica1.agregarProducciones('C', "BC/B2/B*2/2/3/#AB");
        gramatica1.agregarProducciones('D', "B*A*D/1");
        gramatica1.agregarProducciones('E', "BC/CD1/1");
        
        System.out.println("Gramatica antes de modificar:");
        gramatica1.imprimirGramatica();
        System.out.println();
        
        gramatica1.modificarVariableNoTerminal('A', 'Z');
        System.out.println("Gramatica despues de modificar una variable no terminal:");
        gramatica1.imprimirGramatica();
        System.out.println();

        gramatica1.modificarVariableTerminal('1', '7');
        System.out.println("Gramatica despues de modificar una variable terminal:");
        gramatica1.imprimirGramatica();
        System.out.println();
        
        gramatica1.modificarProduccion('Z', "*", "72");
        System.out.println("Gramatica despues de modificar una produccion:");
        gramatica1.imprimirGramatica();
        System.out.println();
        
        gramatica1.modificarVariableInicial('B');
        System.out.println("Gramatica despues de modificar la variable inicial:");
        gramatica1.imprimirGramatica();
        System.out.println();
        
        for (String s : gramatica1.comprobarInutiles()) {
            System.out.println(s);
        }

        /*for (String s : gramatica1.comprobarInalcanzables()) {

            System.out.println(s);

        }*/
        System.out.println();
        
        System.out.println("Gramatica despues de depurar:");
        gramatica1.imprimirGramatica();
        System.out.println();
        
        for(String s:gramatica1.comprobarProducciones())
        {
        
            System.out.println(s);
        
        }
        
        gramatica1.generarPalabras(10, 100);

        Set<String> palabras = gramatica1.getPalabras();

        for (String s : palabras) {

            System.out.println(s);

        }

    }

}
