package com.mlnxBBS.core;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Banner entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "banner", catalog = "mlnx")
public class Banner implements java.io.Serializable {

	// Fields

	private Integer bid;
	private String banImg;
	private String banSize;
	private Integer banPriority;
	private Integer banStatus;

	// Constructors

	/** default constructor */
	public Banner() {
	}

	/** full constructor */
	public Banner(String banImg, String banSize, Integer banPriority,
			Integer banStatus) {
		this.banImg = banImg;
		this.banSize = banSize;
		this.banPriority = banPriority;
		this.banStatus = banStatus;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "bId", unique = true, nullable = false)
	public Integer getBid() {
		return this.bid;
	}

	public void setBid(Integer bid) {
		this.bid = bid;
	}

	@Column(name = "banImg", nullable = false, length = 50)
	public String getBanImg() {
		return this.banImg;
	}

	public void setBanImg(String banImg) {
		this.banImg = banImg;
	}

	@Column(name = "banSize", nullable = false, length = 50)
	public String getBanSize() {
		return this.banSize;
	}

	public void setBanSize(String banSize) {
		this.banSize = banSize;
	}

	@Column(name = "banPriority", nullable = false)
	public Integer getBanPriority() {
		return this.banPriority;
	}

	public void setBanPriority(Integer banPriority) {
		this.banPriority = banPriority;
	}

	@Column(name = "banStatus", nullable = false)
	public Integer getBanStatus() {
		return this.banStatus;
	}

	public void setBanStatus(Integer banStatus) {
		this.banStatus = banStatus;
	}

}