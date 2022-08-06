/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModeloDAO;

import ModeloVO.CuentaVO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.Conexion;

/**
 *
 * @author Sena
 */
public class CuentaDAO extends Conexion {

    private Connection conn;
    private PreparedStatement puente;
    private ResultSet mensajero;
    private String sql;
    boolean operacionExitosa = false;

    private String idCuenta = "", numeroCuenta = "", titular = "", saldo = "", fechaApertura = "", estado = "";

    public CuentaDAO() {
    }

    public CuentaDAO(CuentaVO cuentaVO) {
        super();
        //conectarse a la base de datos
        try {
            conn = this.getConnection();
            //traer al DAO los datos del VO para hacer la operacion y los trae de forma segura
            idCuenta = cuentaVO.getIdCuenta();
            numeroCuenta = cuentaVO.getNumeroCuenta();
            titular = cuentaVO.getTitular();
            saldo = cuentaVO.getSaldo();
            fechaApertura = cuentaVO.getFechaApertura();
            estado = cuentaVO.getSaldo();

        } catch (Exception e) {
            //traiga el log de la clase e imprima los errores
            Logger.getLogger(CuentaDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    //Crear Cuenta
    public int agregarCuenta() {

        int ultimoid = 0;

        try {
            sql = "INSERT INTO `cuenta`( NUMEROCTA, TITULAR, SALDO, FECHAAPERTURA, ESTADO) VALUES (?,?,?,?,?)";
            conn = this.getConnection();
            //crear el puente, prepara lo que va a mandar
            puente = conn.prepareStatement(sql);
            //por el puente manda los datos a insertar
            puente.setString(1, numeroCuenta);
            puente.setString(2, titular);
            puente.setString(3, saldo);
            puente.setString(4, fechaApertura);
            puente.setString(5, estado);

            puente.executeUpdate();

            sql = "SELECT * FROM cuenta ORDER BY  IDCUENTA DESC LIMIT 1 ";
            puente = conn.prepareStatement(sql);
            mensajero = puente.executeQuery();

            if (mensajero.next()) {

                ultimoid = Integer.parseInt(mensajero.getString(1));

            }

        } catch (SQLException e) {
            Logger.getLogger(CuentaDAO.class.getName()).log(Level.SEVERE, null, e);
        } //
        finally {
            try {
                this.close();
            } catch (Exception e) {
                Logger.getLogger(CuentaDAO.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return ultimoid;
    }

    // Método para saber si un usuario ya se encuentra registrado
    public boolean cuentaYaExiste(String numCuenta) {
        sql = "SELECT * FROM cuenta WHERE NUMEROCTA = ?";

        try {
            conn = this.getConnection();
            puente = conn.prepareStatement(sql);
            puente.setString(1, numCuenta);
            mensajero = puente.executeQuery();

            if (mensajero.next()) {
                // Si entra al if es porque si existe
                operacionExitosa = true;
            }
        } catch (SQLException ex) {
            operacionExitosa = false;
            System.out.println("Ocurrió un error: " + ex.toString());
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Cerramos la conexion
            this.close(conn);
        }

        return operacionExitosa;
    }
    
    public boolean update() {

        sql = "UPDATE cuenta SET estado = ? WHERE idcuenta = ?";

        try {
            conn = this.getConnection();
            puente = conn.prepareStatement(sql);
            puente.setString(1, estado);
            puente.setString(2, idCuenta);
            puente.executeUpdate();

            operacionExitosa = true;

        } catch (SQLException ex) {
            operacionExitosa = false;
            System.out.println("Ocurrió un error al actualizar la cuenta: " + ex.toString());
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.close(conn);
        }

        return operacionExitosa;
    }

}
