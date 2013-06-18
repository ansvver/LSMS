package library.seat.manage.dao;

import java.util.List;

import library.seat.manage.util.FieldValueCriteria;
import library.seat.manage.util.IConditionCriteria;

/**
 * 
 * @author mahs
 * @version 1.0
 * @created 1-1-2013 22:50:00
 */
public class BaseDao {
	
	/**
     * 将条件拼接在查询语句当中
     *
     * @param criteria  条件
     * @param queryRecs 查询语句
     * @param param     数据库参数
     */
    protected void appendCriterias(List<FieldValueCriteria> criteria, StringBuilder queryRecs) {
        if (criteria != null) {
            for (IConditionCriteria c : criteria) {
                queryRecs.append(" and (");
                c.appendCriteria(queryRecs);
                queryRecs.append(")");
            }
        }
    }
}
