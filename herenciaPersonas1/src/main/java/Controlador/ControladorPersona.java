package Controlador;

import DAO_Controlador.DAOSQL;
import Excepciones.*;
import Modelo.Cliente;
import Modelo.Empleado;
import Modelo.Ficheros;
import Modelo.Persona;
import Modelo.Vehiculo;
import static MyAPI.EntradaDatos.*;
import static MyAPI.SalidaDatos.mostrarMenu;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControladorPersona {

    //personas
//    public static ArrayList<Persona> personas = new ArrayList<>();
//
//    //vehiculos
//    public static ArrayList<Vehiculo> vehiculos = new ArrayList<>();
//------------------------------------------------------------------------------
    public static void altaEmpleado(Empleado e) throws excepcionPersona, excepcionEmpleado, DAO_Excep {
        DAOSQL sql = new DAOSQL();
        ArrayList<Persona> personas = sql.readAll_P();

        if (personas.contains(new Persona(e.getDNI()))) {
            throw new excepcionPersona("Ya existe una persona con ese DNI.");
        } else {
            if (personas.contains(e)) {
                throw new excepcionEmpleado("Ya existe un empleado con esa ID.");
            } else {
                sql.insertEmpleado(e);

            }
        }

    }

    public static void altaCliente(Cliente c) throws excepcionPersona, excepcionCliente, DAO_Excep {
        DAOSQL sql = new DAOSQL();
        ArrayList<Persona> personas = sql.readAll_P();

        try {
            personas = sql.readAll_P();
        } catch (DAO_Excep ex) {
            Logger.getLogger(ControladorPersona.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (personas.contains(new Persona(c.getDNI()))) {
            throw new excepcionPersona("Ya existe una persona con ese DNI.");
        } else {
            if (personas.contains(c)) {
                throw new excepcionCliente("Ya existe un cliente con esa ID.");
            } else {
                sql.insertCliente(c);

            }
        }
    }

    public static void altaVehiculo(Vehiculo v) throws excepcionVehiculo, DAO_Excep {
        DAOSQL sql = new DAOSQL();
        ArrayList<Vehiculo> vehiculos = sql.readAll_V();

        if (vehiculos.contains(v)) {
            throw new excepcionVehiculo("Ya hay un vehículo registrado con esa matrícula. Se le ha vinculado ese vehículo.");
        } else {
            sql.insertVehiculo(v);
        }
    }

//------------------------------------------------------------------------------
    public static Persona getPersonaByDNI(String DNI) throws excepcionPersona, DAO_Excep {
        DAOSQL sql = new DAOSQL();
        ArrayList<Persona> personas = sql.readAll_P();
        Persona p;

        if (!personas.contains(new Persona(DNI))) {
            throw new excepcionPersona("No hay ninguna persona registrada con ese DNI.");
        }

        int pos = personas.indexOf(new Persona(DNI));
        p = personas.get(pos);
        return p;
    }

    public static Persona getPersonaByNombre(String nombre) throws excepcionPersona, DAO_Excep {
        DAOSQL sql = new DAOSQL();
        ArrayList<Persona> personas = sql.readAll_P();
        Persona p;

        String getNombre = "";

        for (int i = 0; i < personas.size(); i++) {
            Persona getPersona = personas.get(i);
            getNombre = getPersona.getNombre();
            if (getNombre.equalsIgnoreCase(nombre)) {
                int pos = personas.indexOf(getPersona);

                p = personas.get(pos);
                return p;
            }
        }

        return null;

    }
//------------------------------------------------------------------------------

    public static ArrayList<Vehiculo> getVehiculos() throws DAO_Excep {
        DAOSQL sql = new DAOSQL();

        ArrayList<Vehiculo> vehiculos = sql.readAll_V();

        return vehiculos;
    }

//------------------------------------------------------------------------------
    public static void darBaja(Persona p) throws IOException, DAO_Excep {
        DAOSQL sql = new DAOSQL();

        int persona = sql.deletePersona(p);

    }
    
    public static void darBajaVehiculo (Vehiculo v) throws DAO_Excep {
        DAOSQL sql = new DAOSQL();
        
        int vehiculo = sql.deleteVehiculo(v);
    }
//------------------------------------------------------------------------------

    public static void modificarEmpleado(Empleado e) throws DAO_Excep {
        DAOSQL sql = new DAOSQL();
        
        int personas = sql.updateEmpleado(e);
    }
    
    public static void modificarCliente(Cliente c) throws DAO_Excep {
        DAOSQL sql = new DAOSQL();

        int personas = sql.updateCliente(c);
    }
    
    public static Vehiculo modificarVehiculo(Vehiculo v) throws DAO_Excep {
        DAOSQL sql = new DAOSQL();

        int vehiculos = sql.updateVehiculo(v);
        
        return v;
    }

//------------------------------------------------------------------------------
    public static String validarDNI(String DNI) throws excepcionPersona, DAO_Excep {
        DAOSQL sql = new DAOSQL();
        ArrayList<Persona> personas = sql.readAll_P();
        ArrayList<Vehiculo> vehiculos = sql.readAll_V();

        String numeroString;
        int numeroInt;
        char letras[] = {'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'};

        //comprueba si el DNI tiene 9 caracteres
        if (DNI.length() == 9) {

            //guarda los números en un String
            numeroString = DNI.substring(0, 8);

            try {

                //convierte los números del String en int
                numeroInt = Integer.parseInt(numeroString);

                //comprueba si el último caracter es una letra válida
                if (DNI.charAt(8) == letras[numeroInt % 23]) {

                    if (personas.contains(new Persona(DNI))) {
                        throw new Excepciones.excepcionPersona("Ya existe una persona con ese DNI.");
                    } else {
                        return DNI;
                    }
                } else {
                    throw new Excepciones.excepcionPersona("""
                                           DNI inválido. La letra no es la que corresponde.""");
                }

            } catch (NumberFormatException ex) {
                throw new Excepciones.excepcionPersona("DNI inválido. Formato [12345678A].");
            }
        } else {
            throw new Excepciones.excepcionPersona("DNI inválido. Formato [12345678A].");
        }
    }

    public static String validarMatricula(String MATRICULA) throws excepcionVehiculo, DAO_Excep {
        DAOSQL sql = new DAOSQL();
        ArrayList<Vehiculo> vehiculos = sql.readAll_V();

        if (MATRICULA.matches("[0-9]{4}[\s][A-Za-z]{3}")) {
            if (vehiculos.contains(new Vehiculo(MATRICULA))) {
                throw new Excepciones.excepcionVehiculo("Ya existe un vehículo con esa matrícula. "
                        + "Se le ha vinculado ese vehículo.");
            } else {
                System.out.println("Mat valida");
                System.out.println(MATRICULA);
                return MATRICULA;
            }
        } else if (MATRICULA.matches("[0-9]{4}[\s][A-Za-z]{3}") && !(vehiculos.contains(new Vehiculo(MATRICULA)))) {
            throw new Excepciones.excepcionVehiculo("""
                                                    Ya hay un veh\u00edculo con esa matr\u00edcula. 
                                                    A\u00f1ada la matr\u00edcula de un veh\u00edculo nuevo.""");
        } else {
            throw new Excepciones.excepcionVehiculo("Matrícula inválida. Formato [1234 ABC].");
        }

    }

    //--------------------------------------------------------------------------
    public static ArrayList<Persona> getPersonas() throws DAO_Excep {
        DAOSQL sql = new DAOSQL();
        ArrayList<Persona> personas = sql.readAll_P();

        return personas;
    }

}
