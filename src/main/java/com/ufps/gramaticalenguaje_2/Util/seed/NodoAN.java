package com.ufps.gramaticalenguaje_2.Util.seed;

/**
 *
 * @author Juan David Ortiz Cano - 1152298
 * @author Jorge Andres Forero Serrano - 1152328
 */
public class NodoAN<T> {
    
    private T dato;
    private ListaCD<NodoAN<T>> hijos;
    
    public NodoAN(T info)
    {
    
        this.dato = info;
        this.hijos = new ListaCD();
    
    }
    
    public void agregarProduccion(ListaCD<T> producciones)
    {
        
        for(T datos : producciones)
        {
            
            this.hijos.insertarFinal(new NodoAN(datos));
        
        }    
    
    }

    public T getDato() {
        return dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public ListaCD<NodoAN<T>> getHijos() {
        return hijos;
    }

    public void setHijos(ListaCD<NodoAN<T>> hijos) {
        this.hijos = hijos;
    }
    
}
        