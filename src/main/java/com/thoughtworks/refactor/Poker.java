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
        int[] blackHandsNumbers = getHandsNumbers(blackHands);
        int[] whiteHandsNumbers = getHandsNumbers(whiteHands);
        int blackHandsCategoryIndex = getHandsCategoryIndex(blackHandsCategory);
        int whiteHandsCategoryIndex = getHandsCategoryIndex(whiteHandsCategory);
        int[] descendingBlackHandsNumbers = descendingSort(blackHandsNumbers);
        int[] descendingWhiteHandsNumbers = descendingSort(whiteHandsNumbers);
        int[] repeatBlackHandsNumbers = getRepeatNumbers(blackHandsNumbers);
        int[] repeatWhiteHandsNumbers = getRepeatNumbers(whiteHandsNumbers);
        int[] noRepeatBlackHandsNumbers = getNoRepeatNumbers(blackHandsNumbers);
        int[] noRepeatWhiteHandsNumbers = getNoRepeatNumbers(whiteHandsNumbers);
        if (blackHandsCategoryIndex < whiteHandsCategoryIndex) {
            winResult = "black wins - " + handsCategories[blackHandsCategoryIndex];
        } else if (blackHandsCategoryIndex > whiteHandsCategoryIndex) {
            winResult = "white wins - " + handsCategories[whiteHandsCategoryIndex];
        } else {
            if (blackHandsCategoryIndex == 0) { //同花顺
                if (blackHandsNumbers[0] < whiteHandsNumbers[0]) {
                    String sig = showCard(whiteHandsNumbers[0]);
                    winResult = "white wins - high card:" + sig;
                } else if (blackHandsNumbers[0] > whiteHandsNumbers[0]) {
                    String sig = showCard(blackHandsNumbers[0]);
                    winResult = "black wins - high card:" + sig;
                } else {
                    winResult = "tie";
                }
            } else if (blackHandsCategoryIndex == 1) { //铁支
                if (descendingBlackHandsNumbers[0] < descendingWhiteHandsNumbers[0]) {
                    String sig = showCard(descendingWhiteHandsNumbers[0]);
                    winResult = "white wins - high card:" + sig;
                } else {
                    String sig = showCard(descendingBlackHandsNumbers[0]);
                    winResult = "black wins - high card:" + sig;
                }
            } else if (blackHandsCategoryIndex == 2) { //葫芦
                if (descendingBlackHandsNumbers[0] < descendingWhiteHandsNumbers[0]) {
                    String sig = showCard(descendingWhiteHandsNumbers[0]);
                    winResult = "white wins - high card:" + sig;
                } else {
                    String sig = showCard(descendingBlackHandsNumbers[0]);
                    winResult = "black wins - high card:" + sig;
                }
            } else if (blackHandsCategoryIndex == 3) { //同花
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
            } else if (blackHandsCategoryIndex == 4) { //顺子
                if (blackHandsNumbers[0] < whiteHandsNumbers[0]) {
                    String sig = showCard(whiteHandsNumbers[0]);
                    winResult = "white wins - high card:" + sig;
                } else if (blackHandsNumbers[0] > whiteHandsNumbers[0]) {
                    String sig = showCard(blackHandsNumbers[0]);
                    winResult = "black wins - high card:" + sig;
                } else {
                    winResult = "tie";
                }
            } else if (blackHandsCategoryIndex == 5) { //三条
                if (repeatBlackHandsNumbers[0] < repeatWhiteHandsNumbers[0]) {
                    String sig = showCard(repeatWhiteHandsNumbers[0]);
                    winResult = "white wins - high card:" + sig;
                } else {
                    String sig = showCard(repeatBlackHandsNumbers[0]);
                    winResult = "black wins - high card:" + sig;
                }
            } else if (blackHandsCategoryIndex == 6) { //两对
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
            } else if (blackHandsCategoryIndex == 7) { //对子
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

    private int getHandsCategoryIndex(String handsCategory) {
        int index = -1;
        String[] handsCategories = {"StraightFlush", "FourOfAKind", "FullHouse", "Flush", "Straight", "ThreeOfAKind", "TwoPair", "OnePair", "HighCard"};
        for (int i = 0; i < 9; i++) {
            if (handsCategories[i].equals(handsCategory)) {
                index = i;
            }
        }
        return index;
    }

    //判断是什么牌
    private String getHandsCategory(String hands) {
        String handsCategory = "";
        int[] number = getHandsNumbers(hands);

        if (isStraightFlush(hands, number)) { //五个相邻的数字且花色一样——同花顺
            return "StraightFlush";
        }
        if (isStraight(number)) { //五个相邻数字——顺子
            return "Straight";
        }
        if (isFlush(hands, number)) { //同一花色——同花
            return "Flush";
        } else if (isHighCard(number)) { //五个不相邻的数字——散牌
            handsCategory = "HighCard";
        } else if (isOnePair(number)) { //一对相同，其余三个数字不同——对子
            handsCategory = "OnePair";
        } else  if (isTwoPair(number)) { //两对
            handsCategory = "TwoPair";
        } else if (isThreeOfAKind(number)) { //三个数字相同，另外两个数字不同——三条
            handsCategory = "ThreeOfAKind";
        } if (isFourOfAKind(number)) { //三个数字相同，另外两个数字相同——葫芦
            handsCategory = "FourOfAKind";
        } else if (isFullHouse(number)) { //四个数字相同——铁支
            handsCategory = "FullHouse";
        }
        return handsCategory;
    }

    private boolean isFullHouse(int[] number) {
        return getDistinctHandsNumbers(number).size() == 2 || getDistinctHandsNumbers(number).size() == 1;
    }

    private boolean isFourOfAKind(int[] number) {
        return (getDistinctHandsNumbers(number).size() == 2 || getDistinctHandsNumbers(number).size() == 1) && (number[0] != number[1] || number[3] != number[4]);
    }

    private boolean isThreeOfAKind(int[] number) {
        return getDistinctHandsNumbers(number).size() == 3;
    }

    private boolean isTwoPair(int[] number) {
        return getDistinctHandsNumbers(number).size() == 3 && ((number[0] == number[1] && number[2] == number[3]) || (number[1] == number[2] && number[3] == number[4]) || (number[0] == number[1] && number[3] == number[4]));
    }

    private boolean isOnePair(int[] number) {
        return getDistinctHandsNumbers(number).size() == 4;
    }

    private boolean isHighCard(int[] number) {
        return getDistinctHandsNumbers(number).size() == 5;
    }

    private boolean isFlush(String hands, int[] number) {
        return getHandsSuit(hands.split("")).size() == 1 && getDistinctHandsNumbers(number).size() == 5;
    }

    private boolean isStraight(int[] number) {
        return number[0] - number[4] == 4 && (getDistinctHandsNumbers(number).size() == 5);
    }

    private boolean isStraightFlush(String hands, int[] number) {
        return (number[0] - number[4] == 4) && (getHandsSuit(hands.split("")).size() == 1) && (getDistinctHandsNumbers(number).size() == 5);
    }

    private HashSet<String> getHandsSuit(String[] strArray) {
        int i;
        String[] suit = new String[5];
        for (i = 0; i < 5; i++) {
            suit[i] = strArray[i * 3 + 1];
        }
        HashSet<String> suits = new HashSet<String>();
        for (i = 0; i < 5; i++) {
            suits.add(suit[i]);
        }
        return suits;
    }

    private HashSet<Integer> getDistinctHandsNumbers(int[] number) {
        int i;
        HashSet<Integer> distinctNumbers = new HashSet<Integer>();
        for (i = 0; i < 5; i++) {
            distinctNumbers.add(number[i]);
        }
        return distinctNumbers;
    }

    //数字转化并将其从大到小排序
    private int[] getHandsNumbers(String hands) {
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
