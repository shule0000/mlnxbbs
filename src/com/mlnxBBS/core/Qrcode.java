package com.mlnxBBS.core;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Qrcode entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "qrcode", catalog = "mlnx")
public class Qrcode implements java.io.Serializable {

	// Fields

	private Integer qid;
	private String qrName;
	private String qrImg;
	private String qrSize;
	private Integer qrPosition;
	private Integer qrStatus;

	// Constructors

	/** default constructor */
	public Qrcode() {
	}

	/** full constructor */
	public Qrcode(String qrName, String qrImg, String qrSize,
			Integer qrPosition, Integer qrStatus) {
		this.qrName = qrName;
		this.qrImg = qrImg;
		this.qrSize = qrSize;
		this.qrPosition = qrPosition;
		this.qrStatus = qrStatus;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "qId", unique = true, nullable = false)
	public Integer getQid() {
		return this.qid;
	}

	public void setQid(Integer qid) {
		this.qid = qid;
	}

	@Column(name = "qrName", nullable = false, length = 50)
	public String getQrName() {
		return this.qrName;
	}

	public void setQrName(String qrName) {
		this.qrName = qrName;
	}

	@Column(name = "qrImg", nullable = false, length = 50)
	public String getQrImg() {
		return this.qrImg;
	}

	public void setQrImg(String qrImg) {
		this.qrImg = qrImg;
	}

	@Column(name = "qrSize", nullable = false, length = 50)
	public String getQrSize() {
		return this.qrSize;
	}

	public void setQrSize(String qrSize) {
		this.qrSize = qrSize;
	}

	@Column(name = "qrPosition", nullable = false)
	public Integer getQrPosition() {
		return this.qrPosition;
	}

	public void setQrPosition(Integer qrPosition) {
		this.qrPosition = qrPosition;
	}

	@Column(name = "qrStatus", nullable = false)
	public Integer getQrStatus() {
		return this.qrStatus;
	}

	public void setQrStatus(Integer qrStatus) {
		this.qrStatus = qrStatus;
	}

}