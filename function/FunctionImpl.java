package com.example.eleanor.connection.function;

import android.content.Intent;
import android.content.ContentResolver;
import android.provider.ContactsContract;
import android.database.Cursor;
import android.net.Uri;

import com.example.eleanor.connection.data.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    public List<CardInfo> get_cards_by_name(String name){
        return da.get_cards_by_name(current_user.get_id(), name);
    }
    @Override
    public List<CardInfo> get_cards_by_phone_number(String phone){
        return da.get_cards_by_phone_number(current_user.get_id(), phone);
    }
    @Override
    public List<CardInfo> get_cards_by_email(String email){
        return da.get_cards_by_email(current_user.get_id(), email);
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

    @Override
    public Intent send_email(String address, String subject, String text){
        Intent email_intent = new Intent(Intent.ACTION_SEND);
        email_intent.setData(Uri.parse("mailto:"));
        //email_intent.setType("text/plain");
        email_intent.setType("message/rfc822");
        String[] email_address = new String[]{address, "", };
        email_intent.putExtra(Intent.EXTRA_EMAIL, email_address);
        email_intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        email_intent.putExtra(Intent.EXTRA_TEXT, text);

        return email_intent;
    }

    @Override
    public List<CardInfo> get_phone_contact(ContentResolver resolver){
        List<CardInfo> cards = new ArrayList<CardInfo>();
        Cursor cursor = resolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        while (cursor.moveToNext()) {
            String contact_id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
            String contact_name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            String phone_number = "";
            Cursor phone_cursor = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + contact_id, null, null);
            while (phone_cursor.moveToNext()) {
                phone_number = phone_cursor.getString(phone_cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            }

            if (!phone_number.equals("")) {
               // CardInfo card = da.get_name_card(da.get_card_by_phone_number(phone_number));
                CardInfo card = new CardInfo();
                card.set_name(contact_name);
                card.add_phone_number(new Phone("mobile", phone_number));
                cards.add(card);
            }
            if (null != phone_cursor && !phone_cursor.isClosed())
                phone_cursor.close();
        }
        if (null != cursor && !cursor.isClosed())
            cursor.close();
        return cards;
    }

    @Override
    public List<CardInfo> get_SIM_contact(ContentResolver resolver){

        List<CardInfo> cards = new ArrayList<CardInfo>();
        Uri uri = Uri.parse("content://icc/adn");
        Cursor cursor = resolver.query(uri, null, null, null, null);
        while (cursor.moveToNext()) {
            String contact_id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
            String contact_name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            String phone_number = "";
            Cursor phone_cursor = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + contact_id, null, null);
            while (phone_cursor.moveToNext()) {
                phone_number = phone_cursor.getString(phone_cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            }

            if (!phone_number.equals("")) {
                // CardInfo card = da.get_name_card(da.get_card_by_phone_number(phone_number));
                CardInfo card = new CardInfo();
                card.set_name(contact_name);
                card.add_phone_number(new Phone("mobile", phone_number));
                cards.add(card);
            }
            if (null != phone_cursor && !phone_cursor.isClosed())
                phone_cursor.close();
        }
        if (null != cursor && !cursor.isClosed())
            cursor.close();
        return cards;

    }

}
