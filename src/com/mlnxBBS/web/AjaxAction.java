package com.mlnxBBS.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.SortedMap;

import javax.servlet.http.Cookie;

import org.apache.struts2.ServletActionContext;

import com.mlnxBBS.core.Collection;
import com.mlnxBBS.core.PageBean;
import com.mlnxBBS.core.Post;
import com.mlnxBBS.core.Praise;
import com.mlnxBBS.core.Response;
import com.mlnxBBS.core.User;
import com.mlnxBBS.service.CollectionService;
import com.mlnxBBS.service.PageService;
import com.mlnxBBS.service.PostService;
import com.mlnxBBS.service.PraiseService;
import com.mlnxBBS.service.ResponseService;
import com.mlnxBBS.service.UserService;
import com.mlnxBBS.tool.MD5;
import com.mlnxBBS.tool.MailSenderInfo;
import com.mlnxBBS.tool.SimpleMailSender;

public class AjaxAction extends BaseAction {
	PageService pageService = new PageService();
	UserService userService = new UserService();
	ResponseService responseService = new ResponseService();
	PostService postService = new PostService();
	PraiseService praiseService = new PraiseService();
	CollectionService collectionService = new CollectionService();

	/**
	 * 主页分页查找帖子并显示
	 */
	public int page;
	public void queryPost() {
		PageBean pb = new PageBean();
		pb.setCurrentPageNum(page);
		String sql1 = "select * from post where poStatus = ? order by poTime desc";
		Object[] params = new Object[]{0};
		@SuppressWarnings("rawtypes")
		SortedMap[] sm = pageService.execQueryByPage(sql1, params, pb);
		List<List<Object>> posts = new ArrayList<List<Object>>();
		for (int i = 0; i < sm.length; i++) {
			Object[] every = sm[i].values().toArray();
			List<Object> list = new ArrayList<Object>();
			User author = userService.findById((int) every[0]);
			list.add(every[7]);
			list.add(author);
			list.add(every[6]);
			list.add(every[4]);
			if (every[2].toString().replaceAll("<.*?>", "").length() > 30) {
				list.add(every[2].toString().replaceAll("<.*?>", "")
						.substring(0, 30)
						+ "……");
			} else {
				list.add(every[2].toString().replaceAll("<.*?>", ""));
			}
			list.add(every[3]);
			posts.add(list);
		}
		request.setAttribute("pb", pb);
		request.setAttribute("posts", posts);
		this.forward("showPostOfIndex.jsp");
	}

	/**
	 * 论坛用户登录
	 * 
	 * @throws IOException
	 */
	public String uName;
	public String uPass;
	public boolean remember;
	public void bbsLogin() throws IOException {
		PrintWriter out = ServletActionContext.getResponse().getWriter();

		@SuppressWarnings("unchecked")
		List<User> users = userService.findAll();
		boolean exist = true;
		User currUser = new User();
		for (Iterator<User> iterator = users.iterator(); iterator.hasNext();) {
			User user = (User) iterator.next();
			if (user.getUname().equals(uName)) {
				if (user.getUpass().equals(MD5.GetMD5Code(uPass))) {
					exist = true;
					currUser = user;
					break;
				} else {
					exist = false;
				}
			} else {
				exist = false;
			}
		}
		int error = 0;
		if (exist) {
			if (currUser.getUstatus() == 1) {
				SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd EEEE");
				int currTime = Integer.parseInt(df.format(new Date())
						.substring(8, 10));
				if (currTime
						- Integer.parseInt(currUser.getSignInTime().toString()
								.substring(8, 10)) == 0) {
					session.setAttribute("signInFlag", true);
					session.setAttribute("runningDays",
							currUser.getRunningDays());
				} else if (currTime
						- Integer.parseInt(currUser.getSignInTime().toString()
								.substring(8, 10)) > 1) {
					session.setAttribute("signInFlag", false);
					session.setAttribute("runningDays", 0);
					currUser.setRunningDays(0);
					userService.updateObject(currUser);
				} else {
					session.setAttribute("signInFlag", false);
					session.setAttribute("runningDays",
							currUser.getRunningDays());
				}

				session.setAttribute("uId", currUser.getUid());
				session.setAttribute("uAgname", currUser.getUagname());
				if (remember) {
					Cookie cookie1 = new Cookie("uId", currUser.getUid() + "");
					Cookie cookie2 = new Cookie("uPass", currUser.getUpass());

					cookie1.setPath("/");
					cookie2.setPath("/");

					cookie1.setMaxAge(7 * 60 * 60);
					cookie2.setMaxAge(7 * 60 * 60);

					response.addCookie(cookie1);
					response.addCookie(cookie2);
				}
			} else {
				error = 2;
			}
		} else {
			error = 1;
		}

		out.print(error);
	}

