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
	private String uemail;
	private String uagname;
	private String uicon1;
	private String uicon2;
	private String uicon3;
	private Integer historyDays;
	private Integer runningDays;
	private Timestamp signInTime;
	private Integer uscore;
	private Integer ustatus;
	private Set<Collection> collections = new HashSet<Collection>(0);
	private Set<Userinfo> userinfos = new HashSet<Userinfo>(0);
	private Set<Response> responsesForReplyerId = new HashSet<Response>(0);
	private Set<Praise> praises = new HashSet<Praise>(0);
	private Set<Response> responsesForToUid = new HashSet<Response>(0);
	private Set<Post> posts = new HashSet<Post>(0);

	// Constructors

	/** default constructor */
	public User() {
	}

	/** minimal constructor */
	public User(String uname, String upass, String uemail, String uagname,
			String uicon1, String uicon2, String uicon3, Integer historyDays,
			Integer runningDays, Integer uscore, Integer ustatus) {
		this.uname = uname;
		this.upass = upass;
		this.uemail = uemail;
		this.uagname = uagname;
		this.uicon1 = uicon1;
		this.uicon2 = uicon2;
		this.uicon3 = uicon3;
		this.historyDays = historyDays;
		this.runningDays = runningDays;
		this.uscore = uscore;
		this.ustatus = ustatus;
	}

	/** full constructor */
	public User(String uname, String upass, String uemail, String uagname,
			String uicon1, String uicon2, String uicon3, Integer historyDays,
			Integer runningDays, Timestamp signInTime, Integer uscore,
			Integer ustatus, Set<Collection> collections,
			Set<Userinfo> userinfos, Set<Response> responsesForReplyerId,
			Set<Praise> praises, Set<Response> responsesForToUid,
			Set<Post> posts) {
		this.uname = uname;
		this.upass = upass;
		this.uemail = uemail;
		this.uagname = uagname;
		this.uicon1 = uicon1;
		this.uicon2 = uicon2;
		this.uicon3 = uicon3;
		this.historyDays = historyDays;
		this.runningDays = runningDays;
		this.signInTime = signInTime;
		this.uscore = uscore;
		this.ustatus = ustatus;
		this.collections = collections;
		this.userinfos = userinfos;
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

	@Column(name = "uName", nullable = false, length = 50)
	public String getUname() {
		return this.uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	@Column(name = "uPass", nullable = false, length = 50)
	public String getUpass() {
		return this.upass;
	}

	public void setUpass(String upass) {
		this.upass = upass;
	}

	@Column(name = "uEmail", nullable = false, length = 50)
	public String getUemail() {
		return this.uemail;
	}

	public void setUemail(String uemail) {
		this.uemail = uemail;
	}

	@Column(name = "uAgname", nullable = false, length = 50)
	public String getUagname() {
		return this.uagname;
	}

	public void setUagname(String uagname) {
		this.uagname = uagname;
	}

	@Column(name = "uIcon1", nullable = false, length = 50)
	public String getUicon1() {
		return this.uicon1;
	}

	public void setUicon1(String uicon1) {
		this.uicon1 = uicon1;
	}

	@Column(name = "uIcon2", nullable = false, length = 50)
	public String getUicon2() {
		return this.uicon2;
	}

	public void setUicon2(String uicon2) {
		this.uicon2 = uicon2;
	}

	@Column(name = "uIcon3", nullable = false, length = 50)
	public String getUicon3() {
		return this.uicon3;
	}

	public void setUicon3(String uicon3) {
		this.uicon3 = uicon3;
	}

	@Column(name = "historyDays", nullable = false)
	public Integer getHistoryDays() {
		return this.historyDays;
	}

	public void setHistoryDays(Integer historyDays) {
		this.historyDays = historyDays;
	}

	@Column(name = "runningDays", nullable = false)
	public Integer getRunningDays() {
		return this.runningDays;
	}

	public void setRunningDays(Integer runningDays) {
		this.runningDays = runningDays;
	}

	@Column(name = "signInTime", length = 19)
	public Timestamp getSignInTime() {
		return this.signInTime;
	}

	public void setSignInTime(Timestamp signInTime) {
		this.signInTime = signInTime;
	}

	@Column(name = "uScore", nullable = false)
	public Integer getUscore() {
		return this.uscore;
	}

	public void setUscore(Integer uscore) {
		this.uscore = uscore;
	}

	@Column(name = "uStatus", nullable = false)
	public Integer getUstatus() {
		return this.ustatus;
	}

	public void setUstatus(Integer ustatus) {
		this.ustatus = ustatus;
	}
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Collection> getCollections() {
		return this.collections;
	}

	public void setCollections(Set<Collection> collections) {
		this.collections = collections;
	}
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Userinfo> getUserinfos() {
		return this.userinfos;
	}

	public void setUserinfos(Set<Userinfo> userinfos) {
		this.userinfos = userinfos;
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