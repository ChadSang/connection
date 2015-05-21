package data;

public interface DataAccessor {

	/**
	 * if succeed, returns the user_id with the size of 24; else "Used" for the
	 * email being used, "Busy" for network failure
	 */
	String add_user(String email, String password);

	UserInfo verify_user(String email, String password);

	boolean set_user_info(UserInfo u);

	/**
	 * add a name card for the user
	 * 
	 * @returns _id of the card
	 */
	String add_name_card(String user_id, CardInfo c);

	String add_name_card(UserInfo u, CardInfo c);

	CardInfo get_name_card(String _id);

	boolean set_name_card(CardInfo c);

	public static void main(String[] args) {
		DataAccessor da = new MongoAccessor();
		// da.get_name_card("555004aa6d336dc5ae824300");
		System.out.println("555004aa6d336dc5ae824300".length());
	}
}
