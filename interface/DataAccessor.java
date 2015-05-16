package data;

import java.util.ArrayList;

public interface DataAccessor {
	CardInfo get_name_card(Integer id);
	boolean set_name_card(CardInfo c);

	UserInfo get_user_info(Integer id);
	boolean set_user_info(UserInfo u);

	// friend names of a specific user
	ArrayList<String> list_all_name_cards(Integer id);
	ArrayList<String> list_all_name_cards(UserInfo u);
	
}
