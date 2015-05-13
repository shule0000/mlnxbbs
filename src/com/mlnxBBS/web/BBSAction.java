package com.mlnxBBS.web;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;

import javax.servlet.http.Cookie;

import com.mlnxBBS.service.BannerService;
import com.mlnxBBS.service.EventService;
import com.mlnxBBS.service.HeaderService;
import com.mlnxBBS.service.PageService;
import com.mlnxBBS.service.ContactService;
import com.mlnxBBS.service.CopyrightService;
import com.mlnxBBS.service.NavigationService;
import com.mlnxBBS.service.PostService;
import com.mlnxBBS.service.PraiseService;
import com.mlnxBBS.service.QrcodeService;
import com.mlnxBBS.service.ResponseService;
import com.mlnxBBS.service.UserService;
import com.mlnxBBS.tool.MD5;
import com.mlnxBBS.core.PageBean;
import com.mlnxBBS.core.Post;
import com.mlnxBBS.core.User;

public class BBSAction extends BaseAction {
	HeaderService headerService = new HeaderService();
	BannerService bannerService = new BannerService();
	UserService userService = new UserService();
	NavigationService navigationService = new NavigationService();
	QrcodeService qrcodeService = new QrcodeService();
	ContactService contactService = new ContactService();
	CopyrightService copyrightService = new CopyrightService();
	PageService pageService = new PageService();
	PostService postService = new PostService();
	EventService eventService = new EventService();
	ResponseService responseService = new ResponseService();
	PraiseService praiseService = new PraiseService();

