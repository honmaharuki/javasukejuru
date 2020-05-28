package com.example.demo.repository.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.example.demo.entity.*;
import com.example.demo.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TasksRepositoryCustomImpl implements TasksRepositoryCustom {

	@Autowired
	EntityManager manager;

	@SuppressWarnings("unchecked")
	@Override
	public List<Tasks> search(int isDoneCode, int priorityId, String doneAt, int typeId) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT g From Tasks g WHERE ");
		boolean andFlg = false;
		boolean isDoneCodeFlg = false;
		boolean priorityIdFlg = false;
		boolean doneAtFlg = false;
		boolean typeIdFlg = false;
		if (isDoneCode != 3) {
			sql.append(" g.isDoneCode = " + isDoneCode);
			isDoneCodeFlg = true;
			andFlg = true;
		}
		if (priorityId != 0) {
			if (andFlg)
				sql.append(" AND ");
			sql.append("g.priorityId = " + priorityId);
			priorityIdFlg = true;
			andFlg = true;
		}
		if (!"".equals(doneAt)) {
			if (andFlg)
				sql.append(" AND ");
			sql.append("g.doneAt = '" + doneAt + "'");
			doneAtFlg = true;
			andFlg = true;
		}
		if (typeId != 0) {
			if (andFlg)
				sql.append(" AND ");
			sql.append("g.typeId = " + typeId);
			typeIdFlg = true;
			andFlg = true;
		}
		if (andFlg)sql.append(" AND ");
		sql.append("g.isDelete = 0");
		typeIdFlg = true;
		andFlg = true;

		Query query = manager.createQuery(sql.toString());
//		if (isDoneCodeFlg) query.setParameter("isDoneCode",isDoneCode);
//		if (priorityIdFlg) query.setParameter("priorityId",priorityId);
//		if (doneAtFlg) query.setParameter("doneAt",doneAt);
//		if (typeIdFlg) query.setParameter("typeId",typeId);
		return query.getResultList();
	}

}