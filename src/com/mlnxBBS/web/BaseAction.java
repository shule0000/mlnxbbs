package com.mlnxBBS.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.mlnxBBS.core.HibernateSessionFactory;
import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport {

		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpSession session = request.getSession();
		ServletContext application = ServletActionContext.getServletContext();
		
		public void validate() {
			// TODO Auto-generated method stub
			HibernateSessionFactory.getSession().clear();
			try {
				request.setCharacterEncoding("UTF-8");
				response.setCharacterEncoding("UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
		public void forward(String path){
			try {
				request.getRequestDispatcher(path).forward(request, response);
			} catch (ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public void alertRedirect(String mess, String path){
			try {
				response.setCharacterEncoding("UTF-8");
				PrintWriter out = this.response.getWriter();
				out.print("<script>alert('"+mess+"');window.location = '"+path+"';</script>");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
}
