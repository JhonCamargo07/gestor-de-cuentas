<%@page import="java.util.List"%>
<%@page import="ModeloDAO.ClienteDAO"%>
<%@page import="ModeloVO.ClienteVO" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Gesitonar clientes</title>
    </head>
    <body>
        <div class="container">
            <%
                ClienteDAO clienteDao = new ClienteDAO();
                ClienteVO clienteVo = null;
                
                List<ClienteVO> clientes = clienteDao.select();
                
                for(int i = 0; i < clientes.size(); i++){
                    clienteVo = clientes.get(i);
                
                
            %>
                    
            <p><%= clienteVo.getIdCliente() %></p>
            <%
                }
            %>
        </div>
    </body>
</html>
