package com.admin.servlet;

import com.DAO.FeedbackDAOImpl;
import com.DB.DBConnect;
import com.entity.FeedbackDtls;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/addFeedback")
public class FeedBackAdd extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            String fdbname=req.getParameter("name");
            String fdbemail=req.getParameter("email");
            String fdbmessage=req.getParameter("message");


            FeedbackDtls fdb=new FeedbackDtls(fdbname,fdbemail,fdbmessage);

            FeedbackDAOImpl dao=new FeedbackDAOImpl(DBConnect.getConn());
            boolean f =dao.addFeedback(fdb);

            HttpSession sesssion=req.getSession();


            if(f){
                sesssion.setAttribute("added","Successfully Feedback Sent.");
                resp.sendRedirect("feedback.jsp");
            }else{
                sesssion.setAttribute("failed","Something Went Wrong.");
                resp.sendRedirect("feedback.jsp");
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}