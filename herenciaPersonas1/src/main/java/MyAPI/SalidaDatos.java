package MyAPI;

public class SalidaDatos {
    
    public static void mostrarMenu(String... option) {
        System.out.println("##########" + option[0] + "##########");
        if(option.length >=2){
            for (int i=1; i < option.length; i++) {
                System.out.println(option[i]);
            }
        }
    }
    
}
