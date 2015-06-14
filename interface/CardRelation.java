package sxh.connection.data;

import java.util.Calendar;
import java.util.Date;

import org.bson.Document;

public class CardRelation {
	private String card_id;
	private Date anniversary; // yyyy-MM-dd
	private String relation;

	public CardRelation(String card_id, String relation) {
		this.card_id = card_id;
		this.anniversary = Calendar.getInstance().getTime();
		this.relation = relation;
	}

	public CardRelation(Document doc) {
		this.card_id = doc.get("d").toString();
		this.anniversary = (Date) doc.get("a");
		this.relation = doc.get("n").toString();
	}

	public Document toDoc() {
		Document doc = new Document();
		doc.put("d", card_id);
		doc.put("a", anniversary);
		doc.put("n", relation);
		return doc;
	}

	public void _print() {
		System.out.println("\tcard_id: " + card_id);
		System.out.println("\trelation: " + relation);
		System.out.println("\tanniversary: " + anniversary);
	}

	public static void main(String[] args) {
		CardRelation p = new CardRelation("r2-93uu329r-3r2q9u9q", "friend");
		Document d = p.toDoc();
		new CardRelation(d)._print();
	}


    public String get_card_id(){
        return card_id;
    }
    
    public Date get_anniversary() {
    	return anniversary;
    }

    public String get_relation(){
        return relation;
    }
}
