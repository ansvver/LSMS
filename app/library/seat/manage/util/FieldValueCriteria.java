package library.seat.manage.util;


/**
 * 使用Dao时进行查询时的查询条件项
 * 格式为“字段名-运算符-值"
 * 目前运算符仅支持EQUALS
 * <p/>
 * Date: 12-11-7
 * Time: 上午11:14
 *
 * @author : mahs
 */
public class FieldValueCriteria implements IConditionCriteria {
    /**
     * 条件字段名
     */
    private String fieldName;

    /**
     * 条件值
     */
    private Object fieldValue;

    /**
     * 运算符
     */
    private Operator operator;

    public FieldValueCriteria() {
    }

    public FieldValueCriteria(String fieldName, Object fieldValue) {
        this(fieldName, Operator.EQUALS, fieldValue);
    }

    @Override
    public void appendCriteria(StringBuilder sqlScript) {
        if (getOperator() == Operator.EQUALS) {
            sqlScript.append(getFieldName())
                    .append(" = ?");
        } else if (getOperator() == Operator.LIKE) {
            sqlScript.append(getFieldName())
                    .append(" like ?");
        } else if(getOperator() == Operator.GE) {
        	 sqlScript.append(getFieldName())
             .append(" >= ?");
        } else if(getOperator() == Operator.LE) {
	       	 sqlScript.append(getFieldName())
	         .append(" <= ?");
       	}else if(getOperator() == Operator.GT) {
	       	 sqlScript.append(getFieldName())
	         .append(" > ?");
	    } else if(getOperator() == Operator.LT) {
	       	 sqlScript.append(getFieldName())
	         .append(" < ?");
	   	}
        
    }

    public FieldValueCriteria(String fieldName, Operator operator, Object fieldValue) {
        this.fieldName = fieldName;
        this.operator = operator;
        this.fieldValue = fieldValue;
    }


    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Object getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(Object fieldValue) {
        this.fieldValue = fieldValue;
    }

    public Operator getOperator() {
        if (operator != null) {
            return operator;
        }

        return Operator.EQUALS;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }


}
