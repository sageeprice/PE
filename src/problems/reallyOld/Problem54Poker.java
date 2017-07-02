package problems.reallyOld;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by Sage on 9/14/2015.
 *
 * It works. It sucks. I won't touch it again.
 * answer: 376
 */
public class Problem54Poker {

    public static void main(String[] args) throws IOException{
        int leftWins = 0;

        Hand lHand;
        Hand rHand;

        for (String line : Files.readAllLines(Paths.get("C:\\Users\\Sage\\workspace\\SageKatas\\eulerTxtFiles\\p054_poker.txt"))) {
            String left = line.substring(0, 14);
            String right = line.substring(15);
            lHand = new Hand(left);
            rHand = new Hand(right);
            if (lHand.compareTo(rHand) == 1) {
                leftWins++;
            }
        }
        System.out.println("Left hand wins: "+leftWins);

    }

    private static class Card {
        public int rank;
        public int suit;

        public Card(String card) {
            char n = card.charAt(0);
            char s = card.charAt(1);
            switch (s) {
                case 'C':
                    suit = 0;
                    break;
                case 'D':
                    suit = 1;
                    break;
                case 'H':
                    suit = 2;
                    break;
                default:
                    suit = 3;
            }
            switch (n) {
                case 'T':
                    rank = 10;
                    break;
                case 'J':
                    rank = 11;
                    break;
                case 'Q':
                    rank = 12;
                    break;
                case 'K':
                    rank = 13;
                    break;
                case 'A':
                    rank = 14;
                    break;
                default:
                    rank = Integer.valueOf(String.valueOf(n));
            }

        }
    }

    private static class Hand implements Comparable<Hand>{

        private int hand;
        private int high1;
        private int high2;
        private int high3;
        private int high4;
        private int high5; // woohoo
        private Card[] cards;

        public Hand(String newHand) {
            String[] newCards = newHand.split(" ");
            cards = new Card[5];
            for (int i = 0; i < 5; i++) {
                cards[i] = new Card(newCards[i]);
            }
            hand = calculateHand();
        }

        private int calculateHand() {
            int[] cardCount = new int[15];
            int[] suits = new int[5];
            for (int i = 0; i < 5; i++) {
                cardCount[cards[i].rank]++;
                suits[cards[i].suit]++;
            }
            if (isStraightFlush(cardCount, suits)) {
                return 9;
            } else if (isFourOfAKind(cardCount)) {
                return 8;
            } else if (isFullHouse(cardCount)) {
                return 7;
            } else if (isFlush(cardCount,suits)) {
                return 6;
            } else if (isStraight(cardCount)) {
                return 5;
            } else if (isThreeOfAKind(cardCount)) {
                return 4;
            } else if (isTwoPair(cardCount)) {
                return 3;
            } else if (isPair(cardCount)) {
                return 2;
            } else {
                highCard(cardCount);
                return 1;
            }
        }

        private boolean isStraightFlush(int[] nums, int[] suits) {
            for (int i = 0; i < 4; i++) {
                if (suits[i] == 4) {
                    return isStraight(nums);
                }
            }
            return false;
        }

        private boolean isFourOfAKind(int[] nums) {
            for (int i = 2; i < 15; i++) {
                if (nums[i] == 1) {
                    setHigh2(i);
                } else if (nums[i] == 4) {
                    setHigh1(i);
                    return true;
                }
            }
            return false;
        }

        private boolean isFullHouse(int[] nums) {
            int three = 0;
            int two = 0;
            for (int i = 2; i < 15; i++) {
                if (nums[i] == 2) {
                    two = i;
                } else if (nums[i] == 3) {
                    three = i;
                }
            }
            if (three != 0 && two != 0) {
                setHigh1(three);
                setHigh2(two);
                return true;
            } else {
                return false;
            }
        }

        private boolean isFlush(int[] nums, int[] suits) {
            for (int i = 0; i < 4; i++) {
                if (suits[i] == 5) {
                    int c = 0;
                    for (int j = 14; j>1; j--) {
                        if (nums[j]>1) {
                            suits[c] = i;
                            nums[j]--;
                            c++;
                            j++;
                        }
                    }
                    setHigh1(suits[0]);
                    setHigh2(suits[1]);
                    setHigh3(suits[2]);
                    setHigh4(suits[3]);
                    setHigh5(suits[4]);
                    return true;
                }
            }
            return false;
        }

