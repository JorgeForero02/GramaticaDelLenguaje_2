/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufps.gramaticalenguaje_2.Model;

import com.ufps.gramaticalenguaje_2.Util.seed.ArbolDerivacion;
import com.ufps.gramaticalenguaje_2.Util.seed.Cola;
import com.ufps.gramaticalenguaje_2.Util.seed.ListaCD;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author Juan David Ortiz Cano - 1152298
 * @author Jorge Andres Forero Serrano - 1152328
 */
public class Gramatica {

    private String nombre;
    private ListaCD<Character> variablesTerminales;
    private ListaCD<Character> variablesNoTerminales;
    private char variableInicial;
    private Map<Character, ListaCD<String>> producciones;
    private Set<String> palabras;

    public Gramatica(String nombre) {

        this.nombre = nombre;
        this.variablesTerminales = new ListaCD<>();
        this.variablesNoTerminales = new ListaCD<>();
        this.producciones = new HashMap<>();
        this.palabras = new HashSet<>();

    }

    public boolean agregarTerminales(String variables) {

        if (variables == null || variables.isBlank() || variables.isEmpty()) {

            throw new RuntimeException("Entrada vacía.");

        }

        boolean agregado = false;
        ListaCD<Character> temp = new ListaCD();
        variables = variables.toLowerCase();
        String valores[] = variables.split(",");

        for (String s : valores) {

            if (s.length() > 1) {

                throw new RuntimeException("La gramática solo acepta terminales de un carácter, asegurate de separarlos correctamente con una coma (,).");

            }

            char c = s.charAt(0);

            if (!this.variablesTerminales.containTo(c)) {

                if (!temp.containTo(c)) {

                    temp.insertarFinal(c);
                    agregado = true;

                }

            }

        }

        if (!agregado) {

            throw new RuntimeException("Todos las varibles terminales ya se encontraban agregadas.");

        }

        this.variablesTerminales.insertarTodo(temp);

        return agregado;

    }

    public boolean agregarNoTerminales(String variables) {

        if (variables == null || variables.isBlank() || variables.isEmpty()) {

            throw new RuntimeException("Entrada vacía.");

        }

        boolean agregado = false;
        ListaCD<Character> temp = new ListaCD();
        variables = variables.toUpperCase();
        String valores[] = variables.split(",");

        for (String s : valores) {

            if (s.length() > 1) {

                throw new RuntimeException("La gramática solo acepta terminales de un carácter, asegurate de separarlos correctamente con una coma (,).");

            }

            char c = s.charAt(0);

            if (c < 65 && c > 90) {

                throw new RuntimeException("La gramática por convención solo admite variables no terminales con letras mayúsculas.");

            }

            if (!this.variablesNoTerminales.containTo(c)) {

                if (!temp.containTo(c)) {

                    temp.insertarFinal(c);
                    agregado = true;

                }

            }

        }

        if (!agregado) {

            throw new RuntimeException("Todos las varibles no terminales ya se encontraban agregadas.");

        }

        this.variablesNoTerminales.insertarTodo(temp);

        return agregado;

    }

    public boolean agregarProducciones(char noTerminal, String producciones) {

        if (this.variablesNoTerminales.isEmpty()) {

            throw new RuntimeException("No existen no terminales en la gramática.");

        }

        if (!this.variablesNoTerminales.containTo(noTerminal)) {

            throw new RuntimeException("La variable no terminal proporcionada no pertenece al conjunto de no terminales definido en la gramática.");

        }

        boolean agregado = false;
        ListaCD<String> produccion = this.producciones.get(noTerminal);

        if (produccion == null) {

            this.producciones.put(noTerminal, new ListaCD<String>());

        }

        
        String valores[] = producciones.split("/");
        produccion = this.producciones.get(noTerminal);

        for (String s : valores) {

            produccion.insertarFinal(s);
            agregado = false;

        }

        return agregado;

    }

    public void generarPalabras(int numPalabras, int niveles) {

        ArbolDerivacion arbol = new ArbolDerivacion(this.variableInicial, this.variablesTerminales, this.variablesNoTerminales, this.producciones, niveles);

        while (palabras.size() < numPalabras) {

            ListaCD<Character> palabra = arbol.construccionArbolDerivacion();
            this.palabras.add(construccionPalabra(palabra));
            arbol.borrarArbol();

        }

    }