	/**
	 * 显示论坛主页
	 */
	public void showBBSIndex() {
		if (session.getAttribute("uId") == null) {
			Cookie[] cs = request.getCookies();
			if (cs != null) {
				if (cs.length > 0) {
					int uId = 0;
					String uPass = "";
					for (int i = 0; i < cs.length; i++) {
						if (cs[i].getName().equals("uId")) {
							uId = Integer.parseInt(cs[i].getValue());
						}
						if (cs[i].getName().equals("uPass")) {
							uPass = cs[i].getValue();
						}
					}

					if (uId != 0) {

						User user = userService.findById(uId);
						if (user.getUpass().equals(uPass)) {
							SimpleDateFormat df = new SimpleDateFormat(
									"yyyy/MM/dd EEEE");
							int y = Integer.parseInt(df.format(new Date())
									.substring(0, 4));
							int m = Integer.parseInt(df.format(new Date())
									.substring(5, 7));
							int d = Integer.parseInt(df.format(new Date())
									.substring(8, 10));
							if (y > Integer.parseInt(user.getSignInTime()
									.toString().substring(0, 4))) {
								session.setAttribute("signInFlag", false);
								session.setAttribute("runningDays",
										user.getRunningDays());
							} else {
								if (m > Integer.parseInt(user.getSignInTime()
										.toString().substring(5, 7))) {
									session.setAttribute("signInFlag", false);
									session.setAttribute("runningDays",
											user.getRunningDays());
								} else {
									if (d
											- Integer.parseInt(user
													.getSignInTime().toString()
													.substring(8, 10)) == 0) {
										session.setAttribute("signInFlag", true);
										session.setAttribute("runningDays",
												user.getRunningDays());
									} else if (d
											- Integer.parseInt(user
													.getSignInTime().toString()
													.substring(8, 10)) > 1) {
										session.setAttribute("signInFlag",
												false);
										session.setAttribute("runningDays", 0);
										user.setRunningDays(0);
										userService.updateObject(user);
									} else {
										session.setAttribute("signInFlag",
												false);
										session.setAttribute("runningDays",
												user.getRunningDays());
									}
								}
							}

							session.setAttribute("uId", uId);
							session.setAttribute("uAgname", user.getUagname());
						}
					}
				}
			}

		}

		// 显示当前日期
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd EEEE");// 设置日期格式
		String date = df.format(new Date());// new Date()为获取当前系统时间
		request.setAttribute("date", date);

		// 显示logo
		@SuppressWarnings("rawtypes")
		SortedMap[] headers = headerService.executeQuery(
				"select * from header where headerStatus = ?", new Object[]{1});
		request.setAttribute("headers", headers);

		// 显示论坛导航栏菜单
		@SuppressWarnings("rawtypes")
		SortedMap[] BBSNavs = navigationService
				.executeQuery(
						"select * from navigation where navStatus = ? and navType = ? order by navPriority desc",
						new Object[]{1, 2});
		request.setAttribute("BBSNavs", BBSNavs);

		// 显示banner
		@SuppressWarnings("rawtypes")
		SortedMap[] banners = bannerService
				.executeQuery(
						"select * from banner where banStatus = ? order by banPriority desc limit ?, ?",
						new Object[]{1, 0, 3});
		request.setAttribute("banners", banners);

		// 显示二维码
		// 第一显示位置
		@SuppressWarnings("rawtypes")
		SortedMap[] qrcode1 = qrcodeService.executeQuery(
				"select * from qrcode where qrPosition = ? and qrStatus = ?",
				new Object[]{1, 1});
		request.setAttribute("qrcode1", qrcode1);

		// 第二显示位置
		@SuppressWarnings("rawtypes")
		SortedMap[] qrcode2 = qrcodeService.executeQuery(
				"select * from qrcode where qrPosition = ? and qrStatus = ?",
				new Object[]{2, 1});
		request.setAttribute("qrcode2", qrcode2);

		// 显示联系信息
		@SuppressWarnings("rawtypes")
		SortedMap[] contact = contactService.executeQuery(
				"select * from contact where ctStatus = ?", new Object[]{1});
		request.setAttribute("contact", contact);

		// 显示版权信息
		@SuppressWarnings("rawtypes")
		SortedMap[] copyright = copyrightService.executeQuery(
				"select * from copyright where cpStatus = ?", new Object[]{1});
		request.setAttribute("copyright", copyright);

		// 显示最近的三个置顶帖子
		@SuppressWarnings("rawtypes")
		SortedMap[] top = postService
				.executeQuery(
						"select * from post where poStatus = ? order by poTime desc limit ?, ?",
						new Object[]{1, 0, 3});
		List<List<Object>> topPosts = new ArrayList<List<Object>>();
		for (int i = 0; i < top.length; i++) {
			Object[] every = top[i].values().toArray();
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
			topPosts.add(list);
		}

		request.setAttribute("topPosts", topPosts);

		// 分页显示非置顶帖子
		final int iCurrentPageNum = 1;
		PageBean pb = new PageBean();
		pb.setCurrentPageNum(iCurrentPageNum);
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
			if (every[2].toString().replaceAll("<.*?>", "").length() > 300) {
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

		// 显示主要的五条活动
		@SuppressWarnings("rawtypes")
		SortedMap[] topEvents = eventService
				.executeQuery(
						"select * from event where estatus = ? order by epriority desc limit ?, ?",
						new Object[]{1, 0, 5});
		request.setAttribute("topEvents", topEvents);
		this.forward("bbs.jsp");
	}

	/**
	 * 显示论坛登录界面
	 * 
	 * @throws IOException
	 */
	public String url;

	public void showBBSLogin() throws IOException {
		session.setAttribute("url", url);
		Cookie[] cs = request.getCookies();
		if (cs != null) {

			int uId = 0;
			String uPass = "";
			for (int i = 0; i < cs.length; i++) {
				if (cs[i].getName().equals("uId")) {
					uId = Integer.parseInt(cs[i].getValue());
				}
				if (cs[i].getName().equals("uPass")) {
					uPass = cs[i].getValue();
				}
			}
			if (uId != 0) {
				User user = userService.findById(uId);
				if (user.getUpass().equals(uPass)) {
					SimpleDateFormat df = new SimpleDateFormat(
							"yyyy/MM/dd EEEE");
					int y = Integer.parseInt(df.format(new Date()).substring(0,
							4));
					int m = Integer.parseInt(df.format(new Date()).substring(5,
							7));
					int d = Integer.parseInt(df.format(new Date()).substring(8,
							10));
					if (y > Integer.parseInt(user.getSignInTime().toString()
							.substring(0, 4))) {
						session.setAttribute("signInFlag", false);
						session.setAttribute("runningDays",
								user.getRunningDays());
					} else {
						if (m > Integer.parseInt(user.getSignInTime()
								.toString().substring(5, 7))) {
							session.setAttribute("signInFlag", false);
							session.setAttribute("runningDays",
									user.getRunningDays());
						} else {
							if (d
									- Integer.parseInt(user.getSignInTime()
											.toString().substring(8, 10)) == 0) {
								session.setAttribute("signInFlag", true);
								session.setAttribute("runningDays",
										user.getRunningDays());
							} else if (d
									- Integer.parseInt(user.getSignInTime()
											.toString().substring(8, 10)) > 1) {
								session.setAttribute("signInFlag", false);
								session.setAttribute("runningDays", 0);
								user.setRunningDays(0);
								userService.updateObject(user);
							} else {
								session.setAttribute("signInFlag", false);
								session.setAttribute("runningDays",
										user.getRunningDays());
							}
						}
					}
					session.setAttribute("uId", uId);
					session.setAttribute("uAgname", user.getUagname());
				}
				response.sendRedirect("bbs!showBBSIndex.action");
			} else {
				// 显示logo
				@SuppressWarnings("rawtypes")
				SortedMap[] headers = headerService.executeQuery(
						"select * from header where headerStatus = ?",
						new Object[]{1});
				request.setAttribute("headers", headers);

				// 显示二维码
				// 第一显示位置
				@SuppressWarnings("rawtypes")
				SortedMap[] qrcode1 = qrcodeService
						.executeQuery(
								"select * from qrcode where qrPosition = ? and qrStatus = ?",
								new Object[]{1, 1});
				request.setAttribute("qrcode1", qrcode1);

				// 第二显示位置
				@SuppressWarnings("rawtypes")
				SortedMap[] qrcode2 = qrcodeService
						.executeQuery(
								"select * from qrcode where qrPosition = ? and qrStatus = ?",
								new Object[]{2, 1});
				request.setAttribute("qrcode2", qrcode2);

				// 显示联系信息
				@SuppressWarnings("rawtypes")
				SortedMap[] contact = contactService.executeQuery(
						"select * from contact where ctStatus = ?",
						new Object[]{1});
				request.setAttribute("contact", contact);

				// 显示版权信息
				@SuppressWarnings("rawtypes")
				SortedMap[] copyright = copyrightService.executeQuery(
						"select * from copyright where cpStatus = ?",
						new Object[]{1});
				request.setAttribute("copyright", copyright);

				this.forward("bbsLogin.jsp");
			}
		} else {
			// 显示logo
			@SuppressWarnings("rawtypes")
			SortedMap[] headers = headerService.executeQuery(
					"select * from header where headerStatus = ?",
					new Object[]{1});
			request.setAttribute("headers", headers);

			// 显示二维码
			// 第一显示位置
			@SuppressWarnings("rawtypes")
			SortedMap[] qrcode1 = qrcodeService
					.executeQuery(
							"select * from qrcode where qrPosition = ? and qrStatus = ?",
							new Object[]{1, 1});
			request.setAttribute("qrcode1", qrcode1);

			// 第二显示位置
			@SuppressWarnings("rawtypes")
			SortedMap[] qrcode2 = qrcodeService
					.executeQuery(
							"select * from qrcode where qrPosition = ? and qrStatus = ?",
							new Object[]{2, 1});
			request.setAttribute("qrcode2", qrcode2);

			// 显示联系信息
			@SuppressWarnings("rawtypes")
			SortedMap[] contact = contactService
					.executeQuery("select * from contact where ctStatus = ?",
							new Object[]{1});
			request.setAttribute("contact", contact);

			// 显示版权信息
			@SuppressWarnings("rawtypes")
			SortedMap[] copyright = copyrightService.executeQuery(
					"select * from copyright where cpStatus = ?",
					new Object[]{1});
			request.setAttribute("copyright", copyright);

			this.forward("bbsLogin.jsp");
		}
	}

	/**
	 * 论坛用户登出
	 * 
	 * @throws IOException
	 */
	public void bbsLogout() throws IOException {
		Cookie cookie1 = new Cookie("uId", null);
		Cookie cookie2 = new Cookie("uPass", null);
		cookie1.setMaxAge(0);
		cookie2.setMaxAge(0);
		cookie1.setPath("/");
		cookie2.setPath("/");
		response.addCookie(cookie1);
		response.addCookie(cookie2);
		session.invalidate();
		response.sendRedirect("bbs!showBBSLogin.action");
	}

	/**
	 * 显示注册页面
	 */
	public void showBBSRegister() {
		// 显示logo
		@SuppressWarnings("rawtypes")
		SortedMap[] headers = headerService.executeQuery(
				"select * from header where headerStatus = ?", new Object[]{1});
		request.setAttribute("headers", headers);

		// 显示二维码
		// 第一显示位置
		@SuppressWarnings("rawtypes")
		SortedMap[] qrcode1 = qrcodeService.executeQuery(
				"select * from qrcode where qrPosition = ? and qrStatus = ?",
				new Object[]{1, 1});
		request.setAttribute("qrcode1", qrcode1);

		// 第二显示位置
		@SuppressWarnings("rawtypes")
		SortedMap[] qrcode2 = qrcodeService.executeQuery(
				"select * from qrcode where qrPosition = ? and qrStatus = ?",
				new Object[]{2, 1});
		request.setAttribute("qrcode2", qrcode2);

		// 显示联系信息
		@SuppressWarnings("rawtypes")
		SortedMap[] contact = contactService.executeQuery(
				"select * from contact where ctStatus = ?", new Object[]{1});
		request.setAttribute("contact", contact);

		// 显示版权信息
		@SuppressWarnings("rawtypes")
		SortedMap[] copyright = copyrightService.executeQuery(
				"select * from copyright where cpStatus = ?", new Object[]{1});
		request.setAttribute("copyright", copyright);

		this.forward("bbsRegister.jsp");
	}

	/**
	 * 注册新用户
	 * 
	 * @throws IOException
	 */
	public String uName;
	public String uPass;
	public String uEmail;

	public void doBBSRegister() throws IOException {
		User user = new User();
		user.setUname(uName);
		user.setUpass(MD5.GetMD5Code(uPass));
		user.setUemail(uEmail);
		user.setUagname("用户" + uName.substring(0, 2)
				+ String.valueOf(System.currentTimeMillis()).substring(7, 13));
		user.setUicon("defaultIcon.jpg");
		user.setHistoryDays(0);
		user.setRunningDays(0);
		Timestamp ts = new Timestamp(new Date().getTime());
		SimpleDateFormat df = new SimpleDateFormat("MM-dd HH:mm:ss");
		String time = "2000-" + df.format(ts);
		user.setSignInTime(Timestamp.valueOf(time));
		user.setUscore(0);
		user.setUstatus(1);
		userService.save(user);
		response.sendRedirect("bbs!showBBSLogin.action");
	}

	/**
	 * 按第一级类型查找帖子信息并显示
	 */
	public int type;

	public void showPostAll() {
		// 显示logo
		@SuppressWarnings("rawtypes")
		SortedMap[] headers = headerService.executeQuery(
				"select * from header where headerStatus = ?", new Object[]{1});
		request.setAttribute("headers", headers);

		// 显示当前日期
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd EEEE");// 设置日期格式
		String date = df.format(new Date());// new Date()为获取当前系统时间
		request.setAttribute("date", date);

		// 显示论坛导航栏菜单
		@SuppressWarnings("rawtypes")
		SortedMap[] BBSNavs = navigationService
				.executeQuery(
						"select * from navigation where navStatus = ? and navType = ? order by navPriority desc",
						new Object[]{1, 2});
		request.setAttribute("BBSNavs", BBSNavs);

		// 显示二维码
		// 第一显示位置
		@SuppressWarnings("rawtypes")
		SortedMap[] qrcode1 = qrcodeService.executeQuery(
				"select * from qrcode where qrPosition = ? and qrStatus = ?",
				new Object[]{1, 1});
		request.setAttribute("qrcode1", qrcode1);

		// 第二显示位置
		@SuppressWarnings("rawtypes")
		SortedMap[] qrcode2 = qrcodeService.executeQuery(
				"select * from qrcode where qrPosition = ? and qrStatus = ?",
				new Object[]{2, 1});
		request.setAttribute("qrcode2", qrcode2);

		// 显示联系信息
		@SuppressWarnings("rawtypes")
		SortedMap[] contact = contactService.executeQuery(
				"select * from contact where ctStatus = ?", new Object[]{1});
		request.setAttribute("contact", contact);

		// 显示版权信息
		@SuppressWarnings("rawtypes")
		SortedMap[] copyright = copyrightService.executeQuery(
				"select * from copyright where cpStatus = ?", new Object[]{1});
		request.setAttribute("copyright", copyright);

		// 分页显示所有产品帖子
		final int iCurrentPageNum = 1;
		PageBean pb = new PageBean();
		pb.setCurrentPageNum(iCurrentPageNum);
		String sql1 = "select * from post where poType1 = ? order by poTime desc";
		Object[] params = new Object[]{type};
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
			list.add(every[4]);
			list.add(every[3]);
			posts.add(list);
		}
		request.setAttribute("pb", pb);
		request.setAttribute("posts", posts);

		if (type == 1) {
			this.forward("postProduct.jsp");
		}
		if (type == 2) {
			this.forward("postExperience.jsp");
		}
		if (type == 3) {
			this.forward("postProblem.jsp");
		}

	}

