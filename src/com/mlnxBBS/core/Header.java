package com.mlnxBBS.core;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Header entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "header", catalog = "mlnx")
public class Header implements java.io.Serializable {

	// Fields

	private Integer hid;
	private String logoImg;
	private String comName;
	private String logoSize;
	private Integer headerStatus;

	// Constructors

	/** default constructor */
	public Header() {
	}

	/** full constructor */
	public Header(String logoImg, String comName, String logoSize,
			Integer headerStatus) {
		this.logoImg = logoImg;
		this.comName = comName;
		this.logoSize = logoSize;
		this.headerStatus = headerStatus;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "hId", unique = true, nullable = false)
	public Integer getHid() {
		return this.hid;
	}

	public void setHid(Integer hid) {
		this.hid = hid;
	}

	@Column(name = "logoImg", nullable = false, length = 50)
	public String getLogoImg() {
		return this.logoImg;
	}

	public void setLogoImg(String logoImg) {
		this.logoImg = logoImg;
	}

	@Column(name = "comName", nullable = false, length = 50)
	public String getComName() {
		return this.comName;
	}

	public void setComName(String comName) {
		this.comName = comName;
	}

	@Column(name = "logoSize", nullable = false, length = 50)
	public String getLogoSize() {
		return this.logoSize;
	}

	public void setLogoSize(String logoSize) {
		this.logoSize = logoSize;
	}

	@Column(name = "headerStatus", nullable = false)
	public Integer getHeaderStatus() {
		return this.headerStatus;
	}

	public void setHeaderStatus(Integer headerStatus) {
		this.headerStatus = headerStatus;
	}

}