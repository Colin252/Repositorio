import java.util.ArrayList;
import java.util.Scanner;

class Libro {
    private String titulo;
    private String autor;
    private boolean prestado;

    public Libro(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
        this.prestado = false;
    }

    public void prestar() {
        if (!prestado) {
            prestado = true;
            System.out.println("📖 Libro prestado: " + titulo);
        } else {
            System.out.println("❌ El libro ya está prestado.");
        }
    }

    public void devolver() {
        if (prestado) {
            prestado = false;
            System.out.println("✅ Libro devuelto: " + titulo);
        } else {
            System.out.println("❌ El libro no estaba prestado.");
        }
    }

    @Override
    public String toString() {
        return (prestado ? "[Prestado] " : "[Disponible] ") + titulo + " - " + autor;
    }
}

public class Biblioteca {
    private static final ArrayList<Libro> catalogo = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n📚 Sistema de Gestión de Biblioteca 📚");
            System.out.println("1. Agregar libro");
            System.out.println("2. Listar libros");
            System.out.println("3. Prestar libro");
            System.out.println("4. Devolver libro");
            System.out.println("5. Salir");
            System.out.print("Selecciona una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1 -> agregarLibro();
                case 2 -> listarLibros();
                case 3 -> prestarLibro();
                case 4 -> devolverLibro();
                case 5 -> {
                    System.out.println("¡Hasta pronto! 📖");
                    return;
                }
                default -> System.out.println("Opción inválida. Intenta de nuevo.");
            }
        }
    }

    private static void agregarLibro() {
        System.out.print("Ingrese el título del libro: ");
        String titulo = scanner.nextLine();
        System.out.print("Ingrese el autor del libro: ");
        String autor = scanner.nextLine();
        catalogo.add(new Libro(titulo, autor));
        System.out.println("✅ Libro agregado.");
    }

    private static void listarLibros() {
        if (catalogo.isEmpty()) {
            System.out.println("📭 No hay libros en el catálogo.");
            return;
        }
        System.out.println("\n📚 Libros en la biblioteca:");
        for (int i = 0; i < catalogo.size(); i++) {
            System.out.println(i + 1 + ". " + catalogo.get(i));
        }
    }

    private static void prestarLibro() {
        listarLibros();
        if (catalogo.isEmpty()) return;
        System.out.print("Selecciona el número del libro a prestar: ");
        int index = scanner.nextInt() - 1;
        if (index >= 0 && index < catalogo.size()) {
            catalogo.get(index).prestar();
        } else {
            System.out.println("❌ Número inválido.");
        }
    }

    private static void devolverLibro() {
        listarLibros();
        if (catalogo.isEmpty()) return;
        System.out.print("Selecciona el número del libro a devolver: ");
        int index = scanner.nextInt() - 1;
        if (index >= 0 && index < catalogo.size()) {
            catalogo.get(index).devolver();
        } else {
            System.out.println("❌ Número inválido.");
        }
    }
}