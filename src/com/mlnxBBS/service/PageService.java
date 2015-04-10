package com.mlnxBBS.service;

import java.util.SortedMap;

import com.mlnxBBS.core.BaseHibernateDAO;
import com.mlnxBBS.core.PageBean;
import com.mlnxBBS.tool.StringTool;

public class PageService extends BaseHibernateDAO {

	/**
	 * 按照分页标准执行查询
	 * 
	 * @param sql
	 * @param params
	 * @param currentPageNum
	 *            第几页
	 * @param pageCount
	 *            每页显示的数量
	 * @return
	 */
	public SortedMap[] execQueryAll(String sql1, String sql2, PageBean pb) {

		PageBean pageBean = new PageBean();

		SortedMap[] sm = this.executeQuery(sql1,
				new Object[]{(pb.getCurrentPageNum() - 1) * pb.getPageCount(),
						pb.getPageCount()});

		SortedMap[] total = this.executeQuery(sql2, null);

		long tc = (long) (total[0].get("count(*)"));
		pb.setTotalCount((int) tc);

		// 上不去了就锁定第一页
		if (pb.getCurrentPageNum() < 1) {
			pb.setCurrentPageNum(1);
		}
		// 下不来了就锁定最后一页
		if (pb.getCurrentPageNum() > pb.getTotalPageCount()
				&& pb.getTotalPageCount() > 0) {
			pb.setCurrentPageNum(pb.getTotalPageCount());
		}

		return sm;
	}
	/**
	 * 按照分页标准执行查询
	 * 
	 * @param sql
	 * @param params
	 * @param currentPageNum
	 *            第几页
	 * @param pageCount
	 *            每页显示的数量
	 * @return
	 */
	public SortedMap[] execQueryByPage(String sql, Object[] params, PageBean pb) {

		Object[] params2;
		if (params == null) {
			params2 = new Object[2];
		} else {
			// 复制之前的参数
			params2 = new Object[params.length + 2];
			for (int i = 0; i < params.length; i++) {
				params2[i] = params[i];
			}
		}

		// 将select * 换成 select count（*）
		String str = StringTool.mySubString2(sql, " from ");
		String sql2 = "select count(*) " + str;

		// 查出总条数，放入pb中
		SortedMap[] sms2 = this.executeQuery(sql2, params);
		long totalCount = (long) sms2[0].get("count(*)");
		pb.setTotalCount((int) totalCount);

		// 上不去了就锁定第一页
		if (pb.getCurrentPageNum() < 1) {
			pb.setCurrentPageNum(1);
		}
		// 下不来了就锁定最后一页
		if (pb.getCurrentPageNum() > pb.getTotalPageCount()
				&& pb.getTotalPageCount() > 0) {

			pb.setCurrentPageNum(pb.getTotalPageCount());

		}

		sql = sql + " limit ?,?";
		// 设置分页的参数到新的数组中
		params2[params2.length - 1] = pb.getPageCount();
		params2[params2.length - 2] = (pb.getCurrentPageNum() - 1)
				* pb.getPageCount();

		return this.executeQuery(sql, params2);
	}
}
