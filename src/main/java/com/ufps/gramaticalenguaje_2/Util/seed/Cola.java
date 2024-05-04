/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufps.gramaticalenguaje_2.Util.seed;

/**
 *
 * @author Juan David Ortiz Cano - 1152298
 * @author Jorge Andres Forero Serrano - 1152328
 */
public class Cola<T> {

    private ListaCD<T> lista = new ListaCD<>();

    public Cola() {
    }

    public void enColar(T info) {

        this.lista.insertarFinal(info);

    }

    public T deColar() {

        return this.lista.remove(0);

    }

    public boolean isEmpty() {

        return this.lista.isEmpty();

    }
    
    public int size()
    {
    
        return this.lista.getSize();
    
    }

    public T peek()
    {
    
        return this.lista.get(0);
    
    }
    
}
