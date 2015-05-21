package data;

import java.util.ArrayList;

public interface DataAccessor {
	
	
	String add_name_card(CardInfo c); // return _id of the card
	CardInfo get_name_card(String _id);
	boolean set_name_card(CardInfo c);

	UserInfo get_user_info(String _id);
	boolean set_user_info(UserInfo u);

	// friend names of a specific user
	// ArrayList<String> list_all_name_cards(String _id);
	// ArrayList<String> list_all_name_cards(UserInfo u);
	
    public static void main(String[] args) {
    	DataAccessor da = new MongoAccessor();
    	da.get_name_card("555004aa6d336dc5ae824300");
    }
}
