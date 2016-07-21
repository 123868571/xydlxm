package com.paopao.hzgzf.modules.pay.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GzfBillingCycleExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public GzfBillingCycleExample() {
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

        public Criteria andBiilingCycleIdIsNull() {
            addCriterion("biiling_cycle_id is null");
            return (Criteria) this;
        }

        public Criteria andBiilingCycleIdIsNotNull() {
            addCriterion("biiling_cycle_id is not null");
            return (Criteria) this;
        }

        public Criteria andBiilingCycleIdEqualTo(Integer value) {
            addCriterion("biiling_cycle_id =", value, "biilingCycleId");
            return (Criteria) this;
        }

        public Criteria andBiilingCycleIdNotEqualTo(Integer value) {
            addCriterion("biiling_cycle_id <>", value, "biilingCycleId");
            return (Criteria) this;
        }

        public Criteria andBiilingCycleIdGreaterThan(Integer value) {
            addCriterion("biiling_cycle_id >", value, "biilingCycleId");
            return (Criteria) this;
        }

        public Criteria andBiilingCycleIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("biiling_cycle_id >=", value, "biilingCycleId");
            return (Criteria) this;
        }

        public Criteria andBiilingCycleIdLessThan(Integer value) {
            addCriterion("biiling_cycle_id <", value, "biilingCycleId");
            return (Criteria) this;
        }

        public Criteria andBiilingCycleIdLessThanOrEqualTo(Integer value) {
            addCriterion("biiling_cycle_id <=", value, "biilingCycleId");
            return (Criteria) this;
        }

        public Criteria andBiilingCycleIdIn(List<Integer> values) {
            addCriterion("biiling_cycle_id in", values, "biilingCycleId");
            return (Criteria) this;
        }

        public Criteria andBiilingCycleIdNotIn(List<Integer> values) {
            addCriterion("biiling_cycle_id not in", values, "biilingCycleId");
            return (Criteria) this;
        }

        public Criteria andBiilingCycleIdBetween(Integer value1, Integer value2) {
            addCriterion("biiling_cycle_id between", value1, value2, "biilingCycleId");
            return (Criteria) this;
        }

        public Criteria andBiilingCycleIdNotBetween(Integer value1, Integer value2) {
            addCriterion("biiling_cycle_id not between", value1, value2, "biilingCycleId");
            return (Criteria) this;
        }

        public Criteria andBeginDateIsNull() {
            addCriterion("begin_date is null");
            return (Criteria) this;
        }

        public Criteria andBeginDateIsNotNull() {
            addCriterion("begin_date is not null");
            return (Criteria) this;
        }

        public Criteria andBeginDateEqualTo(Date value) {
            addCriterion("begin_date =", value, "beginDate");
            return (Criteria) this;
        }

        public Criteria andBeginDateNotEqualTo(Date value) {
            addCriterion("begin_date <>", value, "beginDate");
            return (Criteria) this;
        }

        public Criteria andBeginDateGreaterThan(Date value) {
            addCriterion("begin_date >", value, "beginDate");
            return (Criteria) this;
        }

        public Criteria andBeginDateGreaterThanOrEqualTo(Date value) {
            addCriterion("begin_date >=", value, "beginDate");
            return (Criteria) this;
        }

        public Criteria andBeginDateLessThan(Date value) {
            addCriterion("begin_date <", value, "beginDate");
            return (Criteria) this;
        }

        public Criteria andBeginDateLessThanOrEqualTo(Date value) {
            addCriterion("begin_date <=", value, "beginDate");
            return (Criteria) this;
        }

        public Criteria andBeginDateIn(List<Date> values) {
            addCriterion("begin_date in", values, "beginDate");
            return (Criteria) this;
        }

        public Criteria andBeginDateNotIn(List<Date> values) {
            addCriterion("begin_date not in", values, "beginDate");
            return (Criteria) this;
        }

        public Criteria andBeginDateBetween(Date value1, Date value2) {
            addCriterion("begin_date between", value1, value2, "beginDate");
            return (Criteria) this;
        }

        public Criteria andBeginDateNotBetween(Date value1, Date value2) {
            addCriterion("begin_date not between", value1, value2, "beginDate");
            return (Criteria) this;
        }

        public Criteria andEndDateIsNull() {
            addCriterion("end_date is null");
            return (Criteria) this;
        }

        public Criteria andEndDateIsNotNull() {
            addCriterion("end_date is not null");
            return (Criteria) this;
        }

        public Criteria andEndDateEqualTo(Date value) {
            addCriterion("end_date =", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotEqualTo(Date value) {
            addCriterion("end_date <>", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateGreaterThan(Date value) {
            addCriterion("end_date >", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateGreaterThanOrEqualTo(Date value) {
            addCriterion("end_date >=", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateLessThan(Date value) {
            addCriterion("end_date <", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateLessThanOrEqualTo(Date value) {
            addCriterion("end_date <=", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateIn(List<Date> values) {
            addCriterion("end_date in", values, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotIn(List<Date> values) {
            addCriterion("end_date not in", values, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateBetween(Date value1, Date value2) {
            addCriterion("end_date between", value1, value2, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotBetween(Date value1, Date value2) {
            addCriterion("end_date not between", value1, value2, "endDate");
            return (Criteria) this;
        }

        public Criteria andLastBillingCycleIdIsNull() {
            addCriterion("last_billing_cycle_id is null");
            return (Criteria) this;
        }

        public Criteria andLastBillingCycleIdIsNotNull() {
            addCriterion("last_billing_cycle_id is not null");
            return (Criteria) this;
        }

        public Criteria andLastBillingCycleIdEqualTo(Integer value) {
            addCriterion("last_billing_cycle_id =", value, "lastBillingCycleId");
            return (Criteria) this;
        }

        public Criteria andLastBillingCycleIdNotEqualTo(Integer value) {
            addCriterion("last_billing_cycle_id <>", value, "lastBillingCycleId");
            return (Criteria) this;
        }

        public Criteria andLastBillingCycleIdGreaterThan(Integer value) {
            addCriterion("last_billing_cycle_id >", value, "lastBillingCycleId");
            return (Criteria) this;
        }

        public Criteria andLastBillingCycleIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("last_billing_cycle_id >=", value, "lastBillingCycleId");
            return (Criteria) this;
        }

        public Criteria andLastBillingCycleIdLessThan(Integer value) {
            addCriterion("last_billing_cycle_id <", value, "lastBillingCycleId");
            return (Criteria) this;
        }

        public Criteria andLastBillingCycleIdLessThanOrEqualTo(Integer value) {
            addCriterion("last_billing_cycle_id <=", value, "lastBillingCycleId");
            return (Criteria) this;
        }

        public Criteria andLastBillingCycleIdIn(List<Integer> values) {
            addCriterion("last_billing_cycle_id in", values, "lastBillingCycleId");
            return (Criteria) this;
        }

        public Criteria andLastBillingCycleIdNotIn(List<Integer> values) {
            addCriterion("last_billing_cycle_id not in", values, "lastBillingCycleId");
            return (Criteria) this;
        }

        public Criteria andLastBillingCycleIdBetween(Integer value1, Integer value2) {
            addCriterion("last_billing_cycle_id between", value1, value2, "lastBillingCycleId");
            return (Criteria) this;
        }

        public Criteria andLastBillingCycleIdNotBetween(Integer value1, Integer value2) {
            addCriterion("last_billing_cycle_id not between", value1, value2, "lastBillingCycleId");
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

        public Criteria andNextBillingCycleIdIsNull() {
            addCriterion("next_billing_cycle_id is null");
            return (Criteria) this;
        }

        public Criteria andNextBillingCycleIdIsNotNull() {
            addCriterion("next_billing_cycle_id is not null");
            return (Criteria) this;
        }

        public Criteria andNextBillingCycleIdEqualTo(Integer value) {
            addCriterion("next_billing_cycle_id =", value, "nextBillingCycleId");
            return (Criteria) this;
        }

        public Criteria andNextBillingCycleIdNotEqualTo(Integer value) {
            addCriterion("next_billing_cycle_id <>", value, "nextBillingCycleId");
            return (Criteria) this;
        }

        public Criteria andNextBillingCycleIdGreaterThan(Integer value) {
            addCriterion("next_billing_cycle_id >", value, "nextBillingCycleId");
            return (Criteria) this;
        }

        public Criteria andNextBillingCycleIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("next_billing_cycle_id >=", value, "nextBillingCycleId");
            return (Criteria) this;
        }

        public Criteria andNextBillingCycleIdLessThan(Integer value) {
            addCriterion("next_billing_cycle_id <", value, "nextBillingCycleId");
            return (Criteria) this;
        }

        public Criteria andNextBillingCycleIdLessThanOrEqualTo(Integer value) {
            addCriterion("next_billing_cycle_id <=", value, "nextBillingCycleId");
            return (Criteria) this;
        }

        public Criteria andNextBillingCycleIdIn(List<Integer> values) {
            addCriterion("next_billing_cycle_id in", values, "nextBillingCycleId");
            return (Criteria) this;
        }

        public Criteria andNextBillingCycleIdNotIn(List<Integer> values) {
            addCriterion("next_billing_cycle_id not in", values, "nextBillingCycleId");
            return (Criteria) this;
        }

        public Criteria andNextBillingCycleIdBetween(Integer value1, Integer value2) {
            addCriterion("next_billing_cycle_id between", value1, value2, "nextBillingCycleId");
            return (Criteria) this;
        }

        public Criteria andNextBillingCycleIdNotBetween(Integer value1, Integer value2) {
            addCriterion("next_billing_cycle_id not between", value1, value2, "nextBillingCycleId");
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