package com.mlnxBBS.core;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import static org.hibernate.criterion.Example.create;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for User
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.mlnxBBS.core.User
 * @author MyEclipse Persistence Tools
 */
public class UserDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(UserDAO.class);
	// property constants
	public static final String UNAME = "uname";
	public static final String UPASS = "upass";
	public static final String UEMAIL = "uemail";
	public static final String UAGNAME = "uagname";
	public static final String UICON1 = "uicon1";
	public static final String UICON2 = "uicon2";
	public static final String UICON3 = "uicon3";
	public static final String HISTORY_DAYS = "historyDays";
	public static final String RUNNING_DAYS = "runningDays";
	public static final String USCORE = "uscore";
	public static final String USTATUS = "ustatus";

	public void save(User transientInstance) {
		log.debug("saving User instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(User persistentInstance) {
		log.debug("deleting User instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public User findById(java.lang.Integer id) {
		log.debug("getting User instance with id: " + id);
		try {
			User instance = (User) getSession()
					.get("com.mlnxBBS.core.User", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<User> findByExample(User instance) {
		log.debug("finding User instance by example");
		try {
			List<User> results = (List<User>) getSession()
					.createCriteria("com.mlnxBBS.core.User")
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
		log.debug("finding User instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from User as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<User> findByUname(Object uname) {
		return findByProperty(UNAME, uname);
	}

	public List<User> findByUpass(Object upass) {
		return findByProperty(UPASS, upass);
	}

	public List<User> findByUemail(Object uemail) {
		return findByProperty(UEMAIL, uemail);
	}

	public List<User> findByUagname(Object uagname) {
		return findByProperty(UAGNAME, uagname);
	}

	public List<User> findByUicon1(Object uicon1) {
		return findByProperty(UICON1, uicon1);
	}

	public List<User> findByUicon2(Object uicon2) {
		return findByProperty(UICON2, uicon2);
	}

	public List<User> findByUicon3(Object uicon3) {
		return findByProperty(UICON3, uicon3);
	}

	public List<User> findByHistoryDays(Object historyDays) {
		return findByProperty(HISTORY_DAYS, historyDays);
	}

	public List<User> findByRunningDays(Object runningDays) {
		return findByProperty(RUNNING_DAYS, runningDays);
	}

	public List<User> findByUscore(Object uscore) {
		return findByProperty(USCORE, uscore);
	}

	public List<User> findByUstatus(Object ustatus) {
		return findByProperty(USTATUS, ustatus);
	}

	public List findAll() {
		log.debug("finding all User instances");
		try {
			String queryString = "from User";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public User merge(User detachedInstance) {
		log.debug("merging User instance");
		try {
			User result = (User) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(User instance) {
		log.debug("attaching dirty User instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(User instance) {
		log.debug("attaching clean User instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}