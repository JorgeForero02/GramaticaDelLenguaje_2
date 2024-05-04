/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufps.gramaticalenguaje_2.Test;

import com.ufps.gramaticalenguaje_2.Util.seed.ArbolDerivacion;
import com.ufps.gramaticalenguaje_2.Util.seed.ListaCD;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Juan David Ortiz Cano - 1152298
 * @author Jorge Andres Forero Serrano - 1152328
 */
public class TestArbol {
    
    public static void main(String[] args) {
        
        
        
        char inicial = 'C';
        
        ListaCD<Character> noTerminales = new ListaCD();
        
        noTerminales.insertarFinal('A');
        noTerminales.insertarFinal('B');
        noTerminales.insertarFinal('C');
        noTerminales.insertarFinal('D');
        
        ListaCD<Character> terminales = new ListaCD();
        
        terminales.insertarFinal('1');
        terminales.insertarFinal('*');
        terminales.insertarFinal('2');
        
        Map<Character, ListaCD<String>> producciones1 = new HashMap<>();
        producciones1.put('C', new ListaCD<>());
        producciones1.get('C').insertarInicio("BC");
        producciones1.get('C').insertarInicio("B2");
        producciones1.get('C').insertarInicio("B*2");
        producciones1.get('C').insertarInicio("2");
        producciones1.put('A', new ListaCD<>());
        producciones1.get('A').insertarInicio("*");
        producciones1.put('B', new ListaCD<>());
        producciones1.get('B').insertarInicio("ABA");
        producciones1.get('B').insertarInicio("D2");
        producciones1.get('B').insertarInicio("*D1");
        producciones1.get('B').insertarInicio("1");
        producciones1.put('D', new ListaCD<>());
        producciones1.get('D').insertarInicio("B*A*D");
        producciones1.get('D').insertarInicio("1");
        
    }
}