package data;

/*
 * Copyright (c) 2015 ObjectLabs Corporation
 * Distributed under the MIT license - http://opensource.org/licenses/MIT
 *
 * Written with mongo-2.11.2.jar
 * Documentation: http://api.mongodb.org/java/
 * A Java class connecting to a MongoDB database given a MongoDB Connection URI.
 */

import java.net.UnknownHostException;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import static com.mongodb.client.model.Filters.*;

public class MongoAccessor implements DataAccessor {

	public static Document[] createSeedData() {

		Document seventies = new Document();
		seventies.put("decade", "1970s");
		seventies.put("artist", "Debby Boone");
		seventies.put("song", "You Light Up My Life");
		seventies.put("weeksAtOne", 10);

		Document eighties = new Document();
		eighties.put("decade", "1980s");
		eighties.put("artist", "Olivia Newton-John");
		eighties.put("song", "Physical");
		eighties.put("weeksAtOne", 10);

		Document nineties = new Document();
		nineties.put("decade", "1990s");
		nineties.put("artist", "Mariah Carey");
		nineties.put("song", "One Sweet Day");
		nineties.put("weeksAtOne", 16);

		// final Document[] seedData = {seventies, eighties, nineties};
		final Document[] seedData = { seventies };

		return seedData;
	}

	public static void main(String[] args) throws UnknownHostException {
		// new MongoAccessor().get_name_card("555004aa6d336dc5ae824300")._print();
		CardInfo ci = new CardInfo();
		ci.setName("Chad2");
		ci.setEmail("s@sjtu");
		ci.addPhone_number(new Phone("work", "12345"));
		ci.addPhone_number(new Phone("home", "23456"));
		System.out.println("begin");
		String id = new MongoAccessor().add_name_card(ci);
		new MongoAccessor().get_name_card(id)._print();
		System.out.println("succ");
	}

	private static MongoCollection<Document> get_users() {
		MongoClientURI uri = new MongoClientURI(
				"mongodb://hci:21543879@ds057000.mongolab.com:57000/hci_connection");
		@SuppressWarnings("resource")
		MongoClient client = new MongoClient(uri);
		MongoDatabase db = client.getDatabase(uri.getDatabase());
		MongoCollection<Document> users = db.getCollection("users");
		return users;
	}

	@Override
	public String add_name_card(CardInfo c) {
		Document doc = c.toDoc();
		doc.remove("_id");
		MongoCollection<Document> users = get_users();
		users.insertOne(doc);
		Document new_doc = users.find(doc).first();
		return new_doc.get("_id").toString();
	}

	@Override
	public CardInfo get_name_card(String _id) {
		Document doc = get_users().find(eq("_id", new ObjectId(_id))).first();
		return new CardInfo(doc);
	}

	@Override
	public boolean set_name_card(CardInfo c) {
		get_users().findOneAndUpdate(eq("_id", new ObjectId(c.get_id())), c.toDoc());
		return true;
	}

	@Override
	public UserInfo get_user_info(String _id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean set_user_info(UserInfo u) {
		// TODO Auto-generated method stub
		return false;
	}


}