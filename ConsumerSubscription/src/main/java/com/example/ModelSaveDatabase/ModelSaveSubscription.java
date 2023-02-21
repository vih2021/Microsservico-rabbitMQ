package com.example.ModelSaveDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class ModelSaveSubscription {
	
	@Id
	@GeneratedValue
	private long subscriptionNumber;
	
	private String date;
	
	private String name;
	
	public String getName() {
		return this.name;
	}
	
	public String getDate() {
		return this.date;
	}
	
	public long getSubscriptionNumber() {
		return this.subscriptionNumber;
	}
	
	public ModelSaveSubscription() {
		
	}
	
	public ModelSaveSubscription(String name, String date) {
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String newdate  = formatter.format(new Date());
		
		if(date != null) {
			this.date = date;
		}else {
			this.date = newdate;
		}
		
		this.name = name;	
	}
	
	public static class ModelSaveSubscriptionBuilder{
		
		private String date;
		
		private String name;
		
		
		public ModelSaveSubscription.ModelSaveSubscriptionBuilder setDate(String date){
			this.date = date;
			return this;
		}
		
		public ModelSaveSubscription.ModelSaveSubscriptionBuilder setName(String name){
			this.name = name;
			return this;
		}
		
		public ModelSaveSubscription.ModelSaveSubscriptionBuilder builder(){
			return new ModelSaveSubscription.ModelSaveSubscriptionBuilder();
		}
		
		public ModelSaveSubscription build() {
			ModelSaveSubscription model = new ModelSaveSubscription();
			model.name = name;
			model.date = date;
			return model;
			
		}
		
	}
}
