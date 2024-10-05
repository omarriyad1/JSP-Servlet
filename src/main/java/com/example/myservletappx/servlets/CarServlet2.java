package com.example.myservletappx.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.example.myservletappx.model.car;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
@WebServlet("/car2")
public class CarServlet2 extends HttpServlet {
    private Map<String, car> carDatabase = new HashMap<>();

    // Handle GET request to show the form
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Forward to the JSP file
        request.getRequestDispatcher("/jsp/carForm.html").forward(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        // Get form parameters
        String id = request.getParameter("id");
        String model = request.getParameter("model");
        String make = request.getParameter("make");
        int year = Integer.parseInt(request.getParameter("year"));

        // Add the car to the database
        car car1 = new car(id, model, make, year);
        carDatabase.put(id, car1);

        // Set a success message attribute
        request.setAttribute("message", "Car added successfully: " + car1.getId() + ", " + car1.getModel() + ", " + car1.getMake() + ", " + car1.getYear());

        // Forward to the success page
        request.getRequestDispatcher("/jsp/carSuccess.html").forward(request, response);
    }

}