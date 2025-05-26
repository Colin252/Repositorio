import java.util.ArrayList;
import java.util.Scanner;

class Task {
    String description;
    boolean isCompleted;

    public Task(String description) {
        this.description = description;
        this.isCompleted = false;
    }

    public void markCompleted() {
        isCompleted = true;
    }

    @Override
    public String toString() {
        return (isCompleted ? "[✔] " : "[ ] ") + description;
    }
}

public class TodoApp {
    private static final ArrayList<Task> tasks = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n📋 Lista de Tareas 📋");
            System.out.println("1. Agregar tarea");
            System.out.println("2. Ver tareas");
            System.out.println("3. Marcar tarea como completada");
            System.out.println("4. Eliminar tarea");
            System.out.println("5. Salir");
            System.out.print("Selecciona una opción: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (choice) {
                case 1 -> addTask();
                case 2 -> listTasks();
                case 3 -> markTaskCompleted();
                case 4 -> deleteTask();
                case 5 -> {
                    System.out.println("¡Hasta pronto! 🎉");
                    return;
                }
                default -> System.out.println("Opción inválida. Intenta de nuevo.");
            }
        }
    }

    private static void addTask() {
        System.out.print("Ingresa la descripción de la tarea: ");
        String description = scanner.nextLine();
        tasks.add(new Task(description));
        System.out.println("✅ Tarea agregada.");
    }

    private static void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println("📭 No hay tareas.");
            return;
        }
        System.out.println("\n📋 Tareas pendientes:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i + 1 + ". " + tasks.get(i));
        }
    }

    private static void markTaskCompleted() {
        listTasks();
        if (tasks.isEmpty()) return;
        System.out.print("Selecciona el número de la tarea completada: ");
        int index = scanner.nextInt() - 1;
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).markCompleted();
            System.out.println("✅ Tarea completada.");
        } else {
            System.out.println("❌ Número inválido.");
        }
    }

    private static void deleteTask() {
        listTasks();
        if (tasks.isEmpty()) return;
        System.out.print("Selecciona el número de la tarea a eliminar: ");
        int index = scanner.nextInt() - 1;
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
            System.out.println("🗑️ Tarea eliminada.");
        } else {
            System.out.println("❌ Número inválido.");
        }
    }
}