package com.mlnxBBS.core;

import java.util.List;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import static org.hibernate.criterion.Example.create;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * Banner entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.mlnxBBS.core.Banner
 * @author MyEclipse Persistence Tools
 */
public class BannerDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(BannerDAO.class);
	// property constants
	public static final String BAN_IMG = "banImg";
	public static final String BAN_SIZE = "banSize";
	public static final String BAN_PRIORITY = "banPriority";
	public static final String BAN_STATUS = "banStatus";

	public void save(Banner transientInstance) {
		log.debug("saving Banner instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Banner persistentInstance) {
		log.debug("deleting Banner instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Banner findById(java.lang.Integer id) {
		log.debug("getting Banner instance with id: " + id);
		try {
			Banner instance = (Banner) getSession().get(
					"com.mlnxBBS.core.Banner", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Banner> findByExample(Banner instance) {
		log.debug("finding Banner instance by example");
		try {
			List<Banner> results = (List<Banner>) getSession()
					.createCriteria("com.mlnxBBS.core.Banner")
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
		log.debug("finding Banner instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Banner as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Banner> findByBanImg(Object banImg) {
		return findByProperty(BAN_IMG, banImg);
	}

	public List<Banner> findByBanSize(Object banSize) {
		return findByProperty(BAN_SIZE, banSize);
	}

	public List<Banner> findByBanPriority(Object banPriority) {
		return findByProperty(BAN_PRIORITY, banPriority);
	}

	public List<Banner> findByBanStatus(Object banStatus) {
		return findByProperty(BAN_STATUS, banStatus);
	}

	public List findAll() {
		log.debug("finding all Banner instances");
		try {
			String queryString = "from Banner";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Banner merge(Banner detachedInstance) {
		log.debug("merging Banner instance");
		try {
			Banner result = (Banner) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Banner instance) {
		log.debug("attaching dirty Banner instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Banner instance) {
		log.debug("attaching clean Banner instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}