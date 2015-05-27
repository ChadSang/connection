package com.example.eleanor.connection.function;

import android.content.Intent;
import android.net.Uri;

import com.example.eleanor.connection.data.*;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Eleanor on 2015/5/23.
 */
public class FunctionImpl implements FunctionAccessor {

    private DataAccessor da = new MongoAccessor();

    private UserInfo current_user = null;
    private UserInfo editing_user = null;
    private CardInfo current_card = null;

    /**
     * Account
     */
    @Override
    public String user_register(String email, String password) {

        return da.add_user(email, password);

    }
    @Override
    public Boolean user_login(String email, String password){
        current_user = da.verify_user(email, password);
        return !(current_user == null);
    }
    @Override
    public Boolean user_logout(){
        current_user = null;
        return (current_user == null);
    }

    /**
     * User
     */
    @Override
    public UserInfo get_current_user(){
        return current_user;
    }
    @Override
    public UserInfo get_editing_user() {
        return editing_user;
    }

    @Override
    public String get_user_id(UserInfo user){
        return user.get_id();
    }
    @Override
    public String get_user_email(UserInfo user){
        return user.get_email();
    }
    @Override
    public ArrayList<String> get_user_cards(UserInfo user){
        return user.get_my_cards();
    }
    @Override
    public ArrayList<String> get_user_card_case(UserInfo user){
        return user.get_card_case();
    }
    @Override
    public ArrayList<Setting> get_user_setting(UserInfo user){
        return user.get_personal_settings();
    }

    @Override
    public Boolean edit_user(){
        editing_user = current_user;
        return !(editing_user == null);
    }
    @Override
    public Boolean modify_edited_user(){
        da.set_user_info(editing_user);
        current_user = editing_user;
        editing_user = null;
        return true;
    }
    @Override
    public Boolean cancel_user_operation(){
        editing_user = null;
        return true;
    }
    @Override
    public Boolean set_password(String password){
        editing_user.set_password(password);
        return true;
    }

    @Override
    public Boolean add_my_card(CardInfo card){
        if (card != null && current_user != null) {
            da.add_name_card(current_user.get_id(), card);
            current_card = null;
            return true;
        }
        return false;
    }
    @Override
    public Boolean add_other_card(CardInfo card){
        if (card != null && current_user != null) {
            da.add_name_card(current_user, card);
            return true;
        }
        return false;
    }
    @Override
    public Boolean delete_my_card(String _id){
        return true;
    }
    @Override
    public Boolean delete_other_card(String _id){
        return true;
    }
    @Override
    public Boolean add_setting(Setting setting){
        if (current_user != null){
            current_user.add_personal_settings(setting);
            return true;
        }
        return false;
    }

    /**
     * Card
     */
    @Override
    public CardInfo get_card_info(String _id){
        current_card = da.get_name_card(_id);
        return current_card;
     }
    @Override
    public CardInfo get_current_card_info(){
        return current_card;
    }

    @Override
    public String get_card_id(CardInfo card){
        return card.get_id();
    }
    @Override
    public  String get_card_name(CardInfo card){
        return card.get_name();
    }
    @Override
    public String get_card_model(CardInfo card){
        return card.get_name_card_model();
    }
    @Override
    public ArrayList<Phone> get_card_phone_number(CardInfo card){
        return card.get_phone_numbers();
    }
    @Override
    public ArrayList<SNS> get_card_sns_account(CardInfo card){
        return card.get_sns_accounts();
    }
    @Override
    public String get_card_email(CardInfo card){
        return card.get_email();
    }
    @Override
    public String get_card_addres(CardInfo card){
        return card.get_address();
    }
    @Override
    public Date get_card_birthday(CardInfo card){
        return card.get_birthday();
    }

    @Override
    public Boolean create_card() {
        current_card = new CardInfo();
        return !(current_card == null);
    }
    @Override
    public Boolean edit_card(String _id){
        current_card = da.get_name_card(_id);
        return !(current_user == null);
    }

    @Override
    public Boolean add_created_card(){
        return add_my_card(current_card);
    }
    @Override
    public Boolean modify_edited_card(){
        if (current_card != null) {
            boolean f = da.set_name_card(current_card);
            current_card = null;
            return f;
        }
        return false;
    }
    @Override
    public Boolean cancel_card_operation(){
        current_card = null;
        return (current_card == null);
    }


    @Override
    public Boolean set_card_name(String name){
        current_card.set_name(name);
        return true;
    }
    @Override
    public Boolean set_card_model(String model){
        current_card.set_name_card_model(model);
        return true;
    }
    @Override
    public Boolean add_card_phone_number(Phone number){
        current_card.add_phone_number(number);
        return true;
    }
    @Override
    public Boolean add_card_sns_account(SNS account){
        current_card.add_sns_account(account);
        return true;
    }
    @Override
    public Boolean set_card_email(String email){
        current_card.set_email(email);
        return true;
    }
    @Override
    public Boolean set_card_address(String address){
        current_card.set_address(address);
        return true;
    }
    @Override
    public Boolean set_card_birthday(Date birthday){
        current_card.set_birthday(birthday);
        return true;
    }


    @Override
    public Intent call_number(String number){
        Intent phone_intent = new Intent(Intent.ACTION_CALL);
        //Intent phone_intent = new Intent(Intent.ACTION_DIAL);
        phone_intent.setData(Uri.parse("tel:" + number));
        return phone_intent;
    }

    @Override
    public Intent send_message(String number, String msg){
        Intent sms_intent = new Intent(Intent.ACTION_VIEW);
        sms_intent.setData(Uri.parse("smsto:"));
        sms_intent.setType("vnd.android-dir/mms-sms");
        sms_intent.putExtra("address", number);
        sms_intent.putExtra("sms_body", msg);

        return sms_intent;
    }


}
