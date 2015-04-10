package com.mlnxBBS.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.SortedMap;

import com.mlnxBBS.service.EventService;
import com.mlnxBBS.service.PageService;
import com.mlnxBBS.service.ContactService;
import com.mlnxBBS.service.CopyrightService;
import com.mlnxBBS.service.NavigationService;
import com.mlnxBBS.service.PostService;
import com.mlnxBBS.service.QrcodeService;
import com.mlnxBBS.service.UserService;
import com.mlnxBBS.core.PageBean;
import com.mlnxBBS.core.User;

public class BBSAction extends BaseAction {
	UserService userService = new UserService();
	NavigationService navigationService = new NavigationService();
	QrcodeService qrcodeService = new QrcodeService();
	ContactService contactService = new ContactService();
	CopyrightService copyrightService = new CopyrightService();
	PageService pageService = new PageService();
	PostService postService = new PostService();
	EventService eventService = new EventService();

	public void showBBSIndex() {

		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd EEEE");// 设置日期格式
		String date = df.format(new Date());// new Date()为获取当前系统时间
		request.setAttribute("date", date);

		// 显示论坛导航栏菜单
		SortedMap[] BBSNavs = navigationService
				.executeQuery(
						"select * from navigation where navStatus = ? and navType = ? order by navPriority desc",
						new Object[]{1, 2});
		request.setAttribute("BBSNavs", BBSNavs);

		// 显示二维码
		// 第一显示位置
		SortedMap[] qrcode1 = qrcodeService.executeQuery(
				"select * from qrcode where qrPosition = ? and qrStatus = ?",
				new Object[]{1, 1});
		request.setAttribute("qrcode1", qrcode1);

		// 第二显示位置
		SortedMap[] qrcode2 = qrcodeService.executeQuery(
				"select * from qrcode where qrPosition = ? and qrStatus = ?",
				new Object[]{2, 1});
		request.setAttribute("qrcode2", qrcode2);

		// 显示联系信息
		SortedMap[] contact = contactService.executeQuery(
				"select * from contact where ctStatus = ?", new Object[]{1});
		request.setAttribute("contact", contact);

		// 显示版权信息
		SortedMap[] copyright = copyrightService.executeQuery(
				"select * from copyright where cpStatus = ?", new Object[]{1});
		request.setAttribute("copyright", copyright);

		// 显示最近的三个置顶帖子
		SortedMap[] top = postService.executeQuery(
				"select * from post where poStatus = ? order by poTime desc",
				new Object[]{1});
		List<List> topPosts = new ArrayList<List>();
		if (top.length > 3) {
			for (int i = 0; i < 3; i++) {
				Object[] every = top[i].values().toArray();
				List list = new ArrayList();
				User author = userService.findById((int) every[0]);
				list.add(every[6]);
				list.add(author);
				list.add(every[5]);
				list.add(every[3]);
				list.add(every[1].toString().replaceAll("<.*?>", "")
						.substring(0, 30)
						+ "……");
				topPosts.add(list);
			}
		} else {
			for (int i = 0; i < top.length; i++) {
				Object[] every = top[i].values().toArray();
				List list = new ArrayList();
				User author = userService.findById((int) every[0]);
				list.add(every[6]);
				list.add(author);
				list.add(every[5]);
				list.add(every[3]);
				list.add(every[1].toString().replaceAll("<.*?>", "")
						.substring(0, 30)
						+ "……");
				topPosts.add(list);
			}
		}
		request.setAttribute("topPosts", topPosts);

		// 分页显示非置顶帖子
		final int iCurrentPageNum = 1;
		PageBean pb = new PageBean();
		pb.setCurrentPageNum(iCurrentPageNum);
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

		// 显示主要的五条活动
		SortedMap[] topEvents = eventService.executeQuery(
				"select * from event where estatus = ?",
				new Object[]{1});
		request.setAttribute("topEvents", topEvents);
		this.forward("bbs.jsp");
	}
}
