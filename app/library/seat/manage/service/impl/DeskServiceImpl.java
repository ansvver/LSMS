package library.seat.manage.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import library.seat.manage.dao.IDeskDao;
import library.seat.manage.dao.IOrderDao;
import library.seat.manage.dto.DeskInfo;
import library.seat.manage.dto.OrdersInfo;
import library.seat.manage.dto.PageInfo;
import library.seat.manage.exception.BusinessException;
import library.seat.manage.exception.DataAccessException;
import library.seat.manage.service.IDeskService;
import library.seat.manage.util.FieldValueCriteria;
import library.seat.manage.util.IConditionCriteria;
import library.seat.manage.util.Operator;

/**
 * 
 * @author mahs
 * @version 1.0
 * @created 1-1-2013 22:50:00
 */
public class DeskServiceImpl implements IDeskService {

	@Inject
	private IDeskDao deskDao;
	@Inject
	private IOrderDao orderDao;
	
	@Override
	public PageInfo<DeskInfo> queryByConditon(int floor, String block,
			PageInfo pageInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DeskInfo getDeskById(int deskId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DeskInfo> queryDeskList(int floor, String block) {
		try {
			List<FieldValueCriteria> criteria = new ArrayList<FieldValueCriteria>();
			
			if (floor != 0) {
				criteria.add(new FieldValueCriteria("FLOOR", floor));
			}
			if (block != null && !block.isEmpty()) {
				criteria.add(new FieldValueCriteria("BLOCK", block));
			}
			
			return deskDao.findByCriteria(criteria);
		} catch (DataAccessException e) {
            throw new BusinessException("error_query_rec_fail", e);
        }
	}

	@Override
	public List<DeskInfo> queryBeResDeskList(int floor, String block) {
		List<DeskInfo> deskList = queryDeskList(floor, block);
		for (DeskInfo desk : deskList) {
			desk.getExtFields().put("reserveStatus", 0);
		}
		List<FieldValueCriteria> criteria = new ArrayList<FieldValueCriteria>();
		Date today = new Date(new Date().getYear(), new Date().getMonth(), new Date().getDay());
		criteria.add(new FieldValueCriteria("RESERVE_BEGIN_TIME", Operator.GE, today.getTime()));
		List<OrdersInfo> orderList = orderDao.findByCriteria(criteria);
		List<DeskInfo> beReserDeskList = new ArrayList<DeskInfo>();
		for(OrdersInfo order : orderList) {
			for (DeskInfo desk : deskList) {
				if(order.getDeskId() == desk.getDeskId()) {
					ChangeReseverStatus(order.getSeatNum(), desk);
				}
			}
		}
		for(DeskInfo desk : deskList) {
			if((Integer)desk.getExtField("reserveStatus") != 0) {
				beReserDeskList.add(desk);
			}
		}
		return beReserDeskList;
	}
	
	private void ChangeReseverStatus(int deskNum, DeskInfo desk) {
		int reserverStatus = 0;
		switch (deskNum) {
		case 1 : 
				reserverStatus = (Integer) desk.getExtFields().get("reserveStatus");
				desk.getExtFields().put("reserveStatus", reserverStatus|128); 
				break;
		case 2 : 
			reserverStatus = (Integer) desk.getExtFields().get("reserveStatus");
			desk.getExtFields().put("reserveStatus", reserverStatus|64);
			break;
		case 3 : 
			reserverStatus = (Integer) desk.getExtFields().get("reserveStatus");
			desk.getExtFields().put("reserveStatus", reserverStatus|32); 
			break;
		case 4 : 
			reserverStatus = (Integer) desk.getExtFields().get("reserveStatus");
			desk.getExtFields().put("reserveStatus", reserverStatus|16); 
			break;
		case 5 : 
			reserverStatus = (Integer) desk.getExtFields().get("reserveStatus");
			desk.getExtFields().put("reserveStatus", reserverStatus|8);
			break;
		case 6 : 
			reserverStatus = (Integer) desk.getExtFields().get("reserveStatus");
			desk.getExtFields().put("reserveStatus", reserverStatus|4); 
			break;
		case 7 : 
			reserverStatus = (Integer) desk.getExtFields().get("reserveStatus");
			desk.getExtFields().put("reserveStatus", reserverStatus|2); 
			break;
		case 8 : 
			reserverStatus = (Integer) desk.getExtFields().get("reserveStatus");
			desk.getExtFields().put("reserveStatus", reserverStatus|1);
			break;
		}
	}
	
	@Override
	public int getDeskId(int floor, String block, int desk_num) throws BusinessException{
		// TODO Auto-generated method stub
		int result = -1;
		try {
			result = deskDao.getDeskId(floor, block, desk_num);
		} catch (DataAccessException e) {
			throw new BusinessException("err_get_recd_fail ", e);
		}
		return result;
	}

	public IDeskDao getDeskDao() {
		return deskDao;
	}

	public void setDeskDao(IDeskDao deskDao) {
		this.deskDao = deskDao;
	}

	public IOrderDao getOrderDao() {
		return orderDao;
	}

	public void setOrderDao(IOrderDao orderDao) {
		this.orderDao = orderDao;
	}

	
}
