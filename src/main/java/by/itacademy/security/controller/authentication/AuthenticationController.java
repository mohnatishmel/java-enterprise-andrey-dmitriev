package by.itacademy.security.controller.authentication;

import by.itacademy.security.exception.authentication.AuthenticationException;
import by.itacademy.security.model.UserDetails;
import by.itacademy.security.model.authentication.AuthenticationToken;
import by.itacademy.security.service.AuthenticationProvider;
import by.itacademy.security.service.SecurityContext;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class AuthenticationController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/pages/login.jsp");

        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String errorMessage = "Invalid userName or Password";

        AuthenticationToken authentication = new AuthenticationToken(userName, password);

        request.setAttribute("authentication", authentication);
        request.setAttribute("errorMessage", errorMessage);

        UserDetails principal;

        try {
            principal = AuthenticationProvider.getInstance().authenticate(authentication);
            SecurityContext.getInstance().setPrincipal(principal);
            request.getSession().setAttribute("principal", principal);

        } catch (AuthenticationException e) {
            RequestDispatcher dispatcher
                    = this.getServletContext().getRequestDispatcher("/pages/login.jsp");

            dispatcher.forward(request, response);
            return;
        }
        response.sendRedirect(request.getContextPath() + "/index");
    }

}

