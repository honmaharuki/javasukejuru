package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.demo.entity.Tasks;

public interface TasksRepository extends JpaRepository<Tasks, Integer>, JpaSpecificationExecutor<Tasks> {

	// Tasks どのでとってくるか
	Tasks findById(int tasksCode);

	// 削除フラグが立っていないもののみ取ってくる
	List<Tasks> findByIsDelete(int isDelete);

//	
//	全部取ってきてそこから選別もありだよね
	// 完了済みを取得
	List<Tasks> findByIsDoneCodeAndIsDelete(int isDoneCode, int isDelete);

	// ソート結果を出すやつだよ！
	List<Tasks> findByIsDoneCodeAndPriorityIdAndDoneAtAndTypeId(int isDoneCode, int priorityId, String doneAt,
			int typeId);

}
