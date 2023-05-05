package com.siddhesh.assignment_5;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "display", value = "/display")
public class DisplayData extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("text/html");

        PrintWriter out = res.getWriter();

        final String query = "select * from ebookshop";

        try {
            Class.forName("com.mysql.jdbc.Driver");
				
				//change password
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/assignment_5", "root","password");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                int book_id = rs.getInt("book_id");
                String book_title = rs.getString("book_title");
                String book_author = rs.getString("book_author");
                String book_price = rs.getString("book_price");
                int quantity = rs.getInt("quantity");
                out.println("Book Id: " + book_id + ", Book Name: " + book_title +  ", Book Author: " + book_author + ", Book Price: " + book_price  + ", Quantity: " + quantity + "<br>");
            }


        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
