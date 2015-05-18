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
import java.util.ArrayList;

import org.bson.types.ObjectId;

import com.mongodb.*;

public class MongoAccessor implements DataAccessor {

	// Extra helper code

	public static BasicDBObject[] createSeedData() {

		BasicDBObject seventies = new BasicDBObject();
		seventies.put("decade", "1970s");
		seventies.put("artist", "Debby Boone");
		seventies.put("song", "You Light Up My Life");
		seventies.put("weeksAtOne", 10);

		BasicDBObject eighties = new BasicDBObject();
		eighties.put("decade", "1980s");
		eighties.put("artist", "Olivia Newton-John");
		eighties.put("song", "Physical");
		eighties.put("weeksAtOne", 10);

		BasicDBObject nineties = new BasicDBObject();
		nineties.put("decade", "1990s");
		nineties.put("artist", "Mariah Carey");
		nineties.put("song", "One Sweet Day");
		nineties.put("weeksAtOne", 16);

		// final BasicDBObject[] seedData = {seventies, eighties, nineties};
		final BasicDBObject[] seedData = { seventies };

		return seedData;
	}

	public static void main(String[] args) throws UnknownHostException {
		new MongoAccessor().get_name_card("555004aa6d336dc5ae824300");
	}

	@Override
	public CardInfo get_name_card(String _id) {
		MongoClientURI uri = new MongoClientURI(
				"mongodb://hci:21543879@ds057000.mongolab.com:57000/hci_connection");
		MongoClient client;
		try {
			client = new MongoClient(uri);
			DB db = client.getDB(uri.getDatabase());
			DBCollection cards = db.getCollection("users");
			BasicDBObject findQuery = new BasicDBObject("_id",
					new ObjectId(_id));
			// BasicDBObject findQuery = new BasicDBObject("name", "Chad");
			DBCursor docs = cards.find(findQuery);
			while (docs.hasNext()) {
				DBObject doc = docs.next();
				return new CardInfo(_id, doc.get("name").toString(), doc.get(
						"email").toString());
			}
			System.out.println("END");
			client.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean set_name_card(CardInfo c) {
		MongoClientURI uri = new MongoClientURI(
				"mongodb://hci:21543879@ds057000.mongolab.com:57000/hci_connection");
		MongoClient client;
		try {
			client = new MongoClient(uri);
			DB db = client.getDB(uri.getDatabase());
			DBCollection cards = db.getCollection("users");
			BasicDBObject updateQuery = new BasicDBObject("_id", new ObjectId(
					c._id));
			BasicDBObject object = new BasicDBObject();
			object.put("name", c.name);
			object.put("email", c.email);
			cards.update(updateQuery, object);
			client.close();
			return true;
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return false;
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

	@Override
	public ArrayList<String> list_all_name_cards(String _id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> list_all_name_cards(UserInfo u) {
		// TODO Auto-generated method stub
		return null;
	}
}