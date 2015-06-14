package sxh.connection.data;

import java.util.ArrayList;

public interface DataAccessor {

	/**
	 * if succeed, returns the user_id with the size of 24; else "Used" for the
	 * email being used, "Busy" for network failure
	 */
	String add_user(String email, String password);

	UserInfo verify_user(String email, String password);
	
	boolean delete_user(String id);

	boolean set_user_info(UserInfo u);

	/**
	 * add a name card for the user
	 * 
	 * @returns _id of the card
	 */
	String add_name_card(String user_id, CardInfo c);

	String add_name_card(UserInfo u, CardInfo c);

	CardInfo get_name_card(String card_id);

	boolean set_name_card(CardInfo c);
	
    boolean delete_name_card_in_my_card(String user_id, String card_id);

    boolean delete_name_card_in_card_case(String user_id, String card_id);

    CardInfo get_card_by_phone_number(String phone_number);

    /**
     * search cards by name in card case
     * @param user_id
     * @param name
     * @return the result list of cards
     */
    ArrayList<CardInfo> get_cards_by_name(String user_id, String name);
    ArrayList<CardInfo> get_cards_by_phone_number(String user_id, String phone);
    ArrayList<CardInfo> get_cards_by_email(String user_id, String email);


	public static void main(String[] args) {
		DataAccessor da = new MongoAccessor();
		String id = da.add_user("user@sjtu", "my_password");
		if (id.length() == 24) {
			System.out.printf("successfully added user %s\n", id);
		}
		else if (id.equals("used")){
			System.out.printf("user already exist\n");
		}

		UserInfo ui = da.verify_user("user@sjtu", "wrong_password");
		if (ui.valid()) {
			System.out.println("successfully log in");
		}
		else {
			System.out.println("wrong password!");
		}

		ui = da.verify_user("user@sjtu", "my_password");
		if (ui.valid()) {
			System.out.println("successfully log in");
		}
		else {
			System.out.println("wrong password!");
		}
	}
}
