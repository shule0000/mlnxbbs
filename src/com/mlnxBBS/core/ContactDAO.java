package com.mlnxBBS.core;

import java.util.List;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import static org.hibernate.criterion.Example.create;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * Contact entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.mlnxBBS.core.Contact
 * @author MyEclipse Persistence Tools
 */
public class ContactDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(ContactDAO.class);
	// property constants
	public static final String CT_PHONE = "ctPhone";
	public static final String CT_ADRESS = "ctAdress";
	public static final String CT_EMAIL = "ctEmail";
	public static final String CT_FAX = "ctFax";
	public static final String CT_STATUS = "ctStatus";

	public void save(Contact transientInstance) {
		log.debug("saving Contact instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Contact persistentInstance) {
		log.debug("deleting Contact instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Contact findById(java.lang.Integer id) {
		log.debug("getting Contact instance with id: " + id);
		try {
			Contact instance = (Contact) getSession().get(
					"com.mlnxBBS.core.Contact", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Contact> findByExample(Contact instance) {
		log.debug("finding Contact instance by example");
		try {
			List<Contact> results = (List<Contact>) getSession()
					.createCriteria("com.mlnxBBS.core.Contact")
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
		log.debug("finding Contact instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Contact as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Contact> findByCtPhone(Object ctPhone) {
		return findByProperty(CT_PHONE, ctPhone);
	}

	public List<Contact> findByCtAdress(Object ctAdress) {
		return findByProperty(CT_ADRESS, ctAdress);
	}

	public List<Contact> findByCtEmail(Object ctEmail) {
		return findByProperty(CT_EMAIL, ctEmail);
	}

	public List<Contact> findByCtFax(Object ctFax) {
		return findByProperty(CT_FAX, ctFax);
	}

	public List<Contact> findByCtStatus(Object ctStatus) {
		return findByProperty(CT_STATUS, ctStatus);
	}

	public List findAll() {
		log.debug("finding all Contact instances");
		try {
			String queryString = "from Contact";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Contact merge(Contact detachedInstance) {
		log.debug("merging Contact instance");
		try {
			Contact result = (Contact) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Contact instance) {
		log.debug("attaching dirty Contact instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Contact instance) {
		log.debug("attaching clean Contact instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}