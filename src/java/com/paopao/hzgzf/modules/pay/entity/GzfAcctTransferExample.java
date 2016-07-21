package com.paopao.hzgzf.modules.pay.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GzfAcctTransferExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public GzfAcctTransferExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andSrcAcctIdIsNull() {
            addCriterion("src_acct_id is null");
            return (Criteria) this;
        }

        public Criteria andSrcAcctIdIsNotNull() {
            addCriterion("src_acct_id is not null");
            return (Criteria) this;
        }

        public Criteria andSrcAcctIdEqualTo(String value) {
            addCriterion("src_acct_id =", value, "srcAcctId");
            return (Criteria) this;
        }

        public Criteria andSrcAcctIdNotEqualTo(String value) {
            addCriterion("src_acct_id <>", value, "srcAcctId");
            return (Criteria) this;
        }

        public Criteria andSrcAcctIdGreaterThan(String value) {
            addCriterion("src_acct_id >", value, "srcAcctId");
            return (Criteria) this;
        }

        public Criteria andSrcAcctIdGreaterThanOrEqualTo(String value) {
            addCriterion("src_acct_id >=", value, "srcAcctId");
            return (Criteria) this;
        }

        public Criteria andSrcAcctIdLessThan(String value) {
            addCriterion("src_acct_id <", value, "srcAcctId");
            return (Criteria) this;
        }

        public Criteria andSrcAcctIdLessThanOrEqualTo(String value) {
            addCriterion("src_acct_id <=", value, "srcAcctId");
            return (Criteria) this;
        }

        public Criteria andSrcAcctIdLike(String value) {
            addCriterion("src_acct_id like", value, "srcAcctId");
            return (Criteria) this;
        }

        public Criteria andSrcAcctIdNotLike(String value) {
            addCriterion("src_acct_id not like", value, "srcAcctId");
            return (Criteria) this;
        }

        public Criteria andSrcAcctIdIn(List<String> values) {
            addCriterion("src_acct_id in", values, "srcAcctId");
            return (Criteria) this;
        }

        public Criteria andSrcAcctIdNotIn(List<String> values) {
            addCriterion("src_acct_id not in", values, "srcAcctId");
            return (Criteria) this;
        }

        public Criteria andSrcAcctIdBetween(String value1, String value2) {
            addCriterion("src_acct_id between", value1, value2, "srcAcctId");
            return (Criteria) this;
        }

        public Criteria andSrcAcctIdNotBetween(String value1, String value2) {
            addCriterion("src_acct_id not between", value1, value2, "srcAcctId");
            return (Criteria) this;
        }

        public Criteria andDestAcctIdIsNull() {
            addCriterion("dest_acct_id is null");
            return (Criteria) this;
        }

        public Criteria andDestAcctIdIsNotNull() {
            addCriterion("dest_acct_id is not null");
            return (Criteria) this;
        }

        public Criteria andDestAcctIdEqualTo(String value) {
            addCriterion("dest_acct_id =", value, "destAcctId");
            return (Criteria) this;
        }

        public Criteria andDestAcctIdNotEqualTo(String value) {
            addCriterion("dest_acct_id <>", value, "destAcctId");
            return (Criteria) this;
        }

        public Criteria andDestAcctIdGreaterThan(String value) {
            addCriterion("dest_acct_id >", value, "destAcctId");
            return (Criteria) this;
        }

        public Criteria andDestAcctIdGreaterThanOrEqualTo(String value) {
            addCriterion("dest_acct_id >=", value, "destAcctId");
            return (Criteria) this;
        }

        public Criteria andDestAcctIdLessThan(String value) {
            addCriterion("dest_acct_id <", value, "destAcctId");
            return (Criteria) this;
        }

        public Criteria andDestAcctIdLessThanOrEqualTo(String value) {
            addCriterion("dest_acct_id <=", value, "destAcctId");
            return (Criteria) this;
        }

        public Criteria andDestAcctIdLike(String value) {
            addCriterion("dest_acct_id like", value, "destAcctId");
            return (Criteria) this;
        }

        public Criteria andDestAcctIdNotLike(String value) {
            addCriterion("dest_acct_id not like", value, "destAcctId");
            return (Criteria) this;
        }

        public Criteria andDestAcctIdIn(List<String> values) {
            addCriterion("dest_acct_id in", values, "destAcctId");
            return (Criteria) this;
        }

        public Criteria andDestAcctIdNotIn(List<String> values) {
            addCriterion("dest_acct_id not in", values, "destAcctId");
            return (Criteria) this;
        }

        public Criteria andDestAcctIdBetween(String value1, String value2) {
            addCriterion("dest_acct_id between", value1, value2, "destAcctId");
            return (Criteria) this;
        }

        public Criteria andDestAcctIdNotBetween(String value1, String value2) {
            addCriterion("dest_acct_id not between", value1, value2, "destAcctId");
            return (Criteria) this;
        }

        public Criteria andSrcSpecPaymentIdIsNull() {
            addCriterion("src_spec_payment_id is null");
            return (Criteria) this;
        }

        public Criteria andSrcSpecPaymentIdIsNotNull() {
            addCriterion("src_spec_payment_id is not null");
            return (Criteria) this;
        }

        public Criteria andSrcSpecPaymentIdEqualTo(Integer value) {
            addCriterion("src_spec_payment_id =", value, "srcSpecPaymentId");
            return (Criteria) this;
        }

        public Criteria andSrcSpecPaymentIdNotEqualTo(Integer value) {
            addCriterion("src_spec_payment_id <>", value, "srcSpecPaymentId");
            return (Criteria) this;
        }

        public Criteria andSrcSpecPaymentIdGreaterThan(Integer value) {
            addCriterion("src_spec_payment_id >", value, "srcSpecPaymentId");
            return (Criteria) this;
        }

        public Criteria andSrcSpecPaymentIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("src_spec_payment_id >=", value, "srcSpecPaymentId");
            return (Criteria) this;
        }

        public Criteria andSrcSpecPaymentIdLessThan(Integer value) {
            addCriterion("src_spec_payment_id <", value, "srcSpecPaymentId");
            return (Criteria) this;
        }

        public Criteria andSrcSpecPaymentIdLessThanOrEqualTo(Integer value) {
            addCriterion("src_spec_payment_id <=", value, "srcSpecPaymentId");
            return (Criteria) this;
        }

        public Criteria andSrcSpecPaymentIdIn(List<Integer> values) {
            addCriterion("src_spec_payment_id in", values, "srcSpecPaymentId");
            return (Criteria) this;
        }

        public Criteria andSrcSpecPaymentIdNotIn(List<Integer> values) {
            addCriterion("src_spec_payment_id not in", values, "srcSpecPaymentId");
            return (Criteria) this;
        }

        public Criteria andSrcSpecPaymentIdBetween(Integer value1, Integer value2) {
            addCriterion("src_spec_payment_id between", value1, value2, "srcSpecPaymentId");
            return (Criteria) this;
        }

        public Criteria andSrcSpecPaymentIdNotBetween(Integer value1, Integer value2) {
            addCriterion("src_spec_payment_id not between", value1, value2, "srcSpecPaymentId");
            return (Criteria) this;
        }

        public Criteria andDestSpecPaymentIdIsNull() {
            addCriterion("dest_spec_payment_id is null");
            return (Criteria) this;
        }

        public Criteria andDestSpecPaymentIdIsNotNull() {
            addCriterion("dest_spec_payment_id is not null");
            return (Criteria) this;
        }

        public Criteria andDestSpecPaymentIdEqualTo(Integer value) {
            addCriterion("dest_spec_payment_id =", value, "destSpecPaymentId");
            return (Criteria) this;
        }

        public Criteria andDestSpecPaymentIdNotEqualTo(Integer value) {
            addCriterion("dest_spec_payment_id <>", value, "destSpecPaymentId");
            return (Criteria) this;
        }

        public Criteria andDestSpecPaymentIdGreaterThan(Integer value) {
            addCriterion("dest_spec_payment_id >", value, "destSpecPaymentId");
            return (Criteria) this;
        }

        public Criteria andDestSpecPaymentIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("dest_spec_payment_id >=", value, "destSpecPaymentId");
            return (Criteria) this;
        }

        public Criteria andDestSpecPaymentIdLessThan(Integer value) {
            addCriterion("dest_spec_payment_id <", value, "destSpecPaymentId");
            return (Criteria) this;
        }

        public Criteria andDestSpecPaymentIdLessThanOrEqualTo(Integer value) {
            addCriterion("dest_spec_payment_id <=", value, "destSpecPaymentId");
            return (Criteria) this;
        }

        public Criteria andDestSpecPaymentIdIn(List<Integer> values) {
            addCriterion("dest_spec_payment_id in", values, "destSpecPaymentId");
            return (Criteria) this;
        }

        public Criteria andDestSpecPaymentIdNotIn(List<Integer> values) {
            addCriterion("dest_spec_payment_id not in", values, "destSpecPaymentId");
            return (Criteria) this;
        }

        public Criteria andDestSpecPaymentIdBetween(Integer value1, Integer value2) {
            addCriterion("dest_spec_payment_id between", value1, value2, "destSpecPaymentId");
            return (Criteria) this;
        }

        public Criteria andDestSpecPaymentIdNotBetween(Integer value1, Integer value2) {
            addCriterion("dest_spec_payment_id not between", value1, value2, "destSpecPaymentId");
            return (Criteria) this;
        }

        public Criteria andOptCodeIsNull() {
            addCriterion("opt_code is null");
            return (Criteria) this;
        }

        public Criteria andOptCodeIsNotNull() {
            addCriterion("opt_code is not null");
            return (Criteria) this;
        }

        public Criteria andOptCodeEqualTo(String value) {
            addCriterion("opt_code =", value, "optCode");
            return (Criteria) this;
        }

        public Criteria andOptCodeNotEqualTo(String value) {
            addCriterion("opt_code <>", value, "optCode");
            return (Criteria) this;
        }

        public Criteria andOptCodeGreaterThan(String value) {
            addCriterion("opt_code >", value, "optCode");
            return (Criteria) this;
        }

        public Criteria andOptCodeGreaterThanOrEqualTo(String value) {
            addCriterion("opt_code >=", value, "optCode");
            return (Criteria) this;
        }

        public Criteria andOptCodeLessThan(String value) {
            addCriterion("opt_code <", value, "optCode");
            return (Criteria) this;
        }

        public Criteria andOptCodeLessThanOrEqualTo(String value) {
            addCriterion("opt_code <=", value, "optCode");
            return (Criteria) this;
        }

        public Criteria andOptCodeLike(String value) {
            addCriterion("opt_code like", value, "optCode");
            return (Criteria) this;
        }

        public Criteria andOptCodeNotLike(String value) {
            addCriterion("opt_code not like", value, "optCode");
            return (Criteria) this;
        }

        public Criteria andOptCodeIn(List<String> values) {
            addCriterion("opt_code in", values, "optCode");
            return (Criteria) this;
        }

        public Criteria andOptCodeNotIn(List<String> values) {
            addCriterion("opt_code not in", values, "optCode");
            return (Criteria) this;
        }

        public Criteria andOptCodeBetween(String value1, String value2) {
            addCriterion("opt_code between", value1, value2, "optCode");
            return (Criteria) this;
        }

        public Criteria andOptCodeNotBetween(String value1, String value2) {
            addCriterion("opt_code not between", value1, value2, "optCode");
            return (Criteria) this;
        }

        public Criteria andSrcBalanceIdIsNull() {
            addCriterion("src_balance_id is null");
            return (Criteria) this;
        }

        public Criteria andSrcBalanceIdIsNotNull() {
            addCriterion("src_balance_id is not null");
            return (Criteria) this;
        }

        public Criteria andSrcBalanceIdEqualTo(String value) {
            addCriterion("src_balance_id =", value, "srcBalanceId");
            return (Criteria) this;
        }

        public Criteria andSrcBalanceIdNotEqualTo(String value) {
            addCriterion("src_balance_id <>", value, "srcBalanceId");
            return (Criteria) this;
        }

        public Criteria andSrcBalanceIdGreaterThan(String value) {
            addCriterion("src_balance_id >", value, "srcBalanceId");
            return (Criteria) this;
        }

        public Criteria andSrcBalanceIdGreaterThanOrEqualTo(String value) {
            addCriterion("src_balance_id >=", value, "srcBalanceId");
            return (Criteria) this;
        }

        public Criteria andSrcBalanceIdLessThan(String value) {
            addCriterion("src_balance_id <", value, "srcBalanceId");
            return (Criteria) this;
        }

        public Criteria andSrcBalanceIdLessThanOrEqualTo(String value) {
            addCriterion("src_balance_id <=", value, "srcBalanceId");
            return (Criteria) this;
        }

        public Criteria andSrcBalanceIdLike(String value) {
            addCriterion("src_balance_id like", value, "srcBalanceId");
            return (Criteria) this;
        }

        public Criteria andSrcBalanceIdNotLike(String value) {
            addCriterion("src_balance_id not like", value, "srcBalanceId");
            return (Criteria) this;
        }

        public Criteria andSrcBalanceIdIn(List<String> values) {
            addCriterion("src_balance_id in", values, "srcBalanceId");
            return (Criteria) this;
        }

        public Criteria andSrcBalanceIdNotIn(List<String> values) {
            addCriterion("src_balance_id not in", values, "srcBalanceId");
            return (Criteria) this;
        }

        public Criteria andSrcBalanceIdBetween(String value1, String value2) {
            addCriterion("src_balance_id between", value1, value2, "srcBalanceId");
            return (Criteria) this;
        }

        public Criteria andSrcBalanceIdNotBetween(String value1, String value2) {
            addCriterion("src_balance_id not between", value1, value2, "srcBalanceId");
            return (Criteria) this;
        }

        public Criteria andDestBalanceIdIsNull() {
            addCriterion("dest_balance_id is null");
            return (Criteria) this;
        }

        public Criteria andDestBalanceIdIsNotNull() {
            addCriterion("dest_balance_id is not null");
            return (Criteria) this;
        }

        public Criteria andDestBalanceIdEqualTo(String value) {
            addCriterion("dest_balance_id =", value, "destBalanceId");
            return (Criteria) this;
        }

        public Criteria andDestBalanceIdNotEqualTo(String value) {
            addCriterion("dest_balance_id <>", value, "destBalanceId");
            return (Criteria) this;
        }

        public Criteria andDestBalanceIdGreaterThan(String value) {
            addCriterion("dest_balance_id >", value, "destBalanceId");
            return (Criteria) this;
        }

        public Criteria andDestBalanceIdGreaterThanOrEqualTo(String value) {
            addCriterion("dest_balance_id >=", value, "destBalanceId");
            return (Criteria) this;
        }

        public Criteria andDestBalanceIdLessThan(String value) {
            addCriterion("dest_balance_id <", value, "destBalanceId");
            return (Criteria) this;
        }

        public Criteria andDestBalanceIdLessThanOrEqualTo(String value) {
            addCriterion("dest_balance_id <=", value, "destBalanceId");
            return (Criteria) this;
        }

        public Criteria andDestBalanceIdLike(String value) {
            addCriterion("dest_balance_id like", value, "destBalanceId");
            return (Criteria) this;
        }

        public Criteria andDestBalanceIdNotLike(String value) {
            addCriterion("dest_balance_id not like", value, "destBalanceId");
            return (Criteria) this;
        }

        public Criteria andDestBalanceIdIn(List<String> values) {
            addCriterion("dest_balance_id in", values, "destBalanceId");
            return (Criteria) this;
        }

        public Criteria andDestBalanceIdNotIn(List<String> values) {
            addCriterion("dest_balance_id not in", values, "destBalanceId");
            return (Criteria) this;
        }

        public Criteria andDestBalanceIdBetween(String value1, String value2) {
            addCriterion("dest_balance_id between", value1, value2, "destBalanceId");
            return (Criteria) this;
        }

        public Criteria andDestBalanceIdNotBetween(String value1, String value2) {
            addCriterion("dest_balance_id not between", value1, value2, "destBalanceId");
            return (Criteria) this;
        }

        public Criteria andAmountIsNull() {
            addCriterion("amount is null");
            return (Criteria) this;
        }

        public Criteria andAmountIsNotNull() {
            addCriterion("amount is not null");
            return (Criteria) this;
        }

        public Criteria andAmountEqualTo(Integer value) {
            addCriterion("amount =", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotEqualTo(Integer value) {
            addCriterion("amount <>", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThan(Integer value) {
            addCriterion("amount >", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThanOrEqualTo(Integer value) {
            addCriterion("amount >=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThan(Integer value) {
            addCriterion("amount <", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThanOrEqualTo(Integer value) {
            addCriterion("amount <=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountIn(List<Integer> values) {
            addCriterion("amount in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotIn(List<Integer> values) {
            addCriterion("amount not in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountBetween(Integer value1, Integer value2) {
            addCriterion("amount between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotBetween(Integer value1, Integer value2) {
            addCriterion("amount not between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andDelFlagIsNull() {
            addCriterion("del_flag is null");
            return (Criteria) this;
        }

        public Criteria andDelFlagIsNotNull() {
            addCriterion("del_flag is not null");
            return (Criteria) this;
        }

        public Criteria andDelFlagEqualTo(String value) {
            addCriterion("del_flag =", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagNotEqualTo(String value) {
            addCriterion("del_flag <>", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagGreaterThan(String value) {
            addCriterion("del_flag >", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagGreaterThanOrEqualTo(String value) {
            addCriterion("del_flag >=", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagLessThan(String value) {
            addCriterion("del_flag <", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagLessThanOrEqualTo(String value) {
            addCriterion("del_flag <=", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagLike(String value) {
            addCriterion("del_flag like", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagNotLike(String value) {
            addCriterion("del_flag not like", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagIn(List<String> values) {
            addCriterion("del_flag in", values, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagNotIn(List<String> values) {
            addCriterion("del_flag not in", values, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagBetween(String value1, String value2) {
            addCriterion("del_flag between", value1, value2, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagNotBetween(String value1, String value2) {
            addCriterion("del_flag not between", value1, value2, "delFlag");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIsNull() {
            addCriterion("update_date is null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIsNotNull() {
            addCriterion("update_date is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateEqualTo(Date value) {
            addCriterion("update_date =", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotEqualTo(Date value) {
            addCriterion("update_date <>", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThan(Date value) {
            addCriterion("update_date >", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("update_date >=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThan(Date value) {
            addCriterion("update_date <", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThanOrEqualTo(Date value) {
            addCriterion("update_date <=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIn(List<Date> values) {
            addCriterion("update_date in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotIn(List<Date> values) {
            addCriterion("update_date not in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateBetween(Date value1, Date value2) {
            addCriterion("update_date between", value1, value2, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotBetween(Date value1, Date value2) {
            addCriterion("update_date not between", value1, value2, "updateDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNull() {
            addCriterion("create_date is null");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNotNull() {
            addCriterion("create_date is not null");
            return (Criteria) this;
        }

        public Criteria andCreateDateEqualTo(Date value) {
            addCriterion("create_date =", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotEqualTo(Date value) {
            addCriterion("create_date <>", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThan(Date value) {
            addCriterion("create_date >", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("create_date >=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThan(Date value) {
            addCriterion("create_date <", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThanOrEqualTo(Date value) {
            addCriterion("create_date <=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateIn(List<Date> values) {
            addCriterion("create_date in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotIn(List<Date> values) {
            addCriterion("create_date not in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateBetween(Date value1, Date value2) {
            addCriterion("create_date between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotBetween(Date value1, Date value2) {
            addCriterion("create_date not between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateByIsNull() {
            addCriterion("create_by is null");
            return (Criteria) this;
        }

        public Criteria andCreateByIsNotNull() {
            addCriterion("create_by is not null");
            return (Criteria) this;
        }

        public Criteria andCreateByEqualTo(String value) {
            addCriterion("create_by =", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotEqualTo(String value) {
            addCriterion("create_by <>", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThan(String value) {
            addCriterion("create_by >", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThanOrEqualTo(String value) {
            addCriterion("create_by >=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThan(String value) {
            addCriterion("create_by <", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThanOrEqualTo(String value) {
            addCriterion("create_by <=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByIn(List<String> values) {
            addCriterion("create_by in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotIn(List<String> values) {
            addCriterion("create_by not in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByBetween(String value1, String value2) {
            addCriterion("create_by between", value1, value2, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotBetween(String value1, String value2) {
            addCriterion("create_by not between", value1, value2, "createBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByIsNull() {
            addCriterion("update_by is null");
            return (Criteria) this;
        }

        public Criteria andUpdateByIsNotNull() {
            addCriterion("update_by is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateByEqualTo(String value) {
            addCriterion("update_by =", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotEqualTo(String value) {
            addCriterion("update_by <>", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByGreaterThan(String value) {
            addCriterion("update_by >", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByGreaterThanOrEqualTo(String value) {
            addCriterion("update_by >=", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLessThan(String value) {
            addCriterion("update_by <", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLessThanOrEqualTo(String value) {
            addCriterion("update_by <=", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByIn(List<String> values) {
            addCriterion("update_by in", values, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotIn(List<String> values) {
            addCriterion("update_by not in", values, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByBetween(String value1, String value2) {
            addCriterion("update_by between", value1, value2, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotBetween(String value1, String value2) {
            addCriterion("update_by not between", value1, value2, "updateBy");
            return (Criteria) this;
        }

        public Criteria andRemarksIsNull() {
            addCriterion("remarks is null");
            return (Criteria) this;
        }

        public Criteria andRemarksIsNotNull() {
            addCriterion("remarks is not null");
            return (Criteria) this;
        }

        public Criteria andRemarksEqualTo(String value) {
            addCriterion("remarks =", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotEqualTo(String value) {
            addCriterion("remarks <>", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksGreaterThan(String value) {
            addCriterion("remarks >", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("remarks >=", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLessThan(String value) {
            addCriterion("remarks <", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLessThanOrEqualTo(String value) {
            addCriterion("remarks <=", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLike(String value) {
            addCriterion("remarks like", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotLike(String value) {
            addCriterion("remarks not like", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksIn(List<String> values) {
            addCriterion("remarks in", values, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotIn(List<String> values) {
            addCriterion("remarks not in", values, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksBetween(String value1, String value2) {
            addCriterion("remarks between", value1, value2, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotBetween(String value1, String value2) {
            addCriterion("remarks not between", value1, value2, "remarks");
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