package com.example.inscricao;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Subscription implements Builder, Serializable{
	
	private String name;
	private String date;

	
	public String getName() {
		return name;
	}
	
	public String getDate() {
		return date;
	}
	
	public static class SubscriptionBuilder{
		
		private static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		
		private String Name;
		private String Date;
			
		public Subscription.SubscriptionBuilder setName(String name) {
			this.Name = name;
			return this;
		}
			
		
		public Subscription.SubscriptionBuilder setDate(String data) {
			this.Date = data;
			return this;
		}
		
		public static Subscription.SubscriptionBuilder builder () {
			return new Subscription.SubscriptionBuilder();
		}

	
		public Subscription build() {
			Subscription sub = new Subscription();
			sub.name = Name;
			sub.date = Date;
			return sub;
		}
		
	}

}