	/**
	 * 签到
	 * 
	 * @throws IOException
	 */
	public int uId;
	public void doSignIn() throws IOException {
		PrintWriter out = ServletActionContext.getResponse().getWriter();
		int runningDays;
		Timestamp ts = new Timestamp(new Date().getTime());
		User user = userService.findById(uId);
		user.setHistoryDays(user.getHistoryDays() + 1);
		user.setRunningDays(user.getRunningDays() + 1);
		user.setSignInTime(ts);
		runningDays = user.getRunningDays();
		session.setAttribute("signInFlag", true);
		session.setAttribute("runningDays", runningDays);
		userService.updateObject(user);
		out.print(runningDays);
	}

	/**
	 * 注册时检验用户名是否存在
	 * 
	 * @throws IOException
	 */
	public String uName1;
	public void checkExist() throws IOException {

		PrintWriter out = ServletActionContext.getResponse().getWriter();

		@SuppressWarnings("unchecked")
		List<User> userList = userService.findAll();
		boolean exist = true;
		for (Iterator<User> iterator = userList.iterator(); iterator.hasNext();) {
			User user = (User) iterator.next();
			if (user.getUname().equals(uName1)) {
				exist = true;
				break;
			} else {
				exist = false;

			}
		}

		if (exist) {
			out.print("1");
		} else {
			out.print("0");
		}

		out.flush();
		out.close();
	}

	/**
	 * 发送验证码
	 */
	public String email;
	public void sendCheck() {
		Random random = new Random();
		String check = "";
		for (int i = 0; i < 6; i++) {
			check += random.nextInt(10);
		}
		Cookie cookie = new Cookie("check", check);
		cookie.setMaxAge(3600 * 2);
		cookie.setPath("/");
		response.addCookie(cookie);
		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setMailServerHost("smtp.qq.com");
		mailInfo.setMailServerPort("25");
		mailInfo.setValidate(true);
		mailInfo.setUserName("460968755@qq.com");
		mailInfo.setPassword("woaiapei.0000");// 您的邮箱密码
		mailInfo.setFromAddress("460968755@qq.com");
		mailInfo.setToAddress(email);
		mailInfo.setSubject("欢迎注册Mlnx论坛新用户");
		mailInfo.setContent("您好！\n感谢您注册宁波Mlnx论坛用户。\n您此次注册的验证码为：" + check
				+ "。如验证码无效，请按页面提示重新发送。（此验证码有效时间为2小时，逾期无效）");
		// 这个类主要来发送邮件
		SimpleMailSender sms = new SimpleMailSender();
		sms.sendTextMail(mailInfo);// 发送文体格式
	}

	/**
	 * 产品分页查找帖子并显示
	 */
	public int page1;
	public int type1;
	public int type2;
	public int type3;
	public void queryPostPdu() {
		PageBean pb = new PageBean();
		pb.setCurrentPageNum(page);
		String sql1;
		Object[] params;
		if (type2 == 0) {
			if (type3 == 0) {
				sql1 = "select * from post where poType1 = ? order by poTime desc";
				params = new Object[]{type1};
			} else if (type3 == 1) {
				sql1 = "select * from post where poType1 = ? and poType3 = ? order by lastRspTime desc";
				params = new Object[]{type1, type3};
			} else {
				sql1 = "select * from post where poType1 = ? and poType3 = ? order by poPraise desc";
				params = new Object[]{type1, type3};
			}

		} else {
			if (type3 == 0) {
				sql1 = "select * from post where poType1 = ? and poType2 = ? order by poTime desc";
				params = new Object[]{type1, type2};
			} else if (type3 == 1) {
				sql1 = "select * from post where poType1 = ? and poType2 = ? and poType3 = ? order by lastRspTime desc";
				params = new Object[]{type1, type2, type3};
			} else {
				sql1 = "select * from post where poType1 = ? and poType2 = ? and poType3 = ? order by poPraise desc";
				params = new Object[]{type1, type2, type3};
			}
		}

		@SuppressWarnings("rawtypes")
		SortedMap[] sm = pageService.execQueryByPage(sql1, params, pb);
		List<List<Object>> posts = new ArrayList<List<Object>>();
		for (int i = 0; i < sm.length; i++) {
			Object[] every = sm[i].values().toArray();
			List<Object> list = new ArrayList<Object>();
			User author = userService.findById((int) every[0]);
			int rspNum = responseService.executeQuery(
					"select * from response where toPoid = ? and toUid = ?",
					new Object[]{every[3], 0}).length;
			list.add(every[7]);
			list.add(author);
			list.add(every[6]);
			list.add(every[4]);
			if (every[2].toString().replaceAll("<.*?>", "").length() > 30) {
				list.add(every[2].toString().replaceAll("<.*?>", "")
						.substring(0, 30)
						+ "……");
			} else {
				list.add(every[2].toString().replaceAll("<.*?>", ""));
			}
			list.add(rspNum);
			list.add(every[1]);
			list.add(every[3]);
			posts.add(list);
		}
		request.setAttribute("pb", pb);
		request.setAttribute("posts", posts);
		if (type3 == 1) {
			this.forward("showPostOfAll_1.jsp");
		} else if (type3 == 2) {
			this.forward("showPostOfAll_2.jsp");
		} else {
			this.forward("showPostOfAll_0.jsp");
		}

	}

