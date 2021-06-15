package org.enchere.servlet.test;

import org.enchere.dal.jdbc.ConectionProvider;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;

@MultipartConfig(maxFileSize = 16177215)
@WebServlet(name = "ServletTestImg", value = "/Image")
public class ServletTestImg extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/test/testAkcel.jsp");
        rd.forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        InputStream inputStream = null;


        Part filePart = request.getPart("photo");
        if (filePart != null) {
            // debug messages
            System.out.println(filePart.getName());
            System.out.println(filePart.getSize());
            System.out.println(filePart.getContentType());

            // obtains input stream of the upload file
            inputStream = filePart.getInputStream();
        }

        String message = null; // message will be sent back to client

        try(Connection connection = ConectionProvider.getConnection()){
            String sql = "INSERT INTO photos (photo) values (?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            // constructs SQL statement




            if (inputStream != null) {
                // fetches input stream of the upload file for the blob column
                stmt.setBlob(1, inputStream);
            }

            // sends the statement to the database server
            int row = stmt.executeUpdate();
            if (row > 0) {
                message = "Image is uploaded successfully into the Database";
            }
        } catch (SQLException ex) {
            message = "ERROR: " + ex.getMessage();
            ex.printStackTrace();
        }
        // sets the message in request scope
        request.setAttribute("Message", message);

        // forwards to the message page
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/enchereNoLog.jsp").forward(request, response);
    }

}
