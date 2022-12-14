package com.act_pic.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.act_pic.model.ActPicService;
import com.act_pic.model.ActPicVO;

@WebServlet("/uploadActImage")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class UploadActImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
        res.setContentType("image/jpeg, image/jpg, image/png, image/gif");
	    res.setCharacterEncoding("UTF-8");
	    
        System.out.println("Fetch Request -> UploadActImageServlet");
        HttpSession session = req.getSession();
        System.out.println("UploadActImageServlet : " + (Integer)session.getAttribute("afterInsertActNO"));
        
		InputStream is = req.getInputStream();
		byte[] image = org.apache.commons.io.IOUtils.toByteArray(is);
	
		ActPicVO actPicVO = new ActPicVO();
		actPicVO.setAct_no((Integer)session.getAttribute("afterInsertActNO"));
		actPicVO.setAct_pic(image);
		
		ActPicService actPicService = new ActPicService();
		actPicService.uploadActPic(actPicVO);	
	}
}
