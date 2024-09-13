package umg.demostracion.DataBase.Dao;

import umg.demostracion.DataBase.Connection.ConexionDB;
import umg.demostracion.DataBase.Model.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {
    // Método para obtener todos los productos
    public List<Producto> getAllProductos() throws SQLException {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM producto";

        try (Connection conn = ConexionDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Producto producto = new Producto(
                        rs.getInt("id_producto"),
                        rs.getString("descripcion"),
                        rs.getString("origen")
                );
                productos.add(producto);
            }
        }
        return productos;
    }

    // Método para obtener un producto por su ID
    public Producto getProductoById(int idProducto) throws SQLException {
        Producto producto = null;
        String sql = "SELECT * FROM producto WHERE id_producto = ?";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idProducto);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    producto = new Producto(
                            rs.getInt("id_producto"),
                            rs.getString("descripcion"),
                            rs.getString("origen")
                    );
                }
            }
        }
        return producto;
    }

    // Método para insertar un producto
    public boolean insertProducto(Producto producto) throws SQLException {
        String sql = "INSERT INTO producto (descripcion, origen) VALUES (?, ?)";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, producto.getDescripcion());
            pstmt.setString(2, producto.getOrigen());

            return pstmt.executeUpdate() > 0;
        }
    }

    // Método para actualizar un producto
    public boolean updateProducto(Producto producto) throws SQLException {
        String sql = "UPDATE producto SET descripcion = ?, origen = ? WHERE id_producto = ?";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, producto.getDescripcion());
            pstmt.setString(2, producto.getOrigen());
            pstmt.setInt(3, producto.getIdProducto());

            return pstmt.executeUpdate() > 0;
        }
    }

    // Método para eliminar un producto
    public boolean deleteProducto(int idProducto) throws SQLException {
        String sql = "DELETE FROM producto WHERE id_producto = ?";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idProducto);
            return pstmt.executeUpdate() > 0;
        }
    }
}
