package problems.euler051to075;

import problems.Problem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Problem 54:
 * https://projecteuler.net/problem=54
 *
 * WARNING: This is broken somewhere. Need to clean it up. Within 4 of the answer.
 *
 * Solved by Sage on 10/26/16.
 */
public class Euler054 implements Problem {

    private static final String FILE_NAME = "p054_poker.txt";

    // Suits
    private static final String SUITS = "CDHS";
    private static final String CLUB = "C";
    private static final String DIAMOND = "D";
    private static final String HEART = "H";
    private static final String SPADE = "S";

    // Royals
    private static final String ROYALS = "TJQKA";
    private static final char TEN = 'T';
    private static final char JACK = 'J';
    private static final char QUEEN = 'Q';
    private static final char KING = 'K';
    private static final char ACE = 'A';

    // Map of char to value for cards
    private static final Map<Character, Integer> valueMap;
    static {
        valueMap = new HashMap<Character, Integer>();
        valueMap.put('2', 2);
        valueMap.put('3', 3);
        valueMap.put('4', 4);
        valueMap.put('5', 5);
        valueMap.put('6', 6);
        valueMap.put('7', 7);
        valueMap.put('8', 8);
        valueMap.put('9', 9);
        valueMap.put(TEN, 10);
        valueMap.put(JACK, 11);
        valueMap.put(QUEEN, 12);
        valueMap.put(KING, 13);
        valueMap.put(ACE, 14);
    }

    @Override
    public String solve() {

        int playerOneWins = 0;
        try {
            // Read in file, remove '"' and ',' and split the words into an array
            BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));

            String game;
            while ((game = reader.readLine()) != null) {
                System.out.print("Winner of " + game.substring(0,14) + " versus " + game.substring(15) + " is ");
                int winner = getWinner(game.substring(0,14), game.substring(15));
                if (winner > 0) {
                    playerOneWins++;
                    System.out.println(" player 1");
                } else
                    System.out.println(" player 2");
            }

        } catch (Exception e) {
            throw new RuntimeException("Something broke...", e);
        }

        return String.valueOf(playerOneWins);
    }

    /**
     * Get the better of the two hands
     * @param handOneStr a hand
     * @param handTwoStr a hand
     * @return x < 0 if hand two, x > 0 if hand one
     */
    private int getWinner(String handOneStr, String handTwoStr) {
        Hand handOne = new Hand(handOneStr.split(" "));
        Hand handTwo = new Hand(handTwoStr.split(" "));

        boolean oneF = handOne.isFlush();
        boolean oneS = handOne.isStraight();
        boolean twoF = handTwo.isFlush();
        boolean twoS = handTwo.isStraight();

        // Straight flushes
        if ((oneF && oneS) && (twoF && twoS)) {
            return handOne.highCard > handTwo.highCard ? 1 : 2;
        } else if ((oneF && oneS) && !(twoF && twoS)) {
            return 1;
        } else if (!(oneF && oneS) && (twoF && twoS)) {
            return -1;
        } // Four of a kind
        else if (handOne.four != handTwo.four) {
            return handOne.four - handTwo.four;
        } // FullHouse
        else if (handOne.three != 0 && handTwo.pairOne != 0) {
            if (handTwo.three != 0 && handTwo.pairOne != 0) {
                return handOne.three - handTwo.three;
            } else
                return 1;
        } else if (handTwo.three != 0 && handTwo.pairOne != 0) {
            return -1;
        } // Flushes
        else if (handOne.isFlush() || handTwo.isFlush()){
            if (handOne.isFlush() && !handTwo.isFlush())
                return 1;
            else if (handTwo.isFlush() && !handOne.isFlush())
                return -1;
            else
                return handOne.highCard - handTwo.highCard;
        } // Straights
        else if (handOne.isStraight()) {
            if (handTwo.isStraight())
                return handOne.highCard - handTwo.highCard;
            else
                return 1;
        } else if (handTwo.isStraight()) {
            return -1;
        } // Threes
        else if (handOne.three != handTwo.three) {
            return handOne.highCard - handTwo.highCard;
        } // Pairs
        else if (handOne.pairOne != 0) {
            // Two pair
            if (handOne.pairTwo != 0) {
                if (handTwo.pairTwo == 0 || handTwo.pairOne < handOne.pairOne) {
                    return 1;
                } else {
                    return handOne.pairTwo - handTwo.pairTwo;
                }
            } else if (handTwo.pairTwo != 0) {
                return -1;
            } else
                return handOne.highCard - handTwo.highCard;
        } else if (handTwo.pairOne > 0) {
            return -1;
        } else
            return handOne.highCard - handTwo.highCard;
    }

    private class Hand {

        int[] cardArray;
        String flushSuit;
        int highCard;
        int four;
        int three;
        int pairOne;
        int pairTwo;

        Hand(String[] hand) {
            this.cardArray = new int[15];
            this.highCard = 0;
            this.flushSuit = hand[0].substring(1,2);
            for (String card : hand) {
                Card c = new Card(card);
                cardArray[c.value]++;
                if (!c.suit.equals(flushSuit)) {
                    flushSuit = null;
                }
                if (c.value > highCard)
                    this.highCard = c.value;
            }

            four = 0;
            three = 0;
            pairOne = 0;
            pairTwo = 0;

            for (int i = 14; i > 1; i--) {
                switch (cardArray[i]) {
                    case 0:
                        break;
                    case 4:
                        four = i;
                        break;
                    case 3:
                        three = i;
                    case 2:
                        if (pairOne > 0)
                            pairTwo = i;
                        else
                            pairOne = i;
                        break;
                    default:
                        break;
                }
            }
        }

        public boolean isFlush() {
            return this.flushSuit == null;
        }

        public int getHighCard() {
            return this.highCard;
        }

        public boolean isStraight() {
            int start = 0;
            for (int i = 0; i < 15; i++) {
                if (cardArray[i] != 0) {
                    start = i;
                }
            }
            if (start > 10)
                return false;
            for (int i = start+1; i < start+5; i++) {
                if (cardArray[i] != 1)
                    return false;
            }
            return true;
        }
    }

    private class Card {
        int value;
        String suit;

        Card(String card) {
            this.value = valueMap.get(card.charAt(0));
            this.suit = card.substring(1,2);
        }
    }
}
