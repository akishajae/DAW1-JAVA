package Modelo;

import java.time.LocalDate;

public class Cliente extends Persona {

    //atributos
    private final int ID_CLIENTE;
    private LocalDate fechaRegistro;
    private boolean vip;

    //constructores
    public Cliente(int ID_CLIENTE, LocalDate fechaRegistro, boolean vip, String DNI, String nombre, char genero, int edad, String direccion, Vehiculo vehiculo) {
        super(DNI, nombre, genero, edad, direccion, vehiculo);
        this.ID_CLIENTE = ID_CLIENTE;
        this.fechaRegistro = fechaRegistro;
        this.vip = vip;
    }

    public Cliente(int ID_CLIENTE, String DNI) {
        super(DNI);
        this.ID_CLIENTE = ID_CLIENTE;
    }

//------------------------------------------------------------------------------
    //sobreescritura de metodos
    @Override
    public boolean equals(Object obj) {
        
        //si el objeto es de la clase Cliente
        if (obj instanceof Cliente) {

            //convierte el objeto en Cliente
            Cliente c = (Cliente) obj;
            
            //devuelve true si las id's son iguales
            return this.ID_CLIENTE == c.getID_CLIENTE();
        }
        
        return false;
    }
    
    //CAMBIAR PARA FICHEROS
    //muestra los datos de la clase
    @Override
    public String toString() {

        return "CLIENTE" + "\t" + super.toString() + "\t" + ID_CLIENTE + "\t" + fechaRegistro 
                + "\t" + vip;
    }

//------------------------------------------------------------------------------
    //getters + setters
    public int getID_CLIENTE() {
        return ID_CLIENTE;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public boolean isVip() {
        return vip;
    }

    public void setVip(boolean vip) {
        this.vip = vip;
    }

}