	/**
	 * 显示帖子具体内容以及评论
	 */
	public int poId;

	public void showPostContent() {
		// 查询是否点赞
		if (session.getAttribute("uId") == null) {
			request.setAttribute("praise", 0);
		} else {
			@SuppressWarnings("rawtypes")
			SortedMap[] sm1 = praiseService.executeQuery(
					"select * from praise where praiserId = ? and toPid = ?",
					new Object[]{session.getAttribute("uId"), poId});
			if (sm1.length > 0) {
				request.setAttribute("praise", 2);
			} else {
				request.setAttribute("praise", 1);
			}
			SortedMap[] sm2 = praiseService.executeQuery(
					"select * from collection where coUid = ? and coPoid = ?",
					new Object[]{session.getAttribute("uId"), poId});
			if (sm2.length > 0) {
				request.setAttribute("collection", 2);
			} else {
				request.setAttribute("collection", 1);
			}
		}

		// 显示logo
		@SuppressWarnings("rawtypes")
		SortedMap[] headers = headerService.executeQuery(
				"select * from header where headerStatus = ?", new Object[]{1});
		request.setAttribute("headers", headers);

		// 显示论坛导航栏菜单
		@SuppressWarnings("rawtypes")
		SortedMap[] BBSNavs = navigationService
				.executeQuery(
						"select * from navigation where navStatus = ? and navType = ? order by navPriority desc",
						new Object[]{1, 2});
		request.setAttribute("BBSNavs", BBSNavs);

		// 显示二维码
		// 第一显示位置
		@SuppressWarnings("rawtypes")
		SortedMap[] qrcode1 = qrcodeService.executeQuery(
				"select * from qrcode where qrPosition = ? and qrStatus = ?",
				new Object[]{1, 1});
		request.setAttribute("qrcode1", qrcode1);

		// 第二显示位置
		@SuppressWarnings("rawtypes")
		SortedMap[] qrcode2 = qrcodeService.executeQuery(
				"select * from qrcode where qrPosition = ? and qrStatus = ?",
				new Object[]{2, 1});
		request.setAttribute("qrcode2", qrcode2);

		// 显示联系信息
		@SuppressWarnings("rawtypes")
		SortedMap[] contact = contactService.executeQuery(
				"select * from contact where ctStatus = ?", new Object[]{1});
		request.setAttribute("contact", contact);

		// 显示版权信息
		@SuppressWarnings("rawtypes")
		SortedMap[] copyright = copyrightService.executeQuery(
				"select * from copyright where cpStatus = ?", new Object[]{1});
		request.setAttribute("copyright", copyright);

		Post post = postService.findById(poId);
		request.setAttribute("post", post);

		// 针对帖子的回复
		final int iCurrentPageNum = 1;
		PageBean pb = new PageBean();
		pb.setCurrentPageNum(iCurrentPageNum);
		String sql1 = "select * from response where toPoid = ? and toUid = ? order by rpPosition desc";
		Object[] params = new Object[]{poId, 0};
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
		request.setAttribute("existRsp", sm1.length > 0 ? true : false);
		request.setAttribute("pb", pb);
		request.setAttribute("responses", responses);

		// 针对回复的子回复
		@SuppressWarnings("rawtypes")
		SortedMap[] count = responseService.executeQuery(
				"select * from response where toPoid = ? and toUid = ?",
				new Object[]{poId, 0});
		@SuppressWarnings("rawtypes")
		SortedMap[] sm2 = responseService
				.executeQuery(
						"select * from response where toPoid = ? and toUid > ? and rpPosition between ? and ? order by rpTime desc",
						new Object[]{poId, 0,
								count.length - 4 > 0 ? count.length - 4 : 0,
								count.length});
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
		this.forward("postContent.jsp");
	}

