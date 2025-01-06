package Modelo;

public class Empleado extends Persona {
    
    //atributos
    
    private final int ID_EMPLEADO;
    private double sueldo;
    
    //constructores
    
    public Empleado(int ID_EMPLEADO, double sueldo, String DNI, String nombre, char genero, int edad, String direccion, Vehiculo vehiculo) {
        super(DNI, nombre, genero, edad, direccion, vehiculo);
        this.ID_EMPLEADO = ID_EMPLEADO;
        this.sueldo = sueldo;
    }

    public Empleado(int ID_EMPLEADO, String DNI) {
        super(DNI);
        this.ID_EMPLEADO = ID_EMPLEADO;
    }
    
    
    
    
//------------------------------------------------------------------------------
    
    //sobreescritura de metodos

    //comparar id's
    @Override
    public boolean equals(Object obj) {
        
        //si el objeto es de la clase Empleado
        if (obj instanceof Empleado) {

            //convierte el objeto en Empleado
            Empleado e = (Empleado) obj;
            
            //devuelve true si las id's son iguales
            return this.ID_EMPLEADO == e.getID_EMPLEADO();
        }

        return false;
    } 
        
    //muestra los datos de la clase
    @Override
    public String toString() {
        return "EMPLEADO" + "\t" + super.toString() + "\t" + ID_EMPLEADO + "\t" + sueldo;
    }
    
    
//------------------------------------------------------------------------------
    
    //getters + setters
    
    public int getID_EMPLEADO() {
        return ID_EMPLEADO;
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }
    
    
    
    
}
