package data;

import java.util.Date;
import java.util.List;

public class CardInfo {
	Integer ID;
	String name;
	String name_card_model;
	List<Phone> phone_numbers;
	List<SNS> sns_accounts;
	String email;
	String address;
	Date birthday;

	public static void main(String[] args) {
		System.out.println("hello");
	}
}
