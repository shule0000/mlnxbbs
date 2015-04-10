package com.mlnxBBS.core;

import java.util.List;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import static org.hibernate.criterion.Example.create;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * Qrcode entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.mlnxBBS.core.Qrcode
 * @author MyEclipse Persistence Tools
 */
public class QrcodeDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(QrcodeDAO.class);
	// property constants
	public static final String QR_NAME = "qrName";
	public static final String QR_IMG = "qrImg";
	public static final String QR_SIZE = "qrSize";
	public static final String QR_POSITION = "qrPosition";
	public static final String QR_STATUS = "qrStatus";

	public void save(Qrcode transientInstance) {
		log.debug("saving Qrcode instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Qrcode persistentInstance) {
		log.debug("deleting Qrcode instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Qrcode findById(java.lang.Integer id) {
		log.debug("getting Qrcode instance with id: " + id);
		try {
			Qrcode instance = (Qrcode) getSession().get(
					"com.mlnxBBS.core.Qrcode", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Qrcode> findByExample(Qrcode instance) {
		log.debug("finding Qrcode instance by example");
		try {
			List<Qrcode> results = (List<Qrcode>) getSession()
					.createCriteria("com.mlnxBBS.core.Qrcode")
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
		log.debug("finding Qrcode instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Qrcode as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Qrcode> findByQrName(Object qrName) {
		return findByProperty(QR_NAME, qrName);
	}

	public List<Qrcode> findByQrImg(Object qrImg) {
		return findByProperty(QR_IMG, qrImg);
	}

	public List<Qrcode> findByQrSize(Object qrSize) {
		return findByProperty(QR_SIZE, qrSize);
	}

	public List<Qrcode> findByQrPosition(Object qrPosition) {
		return findByProperty(QR_POSITION, qrPosition);
	}

	public List<Qrcode> findByQrStatus(Object qrStatus) {
		return findByProperty(QR_STATUS, qrStatus);
	}

	public List findAll() {
		log.debug("finding all Qrcode instances");
		try {
			String queryString = "from Qrcode";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Qrcode merge(Qrcode detachedInstance) {
		log.debug("merging Qrcode instance");
		try {
			Qrcode result = (Qrcode) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Qrcode instance) {
		log.debug("attaching dirty Qrcode instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Qrcode instance) {
		log.debug("attaching clean Qrcode instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}