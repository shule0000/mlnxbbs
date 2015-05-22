package com.mlnxBBS.core;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Userinfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "userinfo", catalog = "mlnx", uniqueConstraints = @UniqueConstraint(columnNames = "uId"))
public class Userinfo implements java.io.Serializable {

	// Fields

	private Integer infoId;
	private User user;
	private String urealname;
	private String usex;
	private String uresidence;
	private String ubirthday;
	private String ubirthplace;
	private String uremark1;
	private String uremark2;

	// Constructors

	/** default constructor */
	public Userinfo() {
	}

	/** minimal constructor */
	public Userinfo(User user) {
		this.user = user;
	}

	/** full constructor */
	public Userinfo(User user, String urealname, String usex,
			String uresidence, String ubirthday, String ubirthplace,
			String uremark1, String uremark2) {
		this.user = user;
		this.urealname = urealname;
		this.usex = usex;
		this.uresidence = uresidence;
		this.ubirthday = ubirthday;
		this.ubirthplace = ubirthplace;
		this.uremark1 = uremark1;
		this.uremark2 = uremark2;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "infoId", unique = true, nullable = false)
	public Integer getInfoId() {
		return this.infoId;
	}

	public void setInfoId(Integer infoId) {
		this.infoId = infoId;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "uId", unique = true, nullable = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "uRealname", length = 50)
	public String getUrealname() {
		return this.urealname;
	}

	public void setUrealname(String urealname) {
		this.urealname = urealname;
	}

	@Column(name = "uSex", length = 50)
	public String getUsex() {
		return this.usex;
	}

	public void setUsex(String usex) {
		this.usex = usex;
	}

	@Column(name = "uResidence", length = 50)
	public String getUresidence() {
		return this.uresidence;
	}

	public void setUresidence(String uresidence) {
		this.uresidence = uresidence;
	}

	@Column(name = "uBirthday", length = 50)
	public String getUbirthday() {
		return this.ubirthday;
	}

	public void setUbirthday(String ubirthday) {
		this.ubirthday = ubirthday;
	}

	@Column(name = "uBirthplace", length = 50)
	public String getUbirthplace() {
		return this.ubirthplace;
	}

	public void setUbirthplace(String ubirthplace) {
		this.ubirthplace = ubirthplace;
	}

	@Column(name = "uRemark1", length = 300)
	public String getUremark1() {
		return this.uremark1;
	}

	public void setUremark1(String uremark1) {
		this.uremark1 = uremark1;
	}

	@Column(name = "uRemark2", length = 300)
	public String getUremark2() {
		return this.uremark2;
	}

	public void setUremark2(String uremark2) {
		this.uremark2 = uremark2;
	}

}