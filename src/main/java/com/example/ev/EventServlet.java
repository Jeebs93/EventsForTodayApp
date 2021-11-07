package com.example.ev;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="eventServlet", value="/events")
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
