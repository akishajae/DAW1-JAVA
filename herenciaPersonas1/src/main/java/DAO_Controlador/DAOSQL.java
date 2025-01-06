package DAO_Controlador;

import Excepciones.DAO_Excep;
import Excepciones.Read_SQL_DAO_Excep;
import Excepciones.Write_SQL_DAO_Excep;
import Modelo.Cliente;
import Modelo.Empleado;
import Modelo.Persona;
import Modelo.Vehiculo;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DAOSQL {

    // BBDD 
    //Variables para la conexión segura contra el servidor (sin especificar DDBB)
    private final String JDBC_URL = "jdbc:mysql://localhost:3306";
    private final String JDBC_COMMU_OPT = "?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private final String JDBC_USER = "root";
//    private final String JDBC_PASSWORD = "P@ssw0rd";
    private final String JDBC_PASSWORD = "";

    //Especificamos la base de Datos
    private final String JDBC_DDBB = "jdbc_personas";
    private final String JDBC_TABLE_P = "personas";
    private final String JDBC_TABLE_V = "vehiculos";
    private final String JDBC_DDBB_TABLE_P = JDBC_DDBB + "." + JDBC_TABLE_P;
    private final String JDBC_DDBB_TABLE_V = JDBC_DDBB + "." + JDBC_TABLE_V;

    //Variables para las consultas SQL------------------------------------------
    private final String SQL_SELECT_ALL = "SELECT " + JDBC_DDBB_TABLE_P + ".*, "
            + JDBC_DDBB_TABLE_V + ".antiguedad FROM " + JDBC_DDBB_TABLE_P
            + " LEFT JOIN " + JDBC_DDBB_TABLE_V + " ON " + JDBC_DDBB_TABLE_P
            + ".vehiculo = " + JDBC_DDBB_TABLE_V + ".matricula;";

    //--------------------------------------------------------------------------
    private final String SQL_INSERT_P = "INSERT INTO " + JDBC_DDBB_TABLE_P
            + " (DNI, nombre, genero, edad, direccion, ID_EMPLEADO, sueldo, ID_CLIENTE, vip, fechaRegistro, vehiculo)"
            + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

    private final String SQL_SELECT_DNI = "SELECT * FROM " + JDBC_DDBB_TABLE_P + " WHERE (DNI = ";
    private final String SQL_SELECT_NOMBRE = "SELECT * FROM " + JDBC_DDBB_TABLE_P + " WHERE (nombre = ";

    private final String SQL_UPDATE_P = "UPDATE " + JDBC_DDBB_TABLE_P + " SET nombre = ?, genero = ?, edad = ?, direccion = ?, sueldo = ?, vip = ?, vehiculo = ? WHERE (DNI = ?);";

//    private final String SQL_UPDATE_NOMBRE = "UPDATE " + JDBC_DDBB_TABLE_P + " SET nombre = ? WHERE (DNI = ?);";
//    private final String SQL_UPDATE_GENERO = "UPDATE " + JDBC_DDBB_TABLE_P + " SET genero = ? WHERE (DNI = ?);";
//    private final String SQL_UPDATE_EDAD = "UPDATE " + JDBC_DDBB_TABLE_P + " SET edad = ? WHERE (DNI = ?);";
//    private final String SQL_UPDATE_DIRECCION = "UPDATE " + JDBC_DDBB_TABLE_P + " SET direccion = ? WHERE (DNI = ?);";
//    private final String SQL_UPDATE_SUELDO = "UPDATE " + JDBC_DDBB_TABLE_P + " SET sueldo = ? WHERE (DNI = ?);";
//    private final String SQL_UPDATE_VIP = "UPDATE " + JDBC_DDBB_TABLE_P + " SET vip = ? WHERE (DNI = ?);";
//    private final String SQL_UPDATE_VEHICULO = "UPDATE " + JDBC_DDBB_TABLE_P + " SET vehiculo = ? WHERE (DNI = ?);";
    private final String SQL_DELETE_P = "DELETE FROM " + JDBC_DDBB_TABLE_P + " WHERE (DNI = ";

    //--------------------------------------------------------------------------
    private final String SQL_SELECT_ALL_V = "SELECT * FROM " + JDBC_DDBB_TABLE_V + ";";
    private final String SQL_SELECT_MATRICULA = "SELECT * FROM " + JDBC_DDBB_TABLE_V + " WHERE (matricula = ";

    private final String SQL_INSERT_V = "INSERT INTO " + JDBC_DDBB_TABLE_V
            + " (matricula, antiguedad)"
            + " VALUES (?, ?);";

    private final String SQL_UPDATE_ANTIGUEDAD = "UPDATE " + JDBC_DDBB_TABLE_V + " SET antiguedad = ? WHERE (matricula = ?);";
    private final String SQL_DELETE_V = "DELETE FROM " + JDBC_DDBB_TABLE_V + " WHERE (matricula = ";

//------------------------------------------------------------------------------
    //CONECTAR, DESCONECTAR BBDD
    public Connection connect() throws DAO_Excep {
        Connection conn = null;
        try {
            //Esta línea no es necesaria, excepto en algunas aplicaciones WEB
            //En aplicaciones locales como esta no sería necesaria
            //Class.forName("com.mysql.cj.jdbc.Driver");
            //getConnection necesita la BBDD, el usuario y la contraseña
            conn = DriverManager.getConnection(JDBC_URL + JDBC_COMMU_OPT, JDBC_USER, JDBC_PASSWORD);
            createDB(conn);
            createTableVehiculos(conn);
            createTablePersonas(conn);
//        } catch (ClassNotFoundException ex) {
//           ex.printStackTrace(System.out);
        } catch (SQLException ex) {
            //ex.printStackTrace(System.out);
            System.out.println(ex.getMessage());
            throw new DAO_Excep("Can not connect or create database with tables: " + JDBC_DDBB);
        }
        return conn;
    }

    private void createDB(Connection conn) throws SQLException {
        //Sentencia SQL que crea la BBDD si no existe en el servidor
        String instruction = "create database if not exists " + JDBC_DDBB + ";";
        Statement stmt = null;
        stmt = conn.createStatement();
        //La clase Statemen nos permite ejecutar sentencias SQL
        stmt.executeUpdate(instruction);
        //Liberamos los recursos de la comunicación   
        stmt.close();
    }

    private void createTableVehiculos(Connection conn) throws SQLException {
        String query = "create table if not exists " + JDBC_DDBB + "." + JDBC_TABLE_V + "("
                + "matricula varchar(8) not null primary key, "
                + "antiguedad int not null);";
        Statement stmt = null;
        stmt = conn.createStatement();
        stmt.executeUpdate(query);
        //Liberamos los recursos de la comunicación   
        stmt.close();
    }

    private void createTablePersonas(Connection conn) throws SQLException {
        String query = "create table if not exists " + JDBC_DDBB + "." + JDBC_TABLE_P + "("
                + "DNI varchar(9) not null primary key, "
                + "nombre varchar(45) not null, "
                + "genero varchar(1) not null, "
                + "edad int not null, "
                + "direccion varchar(255) not null, "
                + "ID_EMPLEADO int, "
                + "sueldo double, "
                + "ID_CLIENTE int, "
                + "vip tinyint, "
                + "fechaRegistro date, "
                + "vehiculo varchar(8), "
                + "foreign key(vehiculo) references vehiculos(matricula));";
        Statement stmt = null;
        stmt = conn.createStatement();
        stmt.executeUpdate(query);
        //Liberamos los recursos de la comunicación   
        stmt.close();
    }

    public void disconnect(Connection conn) throws DAO_Excep {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                throw new DAO_Excep("Can not disconnect from database " + JDBC_DDBB);
            }
        }
    }

