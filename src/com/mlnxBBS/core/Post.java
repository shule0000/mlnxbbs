package com.mlnxBBS.core;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Post entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "post", catalog = "mlnx")
public class Post implements java.io.Serializable {

	// Fields

	private Integer poId;
	private User user;
	private String poTitle;
	private String poContent;
	private Timestamp poTime;
	private Integer poType;
	private Integer poPraise;
	private Integer poStatus;
	private Set<Praise> praises = new HashSet<Praise>(0);
	private Set<Response> responses = new HashSet<Response>(0);

	// Constructors

	/** default constructor */
	public Post() {
	}

	/** minimal constructor */
	public Post(User user, String poTitle, String poContent, Integer poType,
			Integer poPraise, Integer poStatus) {
		this.user = user;
		this.poTitle = poTitle;
		this.poContent = poContent;
		this.poType = poType;
		this.poPraise = poPraise;
		this.poStatus = poStatus;
	}

	/** full constructor */
	public Post(User user, String poTitle, String poContent, Timestamp poTime,
			Integer poType, Integer poPraise, Integer poStatus,
			Set<Praise> praises, Set<Response> responses) {
		this.user = user;
		this.poTitle = poTitle;
		this.poContent = poContent;
		this.poTime = poTime;
		this.poType = poType;
		this.poPraise = poPraise;
		this.poStatus = poStatus;
		this.praises = praises;
		this.responses = responses;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "poId", unique = true, nullable = false)
	public Integer getPoId() {
		return this.poId;
	}

	public void setPoId(Integer poId) {
		this.poId = poId;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "authorId", nullable = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "poTitle", nullable = false, length = 50)
	public String getPoTitle() {
		return this.poTitle;
	}

	public void setPoTitle(String poTitle) {
		this.poTitle = poTitle;
	}

	@Column(name = "poContent", nullable = false, length = 4000)
	public String getPoContent() {
		return this.poContent;
	}

	public void setPoContent(String poContent) {
		this.poContent = poContent;
	}

	@Column(name = "poTime", length = 19)
	public Timestamp getPoTime() {
		return this.poTime;
	}

	public void setPoTime(Timestamp poTime) {
		this.poTime = poTime;
	}

	@Column(name = "poType", nullable = false)
	public Integer getPoType() {
		return this.poType;
	}

	public void setPoType(Integer poType) {
		this.poType = poType;
	}

	@Column(name = "poPraise", nullable = false)
	public Integer getPoPraise() {
		return this.poPraise;
	}

	public void setPoPraise(Integer poPraise) {
		this.poPraise = poPraise;
	}

	@Column(name = "poStatus", nullable = false)
	public Integer getPoStatus() {
		return this.poStatus;
	}

	public void setPoStatus(Integer poStatus) {
		this.poStatus = poStatus;
	}
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "post")
	public Set<Praise> getPraises() {
		return this.praises;
	}

	public void setPraises(Set<Praise> praises) {
		this.praises = praises;
	}
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "post")
	public Set<Response> getResponses() {
		return this.responses;
	}

	public void setResponses(Set<Response> responses) {
		this.responses = responses;
	}

}