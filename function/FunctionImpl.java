package com.example.eleanor.connection.function;

import com.example.eleanor.connection.data.*;

import java.util.Date;

/**
 * Created by Eleanor on 2015/5/23.
 */
public class FunctionImpl implements FunctionAccessor {

    private DataAccessor da = new MongoAccessor();

    private UserInfo current_user_info = null;
    private UserInfo editing_user_info = null;
    private CardInfo current_card_info = null;

    /**
     * Account
     */
    public String user_register(String email, String password) {
        return da.add_user(email, password);
    }
    public Boolean user_login(String email, String password){
        current_user_info = da.verify_user(email, password);
        return !(current_user_info == null);
    }
    public Boolean user_logout(){
        current_user_info = null;
        return (current_user_info == null);
    }

    /**
     * User
     */
    public UserInfo get_current_user(){
        return current_user_info;
    }
    public UserInfo get_editing_user(){
        return editing_user_info;
    }
    public Boolean edit_user(){
        editing_user_info = current_user_info;
        return !(editing_user_info == null);
    }
    public Boolean modify_edited_user(){
        da.set_user_info(editing_user_info);
        current_user_info = editing_user_info;
        editing_user_info = null;
        return true;
    }
    public Boolean cancel_user_operation(){
        editing_user_info = null;
        return true;
    }
    public Boolean set_password(String password){
        editing_user_info.set_password(password);
        return true;
    }

    /**
     * Card
     */
    public CardInfo get_card_info(String _id){
        current_card_info = da.get_name_card(_id);
        return current_card_info;
     }
    public CardInfo get_current_card_info(){
        return current_card_info;
    }

    public Boolean create_card() {
        current_card_info = new CardInfo();
        return !(current_card_info == null);
    }
    public Boolean edit_card(String _id){
        current_card_info = da.get_name_card(_id);
        return !(current_user_info == null);
    }

    public Boolean add_created_card(){
        if (current_user_info != null) {
            da.add_name_card(current_user_info.get_id(), current_card_info);
            current_card_info = null;
            return true;
        }
        return false;
    }
    public Boolean modify_edited_card(){
        if (current_card_info != null) {
            boolean f = da.set_name_card(current_card_info);
            current_card_info = null;
            return f;
        }
        return false;
    }
    public Boolean cancel_card_operation(){
        current_card_info = null;
        return (current_card_info == null);
    }


    public Boolean set_name_card(String name){
        current_card_info.set_name(name);
        return true;
    }
    public Boolean set_model_card(String model){
        current_card_info.set_name_card_model(model);
        return true;
    }
    public Boolean add_phone_number_card(Phone number){
        current_card_info.add_phone_number(number);
        return true;
    }
    public Boolean add_sns_account_card(SNS account){
        current_card_info.add_sns_account(account);
        return true;
    }
    public Boolean set__email_card(String email){
        current_card_info.set_email(email);
        return true;
    }
    public Boolean set_address_card(String address){
        current_card_info.set_address(address);
        return true;
    }
    public Boolean set_birthday_card(Date birthday){
        current_card_info.set_birthday(birthday);
        return true;
    }


    public Boolean add_card(CardInfo card){
        if (current_card_info != null) {
            da.add_name_card(current_user_info, card);
            return true;
        }
        return false;
    }

}
