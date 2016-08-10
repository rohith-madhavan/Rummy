package cards;

public class Test {
	
	public static void main(String[] args) {
	
		RummyHand h = new RummyHand();
		h.addCard(new Card("4","SPADE"));
		h.addCard(new Card("5","SPADE"));
		h.addCard(new Card("6","SPADE"));
		h.addCard(new Card("7","SPADE"));
		
		h.addCard(new Card("A","HEART"));
		h.addCard(new Card("2","HEART"));
		h.addCard(new Card("3","HEART"));
		
		h.addCard(new Card("8","SPADE"));
		h.addCard(new Card("8","DIAMOND"));
		h.addCard(new Card("8","CLUB"));
		
		h.addCard(new Card("2","DIAMOND"));
		h.addCard(new Card("3","DIAMOND"));
		h.addCard(new Card("4","DIAMOND"));
		
		System.out.println(h.toString());
		System.out.println(h.sortByValue(h.getCards()).toString());
		System.out.println(h.sortBySuit(h.getCards()).toString());
		System.out.println(h.sortBySuitAndValue(h.getCards()).toString());
	}
	
}
