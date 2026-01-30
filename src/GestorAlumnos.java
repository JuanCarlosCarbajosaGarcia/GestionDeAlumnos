import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GestorAlumnos {
    public static void main(String[] args) {
        Map<String, List> alumnos = new HashMap<>();
        String FAlumnos = "Alumnos.txt";
        String Resultados = "Resultados.txt";

        //leer el archivo
        try (BufferedReader reader = new BufferedReader(new FileReader(FAlumnos));){
            String line;
            while ((line = reader.readLine()) != null){
                String[] data = line.split(";");

                if(data.length==2){
                    String Nombre = data[0];
                    double Nota = Double.parseDouble(data[1]);

                    alumnos.putIfAbsent(Nombre, new ArrayList());
                    alumnos.get(Nombre).add(Nota);
                }
            }
        }catch (Exception e){
            System.out.println("error al leer el archivo: " + e.getMessage());
            return;
        }

        //Nota media
        Map<String, Double> medias = new HashMap<>();

        for(String alumno : alumnos.keySet()){
            List<Double> notas = alumnos.get(alumno);
            double suma = 0;
            for(double nota : notas){
                suma += nota;
            }
            double media = suma / notas.size();
            medias.put(alumno, media);
        }

        //Alumnos aprovados
        Map<String, Double> aprobados = new HashMap<>();

        for(String alumno : alumnos.keySet()){
            if (medias.get(alumno)>= 5){
                aprobados.put(alumno, medias.get(alumno));
            }
        }

    }
}