/**
 * Sample Skeleton for 'ae.fxml' Controller Class
 */

/**
 *
 * @author Juan David Ortiz Cano - 1152298
 * @author Jorge Andres Forero Serrano - 1152328
 */

package com.ufps.gramaticalenguaje_2.Controller;

import com.ufps.gramaticalenguaje_2.Model.GestorDeGramaticas;
import com.ufps.gramaticalenguaje_2.Model.Gramatica;
import com.ufps.gramaticalenguaje_2.Util.seed.ListaCD;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class GramaticaController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="ListNoTerminales"
    private ListView<Character> ListNoTerminales; // Value injected by FXMLLoader

    @FXML // fx:id="ListProducciones"
    private ListView<String> ListProducciones; // Value injected by FXMLLoader

    @FXML // fx:id="ListTerminales"
    private ListView<Character> ListTerminales; // Value injected by FXMLLoader

    @FXML // fx:id="btnAgregarG"
    private Button btnAgregarG; // Value injected by FXMLLoader

    @FXML // fx:id="btnAgregarProducciones"
    private Button btnAgregarProducciones; // Value injected by FXMLLoader

    @FXML // fx:id="btnAgregarVnt"
    private Button btnAgregarVnt; // Value injected by FXMLLoader

    @FXML // fx:id="btnAgregarVt"
    private Button btnAgregarVt; // Value injected by FXMLLoader

    @FXML // fx:id="btnComprobar"
    private Button btnComprobar; // Value injected by FXMLLoader

    @FXML // fx:id="btnDefinirInicial"
    private Button btnDefinirInicial; // Value injected by FXMLLoader

    @FXML // fx:id="btnEliminarNoTerminal"
    private Button btnEliminarNoTerminal; // Value injected by FXMLLoader

    @FXML // fx:id="btnEliminarProduccion"
    private Button btnEliminarProduccion; // Value injected by FXMLLoader

    @FXML // fx:id="btnEliminarTerminal"
    private Button btnEliminarTerminal; // Value injected by FXMLLoader

    @FXML // fx:id="btnLimpiar"
    private Button btnLimpiar; // Value injected by FXMLLoader

    @FXML // fx:id="btnModificarNoTerminal"
    private Button btnModificarNoTerminal; // Value injected by FXMLLoader

    @FXML // fx:id="btnModificarProduccion"
    private Button btnModificarProduccion; // Value injected by FXMLLoader

    @FXML // fx:id="btnModificarTerminal"
    private Button btnModificarTerminal; // Value injected by FXMLLoader

    @FXML // fx:id="btnPalabras"
    private Button btnPalabras; // Value injected by FXMLLoader
    
    @FXML // fx:id="btnAyuda"
    private Button btnAyuda; // Value injected by FXMLLoader

    @FXML // fx:id="btnCambiarInicial"
    private Button btnCambiarInicial; // Value injected by FXMLLoader

    @FXML // fx:id="txtNumeroPalabras"
    private TextField txtNumeroPalabras; // Value injected by FXMLLoader

    @FXML // fx:id="cboGramatica"
    private ComboBox<Gramatica> cboGramatica; // Value injected by FXMLLoader

    @FXML // fx:id="cboInicial"
    private ComboBox<Character> cboInicial; // Value injected by FXMLLoader

    @FXML // fx:id="cboProducciones"
    private ComboBox<Character> cboProducciones; // Value injected by FXMLLoader

    @FXML // fx:id="eliminarG"
    private Button eliminarG; // Value injected by FXMLLoader

    @FXML // fx:id="txtGramatica"
    private TextField txtGramatica; // Value injected by FXMLLoader

    @FXML // fx:id="txtModificarNoTerminal"
    private TextField txtModificarNoTerminal; // Value injected by FXMLLoader

    @FXML // fx:id="txtModificarProduccion"
    private TextField txtModificarProduccion; // Value injected by FXMLLoader

    @FXML // fx:id="txtModificarTerminal"
    private TextField txtModificarTerminal; // Value injected by FXMLLoader

    @FXML // fx:id="txtPalabras"
    private TextArea txtPalabras; // Value injected by FXMLLoader

    @FXML // fx:id="txtProducciones"
    private TextField txtProducciones; // Value injected by FXMLLoader

    @FXML // fx:id="txtVariablesNoTerminales"
    private TextField txtVariablesNoTerminales; // Value injected by FXMLLoader

    @FXML // fx:id="txtVariablesTerminales"
    private TextField txtVariablesTerminales; // Value injected by FXMLLoader

    @FXML
    private TextField txtNiveles; // Value injected by FXMLLoader
    
    private GestorDeGramaticas gramaticas;
    private ObservableList<Character> variablesTerminales = FXCollections.observableArrayList();
    private ObservableList<Character> variablesNoTerminales = FXCollections.observableArrayList();
    private ObservableList<String> producciones = FXCollections.observableArrayList();
    
    @FXML
    void agregarGramatica(ActionEvent event) {
        
        try
        {
        
            String nombre = this.txtGramatica.getText();
            
            if(gramaticas.insertarGramatica(nombre))
            {
            
                mostrarAlerta("Gramática agregada correctamente.");
            
            }
            
            actualizarCBOGramaticas(this.cboGramatica, 1);
            
            this.txtGramatica.clear();
        
        }catch(Exception e)
        {
        
            mostrarAlertaError(e.getMessage());
        
        }
        
    }
    
    private void gramaticaSeleccionada()
    {
    
        if(this.cboGramatica.getValue() == null)
        {
            
            throw new RuntimeException("No hay ninguna gramática seleccionada, asegurese de ingresar y seleccionar una.");
            
        }    
    
    }
    
    private void actualizarCBOGramaticas(ComboBox combo, int tipo)
    {
        
        combo.getItems().clear();
    
        if(tipo == 1)
        {

            for(Gramatica g : this.gramaticas.getGramaticas())
            {

                combo.getItems().add(g);

            }     
            
            this.cboInicial.getItems().clear();
            this.cboProducciones.getItems().clear();
        
        }else
        {
        
            this.cboProducciones.getItems().clear();          
            
            if(this.cboGramatica.getValue() != null)
            {
                
                ListaCD<Character> variables;  
                variables = this.cboGramatica.getValue().getVariablesNoTerminales();

                for(char c : variables)
                {

                    combo.getItems().add(c);
                    this.cboProducciones.getItems().add(c);

                }       
            
            }           
        
        }
    
    }

    @FXML
    void agregarNoTerminal(ActionEvent event) {
        
        try
        {
            
            gramaticaSeleccionada();
        
            Gramatica actual = this.cboGramatica.getValue();
            String noTerminal = this.txtVariablesNoTerminales.getText();
            
            gramaticas.insertarNoTerminales(actual, noTerminal);
            
             mostrarAlerta("Variables no terminales insertadas correctamente.");
            
            //Actualizar lista;
            
            actualizarCBOGramaticas(this.cboInicial, 2);
            
            this.txtVariablesNoTerminales.clear();
            actualizarTablas(this.cboGramatica.getValue());
            
            sincronizarVariableInicial(this.cboGramatica.getValue());
            
            this.btnPalabras.setDisable(true);
            this.txtNumeroPalabras.setDisable(true);
            this.txtNiveles.setDisable(true);
        
        }catch(Exception e)
        {
        
            mostrarAlertaError(e.getMessage());
        
        }    

    }

    @FXML
    void agregarProducciones(ActionEvent event) {
        
        try
        {
        
            gramaticaSeleccionada();
            
            if(this.cboProducciones.getValue() == null)
            {
            
                throw new RuntimeException("No hay una variable no terminal seleccionada a la cual asignarle las producciones, por favor verificar.");
            
            }
            
            Gramatica actual = this.cboGramatica.getValue();
            char noTerminal = this.cboProducciones.getValue();
            String producciones = this.txtProducciones.getText();
            
            this.gramaticas.insertarProducciones(actual, noTerminal, producciones);
            
            this.txtProducciones.clear();
            
            mostrarAlerta("Producciones definidas correctamente.");
            actualizarTablas(this.cboGramatica.getValue());
            
            this.btnPalabras.setDisable(true);
            this.txtNumeroPalabras.setDisable(true);
            this.txtNiveles.setDisable(true);
        
        }catch(Exception e)
        {
        
            mostrarAlertaError(e.getMessage());
        
        }

    }

    @FXML
    void agregarTerminal(ActionEvent event) {
        
        try
        {
        
            gramaticaSeleccionada();
            
            Gramatica actual = this.cboGramatica.getValue();
            String terminal = this.txtVariablesTerminales.getText();
            gramaticas.insertarTerminales(actual, terminal);
            
            mostrarAlerta("Variables terminales insertadas correctamente.");
            
            this.txtVariablesTerminales.clear();
            actualizarTablas(this.cboGramatica.getValue());
            
            this.btnPalabras.setDisable(true);
            this.txtNumeroPalabras.setDisable(true);
            this.txtNiveles.setDisable(true);
        
        }catch(Exception e)
        {
        
            mostrarAlertaError(e.getMessage());
        
        }    

    }

    @FXML
    void comprobarGramatica(ActionEvent event) {

        try
        {
        
            gramaticaSeleccionada();
            
            Gramatica gramatica = this.cboGramatica.getValue();
            
            ListaCD<String> [] ajustes = gramaticas.comprobaciones(gramatica);
          
            this.txtPalabras.appendText("Producciones y Variables Inutiles: \n");
            
            this.txtPalabras.appendText(ajustes[0].toString());
            this.txtPalabras.appendText(ajustes[1].toString());
            
            this.txtPalabras.appendText("\n");
            
            this.txtPalabras.appendText("Variables Inalcanzables: \n");
            
            this.txtPalabras.appendText(ajustes[2].toString());
            
            this.txtPalabras.appendText("\n");
            
            this.txtPalabras.appendText("Producciones sin cadenas de solo terminales: \n");
            
            this.txtPalabras.appendText(ajustes[3].toString());
            
            actualizarCBOGramaticas(cboInicial, 2);
            actualizarTablas(gramatica);
            sincronizarVariableInicial(gramatica);
            
            this.mostrarAlerta("Comprobaciones realizadas.");
            
            this.btnPalabras.setDisable(false);
            this.txtNumeroPalabras.setDisable(false);
            this.txtNiveles.setDisable(false);
            
        }catch(Exception e)
        {
        
            mostrarAlertaError(e.getMessage());
        
        }
        
    }

    @FXML
    void definirInicial(ActionEvent event) {
        
        try
        {
        
            gramaticaSeleccionada();
            
            if(this.cboInicial.getValue() == null)
            {
            
                throw new RuntimeException("No hay una variable terminal seleccionada.");
            
            }
            
            Gramatica actual = this.cboGramatica.getValue();
            char noTerminal = this.cboInicial.getValue();
            
            gramaticas.definirInicial(actual, noTerminal);
            
            mostrarAlerta("Variables inicial definida correctamente.");
            
            this.btnCambiarInicial.setDisable(false);
            this.cboInicial.setDisable(true);
            this.btnDefinirInicial.setDisable(true);
        
        }catch(Exception e)
        {
        
            mostrarAlertaError(e.getMessage());
        
        } 
        
    }

    @FXML
    void eliminarGramatica(ActionEvent event) {
        
        try
        {
        
            Gramatica gramatica = this.cboGramatica.getValue();
            
            if(mostrarVentanaConfirmacion(gramatica))
            {
            
                mostrarAlerta("Gramática borrada correctamente.");
            
            }
            
            actualizarCBOGramaticas(this.cboGramatica, 1);
            actualizarTablas(gramatica);
            
        
        }catch(Exception e)
        {
        
            mostrarAlertaError(e.getMessage());
        
        }       
        
        this.btnPalabras.setDisable(true);
        this.txtNumeroPalabras.setDisable(true);

    }
    
    private boolean mostrarVentanaConfirmacion(Gramatica gramatica) {
        
        int inicial = gramaticas.getGramaticas().getSize();
        
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmar Eliminación de Gramática");
        alert.setHeaderText("¿Está seguro de que desea eliminar esta gramática?");
        alert.setContentText("Esta acción no se puede deshacer y eliminara TODO lo relacionado a esta.");

        alert.showAndWait().ifPresent(response -> {
            
            if (response == ButtonType.OK) {

                gramaticas.eliminarGramatica(gramatica);
                
            }
            
        });
        
        if(inicial > gramaticas.getGramaticas().getSize())
        {
        
            return true;
            
        }
        
        return false;
        
    }
    
    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Notificación");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
    
    private void mostrarAlertaError(String mensaje) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    
    @FXML
    void eliminarNoTerminal(ActionEvent event) {

        try
        {
        
            gramaticaSeleccionada();
            
            if(this.variablesNoTerminales.isEmpty())
            {
            
                throw new RuntimeException("No hay no terminales ingresadas para eliminar.");
            
            }
            
            char noTerminal = this.ListNoTerminales.getSelectionModel().selectedItemProperty().getValue();

            if(noTerminal == '\0')
            {

                throw new RuntimeException("Por favor seleccione una variable de la lista disponible.");

            } 

            gramaticas.eliminarNoTerminal(this.cboGramatica.getValue(), noTerminal);
            
            this.mostrarAlerta("Fue eliminada con exito.");
            
            this.actualizarTablas(this.cboGramatica.getValue()); 
            actualizarCBOGramaticas(this.cboInicial, 2);
            
            this.btnPalabras.setDisable(true);
            this.txtNumeroPalabras.setDisable(true);
            this.txtNiveles.setDisable(true);
        
        }catch(Exception e)
        {
        
            this.mostrarAlertaError(e.getMessage());
        
        }
        
    }

    @FXML
    void eliminarProduccion(ActionEvent event) {

        try
        {
        
            gramaticaSeleccionada();
            
            if(this.cboProducciones.getValue() == null)
            {
            
                throw new RuntimeException("No hay variable no terminal selecciona, por favor seleccione una de las ingresadas.");
            
            }
            
            if(this.producciones.isEmpty())
            {
            
                throw new RuntimeException("No hay producciones asignadas a esta no temrinal para eliminar.");
            
            }
            
            String produccion = this.ListProducciones.getSelectionModel().selectedItemProperty().getValue();

            if(produccion == null)
            {

                throw new RuntimeException("Por favor seleccione una producción de la lista disponible.");

            } 

            gramaticas.eliminarProducciones(this.cboGramatica.getValue(), this.cboProducciones.getValue(), produccion);
            
            this.mostrarAlerta("Fue eliminada con exito.");
            
            this.actualizarTablas(this.cboGramatica.getValue());  
            
            this.btnPalabras.setDisable(true);
            this.txtNumeroPalabras.setDisable(true);
            this.txtNiveles.setDisable(true);
        
        }catch(Exception e)
        {
        
            this.mostrarAlertaError(e.getMessage());
        
        }
        
    }

    @FXML
    void eliminarTerminal(ActionEvent event) {

        try
        {
        
            gramaticaSeleccionada();
            
            if(this.variablesTerminales.isEmpty())
            {
            
                throw new RuntimeException("No hay terminales ingresadas para eliminar.");
            
            }
            
            char terminal = this.ListTerminales.getSelectionModel().selectedItemProperty().getValue();

            if(terminal == '\0')
            {

                throw new RuntimeException("Por favor seleccione una variable de la lista disponible.");

            } 

            gramaticas.eliminarTerminal(this.cboGramatica.getValue(), terminal);
            
            this.mostrarAlerta("Fue eliminada con exito.");
            
            this.actualizarTablas(this.cboGramatica.getValue());  
            
            this.btnPalabras.setDisable(true);
            this.txtNumeroPalabras.setDisable(true);
            this.txtNiveles.setDisable(true);
        
        }catch(Exception e)
        {
        
            this.mostrarAlertaError(e.getMessage());
        
        }
        
    }

    @FXML
    void generarPalabras(ActionEvent event) {
        
        try
        {
        
            gramaticaSeleccionada();
            
            Set<String> palabras = gramaticas.generarPalabras(this.cboGramatica.getValue(), Integer.parseInt(this.txtNumeroPalabras.getText()), Integer.parseInt(this.txtNiveles.getText()));
            
            this.txtPalabras.appendText("Palabras del Lenguaje: \n");
            this.txtPalabras.appendText("\n");
            
            for(String s : palabras)
            {
            
                this.txtPalabras.appendText(s + "\n");
                this.txtPalabras.appendText("\n");
                
            }
            
            mostrarAlerta("Las Palabras han sido generadas correctamente.");
            
            
        }catch(Exception e)
        {
        
            this.mostrarAlertaError(e.getMessage());
        
        }    

    }

    @FXML
    void limpiarConsola(ActionEvent event) {

        this.txtPalabras.clear();
        
    }

    @FXML
    void modificarNoTerminl(ActionEvent event) {

        try
        {
        
            gramaticaSeleccionada();
            
            if(this.variablesNoTerminales.isEmpty())
            {
            
                throw new RuntimeException("No hay no terminales ingresada para modificar.");
            
            }
            
            String temp = txtModificarNoTerminal.getText();
            
            if(temp == null || temp.isBlank() || temp.isEmpty())
            {
            
                throw new RuntimeException("Entrada vacia.");
            
            }
            
            char terminal = this.ListNoTerminales.getSelectionModel().selectedItemProperty().getValue();
            char nuevaNoTerminal [] = txtModificarNoTerminal.getText().toCharArray();

           if(terminal == '\u0000')
           {
           
               throw new RuntimeException("Por favor seleccione una variable de la lista disponible.");
           
           } 
            
            if(nuevaNoTerminal.length > 1)
            {

                throw new RuntimeException("Las variables terminales deben ser de solo 1 caracter.");

            }

            gramaticas.modificarNoTerminal(this.cboGramatica.getValue(), terminal, nuevaNoTerminal[0]);
            
            this.mostrarAlerta("Cambios realizados con exito.");
            
            this.actualizarTablas(this.cboGramatica.getValue());
            actualizarCBOGramaticas(this.cboInicial, 2);
            
            sincronizarVariableInicial(this.cboGramatica.getValue());
            
            this.txtModificarNoTerminal.clear();
            
            this.btnPalabras.setDisable(true);
            this.txtNumeroPalabras.setDisable(true);
            this.txtNiveles.setDisable(true);
            
        }catch(Exception e)
        {
        
            this.mostrarAlertaError(e.getMessage());
        
        }
        
    }

    @FXML
    void modificarProduccion(ActionEvent event) {

        try
        {
      
            gramaticaSeleccionada();
            
            if(this.cboProducciones.getValue() == null)
            {
            
                throw new RuntimeException("No hay variable no terminal selecciona, por favor seleccione una de las ingresadas.");
            
            }
            
            if(this.producciones.isEmpty())
            {
            
                throw new RuntimeException("No hay producciones en la no terminal ingresada para modificar.");
            
            }
            
            String temp = txtModificarProduccion.getText();
            
            if(temp == null || temp.isBlank() || temp.isEmpty())
            {
            
                throw new RuntimeException("Entrada vacia.");
            
            }
            
            char noTerminal = this.cboProducciones.getValue();
            String produccion = this.ListProducciones.getSelectionModel().selectedItemProperty().getValue();
            String nuevaProduccion = txtModificarProduccion.getText();

            gramaticas.modificarProduccion(this.cboGramatica.getValue(), noTerminal, produccion, nuevaProduccion);
            
            this.mostrarAlerta("Cambios realizados con exito.");
            
            this.actualizarTablas(this.cboGramatica.getValue());
            this.txtModificarProduccion.clear();
            
            this.btnPalabras.setDisable(true);
            this.txtNumeroPalabras.setDisable(true);
            this.txtNiveles.setDisable(true);
            
        }catch(Exception e)
        {
        
            this.mostrarAlertaError(e.getMessage());
        
        }
        
    }

    @FXML
    void modificarTerminal(ActionEvent event) {

        try
        {
        
            gramaticaSeleccionada();
            
            if(this.variablesTerminales.isEmpty())
            {
            
                throw new RuntimeException("No hay terminales ingresadas para modificar.");
            
            }
            
            String temp = txtModificarTerminal.getText();
            
            if(temp == null || temp.isBlank() || temp.isEmpty())
            {
            
                throw new RuntimeException("Entrada vacia.");
            
            }
            
            char terminal = this.ListTerminales.getSelectionModel().selectedItemProperty().getValue();
            char nuevaTerminal [] = txtModificarTerminal.getText().toCharArray();

            if(terminal == '\0')
            {

                throw new RuntimeException("Por favor seleccione una variable de la lista disponible.");

            } 
            
            if(nuevaTerminal.length > 1)
            {

                throw new RuntimeException("Las variables terminales deben ser de solo 1 caracter.");

            }

            gramaticas.modificarTerminal(this.cboGramatica.getValue(), terminal, nuevaTerminal[0]);
            
            this.mostrarAlerta("Cambios realizados con exito.");
            
            this.actualizarTablas(this.cboGramatica.getValue());
            this.txtModificarTerminal.clear();
            
            this.btnPalabras.setDisable(true);
            this.txtNumeroPalabras.setDisable(true);
            this.txtNiveles.setDisable(true);
            
        }catch(Exception e)
        {
        
            this.mostrarAlertaError(e.getMessage());
        
        }
        
    }

    @FXML
    void seleccionarGramatica(ActionEvent event) {

        Gramatica gramatica = this.cboGramatica.getValue();
        actualizarCBOGramaticas(cboInicial, 2);
        
        if(gramatica != null)
        {
        
            if (!this.cboProducciones.getItems().isEmpty()) {
                this.cboProducciones.getSelectionModel().selectFirst();
            }else
            {

                this.cboProducciones.setValue(null);

            }

            sincronizarVariableInicial(gramatica);

            actualizarTablas(gramatica);   
        
        }else
        {
        
            this.variablesTerminales.clear();
            this.variablesNoTerminales.clear();
            this.producciones.clear();
        
        }
        
        this.btnPalabras.setDisable(true);
        this.txtNumeroPalabras.setDisable(true);
        this.txtNiveles.setDisable(true);
        
    }
    
    private void sincronizarVariableInicial(Gramatica gramatica)
    {
    
            if(gramatica.getVariableInicial() == '\0')
            {

                this.cboInicial.setValue(null);
                
                this.btnCambiarInicial.setDisable(true);
                this.cboInicial.setDisable(false);
                this.btnDefinirInicial.setDisable(false);

            }else
            {

                this.cboInicial.setValue(gramatica.getVariableInicial());
                
                this.btnCambiarInicial.setDisable(false);
                this.cboInicial.setDisable(true);
                this.btnDefinirInicial.setDisable(true);

            }
    
    }
    
   private void actualizarTablas(Gramatica gramatica)
   {
       
       this.variablesTerminales.clear();
       this.variablesNoTerminales.clear();
   
       ListaCD<Character> terminales = gramatica.getVariablesTerminales();
       ListaCD<Character> noTerminales = gramatica.getVariablesNoTerminales();
       
       if(!terminales.isEmpty())
       {
       
            for(char c:terminales)
            {

                this.variablesTerminales.add(c);
                
            }   
            
            this.ListTerminales.setItems(this.variablesTerminales);
       
       }
       
      if(!noTerminales.isEmpty())
       {
       
            for(char c:noTerminales)
            {

                this.variablesNoTerminales.add(c);

            }


             this.ListNoTerminales.setItems(this.variablesNoTerminales);
       
       }
       
        cambiarProduccion(gramatica);
        
   }

    @FXML
    void seleccionarInicial(ActionEvent event) {

    }

    @FXML
    void seleccionarProduccion(ActionEvent event) {
        
        if(this.cboGramatica.getValue() != null)
        {
        
            cambiarProduccion(this.cboGramatica.getValue());
        
        }

    }
    
    private void cambiarProduccion(Gramatica gramatica)
    {
    
        this.producciones.clear();
        ListaCD<String> cadenaProducciones = gramatica.getProducciones().get(this.cboProducciones.getValue());
   
        if(cadenaProducciones != null)
        {
        
            for(String s:cadenaProducciones)
            {

                this.producciones.add(s);

            }

            this.ListProducciones.setItems(this.producciones);        
        
        }   
        
    }
    
    @FXML
    void ventanaDeInstrucciones(ActionEvent event) {

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Alerta");
        alert.setHeaderText(null);

        TextArea textArea = new TextArea("Bienvenido a nuestro generador de palabras a partir del lenguaje de una gramática!\n" + "\n" +
        "Por favor ingresa los datos solicitados en cada uno de los apartados para la utilización del programa, recuerda que debes seleccionar una gramática para asignarle sus respectivas variables, puedes agregar variables terminales, no terminales y producciones para cada una de las no terminales.\n" + "\n" +
        "Para ingresar una terminal puedes ingresarlas una por una o una cadena con distintas terminales separadas por comas (,) como en el ejemplo de la entrada.\n" + "\n" +
        "Para ingresar no terminales puedes ingresar una por una o una cadena con distintas no terminales separas por comas (,), como el ejemplo de la entrada\n" + "\n" +
        "Para ingresar una producción debes seleccionar previamente una variable no terminal a la cual asignarse, puedes ingresar cada producción una por una o una cadena de producciones separadas por una barra (/). Como en el ejemplo de la entrada.\n" + "\n" +
        "Cada uno de los elementos ingresados pueden verse en las listas de la parte derecha, cada entrada puede ser eliminada o modificada seleccionando la que se desea cambiar e ingresando la información y botón correspondiente.\n" + "\n" +
        "Finalmente debes seleccionar el botón para comprobar tus entradas, serán eliminadas las producciones y variables que sean consideradas inalcanzables e inútiles y finalmente puedes generar las palabras del lenguaje definiendo la cantidad de palabras que desea y el nivel de profundidad del árbol de derivación. ");
        
        textArea.setEditable(false);
        textArea.setWrapText(true);

        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane content = new GridPane();
        content.setMaxWidth(Double.MAX_VALUE);
        content.add(textArea, 0, 0);

        alert.getDialogPane().setContent(content);
        alert.showAndWait();
        
    }
    
    @FXML
    void cambiarInicial(ActionEvent event) {

        this.btnCambiarInicial.setDisable(true);
        this.cboInicial.setDisable(false);
        this.btnDefinirInicial.setDisable(false);       
        
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert ListNoTerminales != null : "fx:id=\"ListNoTerminales\" was not injected: check your FXML file 'ae.fxml'.";
        assert ListProducciones != null : "fx:id=\"ListProducciones\" was not injected: check your FXML file 'ae.fxml'.";
        assert ListTerminales != null : "fx:id=\"ListTerminales\" was not injected: check your FXML file 'ae.fxml'.";
        assert btnAgregarG != null : "fx:id=\"btnAgregarG\" was not injected: check your FXML file 'ae.fxml'.";
        assert btnAgregarProducciones != null : "fx:id=\"btnAgregarProducciones\" was not injected: check your FXML file 'ae.fxml'.";
        assert btnAgregarVnt != null : "fx:id=\"btnAgregarVnt\" was not injected: check your FXML file 'ae.fxml'.";
        assert btnAgregarVt != null : "fx:id=\"btnAgregarVt\" was not injected: check your FXML file 'ae.fxml'.";
        assert btnComprobar != null : "fx:id=\"btnComprobar\" was not injected: check your FXML file 'ae.fxml'.";
        assert btnDefinirInicial != null : "fx:id=\"btnDefinirInicial\" was not injected: check your FXML file 'ae.fxml'.";
        assert btnEliminarNoTerminal != null : "fx:id=\"btnEliminarNoTerminal\" was not injected: check your FXML file 'ae.fxml'.";
        assert btnEliminarProduccion != null : "fx:id=\"btnEliminarProduccion\" was not injected: check your FXML file 'ae.fxml'.";
        assert btnEliminarTerminal != null : "fx:id=\"btnEliminarTerminal\" was not injected: check your FXML file 'ae.fxml'.";
        assert btnLimpiar != null : "fx:id=\"btnLimpiar\" was not injected: check your FXML file 'ae.fxml'.";
        assert btnModificarNoTerminal != null : "fx:id=\"btnModificarNoTerminal\" was not injected: check your FXML file 'ae.fxml'.";
        assert btnModificarProduccion != null : "fx:id=\"btnModificarProduccion\" was not injected: check your FXML file 'ae.fxml'.";
        assert btnModificarTerminal != null : "fx:id=\"btnModificarTerminal\" was not injected: check your FXML file 'ae.fxml'.";
        assert btnPalabras != null : "fx:id=\"btnPalabras\" was not injected: check your FXML file 'ae.fxml'.";
        assert cboGramatica != null : "fx:id=\"cboGramatica\" was not injected: check your FXML file 'ae.fxml'.";
        assert cboInicial != null : "fx:id=\"cboInicial\" was not injected: check your FXML file 'ae.fxml'.";
        assert cboProducciones != null : "fx:id=\"cboProducciones\" was not injected: check your FXML file 'ae.fxml'.";
        assert eliminarG != null : "fx:id=\"eliminarG\" was not injected: check your FXML file 'ae.fxml'.";
        assert txtGramatica != null : "fx:id=\"txtGramatica\" was not injected: check your FXML file 'ae.fxml'.";
        assert txtModificarNoTerminal != null : "fx:id=\"txtModificarNoTerminal\" was not injected: check your FXML file 'ae.fxml'.";
        assert txtModificarProduccion != null : "fx:id=\"txtModificarProduccion\" was not injected: check your FXML file 'ae.fxml'.";
        assert txtModificarTerminal != null : "fx:id=\"txtModificarTerminal\" was not injected: check your FXML file 'ae.fxml'.";
        assert txtPalabras != null : "fx:id=\"txtPalabras\" was not injected: check your FXML file 'ae.fxml'.";
        assert txtProducciones != null : "fx:id=\"txtProducciones\" was not injected: check your FXML file 'ae.fxml'.";
        assert txtVariablesNoTerminales != null : "fx:id=\"txtVariablesNoTerminales\" was not injected: check your FXML file 'ae.fxml'.";
        assert txtVariablesTerminales != null : "fx:id=\"txtVariablesTerminales\" was not injected: check your FXML file 'ae.fxml'.";
        assert txtNumeroPalabras != null : "fx:id=\"txtNumeroPalabras\" was not injected: check your FXML file 'ViewGramatica.fxml'.";
        assert btnAyuda != null : "fx:id=\"btnAyuda\" was not injected: check your FXML file 'ViewGramatica.fxml'.";
        assert btnCambiarInicial != null : "fx:id=\"btnCambiarInicial\" was not injected: check your FXML file 'ViewGramatica.fxml'.";
        assert txtNiveles != null : "fx:id=\"txtNiveles\" was not injected: check your FXML file 'ViewGramatica.fxml'.";
        
        this.gramaticas = new GestorDeGramaticas();

        this.ListTerminales.setItems(variablesTerminales);
        this.ListNoTerminales.setItems(variablesNoTerminales);
        this.ListProducciones.setItems(producciones);
        
    }

}
