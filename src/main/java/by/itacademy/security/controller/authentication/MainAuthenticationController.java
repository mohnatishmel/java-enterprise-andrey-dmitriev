package by.itacademy.security.controller.authentication;

import by.itacademy.security.exception.web.authentication.AuthenticationException;
import by.itacademy.security.model.authentication.Authentication;
import by.itacademy.security.service.web.config.WebSecurityConfig;
import by.itacademy.security.service.authentication.AuthenticationManager;
import lombok.SneakyThrows;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class MainAuthenticationController extends HttpServlet {

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(WebSecurityConfig.getInstance().getLoginUrl());

        dispatcher.forward(request, response);
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Authentication authentication = (Authentication) request.getAttribute("authentication");

        if (authentication == null) {
            RequestDispatcher dispatcher
                    = this.getServletContext().getRequestDispatcher(WebSecurityConfig.getInstance().getLoginUrl());

            dispatcher.forward(request, response);
            return;
        }

        try {
            authentication = AuthenticationManager.getInstance().authenticate(authentication);

        } catch (AuthenticationException e) {
            RequestDispatcher dispatcher
                    = this.getServletContext().getRequestDispatcher(WebSecurityConfig.getInstance().getLoginUrl());

            dispatcher.forward(request, response);
            return;
        }
        response.sendRedirect(request.getContextPath() + "/index");
    }

}

