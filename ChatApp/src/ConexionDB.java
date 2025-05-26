import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConexionDB {
    private static final String URL = "jdbc:mysql://localhost:3306/chatDB";
    private static final String USUARIO = "root";
    private static final String CONTRASEÑA = "123456";

    public static Connection conectar() {
        Connection conexion = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(URL, USUARIO, CONTRASEÑA);
            System.out.println("✅ Conexión establecida correctamente.");
        } catch (ClassNotFoundException e) {
            System.out.println("❌ Error: Driver JDBC no encontrado.");
        } catch (SQLException e) {
            System.out.println("❌ Error al conectar con la base de datos: " + e.getMessage());
        }
        return conexion;
    }

    public static void listarUsuarios() {
        Connection conexion = conectar();
        if (conexion != null) {
            try {
                // ✅ Consulta corregida sin `fecha_creacion`
                String sql = "SELECT id, nombre, email FROM usuarios"; 

                PreparedStatement stmt = conexion.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();

                System.out.println("\n📋 Lista de Usuarios:");
                while (rs.next()) {
                    System.out.println("🆔 ID: " + rs.getInt("id") +
                                       " | Nombre: " + rs.getString("nombre") +
                                       " | Email: " + rs.getString("email"));
                }

                rs.close();
                stmt.close();
                conexion.close(); // ✅ Cierre correcto de conexión
            } catch (SQLException e) {
                System.out.println("❌ Error al recuperar usuarios: " + e.getMessage());
            }
        } else {
            System.out.println("❌ No se pudo conectar a la base de datos, no se pueden listar los usuarios.");
        }
    }

    public static void main(String[] args) {
        insertarUsuario("Juan", "juan@example.com", "12345");

        // ✅ Llamamos `listarUsuarios()` para ver la lista en consola
        listarUsuarios();
    }

    public static void insertarUsuario(String nombre, String email, String contraseña) {
        Connection conexion = conectar();
        if (conexion != null) {
            try {
                String sql = "INSERT INTO usuarios (nombre, email, contraseña) VALUES (?, ?, ?)";
                PreparedStatement stmt = conexion.prepareStatement(sql);
                stmt.setString(1, nombre);
                stmt.setString(2, email);
                stmt.setString(3, contraseña);

                int filasInsertadas = stmt.executeUpdate();
                if (filasInsertadas > 0) {
                    System.out.println("✅ Usuario agregado correctamente: " + nombre);
                } else {
                    System.out.println("❌ No se pudo insertar el usuario.");
                }

                stmt.close();
                conexion.close();
            } catch (SQLException e) {
                System.out.println("❌ Error al insertar usuario: " + e.getMessage());
            }
        } else {
            System.out.println("❌ No se pudo conectar a la base de datos, no se insertó el usuario.");
        }
        
    }
    
    
    public static void guardarMensaje(int remitenteId, int destinatarioId, String mensaje) {
    Connection conexion = conectar();
    if (conexion != null) {
        try {
            String sql = "INSERT INTO mensajes (remitente_id, destinatario_id, mensaje) VALUES (?, ?, ?)";
            PreparedStatement stmt = conexion.prepareStatement(sql);
            stmt.setInt(1, remitenteId);
            stmt.setInt(2, destinatarioId);
            stmt.setString(3, mensaje);

            int filasInsertadas = stmt.executeUpdate();
            if (filasInsertadas > 0) {
                System.out.println("✅ Mensaje enviado correctamente.");
            } else {
                System.out.println("❌ No se pudo enviar el mensaje.");
            }

            stmt.close();
            conexion.close();
        } catch (SQLException e) {
            System.out.println("❌ Error al guardar mensaje: " + e.getMessage());
        }
    } else {
        System.out.println("❌ No se pudo conectar a la base de datos, el mensaje no se guardó.");
    }
}
    
    
    public static void listarMensajes() {
    Connection conexion = conectar();
    if (conexion != null) {
        try {
            String sql = "SELECT id, remitente_id, destinatario_id, mensaje, fecha_envio FROM mensajes";
            PreparedStatement stmt = conexion.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            System.out.println("\n📩 Historial de mensajes:");
            while (rs.next()) {
                System.out.println("🆔 ID: " + rs.getInt("id") +
                                   " | De: " + rs.getInt("remitente_id") +
                                   " | Para: " + rs.getInt("destinatario_id") +
                                   " | Mensaje: " + rs.getString("mensaje") +
                                   " | Fecha: " + rs.getTimestamp("fecha_envio"));
            }

            rs.close();
            stmt.close();
            conexion.close();
        } catch (SQLException e) {
            System.out.println("❌ Error al recuperar mensajes: " + e.getMessage());
        }
    } else {
        System.out.println("❌ No se pudo conectar a la base de datos, no se pueden listar los mensajes.");
    }
}
    
    
    
    
}