    private String construccionPalabra(ListaCD<Character> palabra) {

        String s = "";
        for (char c : palabra) {

            s += c + "";

        }

        return s;

    }

    public ListaCD<ListaCD<String>> comprobarInalcanzables() {

        Map<Character, Boolean> verificacion = new HashMap();

        for (Character c : this.variablesTerminales) {

            verificacion.put(c, Boolean.FALSE);

        }

        for (Character c : this.variablesNoTerminales) {

            verificacion.put(c, Boolean.FALSE);

        }

        buscarInalcanzables(verificacion);
        
        ListaCD<ListaCD<String>> eliminados = new ListaCD();
        
        ListaCD<String> terminales = new ListaCD();
        ListaCD<String> noTerminales = new ListaCD();
        
        eliminados.insertarFinal(terminales);
        eliminados.insertarFinal(noTerminales);

        for (Map.Entry<Character, Boolean> entry : verificacion.entrySet()) {

            char variable = entry.getKey();
            boolean temp = entry.getValue();

            if (!temp) {

                if (variable >= 65 && variable <= 90) {

                    ListaCD<String> aux = this.producciones.get(variable);
                    noTerminales.insertarFinal("Variable No Terminal con producciones: " + variable + "-->" + aux.toString() + "\n");
                    this.producciones.remove(variable);
                    this.variablesNoTerminales.removeItem(variable);

                } else {

                    terminales.insertarFinal("Variable Terminal: " + variable + "\n");
                    variablesTerminales.removeItem(variable);

                }

            }

        }

        return eliminados;

    }

    private void buscarInalcanzables(Map<Character, Boolean> verificacion) {

        Cola<Character> variables = new Cola();
        variables.enColar(variableInicial);

        while (!variables.isEmpty()) {

            char temp = variables.deColar();

            if (verificacion.get(temp) == false) {

                verificacion.put(temp, Boolean.TRUE);

                if (temp >= 65 && temp <= 90) {

                    ListaCD<String> produccion = this.producciones.get(temp);
                    ingresarVariables(variables, produccion);

                }

            }

        }

    }

    public ListaCD<String> comprobarInutiles() {

        ListaCD<String> eliminados = new ListaCD();
        boolean estado = false;

        while(!estado)
        {
        
            estado = true;
            
            for (char c : variablesNoTerminales) {
                if (!producciones.containsKey(c) || producciones.get(c).isEmpty()) {
                    String eliminado = "" + variablesNoTerminales.removeItem(c);
                    eliminados.insertarFinal("Variable no terminal: " + eliminado + "\n");
                }

            }

            for(char c : variablesNoTerminales)
            {

                for (String d : producciones.get(c)) {

                    for (int i = 0; i < d.length(); i++) {

                        char caracter = d.charAt(i);
                        if (!variablesTerminales.containTo(caracter) && !variablesNoTerminales.containTo(caracter)) {
                            eliminados.insertarFinal("Produccion: " + producciones.get(c).removeItem(d) + "\n");

                            if(producciones.get(c).isEmpty())
                            {

                                variablesNoTerminales.removeItem(c);
                                producciones.remove(c);

                            }

                            estado = false;

                        }

                    }
                }   

            }   
        
        }
        
        return eliminados;
        
    }

    private void ingresarVariables(Cola<Character> cola, ListaCD<String> produccion) {

        for (String s : produccion) {

            char[] aux = s.toCharArray();

            for (char c : aux) {

                cola.enColar(c);

            }

        }

    }

    public void limpiarGramatica() {
        this.variablesTerminales = new ListaCD<>();
        this.variablesNoTerminales = new ListaCD<>();
        this.producciones = new HashMap<>();
        this.palabras = new HashSet<>();
        this.variableInicial = '\0';
    }

    public void modificarVariableInicial(char nuevaInicial) {
        if (!this.variablesNoTerminales.containTo(nuevaInicial)) {
            throw new RuntimeException("La variable dada no pertenece a las variables no terminales.");
        }
        this.variableInicial = nuevaInicial;
    }

