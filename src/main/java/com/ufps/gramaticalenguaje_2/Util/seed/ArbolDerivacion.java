/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufps.gramaticalenguaje_2.Util.seed;

import com.ufps.gramaticalenguaje_2.Util.seed.ListaCD;
import com.ufps.gramaticalenguaje_2.Util.seed.NodoAN;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author Juan David Ortiz Cano - 1152298
 * @author Jorge Andres Forero Serrano - 1152328
 */
public class ArbolDerivacion {
    
    private NodoAN<Character> inicial;
    private int NIVELMAXIMO;
    
    private ListaCD<Character> terminales;
    private ListaCD<Character> noTerminales;
    private Map<Character, ListaCD<String>> producciones;
    
    public ArbolDerivacion(char raiz,  ListaCD<Character> terminales,  ListaCD<Character> noTerminales, Map<Character, ListaCD<String>> producciones, int niveles)
    {
        
        if(raiz == '\0')
        {
        
            throw new RuntimeException("Variable inicial no declarada.");
        
        }
    
        this.inicial = new NodoAN(raiz);
        this.terminales = terminales;
        this.noTerminales = noTerminales;
        this.producciones = producciones;
        this.NIVELMAXIMO = niveles;
    
    }
    
    public ListaCD<Character> construccionArbolDerivacion()
    {
    
        if(terminales.isEmpty() || noTerminales.isEmpty())
        {
        
            throw new RuntimeException("Variables terminales o no terminales nulas.");
        
        }
        
        ListaCD<Character> palabra = new ListaCD();
        
        construccionArbolDerivacion(this.inicial, 0, palabra);       
    
        return palabra;
        
    }
    
    private void construccionArbolDerivacion(NodoAN<Character> actual, int nivel, ListaCD<Character> palabra)
    {
    
        if(esTerminal(actual.getDato()) || nivel == NIVELMAXIMO)
        {
        
            palabra.insertarFinal(actual.getDato());
            return;
        
        }
        
        asignarProduccion(actual);
        
        for(NodoAN<Character> n : actual.getHijos())
        {
        
            construccionArbolDerivacion( n, nivel + 1, palabra);
        
        }
    
    }
    
    private void asignarProduccion(NodoAN<Character> actual)
    {
    
        Random rand = new Random();
                
        char noTerminal = actual.getDato();

        if(noTerminal == '\0')
        {
        
            throw new RuntimeException("Error nodo con dato nulo.");
        
        }
        
        ListaCD<String> produccion = producciones.get(noTerminal);
      
        int i = rand.nextInt(produccion.getSize());        

        char [] simbolos = produccion.get(i).toCharArray();
            
        ListaCD<Character> conv = new ListaCD();
            
        for(char c : simbolos)
        {
            
            conv.insertarFinal(c);
            
        }
            
        actual.agregarProduccion(conv);
    
    }

    private boolean esTerminal(char info)
    {
    
        return this.terminales.containTo(info);
    
    }
    
    public void borrarArbol()
    {
    
        this.inicial = new NodoAN(this.inicial.getDato());
    
    }
}
