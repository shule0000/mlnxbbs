package com.mlnxBBS.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.SortedMap;

import javax.servlet.http.Cookie;

import org.apache.struts2.ServletActionContext;

import com.mlnxBBS.core.PageBean;
import com.mlnxBBS.core.User;
import com.mlnxBBS.service.PageService;
import com.mlnxBBS.service.UserService;
import com.mlnxBBS.tool.MD5;

public class AjaxAction extends BaseAction {
	PageService pageService = new PageService();
	UserService userService = new UserService();

	/**
	 * 分页查找帖子并显示
	 */
	public int page;
	public void queryPost() {
		PageBean pb = new PageBean();
		pb.setCurrentPageNum(page);
		String sql1 = "select * from post where poStatus = ? order by poTime desc";
		Object[] params = new Object[]{0};
		SortedMap[] sm = pageService.execQueryByPage(sql1, params, pb);
		List<List> posts = new ArrayList<List>();
		for (int i = 0; i < sm.length; i++) {
			Object[] every = sm[i].values().toArray();
			List list = new ArrayList();
			User author = userService.findById((int) every[0]);
			list.add(every[6]);
			list.add(author);
			list.add(every[5]);
			list.add(every[3]);
			list.add(every[1].toString().replaceAll("<.*?>", "")
					.substring(0, 30)
					+ "……");
			posts.add(list);
		}
		request.setAttribute("pb", pb);
		request.setAttribute("posts", posts);
		this.forward("showPost.jsp");
	}

	public String uName;
	public String uPass;
	public boolean remember;
	public void bbsLogin() throws IOException {
		PrintWriter out = ServletActionContext.getResponse().getWriter();

		List<User> users = userService.findAll();
		boolean exist = true;
		User currUser = new User();
		for (Iterator<User> iterator = users.iterator(); iterator.hasNext();) {
			User user = (User) iterator.next();
			if (user.getUname().equals(uName)) {
				if (user.getUpass().equals(uPass)) {
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
				session.setAttribute("uId", currUser.getUid());
				session.setAttribute("uAgname", currUser.getUagname());
				if (remember) {
					MD5 md = new MD5();
					Cookie cookie1 = new Cookie("uId", currUser.getUid() + "");
					Cookie cookie2 = new Cookie("uPass", md.GetMD5Code(currUser
							.getUpass()));

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
}
