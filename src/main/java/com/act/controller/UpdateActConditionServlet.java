package com.act.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.act.model.ActService;
import com.act.model.ActVO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.util.LocalDateTimeDeserializer;
import com.util.LocalDateTimeSerializer;

@WebServlet("/updateActCondition")
public class UpdateActConditionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		  req.setCharacterEncoding("UTF-8");
	      res.setContentType("application/json, text/html; charset=UTF-8");
		  res.setCharacterEncoding("UTF-8");
	      System.out.println("Fetch Request -> UpdateActConditionServlet");
	     
	      HttpSession session = req.getSession();
	      Integer memNo1 = (Integer) session.getAttribute("memNo1");
	      Integer memNo2 = (Integer) session.getAttribute("memNo2");
	      Integer memNo3 = (Integer) session.getAttribute("memNo3");
	      
	      ActService actService = new ActService();
	      BufferedReader br = req.getReader();
	      
	      Gson gson = new Gson();
	      GsonBuilder gsonBuilder = new GsonBuilder();  
	      gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer());      
	      gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer());
	      gson = gsonBuilder.setPrettyPrinting().create();
	      ActVO actVO = gson.fromJson(br, ActVO.class);
	     
	      actVO.setMen_no(memNo1);
	      actService.updateAct(actVO);
	      
	      // ??????????????????????????????UpdateActImageServlet??????
	      System.out.println("???????????????" + actVO.getAct_no());
	      session.setAttribute("afterUpdateActNo", actVO.getAct_no());
		  
		  // ?????????Browser ???????????????????????????
		  String json = gson.toJson(actVO);
		  PrintWriter pw = res.getWriter();
		  pw.print(json);		    
	}

}
