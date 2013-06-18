package library.seat.manage.dto;

import java.util.HashMap;
import java.util.Map;

/**
 * 扩展dto属性基类（用于非映射数据库字段属性使用）
 * @author mahs
 * @version 1.0
 * @created 1-1-2013 23:09:28
 */
public class ExtFieldDto {

	/**
	 * dto扩展属性
	 */
	private Map extFields = new HashMap<Object,Object>();

	public ExtFieldDto(){

	}

	public Map getExtFields(){
		return extFields;
	}

	/**
	 * 
	 * @param extFields
	 */
	public void setExtFields(Map extFields){
		this.extFields = extFields;
	}

	/**
	 * 通过属性key取dto属性值
	 * 
	 * @param fieldKey    属性key
	 */
	public Object getExtField(Object fieldKey){
		return extFields.get(fieldKey);
	}

	/**
	 * 
	 * @param fieldKey    dto属性key
	 * @param feildValue    dto属性值
	 */
	public void setExtField(Object fieldKey, Object feildValue){
		extFields.put(fieldKey, feildValue);
	}
}//end ExtFieldDto