        private boolean isStraight(int[] nums) {
            int s = 2;
            while (nums[s] < 1) {
                s++;
            } if (nums[s] > 1) {
                return false;
            }
            for (int i = 0; i < 5; i++) {
                if (nums[s+i] != 1)
                    return false;
            }
            setHigh1(s+4);
            return true;
        }

        private boolean isThreeOfAKind(int[] nums) {
            for (int i = 2; i< 15; i++) {
                if (nums[i] == 3) {
                    setHigh1(i);
                    i = 2;
                    while (nums[i] != 1)
                        i++;
                    setHigh3(i);
                    i = 14;
                    while (nums[i] != 1)
                        i--;
                    setHigh2(i);
                    return true;
                }
            }
            return false;
        }

        private boolean isTwoPair(int[] nums) {
            int lPair = 0;
            int hPair = 0;
            int other = 0;
            for (int i = 2; i < 14; i++) {
                if (nums[i] == 2) {
                    if (lPair == 0) {
                        lPair = i;
                    } else {
                        hPair = i;
                    }
                } else if (nums[i] == 1) {
                    other = i;
                }
            }
            if (hPair != 0) {
                setHigh1(hPair);
                setHigh2(lPair);
                setHigh3(other);
                return true;
            }
            return false;
        }

        private boolean isPair(int[] nums) {
            int[] arr = new int[4];
            int low = 3;
            for (int i = 2; i < 15; i++) {
                if (nums[i] == 2) {
                    arr[0] = i;
                }
            }
            if (arr[0] != 0) {
                for (int i = 2; i < 15; i++) {
                    if (nums[i] == 1) {
                        arr[low] = i;
                        low--;
                    }
                }
                setHigh1(arr[0]);
                setHigh2(arr[1]);
                setHigh3(arr[2]);
                setHigh4(arr[3]);
                return true;
            }
            return false;
        }

        private void highCard(int[] nums) {
            int[] arr = new int[5];
            int index = 0;
            for (int i = 2; i < 15; i++) {
                if (nums[i] == 1) {
                    arr[index] = i;
                    index++;
                }
            }
            setHigh1(arr[4]);
            setHigh2(arr[3]);
            setHigh3(arr[2]);
            setHigh4(arr[1]);
            setHigh5(arr[0]);
        }

        @Override
        public int compareTo(Hand o) {
            if (o.hand < hand) {
                return 1;
            } else if (o.getHand() == getHand()) {
                if (o.getHigh1() < getHigh1()) {
                    return 1;
                } else if (o.getHigh1() > getHigh1()) {
                    return -1;
                } else if (o.getHigh2() < getHigh2()) {
                    return 1;
                } else if (o.getHigh2() > getHigh2()) {
                    return -1;
                } else if (o.getHigh3() < getHigh3()) {
                    return 1;
                } else if (o.getHigh3() > getHigh3()) {
                    return -1;
                } else if (o.getHigh4() < getHigh4()) {
                    return 1;
                } else if (o.getHigh4() > getHigh4()) {
                    return -1;
                } else if (o.getHigh5() < getHigh5()) {
                    return 1;
                } else if (o.getHigh5() > getHigh5()) {
                    return -1;
                }
            }
            return -1;
        }

        public int getHand() {
            return hand;
        }

        public void setHand(int hand) {
            this.hand = hand;
        }

        public int getHigh1() {
            return high1;
        }

        public void setHigh1(int high1) {
            this.high1 = high1;
        }

        public int getHigh2() {
            return high2;
        }

        public void setHigh2(int high2) {
            this.high2 = high2;
        }

        public int getHigh3() {
            return high3;
        }

        public void setHigh3(int high3) {
            this.high3 = high3;
        }

        public int getHigh4() {
            return high4;
        }

        public void setHigh4(int high4) {
            this.high4 = high4;
        }

        public int getHigh5() {
            return high5;
        }

        public void setHigh5(int high5) {
            this.high5 = high5;
        }
    }
}

