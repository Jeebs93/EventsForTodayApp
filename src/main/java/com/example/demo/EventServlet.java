package com.example.demo;

import java.io.*;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(value="/events")
public class EventServlet extends HttpServlet {

    @Inject
    EventListBean eventListBean;

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("events.xhtml");
        requestDispatcher.forward(req,res);
    }

    public void destroy() {
    }


}