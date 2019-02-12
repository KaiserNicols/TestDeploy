package com.revature;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.service.BasicTodoService;
import com.revature.service.TodoService;

public class TodoServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private final ObjectMapper mapper = new ObjectMapper();
	private final TodoService todoService = BasicTodoService.getInstance();
       
    public TodoServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.getWriter().println(mapper.writeValueAsString(todoService.getAllTodos()));
	}

}
