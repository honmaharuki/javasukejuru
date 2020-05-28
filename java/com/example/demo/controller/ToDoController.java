package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Priorities;
import com.example.demo.entity.Tasks;
import com.example.demo.entity.Types;
import com.example.demo.model.Event;
import com.example.demo.repository.PrioritiesRepository;
import com.example.demo.repository.TasksRepository;
import com.example.demo.repository.TypesRepository;
import com.example.demo.service.ToDoService;

@Controller
public class ToDoController {

	@Autowired
	HttpSession session;

	@Autowired
	TasksRepository tasksRepository;

	@Autowired
	TypesRepository typesRepository;

	@Autowired
	PrioritiesRepository prioritiesRepository;

	@Autowired
	ToDoService toDoService;

//	
//	@Autowired
//	Tasks tasks;

	/**
	 * タスク画面を表示
	 */
	@RequestMapping(value = "/ToDoTop")
	public ModelAndView ToDoTop(ModelAndView mv) {
		// セッション情報はクリアする
		session.invalidate();
		List<Tasks> tasksList = toDoService.notDeleteAllList();
		mv.addObject("tasks", tasksList);

		List<Types> typesList = typesRepository.findAll();
		mv.addObject("types", typesList);

		List<Priorities> prioritiesList = prioritiesRepository.findAll();
		mv.addObject("priorities", prioritiesList);

		mv.setViewName("ToDoTop");
		return mv;
	}

	/**
	 * タスク編集画面を表示
	 */
	@RequestMapping(value = "/ToDoTop/edit/{tasksCode}")
	public ModelAndView editToDoDsp(@PathVariable(name = "tasksCode") int tasksCode, ModelAndView mv) {
		// IDベースにレコード取ってくる
		Tasks task = new Tasks();
		task = tasksRepository.findById(tasksCode);

		// //セッションに持ってくIDを詰めておく。
		session.setAttribute("editId", task.getCode());
		
		List<Types> typesList = typesRepository.findAll();
		mv.addObject("types", typesList);
						
		for(Types typeVal : typesList ) {
			if(task.getTypeId()==typeVal.getCode()) {

				mv.addObject("type", typeVal);
			}
		}
		
		// 表示のために詰めておく
		mv.addObject("tasks", task);

		List<Priorities> prioritiesList = prioritiesRepository.findAll();
		mv.addObject("priorities", prioritiesList);

		mv.setViewName("editToDo");

		return mv;
	}

	/**
	 * タスクを編集処理
	 */
	@RequestMapping(value = "/ToDoTop/edit", method = RequestMethod.POST)
	public ModelAndView editToDo(@RequestParam(name = "priorityId") int priorityId,
			@RequestParam(name = "doneAt") String doneAt, @RequestParam(name = "content") String content,
			@RequestParam(name = "typeId") int typeId, ModelAndView mv) {
		// IDベースにレコード取ってくる
		Tasks task = new Tasks();
		// セッションからとってきたIDで検索
		int editId = (int) session.getAttribute("editId");
		task = tasksRepository.findById(editId);

		// 持ってきた情報を詰める
		task.editToDo(priorityId, doneAt, content, typeId, 0);

		// 更新した情報を保存
		tasksRepository.saveAndFlush(task);

		// DBからToDo情報を取得
		List<Tasks> tasksList = toDoService.notDeleteAllList();

		mv.addObject("tasks", tasksList);

		List<Types> typesList = typesRepository.findAll();
		mv.addObject("types", typesList);

		List<Priorities> prioritiesList = prioritiesRepository.findAll();
		mv.addObject("priorities", prioritiesList);

		mv.setViewName("ToDoTop");

		return mv;
	}

	/**
	 * タスク削除処理 削除フラグ利用
	 */
	@RequestMapping("/ToDoTop/delete/{tasksCode}")
	public ModelAndView deleteToDo(@PathVariable(name = "tasksCode") int tasksCode, ModelAndView mv) {

		// IDベースにレコード取ってくる
		Tasks task = new Tasks();
		task = tasksRepository.findById(tasksCode);

		// 削除フラグを立てる
		task.setIsDelete(1);

		// 更新した情報を保存
		tasksRepository.saveAndFlush(task);

		// DBからToDo情報を取得
		List<Tasks> tasksList = toDoService.notDeleteAllList();

		mv.addObject("tasks", tasksList);

		List<Types> typesList = typesRepository.findAll();
		mv.addObject("types", typesList);

		List<Priorities> prioritiesList = prioritiesRepository.findAll();
		mv.addObject("priorities", prioritiesList);

		mv.setViewName("ToDoTop");

		return mv;
	}