    public boolean modificarVariableTerminal(char dato, char nuevoDato) {
        if (!((dato >= 'a' && dato <= 'z') || !Character.isLetter(dato)) || dato == ',') {
            throw new RuntimeException("Las variables terminales deben ser letras minúsculas o caracteres especiales, excluyendo la coma (,)");
        }

        if (!this.variablesTerminales.containTo(dato)) {
            throw new RuntimeException("La variable a modificar no existe en la lista de variables terminales.");
        }

        if (this.variablesTerminales.containTo(nuevoDato)) {
            throw new RuntimeException("La nueva variable ya existe en la lista de variables terminales.");
        }

        for (Map.Entry<Character, ListaCD<String>> entry : this.producciones.entrySet()) {
            ListaCD<String> producciones = entry.getValue();
            for (int i = 0; i < producciones.getSize(); i++) {
                String produccion = producciones.get(i);
                produccion = produccion.replace(dato, nuevoDato);
                producciones.set(i, produccion);
            }
        }

        return this.variablesTerminales.modificarItem(dato, nuevoDato);
    }

    public boolean modificarVariableNoTerminal(char dato, char nuevoDato) {
        if (dato < 65 || dato > 90 || nuevoDato < 65 || nuevoDato > 90) {
            throw new RuntimeException("Las variables no terminales deben ser letras mayúsculas");
        }

        if (!this.variablesNoTerminales.containTo(dato)) {
            throw new RuntimeException("La variable a modificar no existe en la lista de variables no terminales");
        }

        if (this.variablesNoTerminales.containTo(nuevoDato)) {
            throw new RuntimeException("La nueva variable ya existe en la lista de variables no terminales");
        }
        
        for (Map.Entry<Character, ListaCD<String>> entry : this.producciones.entrySet()) {
            ListaCD<String> producciones = entry.getValue();
            for (int i = 0; i < producciones.getSize(); i++) {
                String produccion = producciones.get(i);
                produccion = produccion.replace(dato, nuevoDato);
                producciones.set(i, produccion);
            }
        }

        ListaCD<String> tmp = this.producciones.get(dato);
        if (tmp != null) {
            this.producciones.put(nuevoDato, tmp);
            this.producciones.remove(dato);
        }
        
        boolean modificado = this.variablesNoTerminales.modificarItem(dato, nuevoDato);
        
        if(dato == this.variableInicial)
        {
        
            this.setVariableInicial(nuevoDato);
            
        }

        return modificado;
    }

    public boolean modificarProduccion(char noTerminal, String produccion, String nuevaProduccion) {
        if (!this.producciones.containsKey(noTerminal)) {
            throw new RuntimeException("El no terminal proporcionado no tiene producciones en la gramatica");
        }

        ListaCD<String> tmp = this.producciones.get(noTerminal);

        if (!tmp.containTo(produccion)) {
            throw new RuntimeException("La producción a modificar no existe para el no terminal dado");
        }

        boolean modificado = tmp.modificarItem(produccion, nuevaProduccion);

        this.producciones.put(noTerminal, tmp);
        
        return modificado;
    }
    
    public ListaCD<String> comprobarProducciones()
    {

        ListaCD<String> produccionesSinTerminales = new ListaCD();
        
        for(Map.Entry<Character,ListaCD<String>> entry : this.producciones.entrySet())
        {
            boolean contiene = false;
            for(String s:entry.getValue())
            {
            
                if(produccionSoloTerminales(s))
                {
                
                    contiene = true;
                    break;
                
                }
            
            }
            
            if(!contiene)
            {
            
                produccionesSinTerminales.insertarInicio(entry.getKey() + ",");
            
            }
        
        }    
        
        return produccionesSinTerminales;
        
    }
    
    private boolean produccionSoloTerminales(String produccion)
    {
    
        boolean contiene = true;
        
        for(int i = 0; i < produccion.length() && contiene;i++)
        {
        
            char c = produccion.charAt(i);
            
            if(!this.variablesTerminales.containTo(c))
            {
            
                contiene = false;
            
            }
        
        }
        
        return contiene;
    
    }
    