	public void doSearch() {
		// 显示当前日期
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd EEEE");// 设置日期格式
		String date = df.format(new Date());// new Date()为获取当前系统时间
		request.setAttribute("date", date);

		// 显示logo
		@SuppressWarnings("rawtypes")
		SortedMap[] headers = headerService.executeQuery(
				"select * from header where headerStatus = ?", new Object[]{1});
		request.setAttribute("headers", headers);

		// 显示论坛导航栏菜单
		@SuppressWarnings("rawtypes")
		SortedMap[] BBSNavs = navigationService
				.executeQuery(
						"select * from navigation where navStatus = ? and navType = ? order by navPriority desc",
						new Object[]{1, 2});
		request.setAttribute("BBSNavs", BBSNavs);

		// 显示banner
		@SuppressWarnings("rawtypes")
		SortedMap[] banners = bannerService
				.executeQuery(
						"select * from banner where banStatus = ? order by banPriority desc limit ?, ?",
						new Object[]{1, 0, 3});
		request.setAttribute("banners", banners);

		// 显示二维码
		// 第一显示位置
		@SuppressWarnings("rawtypes")
		SortedMap[] qrcode1 = qrcodeService.executeQuery(
				"select * from qrcode where qrPosition = ? and qrStatus = ?",
				new Object[]{1, 1});
		request.setAttribute("qrcode1", qrcode1);

		// 第二显示位置
		@SuppressWarnings("rawtypes")
		SortedMap[] qrcode2 = qrcodeService.executeQuery(
				"select * from qrcode where qrPosition = ? and qrStatus = ?",
				new Object[]{2, 1});
		request.setAttribute("qrcode2", qrcode2);

		// 显示联系信息
		@SuppressWarnings("rawtypes")
		SortedMap[] contact = contactService.executeQuery(
				"select * from contact where ctStatus = ?", new Object[]{1});
		request.setAttribute("contact", contact);

		// 显示版权信息
		@SuppressWarnings("rawtypes")
		SortedMap[] copyright = copyrightService.executeQuery(
				"select * from copyright where cpStatus = ?", new Object[]{1});
		request.setAttribute("copyright", copyright);

		// 分页显示查询结果
		final int iCurrentPageNum = 1;
		PageBean pb = new PageBean();
		pb.setCurrentPageNum(iCurrentPageNum);
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

		// 显示主要的五条活动
		@SuppressWarnings("rawtypes")
		SortedMap[] topEvents = eventService
				.executeQuery(
						"select * from event where estatus = ? order by epriority desc limit ?, ?",
						new Object[]{1, 0, 5});
		request.setAttribute("topEvents", topEvents);

		session.setAttribute("replace", "<span style='color: red'><b>"
				+ session.getAttribute("key") + "</b></span>");
		this.forward("searchResult.jsp");
	}
}
