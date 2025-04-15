package views;

import data.Persistencia;
import domain.*;

import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.List;

public class Controlador {
    public static TipoAlimentacion[] getTiposAlimentacion(){
        return  TipoAlimentacion.values();
    }
    public static ArrayList<Especie> getEspecies(){
        return Persistencia.getEspecies();
    }
    public static ArrayList<Sector> getSectores(){
        return Persistencia.getSectores();
    }
    
    public static ArrayList<AnimalViewModel> getAnimales(){
        ArrayList<AnimalViewModel> animales = new ArrayList<>();
        for(Mamifero animal : Persistencia.getAnimales()){
            animales.add(new AnimalViewModel(animal));
        }
        return animales;
    }
    
    public static ComidaViewModel  calcularComida(){
        double totalCarnivoros = Persistencia.getTotalComida(TipoAlimentacion.CARNIVORO);
        double totalHerbivoros = Persistencia.getTotalComida(TipoAlimentacion.HERBIVORO);
        return new ComidaViewModel(totalCarnivoros, totalHerbivoros);
    }
    
    public static ArrayList<Pais> getPais(){
        return Persistencia.getPaises();
    }
    
    public static void CargarAnimales(Especie especie, Pais pais, Sector sector, String edad,
            String peso, String valorFijo){
        
        try {
            if(especie.getTipoAlimentacion().esCarnivoro()){
                Mamifero carnivoro = new Carnivoro(Integer.parseInt(edad), Integer.parseInt(peso), especie, sector, pais);
                Persistencia.CargarAnimales(carnivoro);
            } else if(especie.getTipoAlimentacion().esHerbivoro()){
                Mamifero Herbivoro = new Herbivoro(Integer.parseInt(edad), Integer.parseInt(peso), especie, sector, Double.parseDouble(valorFijo), pais);
                Persistencia.CargarAnimales(Herbivoro);
            }
        } catch (Exception e) {
        }
        
    }
    
    
    public static void ControladorGuardarDatos(String especie, String pais, String sector, String edad, String peso, String valorFijo){
        Especie especieSeleccionada = null;
        Pais paisSeleccionado = null;
        Sector sectorSeleccionado = null;
        
        ArrayList<Especie> especies = Controlador.getEspecies();
        for(Especie especieFor : especies){
            if(especieFor.getNombre().equals(especie)){
                especieSeleccionada = especieFor;
            }
        }
        ArrayList<Pais> paises = Controlador.getPais();
        for(Pais paisFor : paises){
            if(paisFor.getNombre().equals(pais)){
                paisSeleccionado = paisFor;
            }
        }
        
        ArrayList<Sector> sectores = Controlador.getSectores();
        for(Sector sectorFor : sectores){
            if(sectorFor.getNumero() == Integer.parseInt(sector)){
                sectorSeleccionado = sectorFor;
            }
        }
        
        Controlador.CargarAnimales(especieSeleccionada, paisSeleccionado, sectorSeleccionado, edad, peso, valorFijo);
    }
                
                
                
                
}           
