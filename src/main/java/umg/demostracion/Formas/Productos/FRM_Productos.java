package umg.demostracion.Formas.Productos;

import umg.demostracion.DataBase.Model.Producto;
import umg.demostracion.DataBase.Service.ProductoService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FRM_Productos {
    private JPanel J_Panel_Principal;
    private JLabel lbl_titulo;
    private JLabel lbl_codigo;
    private JTextField textField_idProducto;
    private JLabel lbl_Nombre;
    private JTextField textField_Nombre;
    private JLabel lbl_origen;
    private JComboBox comboBox_Origen;
    private JButton button_Grabar;
    private JButton button_Buscar;

    public static void main(String[] args) {
        JFrame frame = new JFrame("FRM_Productos");
        frame.setContentPane(new FRM_Productos().J_Panel_Principal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public FRM_Productos() {
        comboBox_Origen.addItem("China");
        comboBox_Origen.addItem("Japón");
        comboBox_Origen.addItem("Corea");

        button_Grabar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Producto producto = new Producto();
                producto.setDescripcion(textField_Nombre.getText());
                producto.setOrigen(comboBox_Origen.getSelectedItem().toString());

                try {
                    new ProductoService().agregarProducto(producto);
                    JOptionPane.showMessageDialog(null, "Producto creado exitosamente");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al guardar el producto");
                }

            }
        });
        button_Buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
             int idProducto = textField_idProducto.getText().isEmpty() ? 0 : Integer.parseInt(textField_idProducto.getText());
             try{
                 Producto ProductoEncontrado = new ProductoService().obtenerProductoPorId();
                 if(ProductoEncontrado != null){
                     textField_idProducto.setText(ProductoEncontrado.getDescripcion());
                     comboBox_Origen.setSelectedItem(ProductoEncontrado.getOrigen());
                 } else{
                     JOptionPane.showMessageDialog(null, "Codigo de Producto no existe");
                 }
             }catch(Exception ex){
                 JOptionPane.showMessageDialog(null, "Error al obtener el producto"+ ex.getMessage());
             }
            }
        });
    }
}