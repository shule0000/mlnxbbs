package com.mlnxBBS.core;

import java.util.List;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import static org.hibernate.criterion.Example.create;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * Header entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.mlnxBBS.core.Header
 * @author MyEclipse Persistence Tools
 */
public class HeaderDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(HeaderDAO.class);
	// property constants
	public static final String LOGO_IMG = "logoImg";
	public static final String COM_NAME = "comName";
	public static final String LOGO_SIZE = "logoSize";
	public static final String HEADER_STATUS = "headerStatus";

	public void save(Header transientInstance) {
		log.debug("saving Header instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Header persistentInstance) {
		log.debug("deleting Header instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Header findById(java.lang.Integer id) {
		log.debug("getting Header instance with id: " + id);
		try {
			Header instance = (Header) getSession().get(
					"com.mlnxBBS.core.Header", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Header> findByExample(Header instance) {
		log.debug("finding Header instance by example");
		try {
			List<Header> results = (List<Header>) getSession()
					.createCriteria("com.mlnxBBS.core.Header")
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
		log.debug("finding Header instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Header as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Header> findByLogoImg(Object logoImg) {
		return findByProperty(LOGO_IMG, logoImg);
	}

	public List<Header> findByComName(Object comName) {
		return findByProperty(COM_NAME, comName);
	}

	public List<Header> findByLogoSize(Object logoSize) {
		return findByProperty(LOGO_SIZE, logoSize);
	}

	public List<Header> findByHeaderStatus(Object headerStatus) {
		return findByProperty(HEADER_STATUS, headerStatus);
	}

	public List findAll() {
		log.debug("finding all Header instances");
		try {
			String queryString = "from Header";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Header merge(Header detachedInstance) {
		log.debug("merging Header instance");
		try {
			Header result = (Header) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Header instance) {
		log.debug("attaching dirty Header instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Header instance) {
		log.debug("attaching clean Header instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}