	/**
	 * 发帖
	 */
	public int type1_1;
	public int type2_1;
	public int type3_1;
	public String title;
	public String content;
	public void doPost() {
		Post post = new Post();
		post.setPoTitle(title);
		post.setPoContent(content);
		post.setUser(userService.findById((int) session.getAttribute("uId")));
		Timestamp ts = new Timestamp(new Date().getTime());
		SimpleDateFormat df = new SimpleDateFormat("MM-dd HH:mm:ss");
		String time = "2000-" + df.format(ts);
		post.setPoTime(ts);
		post.setPoType1(type1_1);
		if (type2_1 == 0) {
			type2_1 = 1;
		}
		post.setPoType2(type2_1);
		post.setPoType3(type3_1);
		post.setPoPraise(0);
		post.setLastRspTime(Timestamp.valueOf(time));
		post.setPoStatus(0);
		postService.save(post);

	}

	/**
	 * 回帖
	 */
	public int poId;
	public int toUid;
	public int position;
	public String repley;
	public void doResponse() {
		Response respons = new Response();
		respons.setPost(postService.findById(poId));
		respons.setUserByToUid(userService.findById(toUid));
		respons.setUserByReplyerId(userService.findById((int) session
				.getAttribute("uId")));
		respons.setRpContent(repley);
		Timestamp ts = new Timestamp(new Date().getTime());
		respons.setRpTime(ts);
		if (position == 0) {
			@SuppressWarnings("rawtypes")
			SortedMap[] sm = responseService.executeQuery(
					"select * from response where toPoid = ? and toUid = ?",
					new Object[]{poId, 0});
			respons.setRpPosition(sm.length + 1);
		} else {
			respons.setRpPosition(position);
		}
		responseService.save(respons);
	}

	/**
	 * 帖子回复分页
	 */
	public int poId2;
	public int page2;
	public void queryRsp() {
		PageBean pb = new PageBean();
		pb.setCurrentPageNum(page2);
		String sql1 = "select * from response where toPoid = ? and toUid = ? order by rpPosition desc";
		Object[] params = new Object[]{poId2, 0};
		@SuppressWarnings("rawtypes")
		SortedMap[] sm1 = pageService.execQueryByPage(sql1, params, pb);
		List<List<Object>> responses = new ArrayList<List<Object>>();
		for (int i = 0; i < sm1.length; i++) {
			Object[] every = sm1[i].values().toArray();
			List<Object> list = new ArrayList<Object>();
			User reUser = userService.findById((int) every[0]);
			list.add(every[3]);
			list.add(reUser);
			list.add(every[1]);
			list.add(every[4]);
			responses.add(list);
		}
		request.setAttribute("pb", pb);
		request.setAttribute("responses", responses);

		@SuppressWarnings("rawtypes")
		SortedMap[] count = responseService.executeQuery(
				"select * from response where toPoid = ? and toUid = ?",
				new Object[]{poId2, 0});
		@SuppressWarnings("rawtypes")
		SortedMap[] sm2 = responseService
				.executeQuery(
						"select * from response where toPoid = ? and toUid > ? and rpPosition between ? and ? order by rpTime desc",
						new Object[]{
								poId2,
								0,
								count.length - (page2 - 1) * 5 - 4 > 0
										? count.length - (page2 - 1) * 5 - 4
										: 1, count.length - (page2 - 1) * 5});
		List<List<Object>> childResponses = new ArrayList<List<Object>>();
		for (int i = 0; i < sm2.length; i++) {
			Object[] every = sm2[i].values().toArray();
			List<Object> list = new ArrayList<Object>();
			User reUser = userService.findById((int) every[0]);
			User toUser = userService.findById((int) every[6]);
			list.add(every[3]);
			list.add(reUser);
			list.add(toUser);
			list.add(every[1]);
			list.add(every[4]);
			childResponses.add(list);
		}
		request.setAttribute("childResponses", childResponses);

		request.setAttribute("poId", poId2);
		this.forward("showResponse.jsp");
	}

