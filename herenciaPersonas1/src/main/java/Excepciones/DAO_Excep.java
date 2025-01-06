package Excepciones;

public class DAO_Excep extends Exception {

    /**
     * Creates a new instance of <code>ExcepcionesAccesoDatos</code> without
     * detail message.
     */
    public DAO_Excep() {
    }

    /**
     * Constructs an instance of <code>ExcepcionesAccesoDatos</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public DAO_Excep(String msg) {
        super(msg);
    }
}
