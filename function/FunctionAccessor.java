package com.example.eleanor.connection.function;

import android.content.Intent;

import com.example.eleanor.connection.data.*;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Eleanor on 2015/5/23.
 */
public interface FunctionAccessor {


/**    Account    */

    /**
     * register / login / logout
     */
    String user_register(String email, String password);
    Boolean user_login(String email, String password);
    Boolean user_logout();


/**    User    */
    /**
     * get current logged user
     * get the user info being edited
     */
    UserInfo get_current_user();
    UserInfo get_editing_user();
    /**
     * get details
     */
    String get_user_id(UserInfo user);
    String get_user_email(UserInfo user);
    ArrayList<String> get_user_cards(UserInfo user);
    ArrayList<String> get_user_card_case(UserInfo user);
    ArrayList<Setting> get_user_setting(UserInfo user);

    /**
     * edit user
     * assure / cancel user operation
     */
    Boolean edit_user();
    Boolean modify_edited_user();
    Boolean cancel_user_operation();

    /**
     * edit user info
     */
    Boolean set_password(String password);
    Boolean add_my_card(CardInfo card);
    Boolean add_other_card(CardInfo card);
    Boolean delete_my_card(String _id);
    Boolean delete_other_card(String _id);
    Boolean add_setting(Setting setting);



/**    Card    */

    /**
     * get card by id
     * get the card being created / edited
     */
    CardInfo get_card_info(String _id);
    CardInfo get_current_card_info();
    /**
     * get details
     */
    String get_card_id(CardInfo card);
    String get_card_name(CardInfo card);
    String get_card_model(CardInfo card);
    ArrayList<Phone> get_card_phone_number(CardInfo card);
    ArrayList<SNS> get_card_sns_account(CardInfo card);
    String get_card_email(CardInfo card);
    String get_card_addres(CardInfo card);
    Date get_card_birthday(CardInfo card);

    /**
     * create your own card
     * edit your own card
     * assure / cancel card operation
     */
    Boolean create_card();
    Boolean edit_card(String _id);
    Boolean add_created_card();
    Boolean modify_edited_card();
    Boolean cancel_card_operation();

    /**
     *set or edit your own card
     */
    Boolean set_card_name(String name);
    Boolean set_card_model(String model);
    Boolean add_card_phone_number(Phone number);
    Boolean add_card_sns_account(SNS account);
    Boolean set_card_email(String email);
    Boolean set_card_address(String address);
    Boolean set_card_birthday(Date birthday);



/** functions */
    Intent call_number(String number);
    Intent send_message(String number, String msg);
}
