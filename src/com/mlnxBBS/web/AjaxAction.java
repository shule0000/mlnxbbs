package com.mlnxBBS.web;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;

import com.mlnxBBS.core.PageBean;
import com.mlnxBBS.core.User;
import com.mlnxBBS.service.PageService;
import com.mlnxBBS.service.UserService;

public class AjaxAction extends BaseAction {
	PageService pageService = new PageService();
	UserService userService = new UserService();

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
}
