package data;

import java.util.ArrayList;
import java.util.Date;
import org.bson.Document;

public class CardInfo {
	private String _id;
	private String name;
	private String name_card_model;
	private ArrayList<Phone> phone_numbers;
	private ArrayList<SNS> sns_accounts;
	private String email;

	private String address;
	private Date birthday; // yyyy-MM-dd


	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName_card_model() {
		return name_card_model;
	}

	public void setName_card_model(String name_card_model) {
		this.name_card_model = name_card_model;
	}

	public ArrayList<Phone> getPhone_numbers() {
		return phone_numbers;
	}

	public void addPhone_number(Phone phone) {
		this.phone_numbers.add(phone);
	}

	public ArrayList<SNS> getSns_accounts() {
		return sns_accounts;
	}

	public void addSns_account(SNS sns) {
		this.sns_accounts.add(sns);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public void _print() {
		System.out.println("***CardInfo***");
		System.out.println("_id\t" + _id);
		System.out.println("name\t" + name);
		System.out.println("model\t" + name_card_model);
		System.out.println("numbers");

		java.util.Iterator<Phone> p_iter = phone_numbers.iterator();
		while (p_iter != null && p_iter.hasNext()) {
			p_iter.next()._print();
		}

		java.util.Iterator<SNS> s_iter = sns_accounts.iterator();
		while (s_iter != null && s_iter.hasNext()) {
			s_iter.next()._print();
		}

		System.out.println("email\t" + email);
		System.out.println("address\t" + address);
		System.out.println("birthday\t" + birthday);
	}

	@SuppressWarnings("unchecked")
	public CardInfo(Document doc) {
		this._id = doc.get("_id").toString();
		this.name = doc.get("n").toString();
		this.name_card_model = doc.get("m").toString();
		this.phone_numbers = (ArrayList<Phone>) doc.get("p");
		this.sns_accounts = (ArrayList<SNS>) doc.get("s");
		this.email = doc.get("e").toString();
		this.address = doc.get("a").toString();
		this.birthday = (Date) doc.get("b");
	}

	public Document toDoc() {
		Document doc = new Document();
		doc.put("_id", _id);
		doc.put("n", name);
		doc.put("m", name_card_model);

		doc.put("p", phone_numbers);
		doc.put("s", sns_accounts);
		doc.put("e", email);
		doc.put("a", address);
		doc.put("b", birthday);
		return doc;
	}

	public CardInfo() {
		_id = "";
		name = "";
		name_card_model = "";
		phone_numbers = new ArrayList<Phone>();
		sns_accounts = new ArrayList<SNS>();
		email = "";
		address = "";
		birthday = new Date();
	}

	public static void main(String[] args) {
		System.out.println("hello");
		// CardInfo ci = new CardInfo("555004aa6d336dc5ae824300", "Chad", "sangchuang@sjtu.edu.cn");
		CardInfo ci = new CardInfo();
		ci.addPhone_number(new Phone("work", "12345"));
		ci.addPhone_number(new Phone("work", "12345"));
		ci._print();
		Document doc = ci.toDoc();
		new CardInfo(doc)._print();
	}
}
