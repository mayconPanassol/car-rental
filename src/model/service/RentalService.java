package model.service;

import model.entities.CarRental;
import model.entities.Invoice;

public class RentalService {

	private Double priceHour;
	private Double priceDay;
	
	private TaxService taxService;

	public RentalService(Double priceHour, Double priceDay, TaxService taxService) {
		super();
		this.priceHour = priceHour;
		this.priceDay = priceDay;
		this.taxService = taxService;
	}

	public void processInvoice(CarRental carRental) {
		
		double duration = carRental.getFinish().getTime() - carRental.getStart().getTime();
		
		duration = duration / 1000 / 60 / 60;
		
		double basicPayment = 0.0; 
		double tax = 0.0;
		
		if (duration < 12) {
			basicPayment = priceHour * Math.ceil(duration); 
			tax = taxService.tax(basicPayment);
		} else {
			duration = duration / 24;
			basicPayment = priceDay * Math.ceil(duration); 
			tax = taxService.tax(basicPayment);
		}
		
		carRental.setInvoice(new Invoice(basicPayment, tax));
		
	}
	
	
	
	
	
}
