package org.example.servlet;

import com.google.gson.Gson;
import org.example.db.ConnectionManager;
import org.example.repository.impl.PersonDaoImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.service.impl.PersonServiceImpl;
import org.example.servlet.dto.PersonDTO;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@WebServlet("/person")
public class PersonServlet extends HttpServlet {

    private final PersonServiceImpl personService = new PersonServiceImpl(new PersonDaoImpl(ConnectionManager.getDataSource()));
    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<PersonDTO> persons = personService.findAll();
        resp.setContentType("application/json");
        resp.getWriter().write(gson.toJson(persons));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PersonDTO personDTO = gson.fromJson(req.getReader(), PersonDTO.class);
        personService.save(PersonDTO);
        resp.setStatus(HttpServletResponse.SC_CREATED);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PersonDTO personDTO = gson.fromJson(req.getReader(), PersonDTO.class);
        personService.updatePerson(PersonDTO);
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        personService.deleteById(id);
        resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }
}