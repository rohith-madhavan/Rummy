package cards;

import java.util.ArrayList;
import java.util.Comparator;

public class RummyHand {

	private Card jokerCard;
	private ArrayList<Card> hand;
	
	public RummyHand() {
		hand = new ArrayList<>();
	}

	public RummyHand(ArrayList<Card> card) {
		hand = new ArrayList<>();
		hand.addAll(card);
	}

	public String toString() {
		return hand.toString();
	}
	
	public ArrayList<Card> getCards() {
		return this.hand;
	}
	
	public void addCard(Card c) {
		hand.add(c);
	}

	public Card removeCard(Card c) {
		hand.remove(c);
		return c;
	}
	
	public ArrayList<Card> sortBySuit(ArrayList<Card> c) {
		c.sort((Card c1, Card c2) -> c1.getSuit().compareTo(c2.getSuit()));
		return c;
	}

	public ArrayList<Card> sortByValue(ArrayList<Card> c) {
		c.sort((Card c1, Card c2) -> c1.getValue() - c2.getValue());
		return c;
	}

	public ArrayList<Card> sortBySuitAndValue(ArrayList<Card> c) {
		c.sort(new Comparator<Card>() {

			public int compare(Card o1, Card o2) {
				if (o1.isSameSuit(o2)) {
					return o1.getValue() - o2.getValue();
				} else {
					return o1.getSuit().compareTo(o2.getSuit());
				}
			}
		});
		
		return c;
	}
	
	public boolean isSet(ArrayList<Card> c){
		return false;
	}
	
	public boolean isSequence(ArrayList<Card> c){
		return false;
	}
	
	public boolean canDeclare(ArrayList<Card> c){
		return false;
	}
	
	public int cardsToDeclare(ArrayList<Card> c){
		return 0;
	}
	

}
