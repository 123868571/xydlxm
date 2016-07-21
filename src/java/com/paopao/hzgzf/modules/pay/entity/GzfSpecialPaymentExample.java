package com.paopao.hzgzf.modules.pay.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GzfSpecialPaymentExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public GzfSpecialPaymentExample() {
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

        public Criteria andSpePaymentIdIsNull() {
            addCriterion("spe_payment_id is null");
            return (Criteria) this;
        }

        public Criteria andSpePaymentIdIsNotNull() {
            addCriterion("spe_payment_id is not null");
            return (Criteria) this;
        }

        public Criteria andSpePaymentIdEqualTo(Integer value) {
            addCriterion("spe_payment_id =", value, "spePaymentId");
            return (Criteria) this;
        }

        public Criteria andSpePaymentIdNotEqualTo(Integer value) {
            addCriterion("spe_payment_id <>", value, "spePaymentId");
            return (Criteria) this;
        }

        public Criteria andSpePaymentIdGreaterThan(Integer value) {
            addCriterion("spe_payment_id >", value, "spePaymentId");
            return (Criteria) this;
        }

        public Criteria andSpePaymentIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("spe_payment_id >=", value, "spePaymentId");
            return (Criteria) this;
        }

        public Criteria andSpePaymentIdLessThan(Integer value) {
            addCriterion("spe_payment_id <", value, "spePaymentId");
            return (Criteria) this;
        }

        public Criteria andSpePaymentIdLessThanOrEqualTo(Integer value) {
            addCriterion("spe_payment_id <=", value, "spePaymentId");
            return (Criteria) this;
        }

        public Criteria andSpePaymentIdIn(List<Integer> values) {
            addCriterion("spe_payment_id in", values, "spePaymentId");
            return (Criteria) this;
        }

        public Criteria andSpePaymentIdNotIn(List<Integer> values) {
            addCriterion("spe_payment_id not in", values, "spePaymentId");
            return (Criteria) this;
        }

        public Criteria andSpePaymentIdBetween(Integer value1, Integer value2) {
            addCriterion("spe_payment_id between", value1, value2, "spePaymentId");
            return (Criteria) this;
        }

        public Criteria andSpePaymentIdNotBetween(Integer value1, Integer value2) {
            addCriterion("spe_payment_id not between", value1, value2, "spePaymentId");
            return (Criteria) this;
        }

        public Criteria andSpePaymentDescIsNull() {
            addCriterion("spe_payment_desc is null");
            return (Criteria) this;
        }

        public Criteria andSpePaymentDescIsNotNull() {
            addCriterion("spe_payment_desc is not null");
            return (Criteria) this;
        }

        public Criteria andSpePaymentDescEqualTo(String value) {
            addCriterion("spe_payment_desc =", value, "spePaymentDesc");
            return (Criteria) this;
        }

        public Criteria andSpePaymentDescNotEqualTo(String value) {
            addCriterion("spe_payment_desc <>", value, "spePaymentDesc");
            return (Criteria) this;
        }

        public Criteria andSpePaymentDescGreaterThan(String value) {
            addCriterion("spe_payment_desc >", value, "spePaymentDesc");
            return (Criteria) this;
        }

        public Criteria andSpePaymentDescGreaterThanOrEqualTo(String value) {
            addCriterion("spe_payment_desc >=", value, "spePaymentDesc");
            return (Criteria) this;
        }

        public Criteria andSpePaymentDescLessThan(String value) {
            addCriterion("spe_payment_desc <", value, "spePaymentDesc");
            return (Criteria) this;
        }

        public Criteria andSpePaymentDescLessThanOrEqualTo(String value) {
            addCriterion("spe_payment_desc <=", value, "spePaymentDesc");
            return (Criteria) this;
        }

        public Criteria andSpePaymentDescLike(String value) {
            addCriterion("spe_payment_desc like", value, "spePaymentDesc");
            return (Criteria) this;
        }

        public Criteria andSpePaymentDescNotLike(String value) {
            addCriterion("spe_payment_desc not like", value, "spePaymentDesc");
            return (Criteria) this;
        }

        public Criteria andSpePaymentDescIn(List<String> values) {
            addCriterion("spe_payment_desc in", values, "spePaymentDesc");
            return (Criteria) this;
        }

        public Criteria andSpePaymentDescNotIn(List<String> values) {
            addCriterion("spe_payment_desc not in", values, "spePaymentDesc");
            return (Criteria) this;
        }

        public Criteria andSpePaymentDescBetween(String value1, String value2) {
            addCriterion("spe_payment_desc between", value1, value2, "spePaymentDesc");
            return (Criteria) this;
        }

        public Criteria andSpePaymentDescNotBetween(String value1, String value2) {
            addCriterion("spe_payment_desc not between", value1, value2, "spePaymentDesc");
            return (Criteria) this;
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