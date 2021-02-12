package by.itacademy.security.controller.authentication;

import by.itacademy.security.model.authentication.Authentication;
import by.itacademy.security.model.authentication.LoginPasswordAuthenticationToken;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/LoginPasswordAuthenticationController"})
public class LoginPasswordAuthenticationController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String errorMessage = "Invalid userName or Password";

        Authentication authentication = new LoginPasswordAuthenticationToken(userName, password);

        request.setAttribute("authentication", authentication);
        request.setAttribute("errorMessage", errorMessage);

        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/login");
        dispatcher.forward(request, response);
    }
}
