package data;

import java.util.Date;
import java.util.Set;

public class UserInfo {
	String _id;

	Date created_date;
	Set<Integer> card_case;
	Set<Setting> personal_settings;

	public boolean add_card(String _id) {
		return true;
	}

	public static void main(String[] args) {
		System.out.println("hello");
	}
}
