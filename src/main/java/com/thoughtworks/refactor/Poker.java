package com.thoughtworks.refactor;

import java.util.*;

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
        return noOrRepeatNumber(handsNumbers, 1);
    }

    private int[] getRepeatNumbers(int[] handsNumbers) {
        return noOrRepeatNumber(handsNumbers, 0);
    }

    private String showCard(int cardNumber) {
        String[] cardViews = {"2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K", "A"};
        return cardViews[cardNumber - 2];
    }

    private int[] descendingSort(int[] handsNumbers) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < handsNumbers.length; i++) {
            if (map.get(handsNumbers[i]) != null) {
                map.put(handsNumbers[i], map.get(handsNumbers[i]) + 1);
            } else {
                map.put(handsNumbers[i], 1);
            }
        }
        List<Map.Entry<Integer, Integer>> list = new ArrayList<Map.Entry<Integer, Integer>>();
        list.addAll(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
            public int compare(Map.Entry<Integer, Integer> arg0, Map.Entry<Integer, Integer> arg1) {
                return arg1.getValue().compareTo(arg0.getValue());
            }
        });
        int[] arrayresult = new int[list.size()];
        int i = 0;
        for (Map.Entry<Integer, Integer> entry : list) {
            arrayresult[i] = entry.getKey();
            i++;
        }
        return arrayresult;
    }

    //先获得数组中每个元素出现的次数，然后再进行计算出现次数大于1的和出现次数等于1的
    private int[] noOrRepeatNumber(int[] handsNumbers, int flag) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < handsNumbers.length; i++) {
            if (map.get(handsNumbers[i]) != null) {
                map.put(handsNumbers[i], map.get(handsNumbers[i]) + 1);
            } else {
                map.put(handsNumbers[i], 1);
            }
        }
        List<Map.Entry<Integer, Integer>> list = new ArrayList<Map.Entry<Integer, Integer>>();
        list.addAll(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
            public int compare(Map.Entry<Integer, Integer> arg0, Map.Entry<Integer, Integer> arg1) {
                return arg1.getValue().compareTo(arg0.getValue());
            }
        });
        int[] repeatnumber = new int[list.size()];
        int[] norepeatnumber = new int[list.size()];
        int i = 0;
        if (flag == 0) {
            for (Map.Entry<Integer, Integer> entry : list) {
                if (entry.getValue() > 1) {
                    repeatnumber[i] = entry.getKey();
                    i++;
                }
            }
        } else {
            for (Map.Entry<Integer, Integer> entry : list) {
                if (entry.getValue() == 1) {
                    norepeatnumber[i] = entry.getKey();
                    i++;
                }
            }
        }
        HashSet<Integer> hashSet = new HashSet<Integer>();
        if (flag == 0) {
            for (i = 0; i < repeatnumber.length; i++) {
                hashSet.add(repeatnumber[i]);
            }
        } else {
            for (i = 0; i < norepeatnumber.length; i++) {
                hashSet.add(norepeatnumber[i]);
            }
        }
        hashSet.remove(0);
        int[] result = new int[hashSet.size()];
        i = 0;
        Iterator<Integer> iterator = hashSet.iterator();
        while (iterator.hasNext()) {
            result[i] = iterator.next();
            i++;
        }
        int[] reResult = new int[result.length];
        for (i = 0; i < result.length; i++) {
            reResult[i] = result[result.length - i - 1];
        }
        return reResult;
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
        String[] strArray = hands.split("");
        int[] number = getHandsNumbers(hands);
        int i;
        String[] suit = new String[5];
        for (i = 0; i < 5; i++) {
            suit[i] = strArray[i * 3 + 1];
        }
        HashSet<Integer> hashSetNumber = new HashSet<Integer>();
        for (i = 0; i < 5; i++) {
            hashSetNumber.add(number[i]);
        }
        HashSet<String> suits = new HashSet<String>();
        for (i = 0; i < 5; i++) {
            suits.add(suit[i]);
        }
        if (hashSetNumber.size() == 5) {
            if ((number[0] - number[4] == 4) && (suits.size() == 1) && (hashSetNumber.size() == 5)) { //五个相邻的数字且花色一样——同花顺
                handsCategory = "StraightFlush";
            } else if (number[0] - number[4] == 4 && (hashSetNumber.size() == 5)) { //五个相邻数字——顺子
                handsCategory = "Straight";
            } else if (suits.size() == 1) { //同一花色——同花
                handsCategory = "Flush";
            } else { //五个不相邻的数字——散牌
                handsCategory = "HighCard";
            }
        } else if (hashSetNumber.size() == 4) { //一对相同，其余三个数字不同——对子
            handsCategory = "OnePair";
        } else if (hashSetNumber.size() == 3) {
            if ((number[0] == number[1] && number[2] == number[3]) || (number[1] == number[2] && number[3] == number[4]) || (number[0] == number[1] && number[3] == number[4])) { //两对
                handsCategory = "TwoPair";
            } else { //三个数字相同，另外两个数字不同——三条
                handsCategory = "ThreeOfAKind";
            }
        } else {
            if (number[0] != number[1] || number[3] != number[4]) { //三个数字相同，另外两个数字相同——葫芦
                handsCategory = "FourOfAKind";
            } else { //四个数字相同——铁支
                handsCategory = "FullHouse";
            }
        }
        return handsCategory;
    }

    //数字转化并将其从大到小排序
    private int[] getHandsNumbers(String hands) {
        int[] number = new int[5];
        String[] strArray = hands.split("");
        int i;
        for (i = 0; i < 5; i++) {
            String c = strArray[i * 3];
            switch (c) {
                case "T":
                    number[i] = 10;
                    break;
                case "J":
                    number[i] = 11;
                    break;
                case "Q":
                    number[i] = 12;
                    break;
                case "K":
                    number[i] = 13;
                    break;
                case "A":
                    number[i] = 14;
                    break;
                default:
                    number[i] = Integer.valueOf(c);
                    break;
            }
        }

        Arrays.sort(number);
        int[] renumber = new int[number.length];
        for (i = 0; i < number.length; i++) {
            renumber[i] = number[number.length - i - 1];
        }
        return renumber;
    }
}
