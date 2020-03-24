package com.play.poc.xcel;

import com.poiji.annotation.ExcelCellName;
import com.poiji.annotation.ExcelSheet;


@ExcelSheet("Header first record Sum")
public class XcelData1 {

    @ExcelCellName("EnrollmentKey")
    private String enrollmentKey;

    @ExcelCellName("PolicyNumber")
    private String policyNumber;

    @ExcelCellName("MedicareID")
    private Integer medicareID;

    @ExcelCellName("EffectiveDate")
    private Integer effectiveDate;

    @ExcelCellName("LedgerItemAgentID")
    private String ledgerItemAgentID;

    @ExcelCellName("AppSignDate")
    private String appSignDate;

    @ExcelCellName("CashPaidAmount")
    private Double cashPaidAmount;

    @ExcelCellName("Paid Year")
    private Integer paidYear;

    @ExcelCellName("State")
    private String state;

    @ExcelCellName("WritingAgentID")
    private String writingAgentID;

    @ExcelCellName("AgentLevel")
    private Integer agentLevel;

    @ExcelCellName("DisabilityIndicator")
    private Integer disabilityIndicator;

    @ExcelCellName("EnrollmentYear")
    private Integer enrollmentYear;

    @ExcelCellName("GICommissionRate")
    private String gICommissionRate;

    @ExcelCellName("NewToUHC")
    private String newToUHC;

    @ExcelCellName("PaidToDate")
    private Integer paidToDate;

    @ExcelCellName("PlanType")
    private String planType;

    @ExcelCellName("MbrLastName")
    private String mbrLastName;

    @ExcelCellName("MbrFirstName")
    private String mbrFirstName;

    @ExcelCellName("TransactionType")
    private String transactionType;

    @ExcelCellName("MonthsOnPlan")
    private Integer monthsOnPlan;

    @ExcelCellName("PaymentPartyID")
    private Integer paymentPartyID;

    @ExcelCellName("HierarchyOwnerID")
    private String hierarchyOwnerID;

    @ExcelCellName("TrueupIndicator")
    private String trueupIndicator;

    @ExcelCellName("Term Date")
    private Integer termDate;

    public String getEnrollmentKey() {
        return enrollmentKey;
    }

    public void setEnrollmentKey(String enrollmentKey) {
        this.enrollmentKey = enrollmentKey;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    public Integer getMedicareID() {
        return medicareID;
    }

    public void setMedicareID(Integer medicareID) {
        this.medicareID = medicareID;
    }

    public Integer getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Integer effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public String getLedgerItemAgentID() {
        return ledgerItemAgentID;
    }

    public void setLedgerItemAgentID(String ledgerItemAgentID) {
        this.ledgerItemAgentID = ledgerItemAgentID;
    }

    public String getAppSignDate() {
        return appSignDate;
    }

    public void setAppSignDate(String appSignDate) {
        this.appSignDate = appSignDate;
    }

    public Double getCashPaidAmount() {
        return cashPaidAmount;
    }

    public void setCashPaidAmount(Double cashPaidAmount) {
        this.cashPaidAmount = cashPaidAmount;
    }

    public Integer getPaidYear() {
        return paidYear;
    }

    public void setPaidYear(Integer paidYear) {
        this.paidYear = paidYear;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getWritingAgentID() {
        return writingAgentID;
    }

    public void setWritingAgentID(String writingAgentID) {
        this.writingAgentID = writingAgentID;
    }

    public Integer getAgentLevel() {
        return agentLevel;
    }

    public void setAgentLevel(Integer agentLevel) {
        this.agentLevel = agentLevel;
    }

    public Integer getDisabilityIndicator() {
        return disabilityIndicator;
    }

    public void setDisabilityIndicator(Integer disabilityIndicator) {
        this.disabilityIndicator = disabilityIndicator;
    }

    public Integer getEnrollmentYear() {
        return enrollmentYear;
    }

    public void setEnrollmentYear(Integer enrollmentYear) {
        this.enrollmentYear = enrollmentYear;
    }

    public String getgICommissionRate() {
        return gICommissionRate;
    }

    public void setgICommissionRate(String gICommissionRate) {
        this.gICommissionRate = gICommissionRate;
    }

    public String getNewToUHC() {
        return newToUHC;
    }

    public void setNewToUHC(String newToUHC) {
        this.newToUHC = newToUHC;
    }

    public Integer getPaidToDate() {
        return paidToDate;
    }

    public void setPaidToDate(Integer paidToDate) {
        this.paidToDate = paidToDate;
    }

    public String getPlanType() {
        return planType;
    }

    public void setPlanType(String planType) {
        this.planType = planType;
    }

    public String getMbrLastName() {
        return mbrLastName;
    }

    public void setMbrLastName(String mbrLastName) {
        this.mbrLastName = mbrLastName;
    }

    public String getMbrFirstName() {
        return mbrFirstName;
    }

    public void setMbrFirstName(String mbrFirstName) {
        this.mbrFirstName = mbrFirstName;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public Integer getMonthsOnPlan() {
        return monthsOnPlan;
    }

    public void setMonthsOnPlan(Integer monthsOnPlan) {
        this.monthsOnPlan = monthsOnPlan;
    }

    public Integer getPaymentPartyID() {
        return paymentPartyID;
    }

    public void setPaymentPartyID(Integer paymentPartyID) {
        this.paymentPartyID = paymentPartyID;
    }

    public String getHierarchyOwnerID() {
        return hierarchyOwnerID;
    }

    public void setHierarchyOwnerID(String hierarchyOwnerID) {
        this.hierarchyOwnerID = hierarchyOwnerID;
    }

    public String getTrueupIndicator() {
        return trueupIndicator;
    }

    public void setTrueupIndicator(String trueupIndicator) {
        this.trueupIndicator = trueupIndicator;
    }

    public Integer getTermDate() {
        return termDate;
    }

    public void setTermDate(Integer termDate) {
        this.termDate = termDate;
    }

    @Override
    public String toString() {
        return "XcelData [enrollmentKey=" + enrollmentKey + ", policyNumber=" + policyNumber + ", medicareID="
                + medicareID + ", effectiveDate=" + effectiveDate + ", ledgerItemAgentID=" + ledgerItemAgentID
                + ", appSignDate=" + appSignDate + ", cashPaidAmount=" + cashPaidAmount + ", paidYear=" + paidYear
                + ", state=" + state + ", writingAgentID=" + writingAgentID + ", agentLevel=" + agentLevel
                + ", disabilityIndicator=" + disabilityIndicator + ", enrollmentYear=" + enrollmentYear
                + ", gICommissionRate=" + gICommissionRate + ", newToUHC=" + newToUHC + ", paidToDate=" + paidToDate
                + ", planType=" + planType + ", mbrLastName=" + mbrLastName + ", mbrFirstName=" + mbrFirstName
                + ", transactionType=" + transactionType + ", monthsOnPlan=" + monthsOnPlan + ", paymentPartyID="
                + paymentPartyID + ", hierarchyOwnerID=" + hierarchyOwnerID + ", trueupIndicator=" + trueupIndicator
                + ", termDate=" + termDate + "]";
    }

}
