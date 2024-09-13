package umg.demostracion.DataBase.Service;

import umg.demostracion.DataBase.Dao.ProductoDAO;
import umg.demostracion.DataBase.Model.Producto;

import java.sql.SQLException;
import java.util.List;

public class ProductoService {
    private ProductoDAO productoDAO;

    public ProductoService() {
        this.productoDAO = new ProductoDAO();
    }

    // Método para obtener todos los productos
    public List<Producto> listarProductos() throws SQLException {
        return productoDAO.getAllProductos();
    }

    // Método para obtener un producto por su ID
    public Producto obtenerProductoPorId() throws SQLException {
        return productoDAO.getProductoById(obtenerProductoPorId().getIdProducto());
    }

    // Método para agregar un nuevo producto
    public boolean agregarProducto(Producto producto) throws SQLException {
        return productoDAO.insertProducto(producto);
    }

    // Método para actualizar un producto existente
    public boolean actualizarProducto(Producto producto) throws SQLException {
        return productoDAO.updateProducto(producto);
    }

    // Método para eliminar un producto
    public boolean eliminarProducto(int id) throws SQLException {
        return productoDAO.deleteProducto(id);
    }
}
