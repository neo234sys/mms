package com.sbmtech.mms.payload.response;

import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SubscriptionPlans {

	private Integer planId;

	private String planName;

	private Double priceMonth;

	private Double priceYear;

	private String currency;

	private Integer durationInDays;

	private Integer trialDays;

	private String description;

	private List<String> features;

	private Map<String, String> metadata;

	private Integer active;

	public SubscriptionPlans(Integer planId, String planName, Double priceMonth, Double priceYear, String currency,
			Integer durationInDays, Integer trialDays, String description, List<String> features,
			Map<String, String> metadata) {

		this.planId = planId;
		this.planName = planName;
		this.priceMonth = priceMonth;
		this.priceYear = priceYear;
		this.currency = currency;
		this.durationInDays = durationInDays;
		this.trialDays = trialDays;
		this.description = description;
		this.features = features;
		this.metadata = metadata;

	}

}
