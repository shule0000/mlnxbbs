package com.mlnxBBS.core;

import java.sql.Timestamp;
import java.util.List;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import static org.hibernate.criterion.Example.create;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for Event
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.mlnxBBS.core.Event
 * @author MyEclipse Persistence Tools
 */
public class EventDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(EventDAO.class);
	// property constants
	public static final String ETITLE = "etitle";
	public static final String ECONTENT = "econtent";
	public static final String EPRIORITY = "epriority";
	public static final String ESTATUS = "estatus";

	public void save(Event transientInstance) {
		log.debug("saving Event instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Event persistentInstance) {
		log.debug("deleting Event instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Event findById(java.lang.Integer id) {
		log.debug("getting Event instance with id: " + id);
		try {
			Event instance = (Event) getSession().get("com.mlnxBBS.core.Event",
					id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Event> findByExample(Event instance) {
		log.debug("finding Event instance by example");
		try {
			List<Event> results = (List<Event>) getSession()
					.createCriteria("com.mlnxBBS.core.Event")
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
		log.debug("finding Event instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Event as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Event> findByEtitle(Object etitle) {
		return findByProperty(ETITLE, etitle);
	}

	public List<Event> findByEcontent(Object econtent) {
		return findByProperty(ECONTENT, econtent);
	}

	public List<Event> findByEpriority(Object epriority) {
		return findByProperty(EPRIORITY, epriority);
	}

	public List<Event> findByEstatus(Object estatus) {
		return findByProperty(ESTATUS, estatus);
	}

	public List findAll() {
		log.debug("finding all Event instances");
		try {
			String queryString = "from Event";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Event merge(Event detachedInstance) {
		log.debug("merging Event instance");
		try {
			Event result = (Event) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Event instance) {
		log.debug("attaching dirty Event instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Event instance) {
		log.debug("attaching clean Event instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}