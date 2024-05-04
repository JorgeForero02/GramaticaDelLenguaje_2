/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Juan David Ortiz Cano - 1152298
 * @author Jorge Andres Forero Serrano - 1152328
 */
package com.ufps.gramaticalenguaje_2.Util.seed;

class NodoD<T> {
    
    private T info;
    private NodoD<T> sig;
    private NodoD<T> ant;

    public NodoD() {
    }

    public NodoD(T info, NodoD<T> sig, NodoD<T> ant) {
        this.info = info;
        this.sig = sig;
        this.ant = ant;
    }

    T getInfo() {
        return info;
    }

    void setInfo(T info) {
        this.info = info;
    }

    NodoD<T> getSig() {
        return sig;
    }

    void setSig(NodoD<T> sig) {
        this.sig = sig;
    }

    NodoD<T> getAnt() {
        return ant;
    }

    void setAnt(NodoD<T> ant) {
        this.ant = ant;
    }
    
    @Override
    public String toString()
    {
    
        return this.info.toString();
        
    }
    
}
