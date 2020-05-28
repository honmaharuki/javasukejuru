package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.*;
import com.example.demo.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ToDoService {

	@Autowired
	TasksRepository tasksRepository;

	@Autowired
	TasksRepositoryCustom tasksRepositoryCustom;

	public List<Tasks> search(int isDoneCode, int priorityId, String doneAt, int typeId) {
		List<Tasks> result;
		if (isDoneCode == 3 && priorityId == 0 && "".equals(doneAt) && typeId == 0) {
			result = notDeleteAllList();
		} else {
			result = tasksRepositoryCustom.search(isDoneCode, priorityId, doneAt, typeId);
		}
		return result;
	}

	public List<Tasks> notDeleteAllList() {
		List<Tasks> result;

		result = tasksRepository.findByIsDelete(0);

		return result;
	}
}
