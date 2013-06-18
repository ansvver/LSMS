package library.seat.manage.service;

import java.util.List;

import library.seat.manage.dto.PageInfo;
import library.seat.manage.dto.DeskInfo;
import library.seat.manage.exception.BusinessException;
import library.seat.manage.exception.DataAccessException;


/**
 * 
 * @author mahs
 * @version 1.0
 * @created 2-1-2013 13:20:00
 */
public interface IDeskService {
	
	PageInfo<DeskInfo> queryByConditon(int floor, String block, 
			PageInfo pageInfo);
	
	DeskInfo getDeskById(int deskId);
	
	List<DeskInfo> queryDeskList(int floor, String block);
	
	List<DeskInfo> queryBeResDeskList(int floor, String block);

	int getDeskId(int floor, String block, int desk_num) throws BusinessException;
	
}
