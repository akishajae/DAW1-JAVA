package Modelo;

public class Persona {

    //atributos
    private final String DNI;
    private String nombre;
    private char genero;
    private int edad;
    private String direccion;

    private Vehiculo vehiculo;

    //constructores
    public Persona(String DNI, String nombre, char genero, int edad,
            String direccion, Vehiculo vehiculo) {
        this.DNI = DNI;
        this.nombre = nombre;
        this.genero = genero;
        this.edad = edad;
        this.direccion = direccion;
        this.vehiculo = vehiculo;
    }

    //permite comparar las id's
    public Persona(String DNI) {
        this.DNI = DNI;
    }

//------------------------------------------------------------------------------
    //sobreescritura de metodos
    //comparar id's
    @Override
    public boolean equals(Object obj) {

        //si el objeto es de la clase persona
        if (obj instanceof Persona) {

            //convierte el objeto en Persona
            Persona p = (Persona) obj;

            //devuelve true si las id's son iguales
            return this.DNI.equalsIgnoreCase(p.getDNI());
        }

        return false;
    }

    //CAMBIAR PARA FICHEROS
    //muestra todos los datos
    @Override
    public String toString() {

        return DNI + "\t" + nombre + "\t" + genero
                + "\t" + edad + "\t" + direccion
                + "\t" + vehiculo;
    }

//------------------------------------------------------------------------------ 
    //getters + setters
    public String getDNI() {
        return DNI;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public char getGenero() {
        return genero;
    }

    public void setGenero(char genero) {
        this.genero = genero;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

}
