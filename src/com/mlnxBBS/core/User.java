package com.mlnxBBS.core;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * User entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "user", catalog = "mlnx")
public class User implements java.io.Serializable {

	// Fields

	private Integer uid;
	private String uname;
	private String upass;
	private String uagname;
	private String uicon;
	private Integer udays;
	private Integer uscore;
	private Integer ustatus;
	private Set<Response> responsesForReplyerId = new HashSet<Response>(0);
	private Set<Praise> praises = new HashSet<Praise>(0);
	private Set<Response> responsesForToUid = new HashSet<Response>(0);
	private Set<Post> posts = new HashSet<Post>(0);

	// Constructors

	/** default constructor */
	public User() {
	}

	/** full constructor */
	public User(String uname, String upass, String uagname, String uicon,
			Integer udays, Integer uscore, Integer ustatus,
			Set<Response> responsesForReplyerId, Set<Praise> praises,
			Set<Response> responsesForToUid, Set<Post> posts) {
		this.uname = uname;
		this.upass = upass;
		this.uagname = uagname;
		this.uicon = uicon;
		this.udays = udays;
		this.uscore = uscore;
		this.ustatus = ustatus;
		this.responsesForReplyerId = responsesForReplyerId;
		this.praises = praises;
		this.responsesForToUid = responsesForToUid;
		this.posts = posts;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "uId", unique = true, nullable = false)
	public Integer getUid() {
		return this.uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	@Column(name = "uName", length = 50)
	public String getUname() {
		return this.uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	@Column(name = "uPass", length = 50)
	public String getUpass() {
		return this.upass;
	}

	public void setUpass(String upass) {
		this.upass = upass;
	}

	@Column(name = "uAgname", length = 50)
	public String getUagname() {
		return this.uagname;
	}

	public void setUagname(String uagname) {
		this.uagname = uagname;
	}

	@Column(name = "uIcon", length = 50)
	public String getUicon() {
		return this.uicon;
	}

	public void setUicon(String uicon) {
		this.uicon = uicon;
	}

	@Column(name = "uDays")
	public Integer getUdays() {
		return this.udays;
	}

	public void setUdays(Integer udays) {
		this.udays = udays;
	}

	@Column(name = "uScore")
	public Integer getUscore() {
		return this.uscore;
	}

	public void setUscore(Integer uscore) {
		this.uscore = uscore;
	}

	@Column(name = "uStatus")
	public Integer getUstatus() {
		return this.ustatus;
	}

	public void setUstatus(Integer ustatus) {
		this.ustatus = ustatus;
	}
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userByReplyerId")
	public Set<Response> getResponsesForReplyerId() {
		return this.responsesForReplyerId;
	}

	public void setResponsesForReplyerId(Set<Response> responsesForReplyerId) {
		this.responsesForReplyerId = responsesForReplyerId;
	}
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Praise> getPraises() {
		return this.praises;
	}

	public void setPraises(Set<Praise> praises) {
		this.praises = praises;
	}
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userByToUid")
	public Set<Response> getResponsesForToUid() {
		return this.responsesForToUid;
	}

	public void setResponsesForToUid(Set<Response> responsesForToUid) {
		this.responsesForToUid = responsesForToUid;
	}
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Post> getPosts() {
		return this.posts;
	}

	public void setPosts(Set<Post> posts) {
		this.posts = posts;
	}

}