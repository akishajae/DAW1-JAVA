package Modelo;

//import static Controlador.ControladorPersona.personas;
//import static Controlador.ControladorPersona.vehiculos;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class Ficheros {

//    public static void escrituraFichero() {
//        //accede al archivo para poder escribir
//        FileWriter outputStream = null;
//
//        //permite al file writer escribir
//        //su método write permite sobreescribir lo que hay dentro del archivo
//        BufferedWriter out = null;
//
//        //para comprobar que los archivos se han leído o hay algún error con ellos
//        try {
//            //evaluar la opcion append del constructor
//            outputStream = new FileWriter("Archivo.txt");
//            out = new BufferedWriter(outputStream);
//
//            //Introduce datos de dos usuarios
//            for (int i = 0; i < personas.size(); i++) {
//                out.write(personas.get(i).toString() + "\n");
//            }
//
//        } catch (java.io.FileNotFoundException ex) {
//            System.out.println("No existe el archivo para ser leido.");
//        } catch (java.io.IOException ex) {
//            System.out.println("No se puede acceder al archivo.");
//        } finally {
//            if (out != null) {
//                try {
//                    out.close();
//                } catch (IOException ex) {
//                    System.out.println("No se puede acceder al archivo.");
//                }
//            }
//        }
//    }
//
//    public static void lecturaFichero() {
//        FileReader inputStream = null;
//        BufferedReader in = null;
//
//        try {
//            inputStream = new FileReader("Archivo.txt");
//            in = new BufferedReader(inputStream);
//
//            //función readLine devuelve el string de una línea
//            String linea = in.readLine();
//
//            //va leyendo las líneas hasta que llegue al final del texto
//            while (linea != null) {
//
//                //comprueba si está vacía la línea
//                if (linea.isEmpty()) {
//                    linea = in.readLine();
//                    continue;
//                }
//
//                System.out.println(linea);
//
//                //para separar cada dato
//                String[] atributos = linea.split("\t");
//
//                //persona
//                String DNI = atributos[1];
//                String nombre = atributos[2];
//                char genero = atributos[3].charAt(0);
//                int edad = Integer.parseInt(atributos[4]);
//                String direccion = atributos[5];
//
//                //vehículo
//                Vehiculo vehiculo;
//
//                if (!atributos[6].equals("null")) {
//
//                    String MATRICULA = atributos[6];
//                    int antiguedad = Integer.parseInt(atributos[7]);
//
//                    if (vehiculos.contains(new Vehiculo(MATRICULA))) {
//                        vehiculo = vehiculos.get(vehiculos.indexOf(new Vehiculo(MATRICULA)));
//                    } else {
//                        vehiculo = new Vehiculo(MATRICULA, antiguedad);
//                        vehiculos.add(vehiculo);
//                    }
//                } else {
//                    vehiculo = null;
//                }
//
//                if (atributos[0].equals("EMPLEADO")) {
//
//                    //empleado
//                    if (!atributos[6].equals("null")) {
//                        int ID_EMPLEADO = Integer.parseInt(atributos[8]);
//                        double sueldo = Double.parseDouble(atributos[9]);
//
//                        personas.add(new Empleado(ID_EMPLEADO, sueldo, DNI, nombre, genero, edad, direccion, vehiculo));
//                    } else {
//                        int ID_EMPLEADO = Integer.parseInt(atributos[7]);
//                        double sueldo = Double.parseDouble(atributos[8]);
//
//                        personas.add(new Empleado(ID_EMPLEADO, sueldo, DNI, nombre, genero, edad, direccion, vehiculo));
//                    }
//
//                } else if (atributos[0].equals("CLIENTE")) {
//
//                    //cliente
//                    if (!atributos[6].equals("null")) {
//                        int ID_CLIENTE = Integer.parseInt(atributos[8]);
//                        LocalDate fechaRegistro = LocalDate.parse(atributos[9]);
//                        boolean vip = Boolean.parseBoolean(atributos[10]);
//
//                        personas.add(new Cliente(ID_CLIENTE, fechaRegistro, vip, DNI, nombre, genero, edad, direccion, vehiculo));
//                    } else {
//                        int ID_CLIENTE = Integer.parseInt(atributos[7]);
//                        LocalDate fechaRegistro = LocalDate.parse(atributos[8]);
//                        boolean vip = Boolean.parseBoolean(atributos[9]);
//
//                        personas.add(new Cliente(ID_CLIENTE, fechaRegistro, vip, DNI, nombre, genero, edad, direccion, vehiculo));
//                    }
//                }
//
//                //para seguir leyendo las 
//                linea = in.readLine();
//            }
//        } catch (java.io.IOException ex) {
//            System.out.println("No se puede acceder al archivo.");
//        } finally {
//            if (in != null) {
//                try {
//                    in.close();
//                } catch (IOException ex) {
//                    System.out.println("No se puede acceder al archivo.");
//                }
//            }
//        }
//
//    }
}
    
