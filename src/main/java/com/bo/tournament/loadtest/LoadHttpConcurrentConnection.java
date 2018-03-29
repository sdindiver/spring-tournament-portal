package com.bo.tournament.loadtest;

import java.io.IOException;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoadHttpConcurrentConnection extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try{
			int number = Integer.valueOf(req.getParameter("number"));
			System.out.println("concurrent connection " + number);
			String GET_URL = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + req.getContextPath() + "/load-test?number=" + (number+1);
			URL url = new URL(GET_URL);
			//HttpURLConnection con = (HttpURLConnection) url.openConnection();
			Object content = url.getContent();
			resp.setContentType("plain/text");
			resp.getWriter().write("ok"+content);
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Reached limit" + req.getParameter("number"));
		}
		
//		con.setRequestMethod("GET");
//		System.out.println(con.getResponseCode());
	}
}