	/**
	 * タスク追加処理
	 */
	@RequestMapping(value = "/ToDoTop/add", method = RequestMethod.POST)
	public ModelAndView addToDo(@RequestParam(name = "priorityId") int priorityId,
			@RequestParam(name = "doneAt") String doneAt, @RequestParam(name = "content") String content,
			@RequestParam(name = "typeId") int typeId, ModelAndView mv) {

		// DBからToDo情報を取得

		// 持ってきた情報を詰める
		Tasks task = new Tasks(priorityId, doneAt, content, typeId, 0);

		// 詰めた情報を保存
		tasksRepository.saveAndFlush(task);

		// DBからToDo情報を取得
		List<Tasks> tasksList = toDoService.notDeleteAllList();

		mv.addObject("tasks", tasksList);

		List<Types> typesList = typesRepository.findAll();
		mv.addObject("types", typesList);

		List<Priorities> prioritiesList = prioritiesRepository.findAll();
		mv.addObject("priorities", prioritiesList);

		mv.setViewName("ToDoTop");

		return mv;
	}

	/**
	 * タスク完了処理 DB更新処理
	 */
	@RequestMapping(value = "/ToDoTop/done/{tasksCode}")
	public ModelAndView doneToDo(@PathVariable("tasksCode") int tasksCode, ModelAndView mv) {
//		1.Repositoryから完了にするタスクを取得(findById等)
//		2.取得するタスクのエンティティのクラスにisDoneCodeのセットメソッドを作成
//		3.1.で取得したタスクのエンティティオブジェクトに対し，2.で作成したセットメソッドを利用してisDoneCodeを書き換える
//		4.isDoneCodeの書き換わったエンティティオブジェクトをRepositoryを介してデータベースの更新を行う

		// IDベースにレコード取ってくる
		Tasks task = new Tasks();
		task = tasksRepository.findById(tasksCode);

		// 情報の更新
		task.isDoneCode(1);

		// 更新した情報を保存
		tasksRepository.saveAndFlush(task);

		// DBからToDo情報を取得
		List<Tasks> tasksList = toDoService.notDeleteAllList();

		mv.addObject("tasks", tasksList);

		List<Types> typesList = typesRepository.findAll();
		mv.addObject("types", typesList);

		List<Priorities> prioritiesList = prioritiesRepository.findAll();
		mv.addObject("priorities", prioritiesList);

		mv.setViewName("ToDoTop");

		return mv;
	}

	/**
	 * status = done:完了タスクのみ表示 yet:まだのタスクのみ表示 all:全タスク表示
	 */
	@RequestMapping(value = "/ToDoTop/list/{listStatus}")
	public ModelAndView listDone(@PathVariable("listStatus") String listStatus, ModelAndView mv) {

		List<Tasks> tasksList = new ArrayList<Tasks>();

		// 完了タスク表示
		if (listStatus.equals("done")) {
			tasksList = tasksRepository.findByIsDoneCodeAndIsDelete(1, 0);
		}
		// まだのタスク表示
		else if (listStatus.equals("yet")) {
			tasksList = tasksRepository.findByIsDoneCodeAndIsDelete(0, 0);
		}
		// 全タスク表示
		else {
			tasksList = toDoService.notDeleteAllList();
		}
		mv.addObject("tasks", tasksList);

		List<Types> typesList = typesRepository.findAll();
		mv.addObject("types", typesList);

		List<Priorities> prioritiesList = prioritiesRepository.findAll();
		mv.addObject("priorities", prioritiesList);

		mv.setViewName("ToDoTop");
		return mv;

	}

	/**
	 * タスクソート処理
	 */
	@RequestMapping(value = "/ToDoTop/sortList", method = RequestMethod.POST)
	public ModelAndView sortList(@RequestParam(name = "isDoneCode") int isDoneCode,
			@RequestParam(name = "priorityId") int priorityId, @RequestParam(name = "doneAt") String doneAt,
			@RequestParam(name = "typeId") int typeId, ModelAndView mv) {

		// DBからToDo情報を取得
//		List<Tasks> tasksList = tasksRepository.findByIsDoneCodeAndPriorityIdAndDoneAtAndTypeId(isDoneCode,priorityId,doneAt,typeId);
		List<Tasks> tasksList = toDoService.search(isDoneCode, priorityId, doneAt, typeId);
		mv.addObject("tasks", tasksList);
		List<Types> typesList = typesRepository.findAll();
		mv.addObject("types", typesList);

		List<Priorities> prioritiesList = prioritiesRepository.findAll();
		mv.addObject("priorities", prioritiesList);

		mv.setViewName("ToDoTop");

		return mv;
	}
//	private Specification<Tasks> companyNameEqual(String companyName) {
//	    return StringUtils.isEmpty(companyName) ? null : new Specification<Tasks>() {
//	        @Override
//	        public Predicate toPredicate(Root<Tasks> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
//	            return criteriaBuilder.equal(root.get("companyName"), companyName);
//	        }
//	    };
//	}
}
