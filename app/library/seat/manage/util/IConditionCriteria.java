package library.seat.manage.util;


/**
 * 数据库查询条件表达式
 * <p/>
 * Date: 12-12-7
 * Time: 上午11:30
 *
 * @author : mahs
 */
public interface IConditionCriteria {
    /**
     * 拼接条件表达式
     *
     * @param sqlScript 数据库脚本
     * @param param     条件
     */
    public void appendCriteria(StringBuilder sqlScript);
}
