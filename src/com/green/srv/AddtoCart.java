package com.green.srv;

import java.io.IOException;


import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.green.beans.DemandBean;
import com.green.beans.ProductBean;
import com.green.service.impl.CartServiceImpl;
import com.green.service.impl.DemandServiceImpl;
import com.green.service.impl.ProductServiceImpl;

/**
 * Servlet implementation class AddtoCart
 */
@WebServlet("/AddtoCart")
public class AddtoCart extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String userName = (String) session.getAttribute("username");
        String password = (String) session.getAttribute("password");
        String userType = (String) session.getAttribute("usertype");

        if (userName == null || password == null || userType == null || !userType.equalsIgnoreCase("customer")) {
            response.sendRedirect("login.jsp?message=Session Expired, Login Again to Continue!");
            return;
        }

        // Login check successful

        String userId = userName;
        String prodId = request.getParameter("pid");
        int pQty = Integer.parseInt(request.getParameter("pqty")); // 1

        CartServiceImpl cart = new CartServiceImpl();
        ProductServiceImpl productDao = new ProductServiceImpl();

        ProductBean product = productDao.getProductDetails(prodId);

        int availableQty = product.getProdQuantity();
        int cartQty = cart.getProductCount(userId, prodId);
        pQty += cartQty;

        if (pQty == cartQty) {
            String status = cart.removeProductFromCart(userId, prodId);
            session.setAttribute("statusMessage", status);
            response.sendRedirect("userHome.jsp");
        } else if (availableQty < pQty) {
            String status = null;
            if (availableQty == 0) {
                status = "Product is Out of Stock!";
            } else {
                cart.updateProductToCart(userId, prodId, availableQty);
                status = "Only " + availableQty + " no of " + product.getProdName()
                        + " are available in the shop! So we are adding only " + availableQty
                        + " products into Your Cart" + "";
            }
            DemandBean demandBean = new DemandBean(userName, product.getProdId(), pQty - availableQty);
            DemandServiceImpl demand = new DemandServiceImpl();
            boolean flag = demand.addProduct(demandBean);

            if (flag) {
                status += "<br/>Later, We Will Mail You when " + product.getProdName()
                        + " will be available into the Store!";
            }
            session.setAttribute("statusMessage", status);
            response.sendRedirect("cartDetails.jsp");
        } else {
            String status = cart.updateProductToCart(userId, prodId, pQty);
            session.setAttribute("statusMessage", status);
            response.sendRedirect("cartDetails.jsp");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
