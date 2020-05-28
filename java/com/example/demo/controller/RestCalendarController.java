package com.example.demo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Tasks;
import com.example.demo.model.Event;
import com.example.demo.repository.TasksRepository;
import com.example.demo.service.ToDoService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/event")
public class RestCalendarController {

	@Autowired
	TasksRepository tasksRepository;

	@Autowired
	ToDoService toDoService;

	/**
	 * カレンダーに表示するEvent情報を取得
	 * 
	 * @return Event情報をjsonエンコードした文字列
	 */
	@GetMapping(value = "/all")
	public String getEvents() {
		String jsonMsg = null;
		try {
			List<Event> events = new ArrayList<Event>();

			List<Tasks> tasksList = toDoService.notDeleteAllList();

			tasksList.forEach((Tasks tasks) -> {
				Event event = new Event();
				event.setTitle(tasks.getContent());
				event.setStart(tasks.getDoneAt());
				event.setUrl("/ToDoTop/edit/" + tasks.getCode());
				events.add(event);
			});

			// FullCalendarにエンコード済み文字列を渡す
			ObjectMapper mapper = new ObjectMapper();
			jsonMsg = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(events);
		} catch (IOException ioex) {
			System.out.println(ioex.getMessage());
		}
		return jsonMsg;
	}
}
