package org.enchere.servlet.test;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Test pool de connection
 */
@WebServlet(name = "ServletTestPoolConnection", value = "/ServletTestPoolConnection")
public class ServletTestPoolConnection extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        try{
            Context context = new InitialContext();
            DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc/pool_cnx");
            Connection connection = dataSource.getConnection();
            out.println("Connection " + (connection.isClosed()?"close":"ouverte"));
            connection.close();
        } catch (SQLException  | NamingException e) {
            e.printStackTrace();
            out.println("Erreur lors de la connection" + e.getMessage());
        }
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
