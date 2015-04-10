package com.mlnxBBS.core;

import java.sql.ResultSet;
import java.util.Iterator;
import java.util.SortedMap;

import javax.servlet.jsp.jstl.sql.Result;
import javax.servlet.jsp.jstl.sql.ResultSupport;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Data access object (DAO) for domain model
 * 
 * @author MyEclipse Persistence Tools
 */
public class BaseHibernateDAO implements IBaseHibernateDAO {

	/**
	 * 执行更新
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	public int execUpdate(String sql, Object[] params) {
		Session session = this.getSession();
		SQLQuery sqlQuery = session.createSQLQuery(sql);
		for (int i = 0; i < params.length; i++) {
			sqlQuery.setParameter(i, params[i]);
		}

		return sqlQuery.executeUpdate();
	}

	/**
	 * 执行查询
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	public SortedMap[] executeQuery(String sql, Object[] params) {

		SortedMap[] sms;

		Session session = this.getSession();
		MyReturningWork myReturningWork = new MyReturningWork();
		myReturningWork.setSql(sql);
		myReturningWork.setParams(params);
		ResultSet resultSet = session.doReturningWork(myReturningWork);

		// 转换结果集为jstl的Result
		Result r = ResultSupport.toResult(resultSet);

		// 最终转换成SortedMap数组，方便jstl页面直接使用
		// 页面上使用规则：el表达式 {表名.数据库列名}
		sms = r.getRows();

		return sms;
	}

	/**
	 * 根据id删除表中的数据
	 * 
	 * @param tableName
	 * @param id
	 * @return
	 */
	public int execDelete(String tableName, int id, String colName) {
		Object[] params = { id };
		return this.execUpdate("delete from " + tableName + " where " + colName
				+ "=?", params);
	}

	/**
	 * 更新对象
	 * 
	 * @param object
	 */
	public void updateObject(Object object) {
		Transaction tx = HibernateSessionFactory.getSession()
				.beginTransaction();
		HibernateSessionFactory.getSession().update(object);
		tx.commit();
	}

	public Session getSession() {
		return HibernateSessionFactory.getSession();
	}

}