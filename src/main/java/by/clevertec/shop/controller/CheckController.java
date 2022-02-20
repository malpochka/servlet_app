package by.clevertec.shop.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.clevertec.shop.dao.PurchaseCsvImplDao;
import by.clevertec.shop.dao.PurchaseDAO;
import by.clevertec.shop.model.entity.Byn;
import by.clevertec.shop.model.entity.DiscountCard;
import by.clevertec.shop.model.entity.Purchase;
import by.clevertec.shop.model.logic.PurchaseLogic;


@WebServlet("/check")
public class CheckController extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int[] idItem = Stream.of(request.getParameterValues("idItem"))
				.mapToInt(Integer::parseInt).toArray();
		int[] quantity =Stream.of(request.getParameterValues("quantity")).filter(x->x!="")
				.mapToInt(Integer::parseInt).toArray();
		for(Integer in:quantity) {
			System.out.println(in);
		}
		Map<Integer,Integer> receiptPurchases=getReceiptPurchases(idItem,quantity);
		PurchaseDAO purchaseDao = new PurchaseCsvImplDao();
		List<Purchase> purchases=purchaseDao.getPurchaseById(receiptPurchases);
		String card =request.getParameter("card");
		DiscountCard customerDiscountCard =getDiscountCard(card);
		
		Byn totalCost =PurchaseLogic.getTotalCost(purchases);
		Byn totalFinalCost = PurchaseLogic.getTotalCostAfterDiscount(purchases, customerDiscountCard);
		Byn discount = PurchaseLogic.getDiscount(purchases, customerDiscountCard);
		request.setAttribute("purchase",purchases);
		request.setAttribute("cost",totalCost);
		request.setAttribute("card",customerDiscountCard.getNameCard());
		request.setAttribute("discount",discount);
		request.setAttribute("totalCost",totalFinalCost);
		
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/check.jsp");
		rd.forward(request, response);	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	private Map<Integer,Integer> getReceiptPurchases(int[] idItem, int[] quantity){
		
		Map<Integer,Integer> receiptPurchases=new HashMap<>();
		for(int i=0;i<idItem.length;i++) {
			receiptPurchases.put(idItem[i], quantity[i]);
		}
		return receiptPurchases;
	}
	private DiscountCard getDiscountCard(String card){
		DiscountCard discountCard = new DiscountCard();
		int idCard;
		
		if(card.equals("")) {
			idCard = 0; 
		}else {
		idCard = Integer.parseInt(card);
		discountCard = new DiscountCard(idCard);
		}
		
		return discountCard;
	}

}
