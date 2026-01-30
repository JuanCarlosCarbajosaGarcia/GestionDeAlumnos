import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class GestorAlumnos {
    public static void main(String[] args) {
        Map<String, Double> Notas = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("Alumnos.txt"))){
            String linea;

            while ((linea = reader.readLine()) != null){
                String[]data = linea.split(";");

                if(data.length==2){
                    String nombre=data[0];
                    Double nota=Double.parseDouble(data[1]);
                }
            }

        }catch (Exception e){
            System.out.println("error al leer el archivo: " + e.getMessage());
        }

        System.out.println("Notas: " + Notas);
    }
}