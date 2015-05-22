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
 * Collection entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "collection", catalog = "mlnx")
public class Collection implements java.io.Serializable {

	// Fields

	private Integer coId;
	private Post post;
	private User user;

	// Constructors

	/** default constructor */
	public Collection() {
	}

	/** full constructor */
	public Collection(Post post, User user) {
		this.post = post;
		this.user = user;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "coId", unique = true, nullable = false)
	public Integer getCoId() {
		return this.coId;
	}

	public void setCoId(Integer coId) {
		this.coId = coId;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "coPoid", nullable = false)
	public Post getPost() {
		return this.post;
	}

	public void setPost(Post post) {
		this.post = post;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "coUid", nullable = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}