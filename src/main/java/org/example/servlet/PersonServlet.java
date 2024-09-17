package org.example.servlet;

import org.example.dao.PersonDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/person")
public class PersonServlet extends HttpServlet {

    private PersonDAO personDAO;

    @Override
    public void init() throws ServletException {
        personDAO = new PersonDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");

        Person person = new Person();
        person.setName(name);
        person.setSurname(surname);

        personDAO.save(person);

        response.sendRedirect("success.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.valueOf(request.getParameter("id"));
        Person person = personDAO.findById(id);

        request.setAttribute("person", person);
        request.getRequestDispatcher("/personDetails.jsp").forward(request, response);
    }
}