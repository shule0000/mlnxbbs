package com.mlnxBBS.core;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Notice entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "notice", catalog = "mlnx")
public class Notice implements java.io.Serializable {

	// Fields

	private Integer ntId;
	private String ntTitle;
	private String ntContent;
	private Timestamp ntTime;
	private Integer ntPriority;
	private Integer ntStatus;

	// Constructors

	/** default constructor */
	public Notice() {
	}

	/** minimal constructor */
	public Notice(String ntTitle, String ntContent, Integer ntPriority,
			Integer ntStatus) {
		this.ntTitle = ntTitle;
		this.ntContent = ntContent;
		this.ntPriority = ntPriority;
		this.ntStatus = ntStatus;
	}

	/** full constructor */
	public Notice(String ntTitle, String ntContent, Timestamp ntTime,
			Integer ntPriority, Integer ntStatus) {
		this.ntTitle = ntTitle;
		this.ntContent = ntContent;
		this.ntTime = ntTime;
		this.ntPriority = ntPriority;
		this.ntStatus = ntStatus;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ntId", unique = true, nullable = false)
	public Integer getNtId() {
		return this.ntId;
	}

	public void setNtId(Integer ntId) {
		this.ntId = ntId;
	}

	@Column(name = "ntTitle", nullable = false, length = 50)
	public String getNtTitle() {
		return this.ntTitle;
	}

	public void setNtTitle(String ntTitle) {
		this.ntTitle = ntTitle;
	}

	@Column(name = "ntContent", nullable = false, length = 4000)
	public String getNtContent() {
		return this.ntContent;
	}

	public void setNtContent(String ntContent) {
		this.ntContent = ntContent;
	}

	@Column(name = "ntTime", length = 19)
	public Timestamp getNtTime() {
		return this.ntTime;
	}

	public void setNtTime(Timestamp ntTime) {
		this.ntTime = ntTime;
	}

	@Column(name = "ntPriority", nullable = false)
	public Integer getNtPriority() {
		return this.ntPriority;
	}

	public void setNtPriority(Integer ntPriority) {
		this.ntPriority = ntPriority;
	}

	@Column(name = "ntStatus", nullable = false)
	public Integer getNtStatus() {
		return this.ntStatus;
	}

	public void setNtStatus(Integer ntStatus) {
		this.ntStatus = ntStatus;
	}

}