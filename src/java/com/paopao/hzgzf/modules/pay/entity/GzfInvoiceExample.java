package com.paopao.hzgzf.modules.pay.entity;

import java.util.ArrayList;
import java.util.List;

public class GzfInvoiceExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public GzfInvoiceExample() {
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

        public Criteria andAccountNameIsNull() {
            addCriterion("account_name is null");
            return (Criteria) this;
        }

        public Criteria andAccountNameIsNotNull() {
            addCriterion("account_name is not null");
            return (Criteria) this;
        }

        public Criteria andAccountNameEqualTo(String value) {
            addCriterion("account_name =", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameNotEqualTo(String value) {
            addCriterion("account_name <>", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameGreaterThan(String value) {
            addCriterion("account_name >", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameGreaterThanOrEqualTo(String value) {
            addCriterion("account_name >=", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameLessThan(String value) {
            addCriterion("account_name <", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameLessThanOrEqualTo(String value) {
            addCriterion("account_name <=", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameLike(String value) {
            addCriterion("account_name like", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameNotLike(String value) {
            addCriterion("account_name not like", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameIn(List<String> values) {
            addCriterion("account_name in", values, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameNotIn(List<String> values) {
            addCriterion("account_name not in", values, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameBetween(String value1, String value2) {
            addCriterion("account_name between", value1, value2, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameNotBetween(String value1, String value2) {
            addCriterion("account_name not between", value1, value2, "accountName");
            return (Criteria) this;
        }

        public Criteria andInvoiceAmountIsNull() {
            addCriterion("invoice_amount is null");
            return (Criteria) this;
        }

        public Criteria andInvoiceAmountIsNotNull() {
            addCriterion("invoice_amount is not null");
            return (Criteria) this;
        }

        public Criteria andInvoiceAmountEqualTo(Integer value) {
            addCriterion("invoice_amount =", value, "invoiceAmount");
            return (Criteria) this;
        }

        public Criteria andInvoiceAmountNotEqualTo(Integer value) {
            addCriterion("invoice_amount <>", value, "invoiceAmount");
            return (Criteria) this;
        }

        public Criteria andInvoiceAmountGreaterThan(Integer value) {
            addCriterion("invoice_amount >", value, "invoiceAmount");
            return (Criteria) this;
        }

        public Criteria andInvoiceAmountGreaterThanOrEqualTo(Integer value) {
            addCriterion("invoice_amount >=", value, "invoiceAmount");
            return (Criteria) this;
        }

        public Criteria andInvoiceAmountLessThan(Integer value) {
            addCriterion("invoice_amount <", value, "invoiceAmount");
            return (Criteria) this;
        }

        public Criteria andInvoiceAmountLessThanOrEqualTo(Integer value) {
            addCriterion("invoice_amount <=", value, "invoiceAmount");
            return (Criteria) this;
        }

        public Criteria andInvoiceAmountIn(List<Integer> values) {
            addCriterion("invoice_amount in", values, "invoiceAmount");
            return (Criteria) this;
        }

        public Criteria andInvoiceAmountNotIn(List<Integer> values) {
            addCriterion("invoice_amount not in", values, "invoiceAmount");
            return (Criteria) this;
        }

        public Criteria andInvoiceAmountBetween(Integer value1, Integer value2) {
            addCriterion("invoice_amount between", value1, value2, "invoiceAmount");
            return (Criteria) this;
        }

        public Criteria andInvoiceAmountNotBetween(Integer value1, Integer value2) {
            addCriterion("invoice_amount not between", value1, value2, "invoiceAmount");
            return (Criteria) this;
        }

        public Criteria andPrintCountIsNull() {
            addCriterion("print_count is null");
            return (Criteria) this;
        }

        public Criteria andPrintCountIsNotNull() {
            addCriterion("print_count is not null");
            return (Criteria) this;
        }

        public Criteria andPrintCountEqualTo(Integer value) {
            addCriterion("print_count =", value, "printCount");
            return (Criteria) this;
        }

        public Criteria andPrintCountNotEqualTo(Integer value) {
            addCriterion("print_count <>", value, "printCount");
            return (Criteria) this;
        }

        public Criteria andPrintCountGreaterThan(Integer value) {
            addCriterion("print_count >", value, "printCount");
            return (Criteria) this;
        }

        public Criteria andPrintCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("print_count >=", value, "printCount");
            return (Criteria) this;
        }

        public Criteria andPrintCountLessThan(Integer value) {
            addCriterion("print_count <", value, "printCount");
            return (Criteria) this;
        }

        public Criteria andPrintCountLessThanOrEqualTo(Integer value) {
            addCriterion("print_count <=", value, "printCount");
            return (Criteria) this;
        }

        public Criteria andPrintCountIn(List<Integer> values) {
            addCriterion("print_count in", values, "printCount");
            return (Criteria) this;
        }

        public Criteria andPrintCountNotIn(List<Integer> values) {
            addCriterion("print_count not in", values, "printCount");
            return (Criteria) this;
        }

        public Criteria andPrintCountBetween(Integer value1, Integer value2) {
            addCriterion("print_count between", value1, value2, "printCount");
            return (Criteria) this;
        }

        public Criteria andPrintCountNotBetween(Integer value1, Integer value2) {
            addCriterion("print_count not between", value1, value2, "printCount");
            return (Criteria) this;
        }

        public Criteria andSupplyCountIsNull() {
            addCriterion("supply_count is null");
            return (Criteria) this;
        }

        public Criteria andSupplyCountIsNotNull() {
            addCriterion("supply_count is not null");
            return (Criteria) this;
        }

        public Criteria andSupplyCountEqualTo(Integer value) {
            addCriterion("supply_count =", value, "supplyCount");
            return (Criteria) this;
        }

        public Criteria andSupplyCountNotEqualTo(Integer value) {
            addCriterion("supply_count <>", value, "supplyCount");
            return (Criteria) this;
        }

        public Criteria andSupplyCountGreaterThan(Integer value) {
            addCriterion("supply_count >", value, "supplyCount");
            return (Criteria) this;
        }

        public Criteria andSupplyCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("supply_count >=", value, "supplyCount");
            return (Criteria) this;
        }

        public Criteria andSupplyCountLessThan(Integer value) {
            addCriterion("supply_count <", value, "supplyCount");
            return (Criteria) this;
        }

        public Criteria andSupplyCountLessThanOrEqualTo(Integer value) {
            addCriterion("supply_count <=", value, "supplyCount");
            return (Criteria) this;
        }

        public Criteria andSupplyCountIn(List<Integer> values) {
            addCriterion("supply_count in", values, "supplyCount");
            return (Criteria) this;
        }

        public Criteria andSupplyCountNotIn(List<Integer> values) {
            addCriterion("supply_count not in", values, "supplyCount");
            return (Criteria) this;
        }

        public Criteria andSupplyCountBetween(Integer value1, Integer value2) {
            addCriterion("supply_count between", value1, value2, "supplyCount");
            return (Criteria) this;
        }

        public Criteria andSupplyCountNotBetween(Integer value1, Integer value2) {
            addCriterion("supply_count not between", value1, value2, "supplyCount");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeIsNull() {
            addCriterion("invoice_type is null");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeIsNotNull() {
            addCriterion("invoice_type is not null");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeEqualTo(String value) {
            addCriterion("invoice_type =", value, "invoiceType");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeNotEqualTo(String value) {
            addCriterion("invoice_type <>", value, "invoiceType");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeGreaterThan(String value) {
            addCriterion("invoice_type >", value, "invoiceType");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeGreaterThanOrEqualTo(String value) {
            addCriterion("invoice_type >=", value, "invoiceType");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeLessThan(String value) {
            addCriterion("invoice_type <", value, "invoiceType");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeLessThanOrEqualTo(String value) {
            addCriterion("invoice_type <=", value, "invoiceType");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeLike(String value) {
            addCriterion("invoice_type like", value, "invoiceType");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeNotLike(String value) {
            addCriterion("invoice_type not like", value, "invoiceType");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeIn(List<String> values) {
            addCriterion("invoice_type in", values, "invoiceType");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeNotIn(List<String> values) {
            addCriterion("invoice_type not in", values, "invoiceType");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeBetween(String value1, String value2) {
            addCriterion("invoice_type between", value1, value2, "invoiceType");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeNotBetween(String value1, String value2) {
            addCriterion("invoice_type not between", value1, value2, "invoiceType");
            return (Criteria) this;
        }

        public Criteria andInvoiceCodeIsNull() {
            addCriterion("invoice_code is null");
            return (Criteria) this;
        }

        public Criteria andInvoiceCodeIsNotNull() {
            addCriterion("invoice_code is not null");
            return (Criteria) this;
        }

        public Criteria andInvoiceCodeEqualTo(String value) {
            addCriterion("invoice_code =", value, "invoiceCode");
            return (Criteria) this;
        }

        public Criteria andInvoiceCodeNotEqualTo(String value) {
            addCriterion("invoice_code <>", value, "invoiceCode");
            return (Criteria) this;
        }

        public Criteria andInvoiceCodeGreaterThan(String value) {
            addCriterion("invoice_code >", value, "invoiceCode");
            return (Criteria) this;
        }

        public Criteria andInvoiceCodeGreaterThanOrEqualTo(String value) {
            addCriterion("invoice_code >=", value, "invoiceCode");
            return (Criteria) this;
        }

        public Criteria andInvoiceCodeLessThan(String value) {
            addCriterion("invoice_code <", value, "invoiceCode");
            return (Criteria) this;
        }

        public Criteria andInvoiceCodeLessThanOrEqualTo(String value) {
            addCriterion("invoice_code <=", value, "invoiceCode");
            return (Criteria) this;
        }

        public Criteria andInvoiceCodeLike(String value) {
            addCriterion("invoice_code like", value, "invoiceCode");
            return (Criteria) this;
        }

        public Criteria andInvoiceCodeNotLike(String value) {
            addCriterion("invoice_code not like", value, "invoiceCode");
            return (Criteria) this;
        }

        public Criteria andInvoiceCodeIn(List<String> values) {
            addCriterion("invoice_code in", values, "invoiceCode");
            return (Criteria) this;
        }

        public Criteria andInvoiceCodeNotIn(List<String> values) {
            addCriterion("invoice_code not in", values, "invoiceCode");
            return (Criteria) this;
        }

        public Criteria andInvoiceCodeBetween(String value1, String value2) {
            addCriterion("invoice_code between", value1, value2, "invoiceCode");
            return (Criteria) this;
        }

        public Criteria andInvoiceCodeNotBetween(String value1, String value2) {
            addCriterion("invoice_code not between", value1, value2, "invoiceCode");
            return (Criteria) this;
        }

        public Criteria andInvoiceBatchIdIsNull() {
            addCriterion("invoice_batch_id is null");
            return (Criteria) this;
        }

        public Criteria andInvoiceBatchIdIsNotNull() {
            addCriterion("invoice_batch_id is not null");
            return (Criteria) this;
        }

        public Criteria andInvoiceBatchIdEqualTo(Integer value) {
            addCriterion("invoice_batch_id =", value, "invoiceBatchId");
            return (Criteria) this;
        }

        public Criteria andInvoiceBatchIdNotEqualTo(Integer value) {
            addCriterion("invoice_batch_id <>", value, "invoiceBatchId");
            return (Criteria) this;
        }

        public Criteria andInvoiceBatchIdGreaterThan(Integer value) {
            addCriterion("invoice_batch_id >", value, "invoiceBatchId");
            return (Criteria) this;
        }

        public Criteria andInvoiceBatchIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("invoice_batch_id >=", value, "invoiceBatchId");
            return (Criteria) this;
        }

        public Criteria andInvoiceBatchIdLessThan(Integer value) {
            addCriterion("invoice_batch_id <", value, "invoiceBatchId");
            return (Criteria) this;
        }

        public Criteria andInvoiceBatchIdLessThanOrEqualTo(Integer value) {
            addCriterion("invoice_batch_id <=", value, "invoiceBatchId");
            return (Criteria) this;
        }

        public Criteria andInvoiceBatchIdIn(List<Integer> values) {
            addCriterion("invoice_batch_id in", values, "invoiceBatchId");
            return (Criteria) this;
        }

        public Criteria andInvoiceBatchIdNotIn(List<Integer> values) {
            addCriterion("invoice_batch_id not in", values, "invoiceBatchId");
            return (Criteria) this;
        }

        public Criteria andInvoiceBatchIdBetween(Integer value1, Integer value2) {
            addCriterion("invoice_batch_id between", value1, value2, "invoiceBatchId");
            return (Criteria) this;
        }

        public Criteria andInvoiceBatchIdNotBetween(Integer value1, Integer value2) {
            addCriterion("invoice_batch_id not between", value1, value2, "invoiceBatchId");
            return (Criteria) this;
        }

        public Criteria andPayAddressIsNull() {
            addCriterion("pay_address is null");
            return (Criteria) this;
        }

        public Criteria andPayAddressIsNotNull() {
            addCriterion("pay_address is not null");
            return (Criteria) this;
        }

        public Criteria andPayAddressEqualTo(String value) {
            addCriterion("pay_address =", value, "payAddress");
            return (Criteria) this;
        }

        public Criteria andPayAddressNotEqualTo(String value) {
            addCriterion("pay_address <>", value, "payAddress");
            return (Criteria) this;
        }

        public Criteria andPayAddressGreaterThan(String value) {
            addCriterion("pay_address >", value, "payAddress");
            return (Criteria) this;
        }

        public Criteria andPayAddressGreaterThanOrEqualTo(String value) {
            addCriterion("pay_address >=", value, "payAddress");
            return (Criteria) this;
        }

        public Criteria andPayAddressLessThan(String value) {
            addCriterion("pay_address <", value, "payAddress");
            return (Criteria) this;
        }

        public Criteria andPayAddressLessThanOrEqualTo(String value) {
            addCriterion("pay_address <=", value, "payAddress");
            return (Criteria) this;
        }

        public Criteria andPayAddressLike(String value) {
            addCriterion("pay_address like", value, "payAddress");
            return (Criteria) this;
        }

        public Criteria andPayAddressNotLike(String value) {
            addCriterion("pay_address not like", value, "payAddress");
            return (Criteria) this;
        }

        public Criteria andPayAddressIn(List<String> values) {
            addCriterion("pay_address in", values, "payAddress");
            return (Criteria) this;
        }

        public Criteria andPayAddressNotIn(List<String> values) {
            addCriterion("pay_address not in", values, "payAddress");
            return (Criteria) this;
        }

        public Criteria andPayAddressBetween(String value1, String value2) {
            addCriterion("pay_address between", value1, value2, "payAddress");
            return (Criteria) this;
        }

        public Criteria andPayAddressNotBetween(String value1, String value2) {
            addCriterion("pay_address not between", value1, value2, "payAddress");
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

        public Criteria andCustBankNameIsNull() {
            addCriterion("cust_bank_name is null");
            return (Criteria) this;
        }

        public Criteria andCustBankNameIsNotNull() {
            addCriterion("cust_bank_name is not null");
            return (Criteria) this;
        }

        public Criteria andCustBankNameEqualTo(String value) {
            addCriterion("cust_bank_name =", value, "custBankName");
            return (Criteria) this;
        }

        public Criteria andCustBankNameNotEqualTo(String value) {
            addCriterion("cust_bank_name <>", value, "custBankName");
            return (Criteria) this;
        }

        public Criteria andCustBankNameGreaterThan(String value) {
            addCriterion("cust_bank_name >", value, "custBankName");
            return (Criteria) this;
        }

        public Criteria andCustBankNameGreaterThanOrEqualTo(String value) {
            addCriterion("cust_bank_name >=", value, "custBankName");
            return (Criteria) this;
        }

        public Criteria andCustBankNameLessThan(String value) {
            addCriterion("cust_bank_name <", value, "custBankName");
            return (Criteria) this;
        }

        public Criteria andCustBankNameLessThanOrEqualTo(String value) {
            addCriterion("cust_bank_name <=", value, "custBankName");
            return (Criteria) this;
        }

        public Criteria andCustBankNameLike(String value) {
            addCriterion("cust_bank_name like", value, "custBankName");
            return (Criteria) this;
        }

        public Criteria andCustBankNameNotLike(String value) {
            addCriterion("cust_bank_name not like", value, "custBankName");
            return (Criteria) this;
        }

        public Criteria andCustBankNameIn(List<String> values) {
            addCriterion("cust_bank_name in", values, "custBankName");
            return (Criteria) this;
        }

        public Criteria andCustBankNameNotIn(List<String> values) {
            addCriterion("cust_bank_name not in", values, "custBankName");
            return (Criteria) this;
        }

        public Criteria andCustBankNameBetween(String value1, String value2) {
            addCriterion("cust_bank_name between", value1, value2, "custBankName");
            return (Criteria) this;
        }

        public Criteria andCustBankNameNotBetween(String value1, String value2) {
            addCriterion("cust_bank_name not between", value1, value2, "custBankName");
            return (Criteria) this;
        }

        public Criteria andCustBankAccountCodeIsNull() {
            addCriterion("cust_bank_account_code is null");
            return (Criteria) this;
        }

        public Criteria andCustBankAccountCodeIsNotNull() {
            addCriterion("cust_bank_account_code is not null");
            return (Criteria) this;
        }

        public Criteria andCustBankAccountCodeEqualTo(String value) {
            addCriterion("cust_bank_account_code =", value, "custBankAccountCode");
            return (Criteria) this;
        }

        public Criteria andCustBankAccountCodeNotEqualTo(String value) {
            addCriterion("cust_bank_account_code <>", value, "custBankAccountCode");
            return (Criteria) this;
        }

        public Criteria andCustBankAccountCodeGreaterThan(String value) {
            addCriterion("cust_bank_account_code >", value, "custBankAccountCode");
            return (Criteria) this;
        }

        public Criteria andCustBankAccountCodeGreaterThanOrEqualTo(String value) {
            addCriterion("cust_bank_account_code >=", value, "custBankAccountCode");
            return (Criteria) this;
        }

        public Criteria andCustBankAccountCodeLessThan(String value) {
            addCriterion("cust_bank_account_code <", value, "custBankAccountCode");
            return (Criteria) this;
        }

        public Criteria andCustBankAccountCodeLessThanOrEqualTo(String value) {
            addCriterion("cust_bank_account_code <=", value, "custBankAccountCode");
            return (Criteria) this;
        }

        public Criteria andCustBankAccountCodeLike(String value) {
            addCriterion("cust_bank_account_code like", value, "custBankAccountCode");
            return (Criteria) this;
        }

        public Criteria andCustBankAccountCodeNotLike(String value) {
            addCriterion("cust_bank_account_code not like", value, "custBankAccountCode");
            return (Criteria) this;
        }

        public Criteria andCustBankAccountCodeIn(List<String> values) {
            addCriterion("cust_bank_account_code in", values, "custBankAccountCode");
            return (Criteria) this;
        }

        public Criteria andCustBankAccountCodeNotIn(List<String> values) {
            addCriterion("cust_bank_account_code not in", values, "custBankAccountCode");
            return (Criteria) this;
        }

        public Criteria andCustBankAccountCodeBetween(String value1, String value2) {
            addCriterion("cust_bank_account_code between", value1, value2, "custBankAccountCode");
            return (Criteria) this;
        }

        public Criteria andCustBankAccountCodeNotBetween(String value1, String value2) {
            addCriterion("cust_bank_account_code not between", value1, value2, "custBankAccountCode");
            return (Criteria) this;
        }

        public Criteria andCustBankAccountNameIsNull() {
            addCriterion("cust_bank_account_name is null");
            return (Criteria) this;
        }

        public Criteria andCustBankAccountNameIsNotNull() {
            addCriterion("cust_bank_account_name is not null");
            return (Criteria) this;
        }

        public Criteria andCustBankAccountNameEqualTo(String value) {
            addCriterion("cust_bank_account_name =", value, "custBankAccountName");
            return (Criteria) this;
        }

        public Criteria andCustBankAccountNameNotEqualTo(String value) {
            addCriterion("cust_bank_account_name <>", value, "custBankAccountName");
            return (Criteria) this;
        }

        public Criteria andCustBankAccountNameGreaterThan(String value) {
            addCriterion("cust_bank_account_name >", value, "custBankAccountName");
            return (Criteria) this;
        }

        public Criteria andCustBankAccountNameGreaterThanOrEqualTo(String value) {
            addCriterion("cust_bank_account_name >=", value, "custBankAccountName");
            return (Criteria) this;
        }

        public Criteria andCustBankAccountNameLessThan(String value) {
            addCriterion("cust_bank_account_name <", value, "custBankAccountName");
            return (Criteria) this;
        }

        public Criteria andCustBankAccountNameLessThanOrEqualTo(String value) {
            addCriterion("cust_bank_account_name <=", value, "custBankAccountName");
            return (Criteria) this;
        }

        public Criteria andCustBankAccountNameLike(String value) {
            addCriterion("cust_bank_account_name like", value, "custBankAccountName");
            return (Criteria) this;
        }

        public Criteria andCustBankAccountNameNotLike(String value) {
            addCriterion("cust_bank_account_name not like", value, "custBankAccountName");
            return (Criteria) this;
        }

        public Criteria andCustBankAccountNameIn(List<String> values) {
            addCriterion("cust_bank_account_name in", values, "custBankAccountName");
            return (Criteria) this;
        }

        public Criteria andCustBankAccountNameNotIn(List<String> values) {
            addCriterion("cust_bank_account_name not in", values, "custBankAccountName");
            return (Criteria) this;
        }

        public Criteria andCustBankAccountNameBetween(String value1, String value2) {
            addCriterion("cust_bank_account_name between", value1, value2, "custBankAccountName");
            return (Criteria) this;
        }

        public Criteria andCustBankAccountNameNotBetween(String value1, String value2) {
            addCriterion("cust_bank_account_name not between", value1, value2, "custBankAccountName");
            return (Criteria) this;
        }

        public Criteria andAgreementCodeIsNull() {
            addCriterion("agreement_code is null");
            return (Criteria) this;
        }

        public Criteria andAgreementCodeIsNotNull() {
            addCriterion("agreement_code is not null");
            return (Criteria) this;
        }

        public Criteria andAgreementCodeEqualTo(String value) {
            addCriterion("agreement_code =", value, "agreementCode");
            return (Criteria) this;
        }

        public Criteria andAgreementCodeNotEqualTo(String value) {
            addCriterion("agreement_code <>", value, "agreementCode");
            return (Criteria) this;
        }

        public Criteria andAgreementCodeGreaterThan(String value) {
            addCriterion("agreement_code >", value, "agreementCode");
            return (Criteria) this;
        }

        public Criteria andAgreementCodeGreaterThanOrEqualTo(String value) {
            addCriterion("agreement_code >=", value, "agreementCode");
            return (Criteria) this;
        }

        public Criteria andAgreementCodeLessThan(String value) {
            addCriterion("agreement_code <", value, "agreementCode");
            return (Criteria) this;
        }

        public Criteria andAgreementCodeLessThanOrEqualTo(String value) {
            addCriterion("agreement_code <=", value, "agreementCode");
            return (Criteria) this;
        }

        public Criteria andAgreementCodeLike(String value) {
            addCriterion("agreement_code like", value, "agreementCode");
            return (Criteria) this;
        }

        public Criteria andAgreementCodeNotLike(String value) {
            addCriterion("agreement_code not like", value, "agreementCode");
            return (Criteria) this;
        }

        public Criteria andAgreementCodeIn(List<String> values) {
            addCriterion("agreement_code in", values, "agreementCode");
            return (Criteria) this;
        }

        public Criteria andAgreementCodeNotIn(List<String> values) {
            addCriterion("agreement_code not in", values, "agreementCode");
            return (Criteria) this;
        }

        public Criteria andAgreementCodeBetween(String value1, String value2) {
            addCriterion("agreement_code between", value1, value2, "agreementCode");
            return (Criteria) this;
        }

        public Criteria andAgreementCodeNotBetween(String value1, String value2) {
            addCriterion("agreement_code not between", value1, value2, "agreementCode");
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