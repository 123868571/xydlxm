package com.paopao.hzgzf.modules.pay.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GzfAcctItemTypeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public GzfAcctItemTypeExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andAcctItemTypeIdIsNull() {
            addCriterion("acct_item_type_id is null");
            return (Criteria) this;
        }

        public Criteria andAcctItemTypeIdIsNotNull() {
            addCriterion("acct_item_type_id is not null");
            return (Criteria) this;
        }

        public Criteria andAcctItemTypeIdEqualTo(Integer value) {
            addCriterion("acct_item_type_id =", value, "acctItemTypeId");
            return (Criteria) this;
        }

        public Criteria andAcctItemTypeIdNotEqualTo(Integer value) {
            addCriterion("acct_item_type_id <>", value, "acctItemTypeId");
            return (Criteria) this;
        }

        public Criteria andAcctItemTypeIdGreaterThan(Integer value) {
            addCriterion("acct_item_type_id >", value, "acctItemTypeId");
            return (Criteria) this;
        }

        public Criteria andAcctItemTypeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("acct_item_type_id >=", value, "acctItemTypeId");
            return (Criteria) this;
        }

        public Criteria andAcctItemTypeIdLessThan(Integer value) {
            addCriterion("acct_item_type_id <", value, "acctItemTypeId");
            return (Criteria) this;
        }

        public Criteria andAcctItemTypeIdLessThanOrEqualTo(Integer value) {
            addCriterion("acct_item_type_id <=", value, "acctItemTypeId");
            return (Criteria) this;
        }

        public Criteria andAcctItemTypeIdIn(List<Integer> values) {
            addCriterion("acct_item_type_id in", values, "acctItemTypeId");
            return (Criteria) this;
        }

        public Criteria andAcctItemTypeIdNotIn(List<Integer> values) {
            addCriterion("acct_item_type_id not in", values, "acctItemTypeId");
            return (Criteria) this;
        }

        public Criteria andAcctItemTypeIdBetween(Integer value1, Integer value2) {
            addCriterion("acct_item_type_id between", value1, value2, "acctItemTypeId");
            return (Criteria) this;
        }

        public Criteria andAcctItemTypeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("acct_item_type_id not between", value1, value2, "acctItemTypeId");
            return (Criteria) this;
        }

        public Criteria andAcctItemTypeCodeIsNull() {
            addCriterion("acct_item_type_code is null");
            return (Criteria) this;
        }

        public Criteria andAcctItemTypeCodeIsNotNull() {
            addCriterion("acct_item_type_code is not null");
            return (Criteria) this;
        }

        public Criteria andAcctItemTypeCodeEqualTo(String value) {
            addCriterion("acct_item_type_code =", value, "acctItemTypeCode");
            return (Criteria) this;
        }

        public Criteria andAcctItemTypeCodeNotEqualTo(String value) {
            addCriterion("acct_item_type_code <>", value, "acctItemTypeCode");
            return (Criteria) this;
        }

        public Criteria andAcctItemTypeCodeGreaterThan(String value) {
            addCriterion("acct_item_type_code >", value, "acctItemTypeCode");
            return (Criteria) this;
        }

        public Criteria andAcctItemTypeCodeGreaterThanOrEqualTo(String value) {
            addCriterion("acct_item_type_code >=", value, "acctItemTypeCode");
            return (Criteria) this;
        }

        public Criteria andAcctItemTypeCodeLessThan(String value) {
            addCriterion("acct_item_type_code <", value, "acctItemTypeCode");
            return (Criteria) this;
        }

        public Criteria andAcctItemTypeCodeLessThanOrEqualTo(String value) {
            addCriterion("acct_item_type_code <=", value, "acctItemTypeCode");
            return (Criteria) this;
        }

        public Criteria andAcctItemTypeCodeLike(String value) {
            addCriterion("acct_item_type_code like", value, "acctItemTypeCode");
            return (Criteria) this;
        }

        public Criteria andAcctItemTypeCodeNotLike(String value) {
            addCriterion("acct_item_type_code not like", value, "acctItemTypeCode");
            return (Criteria) this;
        }

        public Criteria andAcctItemTypeCodeIn(List<String> values) {
            addCriterion("acct_item_type_code in", values, "acctItemTypeCode");
            return (Criteria) this;
        }

        public Criteria andAcctItemTypeCodeNotIn(List<String> values) {
            addCriterion("acct_item_type_code not in", values, "acctItemTypeCode");
            return (Criteria) this;
        }

        public Criteria andAcctItemTypeCodeBetween(String value1, String value2) {
            addCriterion("acct_item_type_code between", value1, value2, "acctItemTypeCode");
            return (Criteria) this;
        }

        public Criteria andAcctItemTypeCodeNotBetween(String value1, String value2) {
            addCriterion("acct_item_type_code not between", value1, value2, "acctItemTypeCode");
            return (Criteria) this;
        }

        public Criteria andAcctItemTypeNameIsNull() {
            addCriterion("acct_item_type_name is null");
            return (Criteria) this;
        }

        public Criteria andAcctItemTypeNameIsNotNull() {
            addCriterion("acct_item_type_name is not null");
            return (Criteria) this;
        }

        public Criteria andAcctItemTypeNameEqualTo(String value) {
            addCriterion("acct_item_type_name =", value, "acctItemTypeName");
            return (Criteria) this;
        }

        public Criteria andAcctItemTypeNameNotEqualTo(String value) {
            addCriterion("acct_item_type_name <>", value, "acctItemTypeName");
            return (Criteria) this;
        }

        public Criteria andAcctItemTypeNameGreaterThan(String value) {
            addCriterion("acct_item_type_name >", value, "acctItemTypeName");
            return (Criteria) this;
        }

        public Criteria andAcctItemTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("acct_item_type_name >=", value, "acctItemTypeName");
            return (Criteria) this;
        }

        public Criteria andAcctItemTypeNameLessThan(String value) {
            addCriterion("acct_item_type_name <", value, "acctItemTypeName");
            return (Criteria) this;
        }

        public Criteria andAcctItemTypeNameLessThanOrEqualTo(String value) {
            addCriterion("acct_item_type_name <=", value, "acctItemTypeName");
            return (Criteria) this;
        }

        public Criteria andAcctItemTypeNameLike(String value) {
            addCriterion("acct_item_type_name like", value, "acctItemTypeName");
            return (Criteria) this;
        }

        public Criteria andAcctItemTypeNameNotLike(String value) {
            addCriterion("acct_item_type_name not like", value, "acctItemTypeName");
            return (Criteria) this;
        }

        public Criteria andAcctItemTypeNameIn(List<String> values) {
            addCriterion("acct_item_type_name in", values, "acctItemTypeName");
            return (Criteria) this;
        }

        public Criteria andAcctItemTypeNameNotIn(List<String> values) {
            addCriterion("acct_item_type_name not in", values, "acctItemTypeName");
            return (Criteria) this;
        }

        public Criteria andAcctItemTypeNameBetween(String value1, String value2) {
            addCriterion("acct_item_type_name between", value1, value2, "acctItemTypeName");
            return (Criteria) this;
        }

        public Criteria andAcctItemTypeNameNotBetween(String value1, String value2) {
            addCriterion("acct_item_type_name not between", value1, value2, "acctItemTypeName");
            return (Criteria) this;
        }

        public Criteria andAcctItemTypeDescIsNull() {
            addCriterion("acct_item_type_desc is null");
            return (Criteria) this;
        }

        public Criteria andAcctItemTypeDescIsNotNull() {
            addCriterion("acct_item_type_desc is not null");
            return (Criteria) this;
        }

        public Criteria andAcctItemTypeDescEqualTo(String value) {
            addCriterion("acct_item_type_desc =", value, "acctItemTypeDesc");
            return (Criteria) this;
        }

        public Criteria andAcctItemTypeDescNotEqualTo(String value) {
            addCriterion("acct_item_type_desc <>", value, "acctItemTypeDesc");
            return (Criteria) this;
        }

        public Criteria andAcctItemTypeDescGreaterThan(String value) {
            addCriterion("acct_item_type_desc >", value, "acctItemTypeDesc");
            return (Criteria) this;
        }

        public Criteria andAcctItemTypeDescGreaterThanOrEqualTo(String value) {
            addCriterion("acct_item_type_desc >=", value, "acctItemTypeDesc");
            return (Criteria) this;
        }

        public Criteria andAcctItemTypeDescLessThan(String value) {
            addCriterion("acct_item_type_desc <", value, "acctItemTypeDesc");
            return (Criteria) this;
        }

        public Criteria andAcctItemTypeDescLessThanOrEqualTo(String value) {
            addCriterion("acct_item_type_desc <=", value, "acctItemTypeDesc");
            return (Criteria) this;
        }

        public Criteria andAcctItemTypeDescLike(String value) {
            addCriterion("acct_item_type_desc like", value, "acctItemTypeDesc");
            return (Criteria) this;
        }

        public Criteria andAcctItemTypeDescNotLike(String value) {
            addCriterion("acct_item_type_desc not like", value, "acctItemTypeDesc");
            return (Criteria) this;
        }

        public Criteria andAcctItemTypeDescIn(List<String> values) {
            addCriterion("acct_item_type_desc in", values, "acctItemTypeDesc");
            return (Criteria) this;
        }

        public Criteria andAcctItemTypeDescNotIn(List<String> values) {
            addCriterion("acct_item_type_desc not in", values, "acctItemTypeDesc");
            return (Criteria) this;
        }

        public Criteria andAcctItemTypeDescBetween(String value1, String value2) {
            addCriterion("acct_item_type_desc between", value1, value2, "acctItemTypeDesc");
            return (Criteria) this;
        }

        public Criteria andAcctItemTypeDescNotBetween(String value1, String value2) {
            addCriterion("acct_item_type_desc not between", value1, value2, "acctItemTypeDesc");
            return (Criteria) this;
        }

        public Criteria andPrintPriorityIsNull() {
            addCriterion("print_priority is null");
            return (Criteria) this;
        }

        public Criteria andPrintPriorityIsNotNull() {
            addCriterion("print_priority is not null");
            return (Criteria) this;
        }

        public Criteria andPrintPriorityEqualTo(Integer value) {
            addCriterion("print_priority =", value, "printPriority");
            return (Criteria) this;
        }

        public Criteria andPrintPriorityNotEqualTo(Integer value) {
            addCriterion("print_priority <>", value, "printPriority");
            return (Criteria) this;
        }

        public Criteria andPrintPriorityGreaterThan(Integer value) {
            addCriterion("print_priority >", value, "printPriority");
            return (Criteria) this;
        }

        public Criteria andPrintPriorityGreaterThanOrEqualTo(Integer value) {
            addCriterion("print_priority >=", value, "printPriority");
            return (Criteria) this;
        }

        public Criteria andPrintPriorityLessThan(Integer value) {
            addCriterion("print_priority <", value, "printPriority");
            return (Criteria) this;
        }

        public Criteria andPrintPriorityLessThanOrEqualTo(Integer value) {
            addCriterion("print_priority <=", value, "printPriority");
            return (Criteria) this;
        }

        public Criteria andPrintPriorityIn(List<Integer> values) {
            addCriterion("print_priority in", values, "printPriority");
            return (Criteria) this;
        }

        public Criteria andPrintPriorityNotIn(List<Integer> values) {
            addCriterion("print_priority not in", values, "printPriority");
            return (Criteria) this;
        }

        public Criteria andPrintPriorityBetween(Integer value1, Integer value2) {
            addCriterion("print_priority between", value1, value2, "printPriority");
            return (Criteria) this;
        }

        public Criteria andPrintPriorityNotBetween(Integer value1, Integer value2) {
            addCriterion("print_priority not between", value1, value2, "printPriority");
            return (Criteria) this;
        }

        public Criteria andBillPriorityIsNull() {
            addCriterion("bill_priority is null");
            return (Criteria) this;
        }

        public Criteria andBillPriorityIsNotNull() {
            addCriterion("bill_priority is not null");
            return (Criteria) this;
        }

        public Criteria andBillPriorityEqualTo(Integer value) {
            addCriterion("bill_priority =", value, "billPriority");
            return (Criteria) this;
        }

        public Criteria andBillPriorityNotEqualTo(Integer value) {
            addCriterion("bill_priority <>", value, "billPriority");
            return (Criteria) this;
        }

        public Criteria andBillPriorityGreaterThan(Integer value) {
            addCriterion("bill_priority >", value, "billPriority");
            return (Criteria) this;
        }

        public Criteria andBillPriorityGreaterThanOrEqualTo(Integer value) {
            addCriterion("bill_priority >=", value, "billPriority");
            return (Criteria) this;
        }

        public Criteria andBillPriorityLessThan(Integer value) {
            addCriterion("bill_priority <", value, "billPriority");
            return (Criteria) this;
        }

        public Criteria andBillPriorityLessThanOrEqualTo(Integer value) {
            addCriterion("bill_priority <=", value, "billPriority");
            return (Criteria) this;
        }

        public Criteria andBillPriorityIn(List<Integer> values) {
            addCriterion("bill_priority in", values, "billPriority");
            return (Criteria) this;
        }

        public Criteria andBillPriorityNotIn(List<Integer> values) {
            addCriterion("bill_priority not in", values, "billPriority");
            return (Criteria) this;
        }

        public Criteria andBillPriorityBetween(Integer value1, Integer value2) {
            addCriterion("bill_priority between", value1, value2, "billPriority");
            return (Criteria) this;
        }

        public Criteria andBillPriorityNotBetween(Integer value1, Integer value2) {
            addCriterion("bill_priority not between", value1, value2, "billPriority");
            return (Criteria) this;
        }

        public Criteria andStateIsNull() {
            addCriterion("state is null");
            return (Criteria) this;
        }

        public Criteria andStateIsNotNull() {
            addCriterion("state is not null");
            return (Criteria) this;
        }

        public Criteria andStateEqualTo(String value) {
            addCriterion("state =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(String value) {
            addCriterion("state <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(String value) {
            addCriterion("state >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(String value) {
            addCriterion("state >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(String value) {
            addCriterion("state <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(String value) {
            addCriterion("state <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLike(String value) {
            addCriterion("state like", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotLike(String value) {
            addCriterion("state not like", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<String> values) {
            addCriterion("state in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<String> values) {
            addCriterion("state not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(String value1, String value2) {
            addCriterion("state between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(String value1, String value2) {
            addCriterion("state not between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateDateIsNull() {
            addCriterion("state_date is null");
            return (Criteria) this;
        }

        public Criteria andStateDateIsNotNull() {
            addCriterion("state_date is not null");
            return (Criteria) this;
        }

        public Criteria andStateDateEqualTo(Date value) {
            addCriterion("state_date =", value, "stateDate");
            return (Criteria) this;
        }

        public Criteria andStateDateNotEqualTo(Date value) {
            addCriterion("state_date <>", value, "stateDate");
            return (Criteria) this;
        }

        public Criteria andStateDateGreaterThan(Date value) {
            addCriterion("state_date >", value, "stateDate");
            return (Criteria) this;
        }

        public Criteria andStateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("state_date >=", value, "stateDate");
            return (Criteria) this;
        }

        public Criteria andStateDateLessThan(Date value) {
            addCriterion("state_date <", value, "stateDate");
            return (Criteria) this;
        }

        public Criteria andStateDateLessThanOrEqualTo(Date value) {
            addCriterion("state_date <=", value, "stateDate");
            return (Criteria) this;
        }

        public Criteria andStateDateIn(List<Date> values) {
            addCriterion("state_date in", values, "stateDate");
            return (Criteria) this;
        }

        public Criteria andStateDateNotIn(List<Date> values) {
            addCriterion("state_date not in", values, "stateDate");
            return (Criteria) this;
        }

        public Criteria andStateDateBetween(Date value1, Date value2) {
            addCriterion("state_date between", value1, value2, "stateDate");
            return (Criteria) this;
        }

        public Criteria andStateDateNotBetween(Date value1, Date value2) {
            addCriterion("state_date not between", value1, value2, "stateDate");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}