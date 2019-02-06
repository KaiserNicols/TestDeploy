package com.revature.model;

import java.util.Objects;

public class Todo {

	private String title;
	private String body;

	// No Arg Constructor for JACKSON
	public Todo() {
	}

	public Todo(String title, String body) {
		super();
		this.title = title;
		this.body = body;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	@Override
	public int hashCode() {
		return Objects.hash(body, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Todo)) {
			return false;
		}
		Todo other = (Todo) obj;
		return Objects.equals(body, other.body) && Objects.equals(title, other.title);
	}

}
