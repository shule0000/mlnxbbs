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

/**
 * Praise entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "praise", catalog = "mlnx")
public class Praise implements java.io.Serializable {

	// Fields

	private Integer paId;
	private Post post;
	private User user;

	// Constructors

	/** default constructor */
	public Praise() {
	}

	/** full constructor */
	public Praise(Post post, User user) {
		this.post = post;
		this.user = user;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "paId", unique = true, nullable = false)
	public Integer getPaId() {
		return this.paId;
	}

	public void setPaId(Integer paId) {
		this.paId = paId;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "toPid", nullable = false)
	public Post getPost() {
		return this.post;
	}

	public void setPost(Post post) {
		this.post = post;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "praiserId", nullable = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}