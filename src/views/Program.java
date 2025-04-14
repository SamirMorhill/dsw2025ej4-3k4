package views;

import data.Persistencia;
import domain.TipoAlimentacion;

import javax.swing.*;
import java.util.InvalidPropertiesFormatException;

public class Program {

    public static void main(String[] args) throws IllegalArgumentException, InvalidPropertiesFormatException {
        Persistencia.inicializar();
        
        new MenuViewModel().setVisible(true);
        
        CargarAnimalViewModel cargarAnimalVista = new CargarAnimalViewModel();
        cargarAnimalVista.setVisible(false);
        
        ListarAnimalesView view = new ListarAnimalesView();
        view.setVisible(false);
        
    }
}
