package com.paopao.hzgzf.modules.pay.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GzfBillRecordExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public GzfBillRecordExample() {
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

        public Criteria andBillingCycleIdIsNull() {
            addCriterion("billing_cycle_id is null");
            return (Criteria) this;
        }

        public Criteria andBillingCycleIdIsNotNull() {
            addCriterion("billing_cycle_id is not null");
            return (Criteria) this;
        }

        public Criteria andBillingCycleIdEqualTo(Integer value) {
            addCriterion("billing_cycle_id =", value, "billingCycleId");
            return (Criteria) this;
        }

        public Criteria andBillingCycleIdNotEqualTo(Integer value) {
            addCriterion("billing_cycle_id <>", value, "billingCycleId");
            return (Criteria) this;
        }

        public Criteria andBillingCycleIdGreaterThan(Integer value) {
            addCriterion("billing_cycle_id >", value, "billingCycleId");
            return (Criteria) this;
        }

        public Criteria andBillingCycleIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("billing_cycle_id >=", value, "billingCycleId");
            return (Criteria) this;
        }

        public Criteria andBillingCycleIdLessThan(Integer value) {
            addCriterion("billing_cycle_id <", value, "billingCycleId");
            return (Criteria) this;
        }

        public Criteria andBillingCycleIdLessThanOrEqualTo(Integer value) {
            addCriterion("billing_cycle_id <=", value, "billingCycleId");
            return (Criteria) this;
        }

        public Criteria andBillingCycleIdIn(List<Integer> values) {
            addCriterion("billing_cycle_id in", values, "billingCycleId");
            return (Criteria) this;
        }

        public Criteria andBillingCycleIdNotIn(List<Integer> values) {
            addCriterion("billing_cycle_id not in", values, "billingCycleId");
            return (Criteria) this;
        }

        public Criteria andBillingCycleIdBetween(Integer value1, Integer value2) {
            addCriterion("billing_cycle_id between", value1, value2, "billingCycleId");
            return (Criteria) this;
        }

        public Criteria andBillingCycleIdNotBetween(Integer value1, Integer value2) {
            addCriterion("billing_cycle_id not between", value1, value2, "billingCycleId");
            return (Criteria) this;
        }

        public Criteria andAccountIdIsNull() {
            addCriterion("account_id is null");
            return (Criteria) this;
        }

        public Criteria andAccountIdIsNotNull() {
            addCriterion("account_id is not null");
            return (Criteria) this;
        }

        public Criteria andAccountIdEqualTo(String value) {
            addCriterion("account_id =", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdNotEqualTo(String value) {
            addCriterion("account_id <>", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdGreaterThan(String value) {
            addCriterion("account_id >", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdGreaterThanOrEqualTo(String value) {
            addCriterion("account_id >=", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdLessThan(String value) {
            addCriterion("account_id <", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdLessThanOrEqualTo(String value) {
            addCriterion("account_id <=", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdLike(String value) {
            addCriterion("account_id like", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdNotLike(String value) {
            addCriterion("account_id not like", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdIn(List<String> values) {
            addCriterion("account_id in", values, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdNotIn(List<String> values) {
            addCriterion("account_id not in", values, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdBetween(String value1, String value2) {
            addCriterion("account_id between", value1, value2, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdNotBetween(String value1, String value2) {
            addCriterion("account_id not between", value1, value2, "accountId");
            return (Criteria) this;
        }

        public Criteria andPaymentIdIsNull() {
            addCriterion("payment_id is null");
            return (Criteria) this;
        }

        public Criteria andPaymentIdIsNotNull() {
            addCriterion("payment_id is not null");
            return (Criteria) this;
        }

        public Criteria andPaymentIdEqualTo(String value) {
            addCriterion("payment_id =", value, "paymentId");
            return (Criteria) this;
        }

        public Criteria andPaymentIdNotEqualTo(String value) {
            addCriterion("payment_id <>", value, "paymentId");
            return (Criteria) this;
        }

        public Criteria andPaymentIdGreaterThan(String value) {
            addCriterion("payment_id >", value, "paymentId");
            return (Criteria) this;
        }

        public Criteria andPaymentIdGreaterThanOrEqualTo(String value) {
            addCriterion("payment_id >=", value, "paymentId");
            return (Criteria) this;
        }

        public Criteria andPaymentIdLessThan(String value) {
            addCriterion("payment_id <", value, "paymentId");
            return (Criteria) this;
        }

        public Criteria andPaymentIdLessThanOrEqualTo(String value) {
            addCriterion("payment_id <=", value, "paymentId");
            return (Criteria) this;
        }

        public Criteria andPaymentIdLike(String value) {
            addCriterion("payment_id like", value, "paymentId");
            return (Criteria) this;
        }

        public Criteria andPaymentIdNotLike(String value) {
            addCriterion("payment_id not like", value, "paymentId");
            return (Criteria) this;
        }

        public Criteria andPaymentIdIn(List<String> values) {
            addCriterion("payment_id in", values, "paymentId");
            return (Criteria) this;
        }

        public Criteria andPaymentIdNotIn(List<String> values) {
            addCriterion("payment_id not in", values, "paymentId");
            return (Criteria) this;
        }

        public Criteria andPaymentIdBetween(String value1, String value2) {
            addCriterion("payment_id between", value1, value2, "paymentId");
            return (Criteria) this;
        }

        public Criteria andPaymentIdNotBetween(String value1, String value2) {
            addCriterion("payment_id not between", value1, value2, "paymentId");
            return (Criteria) this;
        }

        public Criteria andAcctItemIdIsNull() {
            addCriterion("acct_item_id is null");
            return (Criteria) this;
        }

        public Criteria andAcctItemIdIsNotNull() {
            addCriterion("acct_item_id is not null");
            return (Criteria) this;
        }

        public Criteria andAcctItemIdEqualTo(String value) {
            addCriterion("acct_item_id =", value, "acctItemId");
            return (Criteria) this;
        }

        public Criteria andAcctItemIdNotEqualTo(String value) {
            addCriterion("acct_item_id <>", value, "acctItemId");
            return (Criteria) this;
        }

        public Criteria andAcctItemIdGreaterThan(String value) {
            addCriterion("acct_item_id >", value, "acctItemId");
            return (Criteria) this;
        }

        public Criteria andAcctItemIdGreaterThanOrEqualTo(String value) {
            addCriterion("acct_item_id >=", value, "acctItemId");
            return (Criteria) this;
        }

        public Criteria andAcctItemIdLessThan(String value) {
            addCriterion("acct_item_id <", value, "acctItemId");
            return (Criteria) this;
        }

        public Criteria andAcctItemIdLessThanOrEqualTo(String value) {
            addCriterion("acct_item_id <=", value, "acctItemId");
            return (Criteria) this;
        }

        public Criteria andAcctItemIdLike(String value) {
            addCriterion("acct_item_id like", value, "acctItemId");
            return (Criteria) this;
        }

        public Criteria andAcctItemIdNotLike(String value) {
            addCriterion("acct_item_id not like", value, "acctItemId");
            return (Criteria) this;
        }

        public Criteria andAcctItemIdIn(List<String> values) {
            addCriterion("acct_item_id in", values, "acctItemId");
            return (Criteria) this;
        }

        public Criteria andAcctItemIdNotIn(List<String> values) {
            addCriterion("acct_item_id not in", values, "acctItemId");
            return (Criteria) this;
        }

        public Criteria andAcctItemIdBetween(String value1, String value2) {
            addCriterion("acct_item_id between", value1, value2, "acctItemId");
            return (Criteria) this;
        }

        public Criteria andAcctItemIdNotBetween(String value1, String value2) {
            addCriterion("acct_item_id not between", value1, value2, "acctItemId");
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

        public Criteria andLastBillAmountIsNull() {
            addCriterion("last_bill_amount is null");
            return (Criteria) this;
        }

        public Criteria andLastBillAmountIsNotNull() {
            addCriterion("last_bill_amount is not null");
            return (Criteria) this;
        }

        public Criteria andLastBillAmountEqualTo(Integer value) {
            addCriterion("last_bill_amount =", value, "lastBillAmount");
            return (Criteria) this;
        }

        public Criteria andLastBillAmountNotEqualTo(Integer value) {
            addCriterion("last_bill_amount <>", value, "lastBillAmount");
            return (Criteria) this;
        }

        public Criteria andLastBillAmountGreaterThan(Integer value) {
            addCriterion("last_bill_amount >", value, "lastBillAmount");
            return (Criteria) this;
        }

        public Criteria andLastBillAmountGreaterThanOrEqualTo(Integer value) {
            addCriterion("last_bill_amount >=", value, "lastBillAmount");
            return (Criteria) this;
        }

        public Criteria andLastBillAmountLessThan(Integer value) {
            addCriterion("last_bill_amount <", value, "lastBillAmount");
            return (Criteria) this;
        }

        public Criteria andLastBillAmountLessThanOrEqualTo(Integer value) {
            addCriterion("last_bill_amount <=", value, "lastBillAmount");
            return (Criteria) this;
        }

        public Criteria andLastBillAmountIn(List<Integer> values) {
            addCriterion("last_bill_amount in", values, "lastBillAmount");
            return (Criteria) this;
        }

        public Criteria andLastBillAmountNotIn(List<Integer> values) {
            addCriterion("last_bill_amount not in", values, "lastBillAmount");
            return (Criteria) this;
        }

        public Criteria andLastBillAmountBetween(Integer value1, Integer value2) {
            addCriterion("last_bill_amount between", value1, value2, "lastBillAmount");
            return (Criteria) this;
        }

        public Criteria andLastBillAmountNotBetween(Integer value1, Integer value2) {
            addCriterion("last_bill_amount not between", value1, value2, "lastBillAmount");
            return (Criteria) this;
        }

        public Criteria andBillAmountIsNull() {
            addCriterion("bill_amount is null");
            return (Criteria) this;
        }

        public Criteria andBillAmountIsNotNull() {
            addCriterion("bill_amount is not null");
            return (Criteria) this;
        }

        public Criteria andBillAmountEqualTo(Integer value) {
            addCriterion("bill_amount =", value, "billAmount");
            return (Criteria) this;
        }

        public Criteria andBillAmountNotEqualTo(Integer value) {
            addCriterion("bill_amount <>", value, "billAmount");
            return (Criteria) this;
        }

        public Criteria andBillAmountGreaterThan(Integer value) {
            addCriterion("bill_amount >", value, "billAmount");
            return (Criteria) this;
        }

        public Criteria andBillAmountGreaterThanOrEqualTo(Integer value) {
            addCriterion("bill_amount >=", value, "billAmount");
            return (Criteria) this;
        }

        public Criteria andBillAmountLessThan(Integer value) {
            addCriterion("bill_amount <", value, "billAmount");
            return (Criteria) this;
        }

        public Criteria andBillAmountLessThanOrEqualTo(Integer value) {
            addCriterion("bill_amount <=", value, "billAmount");
            return (Criteria) this;
        }

        public Criteria andBillAmountIn(List<Integer> values) {
            addCriterion("bill_amount in", values, "billAmount");
            return (Criteria) this;
        }

        public Criteria andBillAmountNotIn(List<Integer> values) {
            addCriterion("bill_amount not in", values, "billAmount");
            return (Criteria) this;
        }

        public Criteria andBillAmountBetween(Integer value1, Integer value2) {
            addCriterion("bill_amount between", value1, value2, "billAmount");
            return (Criteria) this;
        }

        public Criteria andBillAmountNotBetween(Integer value1, Integer value2) {
            addCriterion("bill_amount not between", value1, value2, "billAmount");
            return (Criteria) this;
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

        public Criteria andUpdateByLike(String value) {
            addCriterion("update_by like", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotLike(String value) {
            addCriterion("update_by not like", value, "updateBy");
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