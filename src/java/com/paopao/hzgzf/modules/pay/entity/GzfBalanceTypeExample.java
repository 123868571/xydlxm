package com.paopao.hzgzf.modules.pay.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GzfBalanceTypeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public GzfBalanceTypeExample() {
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

        public Criteria andBalanceTypeIdIsNull() {
            addCriterion("balance_type_id is null");
            return (Criteria) this;
        }

        public Criteria andBalanceTypeIdIsNotNull() {
            addCriterion("balance_type_id is not null");
            return (Criteria) this;
        }

        public Criteria andBalanceTypeIdEqualTo(Integer value) {
            addCriterion("balance_type_id =", value, "balanceTypeId");
            return (Criteria) this;
        }

        public Criteria andBalanceTypeIdNotEqualTo(Integer value) {
            addCriterion("balance_type_id <>", value, "balanceTypeId");
            return (Criteria) this;
        }

        public Criteria andBalanceTypeIdGreaterThan(Integer value) {
            addCriterion("balance_type_id >", value, "balanceTypeId");
            return (Criteria) this;
        }

        public Criteria andBalanceTypeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("balance_type_id >=", value, "balanceTypeId");
            return (Criteria) this;
        }

        public Criteria andBalanceTypeIdLessThan(Integer value) {
            addCriterion("balance_type_id <", value, "balanceTypeId");
            return (Criteria) this;
        }

        public Criteria andBalanceTypeIdLessThanOrEqualTo(Integer value) {
            addCriterion("balance_type_id <=", value, "balanceTypeId");
            return (Criteria) this;
        }

        public Criteria andBalanceTypeIdIn(List<Integer> values) {
            addCriterion("balance_type_id in", values, "balanceTypeId");
            return (Criteria) this;
        }

        public Criteria andBalanceTypeIdNotIn(List<Integer> values) {
            addCriterion("balance_type_id not in", values, "balanceTypeId");
            return (Criteria) this;
        }

        public Criteria andBalanceTypeIdBetween(Integer value1, Integer value2) {
            addCriterion("balance_type_id between", value1, value2, "balanceTypeId");
            return (Criteria) this;
        }

        public Criteria andBalanceTypeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("balance_type_id not between", value1, value2, "balanceTypeId");
            return (Criteria) this;
        }

        public Criteria andBalanceTypeNameIsNull() {
            addCriterion("balance_type_name is null");
            return (Criteria) this;
        }

        public Criteria andBalanceTypeNameIsNotNull() {
            addCriterion("balance_type_name is not null");
            return (Criteria) this;
        }

        public Criteria andBalanceTypeNameEqualTo(String value) {
            addCriterion("balance_type_name =", value, "balanceTypeName");
            return (Criteria) this;
        }

        public Criteria andBalanceTypeNameNotEqualTo(String value) {
            addCriterion("balance_type_name <>", value, "balanceTypeName");
            return (Criteria) this;
        }

        public Criteria andBalanceTypeNameGreaterThan(String value) {
            addCriterion("balance_type_name >", value, "balanceTypeName");
            return (Criteria) this;
        }

        public Criteria andBalanceTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("balance_type_name >=", value, "balanceTypeName");
            return (Criteria) this;
        }

        public Criteria andBalanceTypeNameLessThan(String value) {
            addCriterion("balance_type_name <", value, "balanceTypeName");
            return (Criteria) this;
        }

        public Criteria andBalanceTypeNameLessThanOrEqualTo(String value) {
            addCriterion("balance_type_name <=", value, "balanceTypeName");
            return (Criteria) this;
        }

        public Criteria andBalanceTypeNameLike(String value) {
            addCriterion("balance_type_name like", value, "balanceTypeName");
            return (Criteria) this;
        }

        public Criteria andBalanceTypeNameNotLike(String value) {
            addCriterion("balance_type_name not like", value, "balanceTypeName");
            return (Criteria) this;
        }

        public Criteria andBalanceTypeNameIn(List<String> values) {
            addCriterion("balance_type_name in", values, "balanceTypeName");
            return (Criteria) this;
        }

        public Criteria andBalanceTypeNameNotIn(List<String> values) {
            addCriterion("balance_type_name not in", values, "balanceTypeName");
            return (Criteria) this;
        }

        public Criteria andBalanceTypeNameBetween(String value1, String value2) {
            addCriterion("balance_type_name between", value1, value2, "balanceTypeName");
            return (Criteria) this;
        }

        public Criteria andBalanceTypeNameNotBetween(String value1, String value2) {
            addCriterion("balance_type_name not between", value1, value2, "balanceTypeName");
            return (Criteria) this;
        }

        public Criteria andAllowDrawIsNull() {
            addCriterion("allow_draw is null");
            return (Criteria) this;
        }

        public Criteria andAllowDrawIsNotNull() {
            addCriterion("allow_draw is not null");
            return (Criteria) this;
        }

        public Criteria andAllowDrawEqualTo(String value) {
            addCriterion("allow_draw =", value, "allowDraw");
            return (Criteria) this;
        }

        public Criteria andAllowDrawNotEqualTo(String value) {
            addCriterion("allow_draw <>", value, "allowDraw");
            return (Criteria) this;
        }

        public Criteria andAllowDrawGreaterThan(String value) {
            addCriterion("allow_draw >", value, "allowDraw");
            return (Criteria) this;
        }

        public Criteria andAllowDrawGreaterThanOrEqualTo(String value) {
            addCriterion("allow_draw >=", value, "allowDraw");
            return (Criteria) this;
        }

        public Criteria andAllowDrawLessThan(String value) {
            addCriterion("allow_draw <", value, "allowDraw");
            return (Criteria) this;
        }

        public Criteria andAllowDrawLessThanOrEqualTo(String value) {
            addCriterion("allow_draw <=", value, "allowDraw");
            return (Criteria) this;
        }

        public Criteria andAllowDrawLike(String value) {
            addCriterion("allow_draw like", value, "allowDraw");
            return (Criteria) this;
        }

        public Criteria andAllowDrawNotLike(String value) {
            addCriterion("allow_draw not like", value, "allowDraw");
            return (Criteria) this;
        }

        public Criteria andAllowDrawIn(List<String> values) {
            addCriterion("allow_draw in", values, "allowDraw");
            return (Criteria) this;
        }

        public Criteria andAllowDrawNotIn(List<String> values) {
            addCriterion("allow_draw not in", values, "allowDraw");
            return (Criteria) this;
        }

        public Criteria andAllowDrawBetween(String value1, String value2) {
            addCriterion("allow_draw between", value1, value2, "allowDraw");
            return (Criteria) this;
        }

        public Criteria andAllowDrawNotBetween(String value1, String value2) {
            addCriterion("allow_draw not between", value1, value2, "allowDraw");
            return (Criteria) this;
        }

        public Criteria andAllowWriteoffIsNull() {
            addCriterion("allow_writeoff is null");
            return (Criteria) this;
        }

        public Criteria andAllowWriteoffIsNotNull() {
            addCriterion("allow_writeoff is not null");
            return (Criteria) this;
        }

        public Criteria andAllowWriteoffEqualTo(String value) {
            addCriterion("allow_writeoff =", value, "allowWriteoff");
            return (Criteria) this;
        }

        public Criteria andAllowWriteoffNotEqualTo(String value) {
            addCriterion("allow_writeoff <>", value, "allowWriteoff");
            return (Criteria) this;
        }

        public Criteria andAllowWriteoffGreaterThan(String value) {
            addCriterion("allow_writeoff >", value, "allowWriteoff");
            return (Criteria) this;
        }

        public Criteria andAllowWriteoffGreaterThanOrEqualTo(String value) {
            addCriterion("allow_writeoff >=", value, "allowWriteoff");
            return (Criteria) this;
        }

        public Criteria andAllowWriteoffLessThan(String value) {
            addCriterion("allow_writeoff <", value, "allowWriteoff");
            return (Criteria) this;
        }

        public Criteria andAllowWriteoffLessThanOrEqualTo(String value) {
            addCriterion("allow_writeoff <=", value, "allowWriteoff");
            return (Criteria) this;
        }

        public Criteria andAllowWriteoffLike(String value) {
            addCriterion("allow_writeoff like", value, "allowWriteoff");
            return (Criteria) this;
        }

        public Criteria andAllowWriteoffNotLike(String value) {
            addCriterion("allow_writeoff not like", value, "allowWriteoff");
            return (Criteria) this;
        }

        public Criteria andAllowWriteoffIn(List<String> values) {
            addCriterion("allow_writeoff in", values, "allowWriteoff");
            return (Criteria) this;
        }

        public Criteria andAllowWriteoffNotIn(List<String> values) {
            addCriterion("allow_writeoff not in", values, "allowWriteoff");
            return (Criteria) this;
        }

        public Criteria andAllowWriteoffBetween(String value1, String value2) {
            addCriterion("allow_writeoff between", value1, value2, "allowWriteoff");
            return (Criteria) this;
        }

        public Criteria andAllowWriteoffNotBetween(String value1, String value2) {
            addCriterion("allow_writeoff not between", value1, value2, "allowWriteoff");
            return (Criteria) this;
        }

        public Criteria andInvoiceOfferIsNull() {
            addCriterion("invoice_offer is null");
            return (Criteria) this;
        }

        public Criteria andInvoiceOfferIsNotNull() {
            addCriterion("invoice_offer is not null");
            return (Criteria) this;
        }

        public Criteria andInvoiceOfferEqualTo(String value) {
            addCriterion("invoice_offer =", value, "invoiceOffer");
            return (Criteria) this;
        }

        public Criteria andInvoiceOfferNotEqualTo(String value) {
            addCriterion("invoice_offer <>", value, "invoiceOffer");
            return (Criteria) this;
        }

        public Criteria andInvoiceOfferGreaterThan(String value) {
            addCriterion("invoice_offer >", value, "invoiceOffer");
            return (Criteria) this;
        }

        public Criteria andInvoiceOfferGreaterThanOrEqualTo(String value) {
            addCriterion("invoice_offer >=", value, "invoiceOffer");
            return (Criteria) this;
        }

        public Criteria andInvoiceOfferLessThan(String value) {
            addCriterion("invoice_offer <", value, "invoiceOffer");
            return (Criteria) this;
        }

        public Criteria andInvoiceOfferLessThanOrEqualTo(String value) {
            addCriterion("invoice_offer <=", value, "invoiceOffer");
            return (Criteria) this;
        }

        public Criteria andInvoiceOfferLike(String value) {
            addCriterion("invoice_offer like", value, "invoiceOffer");
            return (Criteria) this;
        }

        public Criteria andInvoiceOfferNotLike(String value) {
            addCriterion("invoice_offer not like", value, "invoiceOffer");
            return (Criteria) this;
        }

        public Criteria andInvoiceOfferIn(List<String> values) {
            addCriterion("invoice_offer in", values, "invoiceOffer");
            return (Criteria) this;
        }

        public Criteria andInvoiceOfferNotIn(List<String> values) {
            addCriterion("invoice_offer not in", values, "invoiceOffer");
            return (Criteria) this;
        }

        public Criteria andInvoiceOfferBetween(String value1, String value2) {
            addCriterion("invoice_offer between", value1, value2, "invoiceOffer");
            return (Criteria) this;
        }

        public Criteria andInvoiceOfferNotBetween(String value1, String value2) {
            addCriterion("invoice_offer not between", value1, value2, "invoiceOffer");
            return (Criteria) this;
        }

        public Criteria andPriIsNull() {
            addCriterion("pri is null");
            return (Criteria) this;
        }

        public Criteria andPriIsNotNull() {
            addCriterion("pri is not null");
            return (Criteria) this;
        }

        public Criteria andPriEqualTo(Integer value) {
            addCriterion("pri =", value, "pri");
            return (Criteria) this;
        }

        public Criteria andPriNotEqualTo(Integer value) {
            addCriterion("pri <>", value, "pri");
            return (Criteria) this;
        }

        public Criteria andPriGreaterThan(Integer value) {
            addCriterion("pri >", value, "pri");
            return (Criteria) this;
        }

        public Criteria andPriGreaterThanOrEqualTo(Integer value) {
            addCriterion("pri >=", value, "pri");
            return (Criteria) this;
        }

        public Criteria andPriLessThan(Integer value) {
            addCriterion("pri <", value, "pri");
            return (Criteria) this;
        }

        public Criteria andPriLessThanOrEqualTo(Integer value) {
            addCriterion("pri <=", value, "pri");
            return (Criteria) this;
        }

        public Criteria andPriIn(List<Integer> values) {
            addCriterion("pri in", values, "pri");
            return (Criteria) this;
        }

        public Criteria andPriNotIn(List<Integer> values) {
            addCriterion("pri not in", values, "pri");
            return (Criteria) this;
        }

        public Criteria andPriBetween(Integer value1, Integer value2) {
            addCriterion("pri between", value1, value2, "pri");
            return (Criteria) this;
        }

        public Criteria andPriNotBetween(Integer value1, Integer value2) {
            addCriterion("pri not between", value1, value2, "pri");
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