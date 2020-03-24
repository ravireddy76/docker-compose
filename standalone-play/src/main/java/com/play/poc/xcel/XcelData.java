package com.play.poc.xcel;



import io.github.millij.poi.ss.model.annotations.Sheet;
import io.github.millij.poi.ss.model.annotations.SheetColumn;

@Sheet(value="Header first record Sum")
public class XcelData {
	
	@SheetColumn("EnrollmentKey")
	private String enrollmentKey;	
	
	@SheetColumn("PolicyNumber")
	private String policyNumber;
	
	@SheetColumn("MedicareID")
	private Integer medicareID;	
	
	@SheetColumn("EffectiveDate")
	private Integer effectiveDate;	
	
	@SheetColumn("LedgerItemAgentID")
	private String ledgerItemAgentID;	
	
	@SheetColumn("AppSignDate")
	private String appSignDate;
	
	@SheetColumn("CashPaidAmount")
	private Double cashPaidAmount;	
	
	@SheetColumn("Paid Year")
	private Integer paidYear;
	
	@SheetColumn("State")
	private String state;	
	
	@SheetColumn("WritingAgentID")
	private String writingAgentID;	
	
	@SheetColumn("AgentLevel")
	private Integer agentLevel;	
	
	@SheetColumn("DisabilityIndicator")
	private Integer disabilityIndicator;	
	
	@SheetColumn("EnrollmentYear")
	private Integer enrollmentYear;	
	
	@SheetColumn("GICommissionRate")
	private String gICommissionRate;
	
	@SheetColumn("NewToUHC")
	private String newToUHC;
	
	@SheetColumn("PaidToDate")
	private Integer paidToDate;	
	
	@SheetColumn("PlanType")
	private String planType;
	
	@SheetColumn("MbrLastName")
	private String mbrLastName;	
	
	@SheetColumn("MbrFirstName")
	private String mbrFirstName;
	
	@SheetColumn("TransactionType")
	private String transactionType;	
	
	@SheetColumn("MonthsOnPlan")
	private Integer monthsOnPlan;
	
	@SheetColumn("PaymentPartyID")
	private Integer paymentPartyID;	
	
	@SheetColumn("HierarchyOwnerID")
	private String hierarchyOwnerID;
	
	@SheetColumn("TrueupIndicator")
	private String trueupIndicator;	
	
	@SheetColumn("Term Date")
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
