package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CalendarController {

	/**
	 * タスク画面を表示
	 */
	@RequestMapping(value = "/calendar")
	public ModelAndView ToDoTop(ModelAndView mv) {
		mv.setViewName("calendar");
		return mv;
	}

}
