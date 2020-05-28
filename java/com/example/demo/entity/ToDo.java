package com.example.demo.entity;

import java.util.HashMap;
import java.util.Map;

public class ToDo {

	private Map<Integer, Tasks> tasks = new HashMap<>();

	public Map<Integer, Tasks> getTaskS() {
		return tasks;
	}

	public void deleteToDo(int tasksCode) {
		tasks.remove(tasksCode);

	}

}
