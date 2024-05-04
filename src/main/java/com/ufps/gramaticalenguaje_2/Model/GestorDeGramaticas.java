/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.ufps.gramaticalenguaje_2.Model;

import com.ufps.gramaticalenguaje_2.Util.seed.ListaCD;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Juan David Ortiz Cano - 1152298
 * @author Jorge Andres Forero Serrano - 1152328
 */
public class GestorDeGramaticas {
    private ListaCD<Gramatica> gramaticas;

    public GestorDeGramaticas() {
        this.gramaticas = new ListaCD<>();
    }

    public boolean insertarGramatica(String nombre) {
        
        Gramatica temp = new Gramatica(nombre);
 
        if(this.gramaticas.containTo(temp))
        {
        
            throw new RuntimeException("Ya existe una gramática con este nombre, por favor ingresa uno diferente o cambiar el existente.");
        
        }
        
        if(nombre == null || nombre.isEmpty() || nombre.isBlank())
        {
        
            throw new RuntimeException("Entrada vacía.");
        
        }
        
        this.gramaticas.insertarFinal(temp);
        boolean agregado = true;
        
        return agregado;
        
    }
   
    public boolean eliminarGramatica(Gramatica gramatica)
    {
    
        Gramatica temp = this.gramaticas.removeItem(gramatica);
        
        if(temp == null)
        {
        
            throw new RuntimeException("Elemento no encontrado");
        
        }
        
        boolean eliminado = true;
        
        return eliminado;
    
    }
    
    public boolean insertarTerminales(Gramatica gramatica, String variables)
    {
    
        boolean agregado = gramatica.agregarTerminales(variables);
    
        return agregado;
        
    }
    
    public boolean insertarNoTerminales(Gramatica gramatica, String variables)
    {
    
        boolean agregado = gramatica.agregarNoTerminales(variables);
    
        return agregado;
        
    }

    public boolean insertarProducciones(Gramatica gramatica, char noTerminal, String producciones)
    {
    
        return gramatica.agregarProducciones(noTerminal, producciones);
        
    }
    
    public boolean definirInicial(Gramatica gramatica, char noTerminal)
    {
    
        boolean cambiado = false;
        gramatica.setVariableInicial(noTerminal);
        
        return cambiado;
    
    }
    
    public boolean eliminarTerminal(Gramatica gramatica, char terminal)
    {
    
        return gramatica.eliminarTerminal(terminal);
    
    }
   
    public boolean eliminarNoTerminal(Gramatica gramatica, char noTerminal)
    {
    
        return gramatica.eliminarNoTerminal(noTerminal);
    
    }
    
    public boolean eliminarProducciones(Gramatica gramatica, char noTerminal, String produccion)
    {
    
        return gramatica.eliminarProduccion(noTerminal, produccion);
    
    }
    
    public boolean modificarTerminal(Gramatica gramatica, char terminal, char nuevaTerminal)
    {
    
        return gramatica.modificarVariableTerminal(terminal, nuevaTerminal);
    
    } 
    
    public boolean modificarProduccion(Gramatica gramatica, char terminal, String produccion, String nuevaProduccion)
    {
    
        return gramatica.modificarProduccion(terminal, produccion, nuevaProduccion);
    
    } 
 
    public boolean modificarNoTerminal(Gramatica gramatica, char noTerminal, char nuevaNoTerminal)
    {
    
        return gramatica.modificarVariableNoTerminal(noTerminal, nuevaNoTerminal);
    
    } 
    
    public ListaCD<String> [] comprobaciones(Gramatica gramatica)
    {
    
        ListaCD<String> correcciones [] = new ListaCD[4]; 
        
        correcciones[0] = gramatica.comprobarInutiles();
        
        ListaCD<ListaCD<String>> variables = gramatica.comprobarInalcanzables();
        
        correcciones[1] = variables.get(0);
        correcciones[2] = variables.get(1);
        
        correcciones[3] = gramatica.comprobarProducciones();
        
        return correcciones;
        
    }
    
    public Set<String> generarPalabras(Gramatica gramatica, int numPalabra, int niveles)
    {
            
        gramatica.generarPalabras(numPalabra, niveles);
        
        return gramatica.getPalabras();
    
    }
    
    /**
     * @return the gramatica
     */
    public ListaCD<Gramatica> getGramaticas() {
        return gramaticas;
    }

    /**
     * @param gramatica the gramatica to set
     */
    public void setGramatica(ListaCD<Gramatica> gramatica) {
        this.gramaticas = gramatica;
    }
    
}

