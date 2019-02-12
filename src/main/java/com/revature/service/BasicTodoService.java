package com.revature.service;

import java.util.ArrayList;
import java.util.List;

import com.revature.model.Todo;

public class BasicTodoService implements TodoService {
	
	private final List<Todo> todos = new ArrayList<>();
	private static final BasicTodoService instance = new BasicTodoService();
	
	private BasicTodoService() {
		super();
		for (int i = 1; i < 21; i++) {
			todos.add(new Todo("Title: " + i, "Body: " + i));
		}
	}
	
	public static TodoService getInstance() {
		return instance;
	}

	@Override
	public List<Todo> getAllTodos() {
		return todos;
	}

}
