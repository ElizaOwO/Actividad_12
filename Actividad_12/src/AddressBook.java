import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class AddressBook {

    public static Map<String,String> agenda= new HashMap<>();

    //Método de lectura
    //Llenar el HashMap con los datos del archivo txt
    public static void load (){
        String linea="0";
        String[] palabrass = null;
        try {
            FileReader lectura = new FileReader("C:\\Users\\nancy\\Downloads\\s.txt");
            BufferedReader buffer_lectura = new BufferedReader(lectura);

            while ((linea = buffer_lectura.readLine())!=null) {

                //Quitar caracteres para solo obtener key y value
                linea = linea.replace("{", "");
                linea = linea.replace("}", "");
                linea = linea.replace(" ", "");

                //Dividri valores para pasarlos
                palabrass=linea.split(",");

                agenda.put(palabrass[0], palabrass[1]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //Método de escritura
    //guardar los cambios en el archivo txt
    public static void save(){
        try {
            PrintWriter writer = new PrintWriter("C:\\Users\\nancy\\Downloads\\s.txt", "UTF-8");

            for (Map.Entry<String, String> entry : agenda.entrySet()) {
                writer.println("{" + entry.getKey() + "} , {" + entry.getValue() + "}");
            }

            writer.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
