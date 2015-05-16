package data;

import java.util.Date;
import java.util.Set;

public class CardInfo {
	Integer ID;
	String name;
	String name_card_model;
	Set<Phone> phone_numbers;
	Set<SNS> sns_accounts;
	String email;
	String address;
	Date birthday;

	public static void main(String[] args) {
		System.out.println("hello");
	}
}
