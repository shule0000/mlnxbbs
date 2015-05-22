package com.mlnxBBS.core;

import java.util.List;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import static org.hibernate.criterion.Example.create;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * Userinfo entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.mlnxBBS.core.Userinfo
 * @author MyEclipse Persistence Tools
 */
public class UserinfoDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(UserinfoDAO.class);
	// property constants
	public static final String UREALNAME = "urealname";
	public static final String USEX = "usex";
	public static final String URESIDENCE = "uresidence";
	public static final String UBIRTHDAY = "ubirthday";
	public static final String UBIRTHPLACE = "ubirthplace";
	public static final String UREMARK1 = "uremark1";
	public static final String UREMARK2 = "uremark2";

	public void save(Userinfo transientInstance) {
		log.debug("saving Userinfo instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Userinfo persistentInstance) {
		log.debug("deleting Userinfo instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Userinfo findById(java.lang.Integer id) {
		log.debug("getting Userinfo instance with id: " + id);
		try {
			Userinfo instance = (Userinfo) getSession().get(
					"com.mlnxBBS.core.Userinfo", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Userinfo> findByExample(Userinfo instance) {
		log.debug("finding Userinfo instance by example");
		try {
			List<Userinfo> results = (List<Userinfo>) getSession()
					.createCriteria("com.mlnxBBS.core.Userinfo")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Userinfo instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Userinfo as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Userinfo> findByUrealname(Object urealname) {
		return findByProperty(UREALNAME, urealname);
	}

	public List<Userinfo> findByUsex(Object usex) {
		return findByProperty(USEX, usex);
	}

	public List<Userinfo> findByUresidence(Object uresidence) {
		return findByProperty(URESIDENCE, uresidence);
	}

	public List<Userinfo> findByUbirthday(Object ubirthday) {
		return findByProperty(UBIRTHDAY, ubirthday);
	}

	public List<Userinfo> findByUbirthplace(Object ubirthplace) {
		return findByProperty(UBIRTHPLACE, ubirthplace);
	}

	public List<Userinfo> findByUremark1(Object uremark1) {
		return findByProperty(UREMARK1, uremark1);
	}

	public List<Userinfo> findByUremark2(Object uremark2) {
		return findByProperty(UREMARK2, uremark2);
	}

	public List findAll() {
		log.debug("finding all Userinfo instances");
		try {
			String queryString = "from Userinfo";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Userinfo merge(Userinfo detachedInstance) {
		log.debug("merging Userinfo instance");
		try {
			Userinfo result = (Userinfo) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Userinfo instance) {
		log.debug("attaching dirty Userinfo instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Userinfo instance) {
		log.debug("attaching clean Userinfo instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}