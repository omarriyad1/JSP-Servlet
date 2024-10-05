package com.example.myservletappx.servlets;

import com.example.myservletappx.model.car;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import static java.lang.System.out;

public class CarServlet extends HttpServlet {
    private Map<String, car> carDatabase = new HashMap<>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
    {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        ObjectMapper objectMapper = new ObjectMapper();

        String id = request.getParameter("id");
        if (id == null || id.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.println(objectMapper.writeValueAsString("ID not provided"));
        } else {
            car Car = carDatabase.get(id);
            if (Car == null) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                out.println(objectMapper.writeValueAsString("Car not found"));
            } else {
                out.println(objectMapper.writeValueAsString(Car));
            }
        }

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        ObjectMapper objectMapper = new ObjectMapper();

        car newCar = objectMapper.readValue(request.getInputStream(), car.class);

        carDatabase.put(newCar.getId(), newCar);

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("message", "Car added successfully");
        responseMap.put("car", newCar);

        response.setStatus(HttpServletResponse.SC_CREATED);
        out.println(objectMapper.writeValueAsString(responseMap));
    }
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        ObjectMapper objectMapper = new ObjectMapper();

        String id = request.getParameter("id");

        Map<String, String> responseMap = new HashMap<>();


        if (id == null || id.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            responseMap.put("error", "ID not provided or invalid");
        } else {
            car removedCar = carDatabase.remove(id);
            if (removedCar != null) {
                responseMap.put("message", "Car removed successfully");

            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                responseMap.put("error", "Car with ID " + id + " not found");
            }
        }

        out.println(objectMapper.writeValueAsString(responseMap));
    }
}

