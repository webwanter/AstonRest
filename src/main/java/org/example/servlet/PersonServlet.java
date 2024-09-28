package org.example.servlet;

import org.example.repository.impl.PersonDaoImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/person")
public class PersonServlet extends HttpServlet {

    private final PersonServiceImpl userService = new PersonServiceImpl(new PersonDaoImpl(DatabaseConfig.getDataSource()));
    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<PersonDTO> persons = userService.getAllUsers();
        resp.setContentType("application/json");
        resp.getWriter().write(gson.toJson(users));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PersonDTO personDTO = gson.fromJson(req.getReader(), PersonDTO.class);
        userService.addUser(PersonDTO);
        resp.setStatus(HttpServletResponse.SC_CREATED);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PersonDTO personDTO = gson.fromJson(req.getReader(), PersonDTO.class);
        userService.updateUser(PersonDTO);
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        userService.deleteUser(id);
        resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }
}