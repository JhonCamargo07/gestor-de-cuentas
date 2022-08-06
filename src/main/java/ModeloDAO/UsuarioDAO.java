package ModeloDAO;

import ModeloVO.UsuarioVO;
import java.sql.*;
import java.util.logging.*;
import java.util.logging.Logger;
import util.Conexion;

/**
 *
 * @author Camargo
 */
public class UsuarioDAO extends Conexion {

    private Connection conn;
    private PreparedStatement puente;
    private ResultSet mensajero;
    private String sql = "";
    private boolean operacionExitosa = false;

    public int Insert(UsuarioVO usuarioVo) {
        int idUsuario = 0;
        sql = "INSERT INTO USUARIO VALUES (null, ?,?,?)";

        try {
            conn = this.getConnection();
            puente = conn.prepareStatement(sql);
            puente.setString(1, usuarioVo.getLogin());
            puente.setString(2, usuarioVo.getPassword());
            puente.setString(3, usuarioVo.getIdRol());
            puente.executeUpdate();

            sql = "SELECT idUsuario FROM usuario ORDER BY idUsuario desc limit 1";

            puente = conn.prepareStatement(sql);
            mensajero = puente.executeQuery();

            if (mensajero.next()) {
                idUsuario = mensajero.getInt(1);
            }

        } catch (SQLException ex) {
            operacionExitosa = false;
            System.out.println("Ocurrió un error al registrar el cliente: " + ex.toString());
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.close(conn);
        }

        return idUsuario;
    }

    // Método para actualizar el usuario, solo el login y password
    public boolean update(UsuarioVO usuarioVo) {
        sql = "UPDATE usuario SET login = ?, password = ? WHERE idUsuario = ?";

        try {
            conn = this.getConnection();
            puente = conn.prepareStatement(sql);
            puente.setString(1, usuarioVo.getLogin());
            puente.setString(2, usuarioVo.getPassword());
            puente.setString(3, usuarioVo.getIdUsuario());
            puente.executeUpdate();

            // Si se logra actualizar, se cambia el valor de la operacion 
            operacionExitosa = true;
            
        } catch (SQLException ex) {
            operacionExitosa = false;
            System.out.println("Ocurrió un error al actualizar el usuario: " + ex.toString());
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.close(conn);
        }

        return operacionExitosa;
    }

}
