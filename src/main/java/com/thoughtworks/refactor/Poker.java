package com.thoughtworks.refactor;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Poker {
    public String compareResult(String blackHands, String whiteHands) {
        String winResult = "";
        String blackHandsCategory = getHandsCategory(blackHands);
        String whiteHandsCategory = getHandsCategory(whiteHands);
        String[] handsCategories = {"StraightFlush", "FourOfAKind", "FullHouse", "Flush", "Straight", "ThreeOfAKind", "TwoPair", "OnePair", "HighCard"};
        int[] blackHandsNumbers = getDescendingHandsNumbers(blackHands);
        int[] whiteHandsNumbers = getDescendingHandsNumbers(whiteHands);
        int blackHandsCategoryRank = getHandsCategoryRank(blackHandsCategory);
        int whiteHandsCategoryRank = getHandsCategoryRank(whiteHandsCategory);
        int[] descendingBlackHandsNumbers = descendingSort(blackHandsNumbers);
        int[] descendingWhiteHandsNumbers = descendingSort(whiteHandsNumbers);
        int[] repeatBlackHandsNumbers = getRepeatNumbers(blackHandsNumbers);
        int[] repeatWhiteHandsNumbers = getRepeatNumbers(whiteHandsNumbers);
        int[] noRepeatBlackHandsNumbers = getNoRepeatNumbers(blackHandsNumbers);
        int[] noRepeatWhiteHandsNumbers = getNoRepeatNumbers(whiteHandsNumbers);
        if (blackHandsCategoryRank < whiteHandsCategoryRank) {
            winResult = "black wins - " + handsCategories[blackHandsCategoryRank];
        } else if (blackHandsCategoryRank > whiteHandsCategoryRank) {
            winResult = "white wins - " + handsCategories[whiteHandsCategoryRank];
        } else {
            if (blackHandsCategoryRank == 0) { //同花顺
                if (blackHandsNumbers[0] < whiteHandsNumbers[0]) {
                    String sig = showCard(whiteHandsNumbers[0]);
                    winResult = "white wins - high card:" + sig;
                } else if (blackHandsNumbers[0] > whiteHandsNumbers[0]) {
                    String sig = showCard(blackHandsNumbers[0]);
                    winResult = "black wins - high card:" + sig;
                } else {
                    winResult = "tie";
                }
            } else if (blackHandsCategoryRank == 1) { //铁支
                if (descendingBlackHandsNumbers[0] < descendingWhiteHandsNumbers[0]) {
                    String sig = showCard(descendingWhiteHandsNumbers[0]);
                    winResult = "white wins - high card:" + sig;
                } else {
                    String sig = showCard(descendingBlackHandsNumbers[0]);
                    winResult = "black wins - high card:" + sig;
                }
            } else if (blackHandsCategoryRank == 2) { //葫芦
                if (descendingBlackHandsNumbers[0] < descendingWhiteHandsNumbers[0]) {
                    String sig = showCard(descendingWhiteHandsNumbers[0]);
                    winResult = "white wins - high card:" + sig;
                } else {
                    String sig = showCard(descendingBlackHandsNumbers[0]);
                    winResult = "black wins - high card:" + sig;
                }
            } else if (blackHandsCategoryRank == 3) { //同花
                for (int i = 0; i < 5; i++) {
                    if (blackHandsNumbers[i] < whiteHandsNumbers[i]) {
                        String sig = showCard(whiteHandsNumbers[i]);
                        winResult = "white wins - high card:" + sig;
                        break;
                    } else if (blackHandsNumbers[i] > whiteHandsNumbers[i]) {
                        String sig = showCard(blackHandsNumbers[i]);
                        winResult = "black wins - high card:" + sig;
                        break;
                    } else {
                        winResult = "tie";
                    }
                }
            } else if (blackHandsCategoryRank == 4) { //顺子
                if (blackHandsNumbers[0] < whiteHandsNumbers[0]) {
                    String sig = showCard(whiteHandsNumbers[0]);
                    winResult = "white wins - high card:" + sig;
                } else if (blackHandsNumbers[0] > whiteHandsNumbers[0]) {
                    String sig = showCard(blackHandsNumbers[0]);
                    winResult = "black wins - high card:" + sig;
                } else {
                    winResult = "tie";
                }
            } else if (blackHandsCategoryRank == 5) { //三条
                if (repeatBlackHandsNumbers[0] < repeatWhiteHandsNumbers[0]) {
                    String sig = showCard(repeatWhiteHandsNumbers[0]);
                    winResult = "white wins - high card:" + sig;
                } else {
                    String sig = showCard(repeatBlackHandsNumbers[0]);
                    winResult = "black wins - high card:" + sig;
                }
            } else if (blackHandsCategoryRank == 6) { //两对
                for (int i = 0; i < 2; i++) {
                    if (repeatBlackHandsNumbers[i] < repeatWhiteHandsNumbers[i]) {
                        String sig = showCard(repeatWhiteHandsNumbers[i]);
                        winResult = "white wins - high card:" + sig;
                        break;
                    } else if (repeatBlackHandsNumbers[i] > repeatWhiteHandsNumbers[i]) {
                        String sig = showCard(repeatBlackHandsNumbers[i]);
                        winResult = "black wins - high card:" + sig;
                        break;
                    }
                }
                if (winResult == "") {
                    if (noRepeatBlackHandsNumbers[0] < noRepeatWhiteHandsNumbers[0]) {
                        String sig = showCard(noRepeatWhiteHandsNumbers[0]);
                        winResult = "white wins - high card:" + sig;
                    } else if (noRepeatBlackHandsNumbers[0] > noRepeatWhiteHandsNumbers[0]) {
                        String sig = showCard(noRepeatBlackHandsNumbers[0]);
                        winResult = "black wins - high card:" + sig;
                    } else {
                        winResult = "tie";
                    }
                }
            } else if (blackHandsCategoryRank == 7) { //对子
                if (repeatBlackHandsNumbers[0] < repeatWhiteHandsNumbers[0]) {
                    String sig = showCard(repeatWhiteHandsNumbers[0]);
                    winResult = "white wins - high card:" + sig;
                } else if (repeatBlackHandsNumbers[0] > repeatWhiteHandsNumbers[0]) {
                    String sig = showCard(repeatBlackHandsNumbers[0]);
                    winResult = "black wins - high card:" + sig;
                } else {
                    for (int i = 0; i < 3; i++) {
                        if (noRepeatBlackHandsNumbers[i] < noRepeatWhiteHandsNumbers[i]) {
                            String sig = showCard(noRepeatWhiteHandsNumbers[i]);
                            winResult = "white wins - high card:" + sig;
                            break;
                        } else if (noRepeatBlackHandsNumbers[i] > noRepeatWhiteHandsNumbers[i]) {
                            String sig = showCard(noRepeatBlackHandsNumbers[i]);
                            winResult = "black wins - high card:" + sig;
                            break;
                        } else {
                            winResult = "tie";
                        }
                    }
                }
            } else { //散牌
                for (int i = 0; i < 5; i++) {
                    if (blackHandsNumbers[i] < whiteHandsNumbers[i]) {
                        String sig = showCard(whiteHandsNumbers[i]);
                        winResult = "white wins - high card:" + sig;
                        break;
                    } else if (blackHandsNumbers[i] > whiteHandsNumbers[i]) {
                        String sig = showCard(blackHandsNumbers[i]);
                        winResult = "black wins - high card:" + sig;
                        break;
                    } else {
                        winResult = "tie";
                    }
                }
            }
        }
        return winResult;
    }

    private int[] getNoRepeatNumbers(int[] handsNumbers) {
        return IntStream.of(handsNumbers)
            .filter(number -> Collections.frequency(Arrays.stream(handsNumbers).boxed().collect(Collectors.toList()), number) == 1)
            .toArray();
    }

    private int[] getRepeatNumbers(int[] handsNumbers) {
        return IntStream.of(handsNumbers)
            .filter(number -> Collections.frequency(Arrays.stream(handsNumbers)
                .boxed()
                .collect(Collectors.toList()), number) > 1)
            .distinct()
            .toArray();
    }

    private String showCard(int cardNumber) {
        String[] cardViews = {"2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K", "A"};
        return cardViews[cardNumber - 2];
    }

    private int[] descendingSort(int[] handsNumbers) {
        return IntStream.of(handsNumbers)
            .boxed()
            .sorted(Comparator.reverseOrder())
            .mapToInt(i -> i)
            .toArray();
    }

    private int getHandsCategoryRank(String handsCategory) {
        int index = -1;
        String[] handsCategories = {"StraightFlush", "FourOfAKind", "FullHouse", "Flush", "Straight", "ThreeOfAKind", "TwoPair", "OnePair", "HighCard"};
        for (int i = 0; i < 9; i++) {
            if (handsCategories[i].equals(handsCategory)) {
                index = i;
            }
        }
        return index;
    }

    private String getHandsCategory(String hands) {
        if (isStraightFlush(hands)) {
            return "StraightFlush";
        }
        if (isStraight(hands)) {
            return "Straight";
        }
        if (isFlush(hands)) {
            return "Flush";
        }
        if (isHighCard(hands)) {
            return "HighCard";
        }
        if (isOnePair(hands)) {
            return "OnePair";
        }
        if (isTwoPair(hands)) {
            return "TwoPair";
        }
        if (isThreeOfAKind(hands)) {
            return "ThreeOfAKind";
        }
        if (isFourOfAKind(hands)) {
            return "FourOfAKind";
        }
        return "FullHouse";
    }

    private boolean isFourOfAKind(String hands) {
        int[] handsNumbers = getDescendingHandsNumbers(hands);
        return handsNumbers[0] != handsNumbers[1] || handsNumbers[3] != handsNumbers[4];
    }

    private boolean isThreeOfAKind(String hands) {
        return countDistinctNumbers(hands) == 3;
    }

    private boolean isTwoPair(String hands) {
        int[] handsNumbers = getDescendingHandsNumbers(hands);
        return countDistinctNumbers(hands) == 3 &&
            ((handsNumbers[0] == handsNumbers[1] && handsNumbers[2] == handsNumbers[3]) ||
                (handsNumbers[1] == handsNumbers[2] && handsNumbers[3] == handsNumbers[4]) ||
                (handsNumbers[0] == handsNumbers[1] && handsNumbers[3] == handsNumbers[4]));
    }

    private boolean isOnePair(String hands) {
        return countDistinctNumbers(hands) == 4;
    }

    private boolean isHighCard(String hands) {
        return countDistinctNumbers(hands) == 5;
    }

    private boolean isFlush(String hands) {
        return countDistinctNumbers(hands) == 5 && countSuits(hands) == 1;
    }

    private boolean isStraight(String hands) {
        int[] handsNumbers = getDescendingHandsNumbers(hands);
        return countDistinctNumbers(hands) == 5 && handsNumbers[0] - handsNumbers[4] == 4;
    }

    private boolean isStraightFlush(String hands) {
        int[] handsNumbers = getDescendingHandsNumbers(hands);
        return countDistinctNumbers(hands) == 5 && handsNumbers[0] - handsNumbers[4] == 4 && countSuits(hands) == 1;
    }

    private int countDistinctNumbers(String hands) {
        int i;
        HashSet<Integer> distinctNumbers = new HashSet<Integer>();
        for (i = 0; i < 5; i++) {
            distinctNumbers.add(getDescendingHandsNumbers(hands)[i]);
        }
        int distinctNumbersCount = distinctNumbers.size();
        return distinctNumbersCount;
    }

    private int countSuits(String hands) {
        String[] strArray = hands.split("");
        int i;
        String[] suit = new String[5];
        for (i = 0; i < 5; i++) {
            suit[i] = strArray[i * 3 + 1];
        }
        HashSet<String> suits = new HashSet<String>();
        for (i = 0; i < 5; i++) {
            suits.add(suit[i]);
        }
        int suitsCount = suits.size();
        return suitsCount;
    }

    private int[] getDescendingHandsNumbers(String hands) {
        int[] cardNumbers = getCardNumbers(hands);
        return descendingSort(cardNumbers);
    }

    private int[] getCardNumbers(String hands) {
        return getCardNumberViews(hands).stream()
            .mapToInt(cardNumberView -> getCardNumber(cardNumberView))
            .toArray();
    }

    private List<String> getCardNumberViews(String hands) {
        List<String> cardNumberViews = new ArrayList<>();
        String[] strArray = hands.split("");
        for (int i = 0; i < 5; i++) {
            String cardNumberView = strArray[i * 3];
            cardNumberViews.add(cardNumberView);
        }
        return cardNumberViews;
    }

    private int getCardNumber(String cardNumberView) {
        int cardNumber;
        switch (cardNumberView) {
            case "T":
                cardNumber = 10;
                break;
            case "J":
                cardNumber = 11;
                break;
            case "Q":
                cardNumber = 12;
                break;
            case "K":
                cardNumber = 13;
                break;
            case "A":
                cardNumber = 14;
                break;
            default:
                cardNumber = Integer.valueOf(cardNumberView);
                break;
        }
        return cardNumber;
    }
}
