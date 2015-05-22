package com.mlnxBBS.core;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Contact entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "contact", catalog = "mlnx")
public class Contact implements java.io.Serializable {

	// Fields

	private Integer ctId;
	private String ctPhone;
	private String ctAdress;
	private String ctEmail;
	private String ctFax;
	private Integer ctStatus;

	// Constructors

	/** default constructor */
	public Contact() {
	}

	/** full constructor */
	public Contact(String ctPhone, String ctAdress, String ctEmail,
			String ctFax, Integer ctStatus) {
		this.ctPhone = ctPhone;
		this.ctAdress = ctAdress;
		this.ctEmail = ctEmail;
		this.ctFax = ctFax;
		this.ctStatus = ctStatus;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ctId", unique = true, nullable = false)
	public Integer getCtId() {
		return this.ctId;
	}

	public void setCtId(Integer ctId) {
		this.ctId = ctId;
	}

	@Column(name = "ctPhone", nullable = false, length = 50)
	public String getCtPhone() {
		return this.ctPhone;
	}

	public void setCtPhone(String ctPhone) {
		this.ctPhone = ctPhone;
	}

	@Column(name = "ctAdress", nullable = false, length = 50)
	public String getCtAdress() {
		return this.ctAdress;
	}

	public void setCtAdress(String ctAdress) {
		this.ctAdress = ctAdress;
	}

	@Column(name = "ctEmail", nullable = false, length = 50)
	public String getCtEmail() {
		return this.ctEmail;
	}

	public void setCtEmail(String ctEmail) {
		this.ctEmail = ctEmail;
	}

	@Column(name = "ctFax", nullable = false, length = 50)
	public String getCtFax() {
		return this.ctFax;
	}

	public void setCtFax(String ctFax) {
		this.ctFax = ctFax;
	}

	@Column(name = "ctStatus", nullable = false)
	public Integer getCtStatus() {
		return this.ctStatus;
	}

	public void setCtStatus(Integer ctStatus) {
		this.ctStatus = ctStatus;
	}

}