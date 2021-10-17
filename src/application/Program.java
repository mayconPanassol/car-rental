package application;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import model.entities.CarRental;
import model.entities.Vehicle;
import model.service.BrazilTaxService;
import model.service.RentalService;

public class Program {

	public static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {

		try {

			Locale.setDefault(Locale.US);
			Scanner sc = new Scanner(System.in);

			System.out.println("Enter rental data");
			System.out.print("Car model: ");
			String carModel = sc.nextLine();
			System.out.print("Pickup (dd/MM/yyyy hh:mm): ");
			Date dateStart = new SimpleDateFormat("dd/MM/yyyy hh:mm").parse(sc.nextLine());
			System.out.print("Return (dd/MM/yyyy hh:mm): ");
			Date dateFinish = new SimpleDateFormat("dd/MM/yyyy hh:mm").parse(sc.nextLine());
			System.out.print("Enter price per hour: ");
			double priceHour = sc.nextDouble();
			System.out.print("Enter price per day: ");
			double priceDay = sc.nextDouble();
			
			CarRental cr = new CarRental(dateStart, dateFinish, new Vehicle(carModel));

			RentalService rs = new RentalService(priceHour, priceDay, new BrazilTaxService());

			rs.processInvoice(cr);

			System.out.println("INVOICE:");
			System.out.println("Basic payment: " + String.format("%.2f", cr.getInvoice().getBasicPayment()));
			System.out.println("Tax: " + String.format("%.2f", cr.getInvoice().getTax()));
			System.out.println("Total payment: " + String.format("%.2f", cr.getInvoice().totalPayment()));
			
			sc.close();

		} catch (java.text.ParseException e) {
			System.out.println("Error: " + e.getMessage());
		}

		
	}

}
