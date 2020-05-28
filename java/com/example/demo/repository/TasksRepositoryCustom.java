package com.example.demo.repository;

import java.util.List;

import com.example.demo.entity.*;;

public interface TasksRepositoryCustom {
	public List<Tasks> search(int isDoneCode, int priorityId, String doneAt, int typeId);
}