    public boolean eliminarTerminal(char variableTerminal)
    {
    
        if(this.variablesTerminales.getSize() == 0)
        {
        
            throw new RuntimeException("Variables terminales vacias.");
        
        }
        
        if(this.variablesTerminales.removeItem(variableTerminal) == null)
        {
        
            throw new RuntimeException("La variable seleccionada no se encontraba entre las terminales.");
        
        }
    
        return true;
        
    }
    
    public boolean eliminarNoTerminal(char noTerminal)
    {
    
        if(this.variablesNoTerminales.getSize() == 0)
        {
        
            throw new RuntimeException("Variables no terminales vacias.");
        
        }        
        
        if(noTerminal == this.variableInicial)
        {
        
            throw new RuntimeException("La variable seleccionada es la inicial de la gramática, por favor cambia la inicial por otra para eliminarla.");
        
        }
        
        if(this.variablesNoTerminales.removeItem(noTerminal) == null)
        {
        
            throw new RuntimeException("La variable seleccionada no se encontraba entre las terminales.");
        
        }
        
        return true;
    
    }
    
    public boolean eliminarProduccion(char noTerminal, String produccion)
    {
    
        if(this.variablesNoTerminales.getSize() == 0)
        {
        
            throw new RuntimeException("Variables no terminales vacias.");
        
        }     
        
        if(!this.variablesNoTerminales.containTo(noTerminal))
        {
        
            throw new RuntimeException("Variable no terminal no ingresada.");
        
        }     
        
        if(!this.producciones.containsKey(noTerminal))
        {
        
            throw new RuntimeException("Variable no terminal sin producciones.");
        
        }     
        
        ListaCD<String> temp = this.producciones.get(noTerminal);
        
        if(temp.removeItem(produccion) == null)
        {
        
            throw new RuntimeException("La producción para la variable dada no existe.");
        
        }
        
        return true;
    
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.nombre);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Gramatica other = (Gramatica) obj;
        return Objects.equals(this.nombre, other.nombre);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<String> getPalabras() {
        return palabras;
    }

    public void setPalabras(Set<String> palabras) {
        this.palabras = palabras;
    }

    /**
     * @return the variablesTerminales
     */
    public ListaCD<Character> getVariablesTerminales() {
        return variablesTerminales;
    }

    /**
     * @param variablesTerminales the variablesTerminales to set
     */
    public void setVariablesTerminales(ListaCD<Character> variablesTerminales) {
        this.variablesTerminales = variablesTerminales;
    }

    /**
     * @return the variablesNoTerminales
     */
    public ListaCD<Character> getVariablesNoTerminales() {
        return variablesNoTerminales;
    }

    /**
     * @param variablesNoTerminales the variablesNoTerminales to set
     */
    public void setVariablesNoTerminales(ListaCD<Character> variablesNoTerminales) {
        this.variablesNoTerminales = variablesNoTerminales;
    }

    /**
     * @return the variableInicial
     */
    public char getVariableInicial() {
        return variableInicial;
    }

    /**
     * @param variableInicial the variableInicial to set
     */
    public void setVariableInicial(char variableInicial) {

        if (!this.variablesNoTerminales.containTo(variableInicial)) {

            throw new RuntimeException("La variable dada no pertenece a las variables no terminales.");

        }

        this.variableInicial = variableInicial;

    }

    /**
     * @return the producciones
     */
    public Map<Character, ListaCD<String>> getProducciones() {
        return producciones;
    }

    public void setProducciones(Map<Character, ListaCD<String>> producciones) {
        this.producciones = producciones;
    }
    
    public String toString()
    {
    
        return this.nombre;
    
    }

    public void imprimirGramatica() {
        System.out.println("Nombre de la gramatica: " + this.nombre);
        System.out.println("Variables terminales: " + this.variablesTerminales.toString());
        System.out.println("Variables no terminales: " + this.variablesNoTerminales.toString());
        System.out.println("Variable inicial: " + this.variableInicial);
        System.out.println("Producciones:");
        for (Map.Entry<Character, ListaCD<String>> entry : this.producciones.entrySet()) {
            System.out.println("   " + entry.getKey() + " -> " + entry.getValue().toString());
        }
    }

}
