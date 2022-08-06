<%-- 
    Document   : registroCuenta
    Created on : 5/08/2022, 11:25:15 a. m.
    Author     : Sena
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registro Cueta</title>
    </head>
    <body>
        <h1>Registro</h1>
        <form method="post" action="Cuenta">


            <label>Numero Cuenta</label>
            <input type="text" name="txtNumeroCuenta"><br>
            <label>Titular</label>
            <input type="text" name="txtTitular"><br>
            <label>Saldo</label>
            <input type="text" name="txtSaldo"><br>
            <label>Fecha Apertura</label>
            <input type="date" name="txtFecha"><br>
            <label>Estado</label>
            <select name="txtEstado">
                <option>Abierto</option>
                <option>Cerrado</option>
            </select><br>
            <label>Cedula Cliente</label>
            <input type="number" name="txtCedula"><br>
            <label>Nombre</label>
            <input type="text" name="txtFecha"><br>
            <label>Telefono</label>
            <input type="text" name="txtFecha"><br>
            <label>Id Usuario</label>

            <input type="hidden" value="1" name="opcion">
        </form><br>


        <%
            if (request.getAttribute("mensajeError") != null) { %>
        <h5 class="mensaje">
            ${mensajeError} 
        </h5> 
        <%} else {%>
        ${mensajeExito}
        <% }%>   
    </body>
</html>
