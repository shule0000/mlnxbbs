package com.mlnxBBS.core;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Event entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "event", catalog = "mlnx")
public class Event implements java.io.Serializable {

	// Fields

	private Integer eid;
	private String etitle;
	private String econtent;
	private Timestamp etime;
	private Integer epriority;
	private Integer estatus;

	// Constructors

	/** default constructor */
	public Event() {
	}

	/** minimal constructor */
	public Event(String etitle, String econtent, Integer epriority,
			Integer estatus) {
		this.etitle = etitle;
		this.econtent = econtent;
		this.epriority = epriority;
		this.estatus = estatus;
	}

	/** full constructor */
	public Event(String etitle, String econtent, Timestamp etime,
			Integer epriority, Integer estatus) {
		this.etitle = etitle;
		this.econtent = econtent;
		this.etime = etime;
		this.epriority = epriority;
		this.estatus = estatus;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "eId", unique = true, nullable = false)
	public Integer getEid() {
		return this.eid;
	}

	public void setEid(Integer eid) {
		this.eid = eid;
	}

	@Column(name = "eTitle", nullable = false, length = 50)
	public String getEtitle() {
		return this.etitle;
	}

	public void setEtitle(String etitle) {
		this.etitle = etitle;
	}

	@Column(name = "eContent", nullable = false, length = 4000)
	public String getEcontent() {
		return this.econtent;
	}

	public void setEcontent(String econtent) {
		this.econtent = econtent;
	}

	@Column(name = "eTime", length = 19)
	public Timestamp getEtime() {
		return this.etime;
	}

	public void setEtime(Timestamp etime) {
		this.etime = etime;
	}

	@Column(name = "ePriority", nullable = false)
	public Integer getEpriority() {
		return this.epriority;
	}

	public void setEpriority(Integer epriority) {
		this.epriority = epriority;
	}

	@Column(name = "eStatus", nullable = false)
	public Integer getEstatus() {
		return this.estatus;
	}

	public void setEstatus(Integer estatus) {
		this.estatus = estatus;
	}

}