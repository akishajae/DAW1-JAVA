package MyAPI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;

public class EntradaDatos {
    
    /**
     * Método que pide al usuario que introduzca texto.
     * @return texto que introduce el usuario
     * @throws IOException 
     */
    public static String pedirTexto() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String texto = br.readLine();

        return texto;
    }

    /**
     * Método que pide al usuario que introduzca un texto en concreto, <br>
     * dentro de las opciones que se den en los parámetros de entrada. <br>
     * Y valida el dato; sigue pidiendo al usuario hasta que se introduzca <br>
     * un dato correcto.
     * @param texto opciones de texto que podrá usar el usuario
     * @return respuesta, el dato validado introducido por el usuario
     * @throws IOException 
     */
    public static String pedirTextoEspecifico(String... texto) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean error = true;
        String respuesta;

        do {
            respuesta = br.readLine();

            for (int i = 0; i < texto.length; i++) {
                //compara la respuesta con las opciones
                if (respuesta.equalsIgnoreCase(texto[i])) {
                    error = false;
//                } else {
//                    System.out.print("Incorrecto. Introduzca alguno de estos datos ");
//                    
//                    //muestra las opciones que hay
//                    for (int j = 0; j < texto.length; j++) {
//                        System.out.print("[" + texto[j] + "]");
//                        
//                        //dos puntos al final de la frase
//                        if (texto[j].equals(texto[texto.length - 1])) {
//                            System.out.print(": ");
//                        }
//                    }
//                    break;
                }
            }

            if (error) {
                System.out.print("Incorrecto. Introduzca alguno de estos datos ");
                for (int i = 0; i < texto.length; i++) {
                    //muestra las opciones que hay
                    System.out.print("[" + texto[i] + "]");

                    //dos puntos al final de la frase
                    if (texto[i].equals(texto[texto.length - 1])) {
                        System.out.print(": ");
                    }
                }
            }
        } while (error);

        return respuesta;
    }
    
    //--------------------------------------------------------------------------

    /**
     * Método que pide al usuario que introduzca un número entero entre un número mínimo y máximo. <br>
     * Y valida el dato, sigue pidiendo al usuario hasta que se introduzca <br>
     * un dato correcto.
     * @param min número mínimo del rango
     * @param max número máximo del rango
     * @return num, número que se encuentra dentro del rango entre el número mínimo y máximo
     * @throws IOException 
     */
    public static int pedirIntEntre(int min, int max) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = 0;
        boolean error = true;

        do {
            try {
                System.out.print("Introduzca un número entre [" + min + "-" + max + "]: ");
                num = Integer.parseInt(br.readLine());

                if (num >= min && num <= max) {
                    error = false;
                }
            } catch (InputMismatchException | NumberFormatException e) {
                System.out.print("Incorrecto. ");
            }
        } while (error);

        return num;
    }

    /**
     * Método que pide al usuario que introduzca un número double entre un número mínimo y máximo. <br>
     * Y valida el dato, sigue pidiendo al usuario hasta que se introduzca <br>
     * un dato correcto.
     * @param min número mínimo del rango
     * @param max número máximo del rango
     * @return num, número que se encuentra dentro del rango entre el número mínimo y máximo
     * @throws IOException 
     */
    public static double pedirDoubleEntre(double min, double max) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        double num = 0;
        boolean error = true;

        do {
            try {
                System.out.print("Introduzca un número entre [" + min + "-" + max + "]: ");
                num = Integer.parseInt(br.readLine());

                if (num >= min && num <= max) {
                    error = false;
                }
            } catch (InputMismatchException | NumberFormatException e) {
                System.out.print("Incorrecto. ");
            }
        } while (error);

        return num;
    }
    
    //--------------------------------------------------------------------------

    /**
     * Método que pide al usuario que introduzca un número entero. <br>
     * Y valida el dato, sigue pidiendo al usuario hasta que se introduzca <br>
     * un dato correcto.
     * @return num, número entero y no texto
     * @throws IOException 
     */
    public static int pedirInt() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = 0;
        boolean error = true;

        do {
            try {
                num = Integer.parseInt(br.readLine());
                error = false;
            } catch (InputMismatchException | NumberFormatException e) {
                System.out.print("Incorrecto. Introduzca un número: ");
            }
        } while (error);

        return num;
    }

    /**
     * Método que pide al usuario que introduzca un número double. <br>
     * Y valida el dato, sigue pidiendo al usuario hasta que se introduzca <br>
     * un dato correcto.
     * @return num, número double y no texto
     * @throws IOException 
     */
    public static double pedirDouble() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        double num = 0.0;
        boolean error = true;

        do {
            try {
                num = Double.parseDouble(br.readLine());
                error = false;
            } catch (InputMismatchException | NumberFormatException e) {
                System.out.print("Incorrecto. Introduzca un número: ");
            }
        } while (error);

        return num;
    }
    
    //--------------------------------------------------------------------------

    /**
     * Método que pide al usuario que introduzca un número entero que sea mayor o igual <br>
     * que el número mínimo.
     * Y valida el dato, sigue pidiendo al usuario hasta que se introduzca <br>
     * un dato correcto.
     * @param min número mínimo que se puede introducir
     * @return num, número entero mayor o igual que el parámetro 
     * @throws IOException 
     */
    public static int pedirIntMin(int min) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = -1;
        boolean error = true;

        do {
            try {
                num = Integer.parseInt(br.readLine());

                if (num >= min) {
                    error = false;
                } else {
                    System.out.print("Incorrecto. Introduzca un número mayor o igual que " + (min) + ": ");
                }

            } catch (InputMismatchException | NumberFormatException e) {
                System.out.print("Incorrecto. Introduzca un número: ");
            }
        } while (error);

        return num;
    }
    
    public static double pedirDoubleMin(double min) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        double num = -1;
        boolean error = true;

        do {
            try {
                num = Double.parseDouble(br.readLine());

                if (num >= min) {
                    error = false;
                } else {
                    System.out.print("Incorrecto. Introduzca un número mayor o igual que " + (min) + ": ");
                }

            } catch (InputMismatchException | NumberFormatException e) {
                System.out.print("Incorrecto. Introduzca un número: ");
            }
        } while (error);

        return num;
    }
    
}
