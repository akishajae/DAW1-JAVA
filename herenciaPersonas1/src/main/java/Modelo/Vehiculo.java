package Modelo;

public class Vehiculo {
    
    //atributos
    private final String MATRICULA;
    private int antiguedad;
    
    //constructores

    public Vehiculo(String MATRICULA, int antiguedad) {
        this.MATRICULA = MATRICULA;
        this.antiguedad = antiguedad;
    }

    public Vehiculo(String MATRICULA) {
        this.MATRICULA = MATRICULA;
    }
    
//------------------------------------------------------------------------------

    //sobreescritura de métodos

    //comparar id's
    @Override
    public boolean equals(Object obj) {
      
        if (obj instanceof Vehiculo) {
            
            Vehiculo v = (Vehiculo) obj;
            
            return this.MATRICULA.equalsIgnoreCase(v.getMATRICULA());
        }
        
        return false;
        
    }
    
    //mostrar los datos
    @Override
    public String toString() {
        return MATRICULA + "\t" + antiguedad; 
    }
    
    
    
//------------------------------------------------------------------------------
    
    //getters + setters

    public String getMATRICULA() {
        return MATRICULA;
    }
    

    public int getAntiguedad() {
        return antiguedad;
    }

    public void setAntiguedad(int antiguedad) {
        this.antiguedad = antiguedad;
    }
    
}
