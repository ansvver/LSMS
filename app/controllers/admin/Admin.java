package controllers.admin;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.google.gson.Gson;



import library.seat.manage.service.IDeskService;
import library.seat.manage.service.IOrderService;
import controllers.BaseController;
import library.seat.manage.dto.DeskInfo;

/**
 * 
 * @author mahs
 * @version 1.0
 * @created 1-1-2013 22:50:00
 */
public class Admin extends BaseController {
	
	@Inject
	private static IDeskService deskService;
	@Inject
	private static IOrderService orderService;
	
	public static void index(){
		List<DeskInfo> beReserDeskList = new ArrayList<DeskInfo>();
		beReserDeskList = deskService.queryBeResDeskList(0, null);
		Gson gson = new Gson();
		String deskJson = gson.toJson(beReserDeskList);
		renderTemplate("admin/index.html", deskJson);
	}
	
	public static IDeskService getDeskService() {
		return deskService;
	}
	public static void setDeskService(IDeskService deskService) {
		Admin.deskService = deskService;
	}
	public static IOrderService getOrderService() {
		return orderService;
	}
	public static void setOrderService(IOrderService orderService) {
		Admin.orderService = orderService;
	}
	
	
}
