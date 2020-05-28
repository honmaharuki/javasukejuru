package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "priorities")
public class Priorities {
	@Id
	@Column(name = "code")
	private Integer code;

	@Column(name = "rank")
	private String rank;

	public Integer getCode() {
		return code;
	}

	public String getRank() {
		return rank;
	}

}
