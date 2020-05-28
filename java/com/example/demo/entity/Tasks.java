package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tasks")
public class Tasks {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // IDENTITYで自動でcodeに入るよ
	@Column(name = "code")
	private Integer code;

	@Column(name = "entry_at")
	private String entryAt;

	@Column(name = "done_at")
	private String doneAt;

	@Column(name = "priority_id")
	private Integer priorityId;

	@Column(name = "type_id")
	private Integer typeId;

	@Column(name = "is_done_code")
	private Integer isDoneCode;

	@Column(name = "content")
	private String content;

	@Column(name = "message")

	private String message;

	@Column(name = "is_delete")
	private Integer isDelete;

	public Tasks(int priorityId, String doneAt, String content, int typeId, int isDoneCode) {
		super();
		this.priorityId = priorityId;
		this.doneAt = doneAt;
		this.content = content;
		this.typeId = typeId;
		this.isDoneCode = isDoneCode;
		this.isDelete = 0;

	}

	public Tasks() {
	}

	public void isDoneCode(int isDoneCode) {
		this.isDoneCode = isDoneCode;
	}

	public void editToDo(int priorityId, String doneAt, String content, int typeId, int isDoneCode) {
		this.priorityId = priorityId;
		this.doneAt = doneAt;
		this.content = content;
		this.typeId = typeId;
		this.isDoneCode = isDoneCode;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getEntryAt() {
		return entryAt;
	}

	public void setEntryAt(String entryAt) {
		this.entryAt = entryAt;
	}

	public String getDoneAt() {
		return doneAt;
	}

	public void setDoneAt(String doneAt) {
		this.doneAt = doneAt;
	}

	public Integer getPriorityId() {
		return priorityId;
	}

	public void setPriorityId(Integer priorityId) {
		this.priorityId = priorityId;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public Integer getIsDoneCode() {
		return isDoneCode;
	}

	public void setIsDoneCode(Integer isDoneCode) {
		this.isDoneCode = isDoneCode;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

}