	public int poId3;
	public int position3;
	public int clickNum;
	public void showMore() {
		@SuppressWarnings("rawtypes")
		SortedMap[] more = responseService
				.executeQuery(
						"select * from response where toPoid = ? and toUid > ? and rpPosition = ? order by rpTime desc limit ?, ?",
						new Object[]{poId3, 0, position3, clickNum * 5,
								clickNum * 5 + 5});
		List<List<Object>> mores = new ArrayList<List<Object>>();
		for (int i = 0; i < more.length; i++) {
			Object[] every = more[i].values().toArray();
			List<Object> list = new ArrayList<Object>();
			User reUser = userService.findById((int) every[0]);
			User toUser = userService.findById((int) every[6]);
			list.add(every[3]);
			list.add(reUser);
			list.add(toUser);
			list.add(every[1]);
			list.add(every[4]);
			mores.add(list);
		}
		request.setAttribute("poId", poId3);
		request.setAttribute("position", position3);
		request.setAttribute("mores", mores);
		this.forward("showMore.jsp");
	}

	/**
	 * 赞帖子
	 */
	public int praiserId;
	public int toPid;
	public void doPraise() {
		Praise praise = new Praise();
		praise.setUser(userService.findById(praiserId));
		praise.setPost(postService.findById(toPid));

		praiseService.save(praise);

		Post post = postService.findById(toPid);
		post.setPoPraise(post.getPoPraise() + 1);
		postService.updateObject(post);
	}

	/**
	 * 收藏帖子
	 */
	public int coUid;
	public int coPoid;
	public void doCollection() {
		Collection coll = new Collection();
		coll.setUser(userService.findById(coUid));
		coll.setPost(postService.findById(coPoid));
		collectionService.save(coll);

	}

	/**
	 * 打赏帖子作者
	 */
	public int doerId;
	public int rewardId;
	public void doReward() throws IOException {
		System.out.println(doerId);
		System.out.println(rewardId);
		PrintWriter out = ServletActionContext.getResponse().getWriter();

		User doer = userService.findById(doerId);
		if (doer.getUscore() >= 10) {
			User author = userService.findById(rewardId);
			doer.setUscore(doer.getUscore() - 10);
			author.setUscore(author.getUscore() + 10);
			userService.updateObject(doer);
			userService.updateObject(author);
			out.print(doer.getUscore());
		} else {
			out.print("-1");
		}

	}

	/**
	 * 保存搜索关键字
	 */
	public String key;
	public void saveKey() {
		session.setAttribute("key", key);
	}

	/**
	 * 分页显示查询帖子
	 */
	public int page3;
	public void querySearchPost() {
		PageBean pb = new PageBean();
		pb.setCurrentPageNum(page3);
		String sql1 = "select * from post where poTitle like ? or authorId in (select uId from user where uName like ? or uAgname like ?) or poTime like ? order by poTime desc";
		Object[] params = new Object[]{"%" + session.getAttribute("key") + "%",
				"%" + session.getAttribute("key") + "%",
				"%" + session.getAttribute("key") + "%",
				"%" + session.getAttribute("key") + "%"};
		@SuppressWarnings("rawtypes")
		SortedMap[] sm = pageService.execQueryByPage(sql1, params, pb);
		if (sm.length > 0) {
			request.setAttribute("existPost", "1");
		} else {
			request.setAttribute("existPost", "0");
		}
		List<List<Object>> posts = new ArrayList<List<Object>>();
		for (int i = 0; i < sm.length; i++) {
			Object[] every = sm[i].values().toArray();
			List<Object> list = new ArrayList<Object>();
			User author = userService.findById((int) every[0]);
			list.add(every[7]);
			list.add(author);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			list.add(sdf.format(every[6]));
			list.add(every[4]);
			if (every[2].toString().replaceAll("<.*?>", "").length() > 30) {
				list.add(every[2].toString().replaceAll("<.*?>", "")
						.substring(0, 30)
						+ "……");
			} else {
				list.add(every[2].toString().replaceAll("<.*?>", ""));
			}
			list.add(every[3]);
			posts.add(list);
		}
		request.setAttribute("pb", pb);
		request.setAttribute("posts", posts);

		session.setAttribute("replace", "<span style='color: red'><b>"
				+ session.getAttribute("key") + "</b></span>");
		this.forward("showSearchResult.jsp");
	}
}
