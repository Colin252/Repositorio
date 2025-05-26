/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package motrodebusqueda;

import java.util.*;

class Document {
    String titulo;
    String contenido;
    String fecha;

    public Document(String titulo, String contenido, String fecha) {
        this.titulo = titulo;
        this.contenido = contenido;
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Título: " + titulo + "\nFecha: " + fecha + "\nFragmento: " + obtenerFragmento() + "\n";
    }

    public String obtenerFragmento() {
        return contenido.length() > 60 ? contenido.substring(0, 60) + "..." : contenido;
    }
}

class Buscador {
    List<Document> documentos = new ArrayList<>();

    public void agregarDocumento(Document doc) {
        documentos.add(doc);
    }

    public List<Document> buscar(String consulta) {
        List<Document> resultados = new ArrayList<>();
        consulta = consulta.toLowerCase();
        for (Document doc : documentos) {
            if (doc.contenido.toLowerCase().contains(consulta)) {
                resultados.add(doc);
            }
        }
        return resultados;
    }

    public List<Document> ordenarPorFecha(List<Document> resultados) {
        resultados.sort(Comparator.comparing(doc -> doc.fecha));
        return resultados;
    }
}

public class MotorDeBusqueda {
    public static void main(String[] args) {
        Buscador buscador = new Buscador();
        
        buscador.agregarDocumento(new Document("Java Básico", "Java es un lenguaje robusto y versátil.", "2024-05-01"));
        buscador.agregarDocumento(new Document("Programación", "Aprender a programar es una habilidad valiosa.", "2023-12-10"));
        buscador.agregarDocumento(new Document("Desarrollo Web", "HTML, CSS y JavaScript son esenciales.", "2025-02-15"));

        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese término de búsqueda: ");
        String consulta = scanner.nextLine();
        
        List<Document> resultados = buscador.buscar(consulta);
        resultados = buscador.ordenarPorFecha(resultados);

        if (resultados.isEmpty()) {
            System.out.println("No se encontraron resultados.");
        } else {
            System.out.println("Resultados encontrados:");
            for (Document doc : resultados) {
                System.out.println(doc);
            }
        }
        
        scanner.close();
    }
}