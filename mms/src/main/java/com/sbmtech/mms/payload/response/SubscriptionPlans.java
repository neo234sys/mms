package com.sbmtech.mms.payload.response;

public class SubscriptionPlans {

	
	private Integer planId;

	private String planName;

	private Integer planDurationDays;

	private Float planPrice;

	private String features;
	
	private Integer trialDays;
	

	public SubscriptionPlans(Integer planId, String planName, Integer planDurationDays, Float planPrice,
			String features,Integer trialDays) {
		super();
		this.planId = planId;
		this.planName = planName;
		this.planDurationDays = planDurationDays;
		this.planPrice = planPrice;
		this.features = features;
		this.trialDays=trialDays;
	}

	public Integer getPlanId() {
		return planId;
	}

	public void setPlanId(Integer planId) {
		this.planId = planId;
	}

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public Integer getPlanDurationDays() {
		return planDurationDays;
	}

	public void setPlanDurationDays(Integer planDurationDays) {
		this.planDurationDays = planDurationDays;
	}

	public Float getPlanPrice() {
		return planPrice;
	}

	public void setPlanPrice(Float planPrice) {
		this.planPrice = planPrice;
	}

	public String getFeatures() {
		return features;
	}

	public void setFeatures(String features) {
		this.features = features;
	}

	public Integer getTrialDays() {
		return trialDays;
	}

	public void setTrialDays(Integer trialDays) {
		this.trialDays = trialDays;
	}

}
