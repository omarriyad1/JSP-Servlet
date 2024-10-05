package com.example.myservletappx.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

public class UserServlet extends HttpServlet{
    HashMap<String, String> users = new HashMap<String, String>();
    static int counter=0;
    @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
        {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();

            String id = request.getParameter("id");
            if (id == null || id.isEmpty()) {
                out.println("<h1>ID not provided</h1>");
            } else {
                users.get(id);
                out.println("<h1>Hello, "+users.get(id) +" :} </h1>");
            }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String id = request.getParameter("id");
        String name = request.getParameter("name");

        if (name == null || name.isEmpty()  || id== null) {
            out.println("<h1> name or id -> not provided</h1>");
        } else {
            users.put(id,name);
            out.println("<h1>Welcome, "+users.get(id) +" :} </h1>");
        }
    }
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String id = request.getParameter("id");

        if (id == null || id.isEmpty()  || id== null) {
            out.println("<h1> name or id -> not provided</h1>");
        } else {
            String removedUser = users.remove(id);
            out.println("<h1>goodbye, "+removedUser +" :} </h1>");
        }
    }
}
