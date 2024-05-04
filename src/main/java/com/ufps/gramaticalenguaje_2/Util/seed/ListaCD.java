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

import java.util.Iterator;

public class ListaCD<T> implements Iterable<T> {

    private NodoD<T> cabeza;
    private int size;

    public ListaCD() {

        this.cabeza = new NodoD(null, null, null);
        this.cabeza.setAnt(cabeza);
        this.cabeza.setSig(cabeza);
        this.size = 0;

    }

    public void insertarInicio(T info) {

        NodoD temp = new NodoD(info, this.cabeza.getSig(), this.cabeza);
        this.cabeza.setSig(temp);
        temp.getSig().setAnt(temp);
        this.size++;

    }

    public void insertarFinal(T info) {

        NodoD temp = new NodoD(info, this.cabeza, this.cabeza.getAnt());
        this.cabeza.setAnt(temp);
        temp.getAnt().setSig(temp);
        this.size++;

    }

    public boolean containTo(T info) {

        for (NodoD<T> temp = this.cabeza.getSig(); temp != this.cabeza; temp = temp.getSig()) {

            if (temp.getInfo().equals(info)) {

                return true;

            }

        }

        return false;

    }

    public String toString() {

        String msg = "";

        for (NodoD<T> temp = this.cabeza.getSig(); temp != this.cabeza; temp = temp.getSig()) {

            msg += temp.toString();

        }

        return msg;

    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    private NodoD<T> getPos(int pos) {
        if (this.isEmpty()) {
            throw new RuntimeException("Lista vacìa, no hay posiciones");
        }
        if (pos < 0 || pos >= this.size) {
            throw new RuntimeException("Posición fuera de rango, esta debe ser entre:0 y " + (this.size - 1));
        }

        NodoD<T> temp = this.cabeza.getSig();
        //int i=0;
        while (pos > 0) //i<pos
        {
            temp = temp.getSig();
            pos--; //i++
        }
        return temp;
    }

    public T get(int i) {
        return this.getPos(i).getInfo();
    }

    public void set(int i, T info) {
        this.getPos(i).setInfo(info);
    }

    public int getSize() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new IteratorLCD(this.cabeza);
    }

    public T remove(int pos) {
        if (pos < 0 || pos >= this.size) {
            throw new RuntimeException("Posición fuera de rango, esta debe ser entre:0 y " + (this.size - 1));
        }
        NodoD<T> borrar = this.getPos(pos);
        borrar.getAnt().setSig(borrar.getSig());
        borrar.getSig().setAnt(borrar.getAnt());
        borrar.setAnt(null);
        borrar.setSig(null);
        this.size--;
        return borrar.getInfo();

    }

    public T removeItem(T item) {
        if (isEmpty()) {
            return null;
        }

        NodoD<T> actual = cabeza.getSig();

        while (actual != cabeza) {
            if (actual.getInfo().equals(item)) {
                actual.getAnt().setSig(actual.getSig());
                actual.getSig().setAnt(actual.getAnt());
                actual.setAnt(null);
                actual.setSig(null);
                size--;
                return actual.getInfo();
            }
            actual = actual.getSig();
        }

        return null;
    }

    public boolean modificarItem(T item, T newItem) {

        NodoD<T> actual = cabeza.getSig();

        while (actual != cabeza) {
            if (actual.getInfo().equals(item)) {
                actual.setInfo(newItem);
                return true;
            }
            actual = actual.getSig();
        }
        
        return false;
    }

    public void insertarTodo(ListaCD<T> temp) {

        NodoD<T> cola = temp.cabeza.getAnt();

        this.cabeza.getAnt().setSig(temp.cabeza.getSig());
        temp.cabeza.getSig().setAnt(this.cabeza.getAnt());

        cola.setSig(this.cabeza);
        this.cabeza.setAnt(cola);

        this.size += temp.size;

        temp.cabeza.setAnt(null);
        temp.cabeza.setAnt(null);

    }

}
