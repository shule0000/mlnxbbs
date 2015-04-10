package com.mlnxBBS.core;

import java.util.List;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import static org.hibernate.criterion.Example.create;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * Navigation entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.mlnxBBS.core.Navigation
 * @author MyEclipse Persistence Tools
 */
public class NavigationDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(NavigationDAO.class);
	// property constants
	public static final String NAV_NAME = "navName";
	public static final String NAV_PRIORITY = "navPriority";
	public static final String NAV_TYPE = "navType";
	public static final String NAV_STATUS = "navStatus";

	public void save(Navigation transientInstance) {
		log.debug("saving Navigation instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Navigation persistentInstance) {
		log.debug("deleting Navigation instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Navigation findById(java.lang.Integer id) {
		log.debug("getting Navigation instance with id: " + id);
		try {
			Navigation instance = (Navigation) getSession().get(
					"com.mlnxBBS.core.Navigation", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Navigation> findByExample(Navigation instance) {
		log.debug("finding Navigation instance by example");
		try {
			List<Navigation> results = (List<Navigation>) getSession()
					.createCriteria("com.mlnxBBS.core.Navigation")
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
		log.debug("finding Navigation instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Navigation as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Navigation> findByNavName(Object navName) {
		return findByProperty(NAV_NAME, navName);
	}

	public List<Navigation> findByNavPriority(Object navPriority) {
		return findByProperty(NAV_PRIORITY, navPriority);
	}

	public List<Navigation> findByNavType(Object navType) {
		return findByProperty(NAV_TYPE, navType);
	}

	public List<Navigation> findByNavStatus(Object navStatus) {
		return findByProperty(NAV_STATUS, navStatus);
	}

	public List findAll() {
		log.debug("finding all Navigation instances");
		try {
			String queryString = "from Navigation";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Navigation merge(Navigation detachedInstance) {
		log.debug("merging Navigation instance");
		try {
			Navigation result = (Navigation) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Navigation instance) {
		log.debug("attaching dirty Navigation instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Navigation instance) {
		log.debug("attaching clean Navigation instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}