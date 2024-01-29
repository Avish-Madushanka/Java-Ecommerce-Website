package com.green.srv;

import java.io.IOException;





import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import com.green.beans.UserBean;
import com.green.service.impl.UserServiceImpl;

/**
 * Servlet implementation class LoginSrv
 */

@WebServlet("/LoginSrv")
public class LoginSrv extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LoginSrv() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        String userType = request.getParameter("usertype");
        response.setContentType("text/html");

        String status = "Login Denied! Invalid Username or password.";

        if (userType.equals("admin")) { // Login as Admin
            if (password.equals("admin") && userName.equals("admin@gmail.com")) {
                // valid
                RequestDispatcher rd = request.getRequestDispatcher("adminViewProduct.jsp");
                HttpSession session = request.getSession();
                session.setAttribute("username", userName);
                session.setAttribute("password", password);
                session.setAttribute("usertype", userType);
                rd.forward(request, response);
            } else {
                // Invalid;
                RequestDispatcher rd = request.getRequestDispatcher("login.jsp?message=" + status);
                rd.include(request, response);
            }
        } else { // Login as customer
            UserServiceImpl udao = new UserServiceImpl();
            status = udao.isValidCredential(userName, password);

            if (status.equalsIgnoreCase("valid")) {
                // valid user
                UserBean user = udao.getUserDetails(userName, password);
                HttpSession session = request.getSession();
                session.setAttribute("userdata", user);
                session.setAttribute("username", userName);
                session.setAttribute("password", password);
                session.setAttribute("usertype", userType);

                // Redirect to the original request URL if available
                String originalRequestUrl = request.getParameter("originalRequestUrl");
                if (originalRequestUrl != null && !originalRequestUrl.isEmpty()) {
                    response.sendRedirect(originalRequestUrl);
                } else {
                    // If no original request URL, redirect to a default page (e.g., userHome.jsp)
                    response.sendRedirect("userHome.jsp");
                }
            } else {
                // invalid user;
                RequestDispatcher rd = request.getRequestDispatcher("login.jsp?message=" + status);
                rd.forward(request, response);
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
