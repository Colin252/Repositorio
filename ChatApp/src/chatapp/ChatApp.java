public class ChatApp {
    public static void main(String[] args) {
        // Insertar usuarios de prueba
        ConexionDB.insertarUsuario("Juan", "juan@example.com", "12345");
        ConexionDB.insertarUsuario("Maria", "maria@example.com", "67890");

        // ✅ Enviar mensajes de prueba
        ConexionDB.guardarMensaje(1, 2, "Hola María, ¿cómo estás?");
        ConexionDB.guardarMensaje(2, 1, "¡Hola Juan! Estoy bien, ¿y tú?");

        // ✅ Mostrar el historial de mensajes
        ConexionDB.listarMensajes();
    }
}