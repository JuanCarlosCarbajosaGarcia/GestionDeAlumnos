import java.io.*;
import java.util.*;

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
        }catch (IOException e){
            System.out.println("error al leer el archivo: " + e.getMessage());
            return;
        }
        //mostrar HashMap
        for(String Nombre : alumnos.keySet()){
            System.out.println("Alumno: " + Nombre + ": " + alumnos.get(Nombre));
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

        //mostrar medias
        System.out.println("--------------------");
        for(String alumno : alumnos.keySet()){
            System.out.println("Notas medias: " + alumno + ": " + medias.get(alumno));
        }

        //Alumnos aprobados
        Map<String, Double> aprobados = new HashMap<>();

        for(String alumno : alumnos.keySet()){
            if (medias.get(alumno)>= 5){
                aprobados.put(alumno, medias.get(alumno));
            }
        }

        //mostrar aprobados
        System.out.println("--------------------");
        for(String alumno : alumnos.keySet()) {
            if (aprobados.containsKey(alumno)){System.out.println("Aprobados: " + alumno + ": " + aprobados.get(alumno));
            }else {
                System.out.println("Suspenso: " + alumno + ": Suspenso");
            }
        }


        //ordenar por nota media
        List<Map.Entry<String, Double>> ordenada = new ArrayList<>(aprobados.entrySet());

        Collections.sort(ordenada, new Comparator<Map.Entry<String, Double>>() {
            public int compare(Map.Entry<String, Double> a, Map.Entry<String, Double> b) {
                return Double.compare(b.getValue(), a.getValue());
            }
        });

        //alumno con la mejor nota media
        Map.Entry<String, Double> mejorAlumno = ordenada.get(0);

        for (Map.Entry<String, Double> alumno : aprobados.entrySet()) {
            if (alumno.getKey().equals(mejorAlumno.getKey())){
                mejorAlumno = alumno;
                System.out.println("--------------------");
                System.out.println("mejor alumno: " + mejorAlumno.getKey() + ": " + mejorAlumno.getValue());
            }
        }
    }
}