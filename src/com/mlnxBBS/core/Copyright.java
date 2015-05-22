package com.mlnxBBS.core;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Copyright entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "copyright", catalog = "mlnx")
public class Copyright implements java.io.Serializable {

	// Fields

	private Integer cpId;
	private String cpDetail;
	private Integer cpStatus;

	// Constructors

	/** default constructor */
	public Copyright() {
	}

	/** full constructor */
	public Copyright(String cpDetail, Integer cpStatus) {
		this.cpDetail = cpDetail;
		this.cpStatus = cpStatus;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "cpId", unique = true, nullable = false)
	public Integer getCpId() {
		return this.cpId;
	}

	public void setCpId(Integer cpId) {
		this.cpId = cpId;
	}

	@Column(name = "cpDetail", nullable = false, length = 2000)
	public String getCpDetail() {
		return this.cpDetail;
	}

	public void setCpDetail(String cpDetail) {
		this.cpDetail = cpDetail;
	}

	@Column(name = "cpStatus", nullable = false)
	public Integer getCpStatus() {
		return this.cpStatus;
	}

	public void setCpStatus(Integer cpStatus) {
		this.cpStatus = cpStatus;
	}

}