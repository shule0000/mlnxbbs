package com.mlnxBBS.core;

import java.util.List;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import static org.hibernate.criterion.Example.create;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * Province entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.mlnxBBS.core.Province
 * @author MyEclipse Persistence Tools
 */
public class ProvinceDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(ProvinceDAO.class);
	// property constants
	public static final String PROVINCE_NAME = "provinceName";

	public void save(Province transientInstance) {
		log.debug("saving Province instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Province persistentInstance) {
		log.debug("deleting Province instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Province findById(java.lang.Integer id) {
		log.debug("getting Province instance with id: " + id);
		try {
			Province instance = (Province) getSession().get(
					"com.mlnxBBS.core.Province", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Province> findByExample(Province instance) {
		log.debug("finding Province instance by example");
		try {
			List<Province> results = (List<Province>) getSession()
					.createCriteria("com.mlnxBBS.core.Province")
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
		log.debug("finding Province instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Province as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Province> findByProvinceName(Object provinceName) {
		return findByProperty(PROVINCE_NAME, provinceName);
	}

	public List findAll() {
		log.debug("finding all Province instances");
		try {
			String queryString = "from Province";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Province merge(Province detachedInstance) {
		log.debug("merging Province instance");
		try {
			Province result = (Province) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Province instance) {
		log.debug("attaching dirty Province instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Province instance) {
		log.debug("attaching clean Province instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}