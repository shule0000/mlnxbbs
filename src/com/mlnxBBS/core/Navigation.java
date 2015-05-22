package com.mlnxBBS.core;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Navigation entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "navigation", catalog = "mlnx")
public class Navigation implements java.io.Serializable {

	// Fields

	private Integer nid;
	private String navName;
	private Integer navPriority;
	private Integer navType;
	private Integer navStatus;

	// Constructors

	/** default constructor */
	public Navigation() {
	}

	/** full constructor */
	public Navigation(String navName, Integer navPriority, Integer navType,
			Integer navStatus) {
		this.navName = navName;
		this.navPriority = navPriority;
		this.navType = navType;
		this.navStatus = navStatus;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "nId", unique = true, nullable = false)
	public Integer getNid() {
		return this.nid;
	}

	public void setNid(Integer nid) {
		this.nid = nid;
	}

	@Column(name = "navName", nullable = false, length = 300)
	public String getNavName() {
		return this.navName;
	}

	public void setNavName(String navName) {
		this.navName = navName;
	}

	@Column(name = "navPriority", nullable = false)
	public Integer getNavPriority() {
		return this.navPriority;
	}

	public void setNavPriority(Integer navPriority) {
		this.navPriority = navPriority;
	}

	@Column(name = "navType", nullable = false)
	public Integer getNavType() {
		return this.navType;
	}

	public void setNavType(Integer navType) {
		this.navType = navType;
	}

	@Column(name = "navStatus", nullable = false)
	public Integer getNavStatus() {
		return this.navStatus;
	}

	public void setNavStatus(Integer navStatus) {
		this.navStatus = navStatus;
	}

}