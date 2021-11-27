package com.example.demo;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/")
public class HomeServlet extends HttpServlet {

    @Inject
    Producer producer;

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {


        producer.sendString("Request");
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("home.xhtml");
        requestDispatcher.forward(req,res);
    }

    public void destroy() {
    }
}