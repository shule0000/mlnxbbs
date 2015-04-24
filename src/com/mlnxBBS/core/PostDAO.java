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
 * A data access object (DAO) providing persistence and search support for Post
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.mlnxBBS.core.Post
 * @author MyEclipse Persistence Tools
 */
public class PostDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(PostDAO.class);
	// property constants
	public static final String PO_TITLE = "poTitle";
	public static final String PO_CONTENT = "poContent";
	public static final String PO_TYPE1 = "poType1";
	public static final String PO_TYPE2 = "poType2";
	public static final String PO_TYPE3 = "poType3";
	public static final String PO_PRAISE = "poPraise";
	public static final String PO_STATUS = "poStatus";

	public void save(Post transientInstance) {
		log.debug("saving Post instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Post persistentInstance) {
		log.debug("deleting Post instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Post findById(java.lang.Integer id) {
		log.debug("getting Post instance with id: " + id);
		try {
			Post instance = (Post) getSession()
					.get("com.mlnxBBS.core.Post", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Post> findByExample(Post instance) {
		log.debug("finding Post instance by example");
		try {
			List<Post> results = (List<Post>) getSession()
					.createCriteria("com.mlnxBBS.core.Post")
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
		log.debug("finding Post instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Post as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Post> findByPoTitle(Object poTitle) {
		return findByProperty(PO_TITLE, poTitle);
	}

	public List<Post> findByPoContent(Object poContent) {
		return findByProperty(PO_CONTENT, poContent);
	}

	public List<Post> findByPoType1(Object poType1) {
		return findByProperty(PO_TYPE1, poType1);
	}

	public List<Post> findByPoType2(Object poType2) {
		return findByProperty(PO_TYPE2, poType2);
	}

	public List<Post> findByPoType3(Object poType3) {
		return findByProperty(PO_TYPE3, poType3);
	}

	public List<Post> findByPoPraise(Object poPraise) {
		return findByProperty(PO_PRAISE, poPraise);
	}

	public List<Post> findByPoStatus(Object poStatus) {
		return findByProperty(PO_STATUS, poStatus);
	}

	public List findAll() {
		log.debug("finding all Post instances");
		try {
			String queryString = "from Post";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Post merge(Post detachedInstance) {
		log.debug("merging Post instance");
		try {
			Post result = (Post) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Post instance) {
		log.debug("attaching dirty Post instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Post instance) {
		log.debug("attaching clean Post instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}