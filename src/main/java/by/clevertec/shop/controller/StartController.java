package by.clevertec.shop.controller;

import java.util.List;
import java.util.ResourceBundle;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import by.clevertec.shop.dao.PurchaseCsvImplDao;
import by.clevertec.shop.dao.PurchaseDAO;
import by.clevertec.shop.exception.DaoException;
import by.clevertec.shop.exception.InitException;
import by.clevertec.shop.model.entity.Purchase;
import static by.clevertec.shop.util.Constant.*;


@WebServlet(urlPatterns = "/start", initParams = {
		@WebInitParam(name = "propertiesName", value = "resources.shop") }, loadOnStartup = 1)

public class StartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	public void init(ServletConfig sc) throws ServletException {
		super.init(sc);
		try {
			String propertiesName = sc.getInitParameter("propertiesName");
			ResourceBundle rb = ResourceBundle.getBundle(propertiesName);
			PurchaseCsvImplDao.init(rb);
			PurchaseDAO purchaseDao = new PurchaseCsvImplDao();
			List<Purchase> purchases = purchaseDao.getPurchases();
			for(Purchase pur:purchases) {
				System.out.println(pur);
			}
			if (purchases.isEmpty()) {
				throw new InitException(ERROR_PURCHASES);
			}
			
			getServletContext().setAttribute(PURCHASES, purchases);
		} catch (DaoException e) {
			e.printStackTrace();
		} catch (InitException e) {
			throw new ServletException(e);
		
		}
	}

}
