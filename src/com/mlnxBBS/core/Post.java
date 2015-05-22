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
	private Integer poType1;
	private Integer poType2;
	private Integer poType3;
	private Integer poPraise;
	private Timestamp lastRspTime;
	private Integer poStatus;
	private Set<Praise> praises = new HashSet<Praise>(0);
	private Set<Collection> collections = new HashSet<Collection>(0);
	private Set<Response> responses = new HashSet<Response>(0);

	// Constructors

	/** default constructor */
	public Post() {
	}

	/** minimal constructor */
	public Post(User user, String poTitle, String poContent, Integer poType1,
			Integer poType2, Integer poType3, Integer poPraise, Integer poStatus) {
		this.user = user;
		this.poTitle = poTitle;
		this.poContent = poContent;
		this.poType1 = poType1;
		this.poType2 = poType2;
		this.poType3 = poType3;
		this.poPraise = poPraise;
		this.poStatus = poStatus;
	}

	/** full constructor */
	public Post(User user, String poTitle, String poContent, Timestamp poTime,
			Integer poType1, Integer poType2, Integer poType3,
			Integer poPraise, Timestamp lastRspTime, Integer poStatus,
			Set<Praise> praises, Set<Collection> collections,
			Set<Response> responses) {
		this.user = user;
		this.poTitle = poTitle;
		this.poContent = poContent;
		this.poTime = poTime;
		this.poType1 = poType1;
		this.poType2 = poType2;
		this.poType3 = poType3;
		this.poPraise = poPraise;
		this.lastRspTime = lastRspTime;
		this.poStatus = poStatus;
		this.praises = praises;
		this.collections = collections;
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

	@Column(name = "poType1", nullable = false)
	public Integer getPoType1() {
		return this.poType1;
	}

	public void setPoType1(Integer poType1) {
		this.poType1 = poType1;
	}

	@Column(name = "poType2", nullable = false)
	public Integer getPoType2() {
		return this.poType2;
	}

	public void setPoType2(Integer poType2) {
		this.poType2 = poType2;
	}

	@Column(name = "poType3", nullable = false)
	public Integer getPoType3() {
		return this.poType3;
	}

	public void setPoType3(Integer poType3) {
		this.poType3 = poType3;
	}

	@Column(name = "poPraise", nullable = false)
	public Integer getPoPraise() {
		return this.poPraise;
	}

	public void setPoPraise(Integer poPraise) {
		this.poPraise = poPraise;
	}

	@Column(name = "lastRspTime", length = 19)
	public Timestamp getLastRspTime() {
		return this.lastRspTime;
	}

	public void setLastRspTime(Timestamp lastRspTime) {
		this.lastRspTime = lastRspTime;
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
	public Set<Collection> getCollections() {
		return this.collections;
	}

	public void setCollections(Set<Collection> collections) {
		this.collections = collections;
	}
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "post")
	public Set<Response> getResponses() {
		return this.responses;
	}

	public void setResponses(Set<Response> responses) {
		this.responses = responses;
	}

}