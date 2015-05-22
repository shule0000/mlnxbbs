package com.mlnxBBS.core;

import java.sql.Timestamp;
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
 * Response entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "response", catalog = "mlnx")
public class Response implements java.io.Serializable {

	// Fields

	private Integer rpId;
	private User userByReplyerId;
	private User userByToUid;
	private Post post;
	private String rpContent;
	private Timestamp rpTime;
	private Integer rpPosition;

	// Constructors

	/** default constructor */
	public Response() {
	}

	/** minimal constructor */
	public Response(User userByReplyerId, User userByToUid, Post post,
			String rpContent, Integer rpPosition) {
		this.userByReplyerId = userByReplyerId;
		this.userByToUid = userByToUid;
		this.post = post;
		this.rpContent = rpContent;
		this.rpPosition = rpPosition;
	}

	/** full constructor */
	public Response(User userByReplyerId, User userByToUid, Post post,
			String rpContent, Timestamp rpTime, Integer rpPosition) {
		this.userByReplyerId = userByReplyerId;
		this.userByToUid = userByToUid;
		this.post = post;
		this.rpContent = rpContent;
		this.rpTime = rpTime;
		this.rpPosition = rpPosition;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "rpId", unique = true, nullable = false)
	public Integer getRpId() {
		return this.rpId;
	}

	public void setRpId(Integer rpId) {
		this.rpId = rpId;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "replyerId", nullable = false)
	public User getUserByReplyerId() {
		return this.userByReplyerId;
	}

	public void setUserByReplyerId(User userByReplyerId) {
		this.userByReplyerId = userByReplyerId;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "toUid", nullable = false)
	public User getUserByToUid() {
		return this.userByToUid;
	}

	public void setUserByToUid(User userByToUid) {
		this.userByToUid = userByToUid;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "toPoid", nullable = false)
	public Post getPost() {
		return this.post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	@Column(name = "rpContent", nullable = false, length = 140)
	public String getRpContent() {
		return this.rpContent;
	}

	public void setRpContent(String rpContent) {
		this.rpContent = rpContent;
	}

	@Column(name = "rpTime", length = 19)
	public Timestamp getRpTime() {
		return this.rpTime;
	}

	public void setRpTime(Timestamp rpTime) {
		this.rpTime = rpTime;
	}

	@Column(name = "rpPosition", nullable = false)
	public Integer getRpPosition() {
		return this.rpPosition;
	}

	public void setRpPosition(Integer rpPosition) {
		this.rpPosition = rpPosition;
	}

}