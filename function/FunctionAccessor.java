package com.example.eleanor.connection.function;

import com.example.eleanor.connection.data.*;

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
    //Boolean delete_my_card(String _id);
    //Boolean delete_other_card(String _id);
    //Boolean add_setting(Setting setting);



    /**    Card    */

    /**
     * get card by id
     * get the card being created / edited
     */
    CardInfo get_card_info(String _id);
    CardInfo get_current_card_info();

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
    Boolean set_name_card(String name);
    Boolean set_model_card(String model);
    Boolean add_phone_number_card(Phone number);
    Boolean add_sns_account_card(SNS account);
    Boolean set__email_card(String email);
    Boolean set_address_card(String address);
    Boolean set_birthday_card(Date birthday);

    /**
     * add other's card
     */
    Boolean add_card(CardInfo card);

}
