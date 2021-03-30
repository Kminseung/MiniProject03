package com.bit.servlet.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit.servlet.dao.PhoneDao;
import com.bit.servlet.dao.PhoneDaoOrclImpl;
import com.bit.servlet.dao.PhoneVo;

@WebServlet(name="addresbook", urlPatterns="/new")
public class PhoneServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("a");
		
		if("form".equals(action)) {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/views/addressbook/form.jsp");
			rd.forward(req, resp);
		} else if("delete".equals(action)) {
			Long id = Long.valueOf(req.getParameter("id"));
			
			PhoneDao dao = new PhoneDaoOrclImpl();
		
			dao.delete(id);
			
			resp.sendRedirect(req.getContextPath() + "/new");
		} else if("search".equals(action)) {
			PhoneDao dao = new PhoneDaoOrclImpl();
			
			String keyword = req.getParameter("keyword");
			List<PhoneVo> list = dao.search(keyword);
			
			req.setAttribute("list", list);
			req.setAttribute("keyword", keyword);
			
			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/addressbook/index.jsp");
			rd.forward(req, resp);
		} else {
			PhoneDao dao = new PhoneDaoOrclImpl();
			List<PhoneVo> list = dao.getList();
			
			req.setAttribute("list", list);
			
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/views/addressbook/index.jsp");
			rd.forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		
		if("insert".equals(action)) {
			String name = req.getParameter("name");
			String hp = req.getParameter("hp");
			String tel = req.getParameter("tel");
			
			PhoneVo vo = new PhoneVo();
			vo.setName(name);
			vo.setHp(hp);
			vo.setTel(tel);
			
			PhoneDao dao = new PhoneDaoOrclImpl();
			
			dao.insert(vo);
			
			// 리스트 페이지로 리다이렉트
			resp.sendRedirect(req.getContextPath() + "/new");
		} else {
			doGet(req, resp);
		}
	}
}
