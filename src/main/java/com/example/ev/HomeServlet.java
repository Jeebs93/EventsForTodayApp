package com.example.ev;

import java.io.*;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "homeServlet", value = "/")
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