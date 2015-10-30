package server;

import logic.Avtor;

import javax.annotation.Resource;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yevhen on 30.10.2015.
 */
@WebServlet(name = "DBServlet", urlPatterns = {"/avtors"})
public class DBServlet extends HttpServlet {
    @Resource (mappedName = "jdbc/library") DataSource ds;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Avtor> avtors = new ArrayList<>();
        try {
//            InitialContext initialContext = new InitialContext();
//            DataSource ds = (DataSource) initialContext.lookup("jdbc/library");
            Connection connection = ds.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM avtor");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Avtor a = new Avtor(rs.getInt("id"), rs.getString("name"), rs.getString("comment"));
                avtors.add(a);
            }
            rs.close();
            preparedStatement.close();
            connection.close();
            request.setAttribute("avtors", avtors);
            request.getRequestDispatcher("/avtors.jsp").forward(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