//------------------------------------------------------------------------------
    //READ
    public ArrayList<Persona> readAll_P() throws DAO_Excep {

        ArrayList<Persona> personas = new ArrayList<>();
        ArrayList<Vehiculo> vehiculos = new ArrayList<>();
        Connection conn = null;
        Statement instruction = null;
        ResultSet rs = null;

        try {
            conn = connect();
            //Creates a {@code Statement} object for sending SQL statements to db
            instruction = conn.createStatement();

            rs = instruction.executeQuery(SQL_SELECT_ALL);

            if (rs == null) {
                throw new Excepciones.DAO_Excep("No hay ninguna persona registrada.");
            }

            //Moves the cursor forward one row from its current position.
            while (rs.next()) {
                String DNI = rs.getString("DNI");
                String nombre = rs.getString("nombre");
                String getGenero = rs.getString("genero");
                char genero = getGenero.charAt(0);
                int edad = rs.getInt("edad");
                String direccion = rs.getString("direccion");

                String vehiculo = rs.getString("vehiculo");

                Vehiculo v = null;
                if (vehiculo != null) {
                    if (!(vehiculos.contains(new Vehiculo(vehiculo)))) {
                        int antiguedad = rs.getInt("antiguedad");

                        v = new Vehiculo(vehiculo, antiguedad);
                        vehiculos.add(v);
                    } else {

                        int pos = vehiculos.indexOf(new Vehiculo(vehiculo));
                        v = vehiculos.get(pos);
                    }
                }

                if (rs.getObject("ID_EMPLEADO") != null) {
                    int ID_EMPLEADO = rs.getInt("ID_EMPLEADO");
                    double sueldo = rs.getDouble("sueldo");

                    Empleado e = new Empleado(ID_EMPLEADO, sueldo, DNI, nombre, genero, edad, direccion, v);
                    personas.add(e);
                }

                if (rs.getObject("ID_CLIENTE") != null) {
                    int ID_CLIENTE = rs.getInt("ID_CLIENTE");
                    boolean vip = rs.getBoolean("vip");
                    Date getFechaRegistro = rs.getDate("fechaRegistro");
                    LocalDate fechaRegistro = getFechaRegistro.toLocalDate();

                    Cliente c = new Cliente(ID_CLIENTE, fechaRegistro, vip, DNI, nombre, genero, edad, direccion, v);
                    personas.add(c);
                }

            }
        } catch (SQLException ex) {
            //ex.printStackTrace(System.out);
            System.out.println(ex.getMessage());
            throw new Read_SQL_DAO_Excep("Can not read from database - readAll");
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (instruction != null) {
                    instruction.close();
                }
                disconnect(conn);
            } catch (SQLException ex) {
                //ex.printStackTrace(System.out);
                throw new Read_SQL_DAO_Excep("Can not read from database - readAll");
            }
        }

        return personas;
    }

    public ArrayList<Vehiculo> readAll_V() throws DAO_Excep {

        ArrayList<Persona> personas = new ArrayList<>();
        ArrayList<Vehiculo> vehiculos = new ArrayList<>();
        Connection conn = null;
        Statement instruction = null;
        ResultSet rs = null;
        Vehiculo v = null;

        try {
            conn = connect();
            //Creates a {@code Statement} object for sending SQL statements to db
            instruction = conn.createStatement();

            rs = instruction.executeQuery(SQL_SELECT_ALL_V);

            if (rs == null) {
                throw new Excepciones.DAO_Excep("No hay ningún vehículo registrado.");
            }

            //Moves the cursor forward one row from its current position.
            while (rs.next()) {
                String MATRICULA = rs.getString("matricula");
                int antiguedad = rs.getInt("antiguedad");
                
                v = new Vehiculo(MATRICULA, antiguedad);
                vehiculos.add(v);

            }
        } catch (SQLException ex) {
            //ex.printStackTrace(System.out);
            System.out.println(ex.getMessage());
            throw new Read_SQL_DAO_Excep("Can not read from database - readAll");
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (instruction != null) {
                    instruction.close();
                }
                disconnect(conn);
            } catch (SQLException ex) {
                //ex.printStackTrace(System.out);
                throw new Read_SQL_DAO_Excep("Can not read from database - readAll");
            }
        }

        return vehiculos;
    }

    //--------------------------------------------------------------------------
    //INSERT
    public int insertEmpleado(Empleado e) throws DAO_Excep {
        Connection conn = null;
        //La clase PreparedStatement también permite ejecutar sentencias SQL
        //pero con mayor flexibilidad
        PreparedStatement instruction = null;
        int registers = 0;

        try {
            conn = connect();
            instruction = conn.prepareStatement(SQL_INSERT_P);

            instruction.setString(1, e.getDNI());
            instruction.setString(2, e.getNombre());
            instruction.setString(3, String.valueOf(e.getGenero()));
            instruction.setInt(4, e.getEdad());
            instruction.setString(5, e.getDireccion());

            instruction.setInt(6, e.getID_EMPLEADO());
            instruction.setDouble(7, e.getSueldo());
            instruction.setObject(8, null);
            instruction.setObject(9, null);

            instruction.setObject(10, null);

            if (e.getVehiculo() == null) {
                instruction.setObject(11, null);
            } else {
                instruction.setString(11, e.getVehiculo().getMATRICULA());
            }

            registers = instruction.executeUpdate();
            System.out.println("Persona registrada.");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new Write_SQL_DAO_Excep("Can not write to database (DAO_COntroller.DAOSQL.insert)");
        } finally {
            try {
                instruction.close();
                disconnect(conn);
            } catch (SQLException ex) {
                //ex.printStackTrace(System.out);
                throw new Write_SQL_DAO_Excep("Can not close database write process (DAO_COntroller.DAOSQL.insert)");
            }
        }

        //Devolvemos la cantidad de registros afectados, en nuestro caso siempre uno
        return registers;
    }

    public int insertCliente(Cliente c) throws DAO_Excep {
        Connection conn = null;
        //La clase PreparedStatement también permite ejecutar sentencias SQL
        //pero con mayor flexibilidad
        PreparedStatement instruction = null;
        int registers = 0;

        try {
            conn = connect();
            instruction = conn.prepareStatement(SQL_INSERT_P);

            instruction.setString(1, c.getDNI());
            instruction.setString(2, c.getNombre());
            instruction.setString(3, String.valueOf(c.getGenero()));
            instruction.setInt(4, c.getEdad());
            instruction.setString(5, c.getDireccion());

            instruction.setObject(6, null);
            instruction.setObject(7, null);
            instruction.setInt(8, c.getID_CLIENTE());
            instruction.setBoolean(9, c.isVip());

            LocalDate getFechaRegistro = c.getFechaRegistro();
            Date fechaRegistro = Date.valueOf(getFechaRegistro);
            instruction.setDate(10, fechaRegistro);

            if (c.getVehiculo() == null) {
                instruction.setObject(11, null);
            } else {
                instruction.setString(11, c.getVehiculo().getMATRICULA());
            }

            registers = instruction.executeUpdate();
            System.out.println("Persona registrada.");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new Write_SQL_DAO_Excep("Can not write to database (DAO_COntroller.DAOSQL.insert)");
        } finally {
            try {
                instruction.close();
                disconnect(conn);
            } catch (SQLException ex) {
                //ex.printStackTrace(System.out);
                throw new Write_SQL_DAO_Excep("Can not close database write process (DAO_COntroller.DAOSQL.insert)");
            }
        }

        //Devolvemos la cantidad de registros afectados, en nuestro caso siempre uno
        return registers;
    }

    public int insertVehiculo(Vehiculo v) throws DAO_Excep {
        Connection conn = null;
        //La clase PreparedStatement también permite ejecutar sentencias SQL
        //pero con mayor flexibilidad
        PreparedStatement instruction = null;
        int registers = 0;

        try {
            conn = connect();
            instruction = conn.prepareStatement(SQL_INSERT_V);

            instruction.setString(1, v.getMATRICULA());
            instruction.setInt(2, v.getAntiguedad());

            registers = instruction.executeUpdate();
            System.out.println("Vehículo registrado.");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new Write_SQL_DAO_Excep("Can not write to database (DAO_COntroller.DAOSQL.insert)");
        } finally {
            try {
                instruction.close();
                disconnect(conn);
            } catch (SQLException ex) {
                //ex.printStackTrace(System.out);
                throw new Write_SQL_DAO_Excep("Can not close database write process (DAO_COntroller.DAOSQL.insert)");
            }
        }

        //Devolvemos la cantidad de registros afectados, en nuestro caso siempre uno
        return registers;
    }

    //--------------------------------------------------------------------------
    //DELETE
    public int deletePersona(Persona p) throws DAO_Excep {
        Connection conn = null;
        //La clase PreparedStatement también permite ejecutar sentencias SQL
        //pero con mayor flexibilidad
        PreparedStatement instruction = null;
        int registers = 0;

        try {
            conn = connect();
            String query = SQL_DELETE_P + "'" + p.getDNI() + "'" + ");";
            instruction = conn.prepareStatement(query);

            registers = instruction.executeUpdate();
            System.out.println("Persona dada de baja.");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new Write_SQL_DAO_Excep("Can not write to database (DAO_COntroller.DAOSQL.insert)");
        } finally {
            try {
                instruction.close();
                disconnect(conn);
            } catch (SQLException ex) {
                //ex.printStackTrace(System.out);
                throw new Write_SQL_DAO_Excep("Can not close database write process (DAO_COntroller.DAOSQL.insert)");
            }
        }
        //Devolvemos la cantidad de registros afectados, en nuestro caso siempre uno
        return registers;
    }

    public int deleteVehiculo(Vehiculo v) throws DAO_Excep {
        Connection conn = null;
        //La clase PreparedStatement también permite ejecutar sentencias SQL
        //pero con mayor flexibilidad
        PreparedStatement instruction = null;
        int registers = 0;

        try {
            conn = connect();
            String query = SQL_DELETE_V + "'" + v.getMATRICULA() + "'" + ");";
            instruction = conn.prepareStatement(query);

            registers = instruction.executeUpdate();
            System.out.println("Vehículo dado de baja.");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new Write_SQL_DAO_Excep("Can not write to database (DAO_COntroller.DAOSQL.insert)");
        } finally {
            try {
                instruction.close();
                disconnect(conn);
            } catch (SQLException ex) {
                //ex.printStackTrace(System.out);
                throw new Write_SQL_DAO_Excep("Can not close database write process (DAO_COntroller.DAOSQL.insert)");
            }
        }
        //Devolvemos la cantidad de registros afectados, en nuestro caso siempre uno
        return registers;
    }

    //--------------------------------------------------------------------------
    //UPDATE
    public int updateEmpleado(Empleado e) throws DAO_Excep {
        Connection conn = null;
        //La clase PreparedStatement también permite ejecutar sentencias SQL
        //pero con mayor flexibilidad
        PreparedStatement instruction = null;
        int registers = 0;

        try {
            conn = connect();
            instruction = conn.prepareStatement(SQL_UPDATE_P);

            instruction.setString(1, e.getNombre());
            instruction.setString(2, String.valueOf(e.getGenero()));
            instruction.setInt(3, e.getEdad());
            instruction.setString(4, e.getDireccion());

            instruction.setDouble(5, e.getSueldo());
            instruction.setObject(6, null);

            if (e.getVehiculo() == null) {
                instruction.setObject(7, null);
            } else {
                instruction.setString(7, e.getVehiculo().getMATRICULA());
            }

            instruction.setString(8, e.getDNI());

            registers = instruction.executeUpdate();
            System.out.println("Persona modificada.");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new Write_SQL_DAO_Excep("Can not write to database (DAO_COntroller.DAOSQL.insert)");
        } finally {
            try {
                instruction.close();
                disconnect(conn);
            } catch (SQLException ex) {
                //ex.printStackTrace(System.out);
                throw new Write_SQL_DAO_Excep("Can not close database write process (DAO_COntroller.DAOSQL.insert)");
            }
        }

        //Devolvemos la cantidad de registros afectados, en nuestro caso siempre uno
        return registers;
    }

    public int updateCliente(Cliente c) throws DAO_Excep {
        Connection conn = null;
        //La clase PreparedStatement también permite ejecutar sentencias SQL
        //pero con mayor flexibilidad
        PreparedStatement instruction = null;
        int registers = 0;

        try {
            conn = connect();
            instruction = conn.prepareStatement(SQL_UPDATE_P);

            instruction.setString(1, c.getNombre());
            instruction.setString(2, String.valueOf(c.getGenero()));
            instruction.setInt(3, c.getEdad());
            instruction.setString(4, c.getDireccion());

            instruction.setObject(5, null);
            instruction.setBoolean(6, c.isVip());

            if (c.getVehiculo() == null) {
                instruction.setObject(7, null);
            } else {
                instruction.setString(7, c.getVehiculo().getMATRICULA());
            }

            instruction.setString(8, c.getDNI());

            registers = instruction.executeUpdate();
            System.out.println("Persona modificada.");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new Write_SQL_DAO_Excep("Can not write to database (DAO_COntroller.DAOSQL.insert)");
        } finally {
            try {
                instruction.close();
                disconnect(conn);
            } catch (SQLException ex) {
                //ex.printStackTrace(System.out);
                throw new Write_SQL_DAO_Excep("Can not close database write process (DAO_COntroller.DAOSQL.insert)");
            }
        }

        //Devolvemos la cantidad de registros afectados, en nuestro caso siempre uno
        return registers;
    }

    public int updateVehiculo(Vehiculo v) throws DAO_Excep {
        Connection conn = null;
        //La clase PreparedStatement también permite ejecutar sentencias SQL
        //pero con mayor flexibilidad
        PreparedStatement instruction = null;
        int registers = 0;

        try {
            conn = connect();
            instruction = conn.prepareStatement(SQL_UPDATE_ANTIGUEDAD);

            if (v != null) {
                instruction.setInt(1, v.getAntiguedad());
                instruction.setString(2, v.getMATRICULA());
            } else {
                instruction.setObject(1, null);
                instruction.setObject(2, null);
            }

            registers = instruction.executeUpdate();
            System.out.println("Vehículo registrado.");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new Write_SQL_DAO_Excep("Can not write to database (DAO_COntroller.DAOSQL.insert)");
        } finally {
            try {
                instruction.close();
                disconnect(conn);
            } catch (SQLException ex) {
                //ex.printStackTrace(System.out);
                throw new Write_SQL_DAO_Excep("Can not close database write process (DAO_COntroller.DAOSQL.insert)");
            }
        }

        //Devolvemos la cantidad de registros afectados, en nuestro caso siempre uno
        return registers;
    }

}
