package sxh.connection.data;

import java.util.ArrayList;

import org.bson.Document;

public class Label {
	private String description;
	private String cover_uri;
	private ArrayList<String> cards;

	public Label(String description, String cover_uri) {
		this.description = description;
		this.cover_uri = cover_uri;
		this.cards = new ArrayList<String>();
	}

	@SuppressWarnings("unchecked")
	public Label(Document doc) {
		this.description = doc.get("d").toString();
		this.cover_uri = doc.get("n").toString();
		this.cards = (ArrayList<String>) doc.get("c");
	}

	public void add_card(String card_id) {
		cards.add(card_id);
	}

	public void del_card(String card_id) {
		cards.remove(card_id);
	}

	public Document toDoc() {
		Document doc = new Document();
		doc.put("d", description);
		doc.put("n", cover_uri);
		doc.put("c", cards);
		return doc;
	}

	public void _print() {
		System.out.println("\tdescroption: " + description);
		System.out.println("\tcover_uri: " + cover_uri);
		System.out.print("\tcards: ");
		for (String iter : cards) {
			System.out.print(iter + "\t");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		Label p = new Label("SE", "/src/pic/001.jpg");
		p.add_card("1234");
		p.add_card("12345");
		p.add_card("123456");
		p.del_card("123");
		p.del_card("1234");
		Document d = p.toDoc();
		new Label(d)._print();
	}

	public String get_description() {
		return description;
	}

	public String get_cover_uri() {
		return cover_uri;
	}

	public ArrayList<String> get_cards() {
		return cards;
	}
}
