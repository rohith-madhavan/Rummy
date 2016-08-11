
import java.util.ArrayList;
import java.util.Comparator;

public class RummyHand {

	String rankDiffStr = "";
	String suitDiffStr = "";
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

	public int countMatches(String str, String findStr) {
		int lastIndex = 0;
		int count = 0;
		while (lastIndex != -1) {

			lastIndex = str.indexOf(findStr, lastIndex);

			if (lastIndex != -1) {
				count++;
				lastIndex += findStr.length();
			}
		}
		return count;
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

	public boolean isSet(ArrayList<Card> c) {
		c = sortByValue(c);
		String suit = c.get(0).getSuit();
		int countSuit = 0;
		int value = c.get(0).getValue();
		int countValue = 0;
		for (Card s : c) {
			if (s.getSuit().equals(suit)) {
				countSuit++;
			}
			if (s.getValue() == value) {
				countValue++;
			}
		}
		if (countSuit == 1 && countValue == c.size())
			return true;
		return false;
	}

	public String classify(ArrayList<Card> cards) {
		RummyHand hand=new RummyHand();String s="";int count=4;
		cards=hand.sortBySuitAndValue(cards);
		for(int i=0;i<cards.size()-3;i++){
			hand=new RummyHand();
			for(int j=i;j<i+4;j++){
				hand.addCard(cards.get(j));
			}
			if(hand.isSequence(hand.getCards()))
			{
				s+=hand.toString()+"Is a sequence\n";
			}
		}
		cards=hand.sortByValue(cards);
		for(int i=0;i<cards.size()-2;i++){
			hand=new RummyHand();
			for(int j=i;j<i+3;j++){
				hand.addCard(cards.get(j));
			}
			if(hand.isSet(hand.getCards()))
			{
				s+=hand.toString()+"Is a set\n";
			}
		}
		return s;

	}

	public boolean isSequence(ArrayList<Card> cards) {
		String seqStr = "A23456789TJQKA";
		String cardStr = "";

		RummyHand hand = new RummyHand();
		cards = hand.sortBySuitAndValue(cards);

		cardStr += cards.get(0).convertToDisplayValue(cards.get(0).getValue());

		for (int i = 1; i < cards.size(); ++i) {
			Card prev = cards.get(i - 1);
			Card curr = cards.get(i);
			if (!curr.isSameSuit(prev)) {
				return false;
			}
			cardStr += curr.convertToDisplayValue(curr.getValue());
		}
		return seqStr.contains(cardStr);
	}

	public boolean canDeclare(ArrayList<Card> c) {
		return false;
	}

	public int cardsToDeclare(ArrayList<Card> c) {
		return 0;
	}

	public String getRankDiff(ArrayList<Card> c) {

		c = sortBySuitAndValue(c);
		String rankDiff = "";
		rankDiff += "x";
		for (int i = 1; i < c.size(); ++i) {
			Card current = c.get(i);
			Card prev = c.get(i - 1);

			if (current.isSameSuit(prev)) {
				int diff = current.getValue() - prev.getValue();
				if (diff == 1) {
					rankDiff += "1";
				} else {
					rankDiff += "x";
				}
			} else {
				rankDiff += "x";
			}
		}
		return rankDiff;

	}

	public String getSuitDiff(ArrayList<Card> c) {
		c = sortBySuitAndValue(c);
		String suitDiff = "";
		String cardStr = "";

		for (int i = 0; i < c.size(); ++i) {
			Card curr = c.get(i);
			cardStr += curr.convertToDisplayValue(curr.getValue());
		}

		for (int i = 0; i < c.size(); ++i) {
			if (countMatches(cardStr, c.get(i).getDisplayValue()) > 2) {
				suitDiff += c.get(i).getDisplayValue();
			} else {
				suitDiff += "x";
			}
		}

		return suitDiff;
	}

	public boolean isRummy() {

		ArrayList<Card> c = hand;
		for (int i = 1; i < c.size(); ++i) {

		}
		rankDiffStr = getRankDiff(hand);
		suitDiffStr = getSuitDiff(hand);
		System.out.println(countMatches(rankDiffStr, "x11"));
		System.out.println(countMatches(suitDiffStr, "00"));
		return true;
	}

}
