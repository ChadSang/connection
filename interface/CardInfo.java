package data;

import java.util.Date;
import java.util.Set;

public class CardInfo {
	String _id;
	String name;
	// String name_card_model;
	// Set<Phone> phone_numbers;
	// Set<SNS> sns_accounts;
	String email;
	// String address;
	// Date birthday;


	public CardInfo(String _id, String name, String email) {
		super();
		this._id = _id;
		this.name = name;
		this.email = email;
	}


	public static void main(String[] args) {
		System.out.println("hello");
	}
}
