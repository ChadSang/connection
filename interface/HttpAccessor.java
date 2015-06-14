package sxh.connection.data;

import java.net.UnknownHostException;
import java.util.ArrayList;
import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import static com.mongodb.client.model.Filters.*;

public class HttpAccessor implements DataAccessor {

	@Override
	public String add_user(String email, String password) {
		MongoCollection<Document> users = get_users();
		Document doc = users.find(eq("e", email.toLowerCase())).first();
		if (doc != null)
			return "Used";
		doc = new UserInfo(email, password).toDoc();
		doc.remove("_id");
		users.insertOne(doc);
		Document new_doc = users.find(doc).first();
		return new_doc.get("_id").toString();
		// TODO return "Busy";
	}

	@Override
	public UserInfo verify_user(String email, String password) {
		MongoCollection<Document> users = get_users();
		Document doc = users.find(
				and(eq("e", email.toLowerCase()), eq("p", password))).first();
		UserInfo res = new UserInfo(doc);
		return res;
	}

	@Override
	public boolean delete_user(String id) {
		MongoCollection<Document> users = get_users();
		users.find(eq("_id", id));
		return true;
	}

	private static void test_card() {
		CardInfo ci = new CardInfo();
		ci.set_name("Chad2");
		ci.set_email("s@sjtu");
		ci.add_phone_number(new Phone("work", "12345"));
		ci.add_phone_number(new Phone("home", "23456"));
		ci.add_sns_account(new SNS("QQ", "1218123678"));
		System.out.println("begin");
		String id = new HttpAccessor().add_name_card("", ci);
		new HttpAccessor().get_name_card(id)._print();
		System.out.println("success");
	}

	public static void main(String[] args) throws UnknownHostException {
		// MongoAccessor().get_name_card("555004aa6d336dc5ae824300")._print();
		// new MongoAccessor().verify_user("chadx@sjtu", "PASSWORD")._print();
		test_card();
		// new MongoAccessor().verify_user("hi@sjtu", "PASSWORD")._print();
	}

	private static MongoCollection<Document> get_cards() {
		MongoClientURI uri = new MongoClientURI(
				"mongodb://hci:21543879@ds057000.mongolab.com:57000/hci_connection");
		@SuppressWarnings("resource")
		MongoClient client = new MongoClient(uri);
		MongoDatabase db = client.getDatabase(uri.getDatabase());
		MongoCollection<Document> cards = db.getCollection("cards");
		return cards;
	}

	private static MongoCollection<Document> get_users() {
		// MongoLabDB db = new MongoLabDB("hci_connection",
		// "gaxTO-7B0dmSkEV3wtNsqFWGB1jVjnhi");
		// return db.getCollection("users");

		MongoClientURI uri = new MongoClientURI(
				"mongodb://hci:21543879@ds057000.mongolab.com:57000/hci_connection");
		@SuppressWarnings("resource")
		MongoClient client = new MongoClient(uri);
		MongoDatabase db = client.getDatabase(uri.getDatabase());
		MongoCollection<Document> users = db.getCollection("users");
		return users;
	}

	@Override
	public String add_name_card(String user_id, CardInfo c) {
		Document doc = c.toDoc();
		doc.remove("_id");
		MongoCollection<Document> cards = get_cards();
		MongoCollection<Document> users = get_users();
		cards.insertOne(doc);
		Document new_doc = cards.find(doc).first();
		String card_id = new_doc.get("_id").toString();
		Document target_user = users.find(eq("_id", user_id)).first();
		target_user = new UserInfo(target_user).add_my_cards(card_id).toDoc();
		users.findOneAndUpdate(eq("_id", card_id), target_user);
		return card_id;
	}

	@Override
	public String add_name_card(UserInfo u, CardInfo c) {
		return add_name_card(u.get_id(), c);
	}

	@Override
	public CardInfo get_name_card(String _id) {
		Document doc = get_cards().find(eq("_id", new ObjectId(_id))).first();
		return new CardInfo(doc);
	}

	@Override
	public boolean set_name_card(CardInfo c) {
		get_cards().findOneAndUpdate(eq("_id", new ObjectId(c.get_id())),
				c.toDoc());
		return true;
	}

	@Override
	public boolean set_user_info(UserInfo u) {
		get_cards().findOneAndUpdate(eq("_id", new ObjectId(u.get_id())),
				u.toDoc());
		return false;
	}

	@Override
	public boolean delete_name_card_in_my_card(String user_id, String card_id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete_name_card_in_card_case(String user_id, String card_id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public CardInfo get_card_by_phone_number(String phone_number) {
		MongoCollection<Document> cards = get_cards();
		ArrayList<Document> foundDocument = cards.find(
				eq("e", phone_number.toLowerCase())).into(
				new ArrayList<Document>());
		ArrayList<CardInfo> result = new ArrayList<CardInfo>();
		for (Document iter : foundDocument) {
			result.add(new CardInfo(iter));
		}
		return null;
	}

	@Override
	public ArrayList<CardInfo> get_cards_by_name(String user_id, String name) {
		UserInfo user = new UserInfo(get_users().find(
				eq("_id", new ObjectId(user_id))).first());
		ArrayList<String> card_case = user.get_card_case();
		MongoCollection<Document> cards = get_cards();
		ArrayList<CardInfo> result = new ArrayList<CardInfo>();

		for (String card_id : card_case) {
			Document doc = cards.find(eq("_id", new ObjectId(card_id))).first();
			CardInfo iter = new CardInfo(doc);
			if (name.equals(iter.get_name())) {
				result.add(iter);
			}
		}
		return result;
	}

	@Override
	public ArrayList<CardInfo> get_cards_by_phone_number(String user_id,
			String phone_number) {
		return null;
	}

	@Override
	public ArrayList<CardInfo> get_cards_by_email(String user_id, String email) {
		return null;
	}

}