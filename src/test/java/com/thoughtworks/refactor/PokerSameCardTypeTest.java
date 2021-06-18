package com.thoughtworks.refactor;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class PokerSameCardTypeTest {

    private static final String STRAIGHT_FLUSH_HIGH = "A2 K2 Q2 J2 T2";
    private static final String STRAIGHT_FLUSH_SAME_HIGH = "A1 K1 Q1 J1 T1";
    private static final String STRAIGHT_FLUSH_LOW = "K2 Q2 J2 T2 92";
    private static final String STRAIGHT_FLUSH_SAME_LOW = "K1 Q1 J1 T1 91";

    public static final String FOUR_OF_A_KIND_HIGH = "A1 A2 A3 A4 T3";
    public static final String FOUR_OF_A_KIND_LOW = "K1 K2 K3 K4 Q3";

    public static final String FULL_HOUSE_HIGH = "A1 A3 A4 J1 J3";
    public static final String FULL_HOUSE_LOW = "K1 K3 K4 Q1 Q3";

    public static final String FLUSH_HIGH = "A1 K1 Q1 J1 91";
    public static final String FLUSH_SAME_HIGH = "A3 K3 Q3 J3 93";
    public static final String FLUSH_LOW = "A2 Q2 J2 T2 92";
    public static final String FLUSH_SAME_LOW = "A4 Q4 J4 T4 94";

    public static final String STRAIGHT_HIGH = "A1 K1 Q1 J1 T3";
    public static final String STRAIGHT_SAME_HIGH = "A2 K2 Q2 J2 T4";
    public static final String STRAIGHT_LOW = "K2 Q2 J2 T2 91";
    public static final String STRAIGHT_SAME_LOW = "K3 Q3 J3 T3 94";

    public static final String THREE_OF_A_KIND_HIGH = "A1 A3 A4 91 83";
    public static final String THREE_OF_A_KIND_LOW = "K2 K3 K4 Q1 J3";

    public static final String TWO_PAIR_HIGH = "A1 A3 K4 K1 Q3";
    public static final String TWO_PAIR_FIRST_LOW = "K1 K3 Q4 Q1 J3";
    public static final String TWO_PAIR_SECOND_LOW = "A2 A4 Q4 Q1 J3";
    public static final String TWO_PAIR_SINGLE_LOW = "A1 A2 K2 K3 J3";
    public static final String TWO_PAIR_SAME_HIGH = "A3 A4 K2 K3 Q1";

    public static final String ONE_PAIR_HIGH = "A1 A3 K4 Q1 J3";
    public static final String ONE_PAIR_PAIR_LOW = "K1 K3 Q4 J1 T3";
    public static final String ONE_PAIR_SINGLE_LOW = "A2 A4 K2 Q2 T1";
    public static final String ONE_PAIR_SAME_HIGH = "A2 A4 K2 Q2 J1";

    public static final String HIGH_CARD_HIGH = "A1 K3 Q4 J1 83";
    public static final String HIGH_CARD_LOW = "A2 K4 Q2 T1 93";
    public static final String HIGH_CARD_SAME_HIGH = "A2 K4 Q2 J3 81";

    @DisplayName("The StraightFlush with higher card should win, or should tie")
    @ParameterizedTest(name = "should return {2} when compare black: {0} and white: {1}")
    @MethodSource("stringBothStraightFlushProvider")
    void test_compare_both_StraightFlush(String black, String white, String expectResult) {
        String actualResult = new Poker().compairResult(black, white);

        assertThat(actualResult).isEqualTo(expectResult);
    }

    static Stream<Arguments> stringBothStraightFlushProvider() {
        return Stream.of(
                Arguments.of(STRAIGHT_FLUSH_HIGH, STRAIGHT_FLUSH_LOW, "black wins - high card:A"),
                Arguments.of(STRAIGHT_FLUSH_LOW, STRAIGHT_FLUSH_HIGH, "white wins - high card:A"),
                Arguments.of(STRAIGHT_FLUSH_HIGH, STRAIGHT_FLUSH_SAME_HIGH, "tie"),
                Arguments.of(STRAIGHT_FLUSH_LOW, STRAIGHT_FLUSH_SAME_LOW, "tie")
        );
    }


    @DisplayName("The FourOfAKind with higher four cards should win")
    @ParameterizedTest(name = "should return {2} when compare black: {0} and white: {1}")
    @MethodSource("stringBothFourOfAKindProvider")
    void test_compare_both_FourOfAKind(String black, String white, String expectResult) {
        String actualResult = new Poker().compairResult(black, white);

        assertThat(actualResult).isEqualTo(expectResult);
    }

    static Stream<Arguments> stringBothFourOfAKindProvider() {
        return Stream.of(
                Arguments.of(FOUR_OF_A_KIND_HIGH, FOUR_OF_A_KIND_LOW, "black wins - high card:A"),
                Arguments.of(FOUR_OF_A_KIND_LOW, FOUR_OF_A_KIND_HIGH, "white wins - high card:A")
        );
    }

    @DisplayName("The FullHouse with higher three cards should win")
    @ParameterizedTest(name = "should return {2} when compare black: {0} and white: {1}")
    @MethodSource("stringBothFullHouseProvider")
    void test_compare_both_FullHouse(String black, String white, String expectResult) {
        String actualResult = new Poker().compairResult(black, white);

        assertThat(actualResult).isEqualTo(expectResult);
    }

    static Stream<Arguments> stringBothFullHouseProvider() {
        return Stream.of(
                Arguments.of(FULL_HOUSE_HIGH, FULL_HOUSE_LOW, "black wins - high card:A"),
                Arguments.of(FULL_HOUSE_LOW, FULL_HOUSE_HIGH, "white wins - high card:A")
        );
    }

    @DisplayName("The Flush with higher card should win, or should tie")
    @ParameterizedTest(name = "should return {2} when compare black: {0} and white: {1}")
    @MethodSource("stringBothFlushProvider")
    void test_compare_both_Flush(String black, String white, String expectResult) {
        String actualResult = new Poker().compairResult(black, white);

        assertThat(actualResult).isEqualTo(expectResult);
    }

    static Stream<Arguments> stringBothFlushProvider() {
        return Stream.of(
                Arguments.of(FLUSH_HIGH, FLUSH_LOW, "black wins - high card:K"),
                Arguments.of(FLUSH_LOW, FLUSH_HIGH, "white wins - high card:K"),
                Arguments.of(FLUSH_HIGH, FLUSH_SAME_HIGH, "tie"),
                Arguments.of(FLUSH_LOW, FLUSH_SAME_LOW, "tie")
        );
    }

    @DisplayName("The Straight with higher card should win, or should tie")
    @ParameterizedTest(name = "should return {2} when compare black: {0} and white: {1}")
    @MethodSource("stringBothStraightProvider")
    void test_compare_both_Straight(String black, String white, String expectResult) {
        String actualResult = new Poker().compairResult(black, white);

        assertThat(actualResult).isEqualTo(expectResult);
    }

    static Stream<Arguments> stringBothStraightProvider() {
        return Stream.of(
                Arguments.of(STRAIGHT_HIGH, STRAIGHT_LOW, "black wins - high card:A"),
                Arguments.of(STRAIGHT_LOW, STRAIGHT_HIGH, "white wins - high card:A"),
                Arguments.of(STRAIGHT_HIGH, STRAIGHT_SAME_HIGH, "tie"),
                Arguments.of(STRAIGHT_LOW, STRAIGHT_SAME_LOW, "tie")
        );
    }

    @DisplayName("The ThreeOfAKind with higher three cards should win")
    @ParameterizedTest(name = "should return {2} when compare black: {0} and white: {1}")
    @MethodSource("stringBothThreeOfAKindProvider")
    void test_compare_both_ThreeOfAKind(String black, String white, String expectResult) {
        String actualResult = new Poker().compairResult(black, white);

        assertThat(actualResult).isEqualTo(expectResult);
    }

    static Stream<Arguments> stringBothThreeOfAKindProvider() {
        return Stream.of(
                Arguments.of(THREE_OF_A_KIND_HIGH, THREE_OF_A_KIND_LOW, "black wins - high card:A"),
                Arguments.of(THREE_OF_A_KIND_LOW, THREE_OF_A_KIND_HIGH, "white wins - high card:A")
        );
    }

    @DisplayName("The TwoPair with higher two cards should win, or with higher simple card should win, or tie")
    @ParameterizedTest(name = "should return {2} when compare black: {0} and white: {1}")
    @MethodSource("stringBothTwoPairProvider")
    void test_compare_both_TwoPair(String black, String white, String expectResult) {
        String actualResult = new Poker().compairResult(black, white);

        assertThat(actualResult).isEqualTo(expectResult);
    }

    static Stream<Arguments> stringBothTwoPairProvider() {
        return Stream.of(
                Arguments.of(TWO_PAIR_HIGH, TWO_PAIR_FIRST_LOW, "black wins - high card:A"),
                Arguments.of(TWO_PAIR_FIRST_LOW, TWO_PAIR_HIGH, "white wins - high card:A"),
                Arguments.of(TWO_PAIR_HIGH, TWO_PAIR_SECOND_LOW, "black wins - high card:K"),
                Arguments.of(TWO_PAIR_SECOND_LOW, TWO_PAIR_HIGH, "white wins - high card:K"),
                Arguments.of(TWO_PAIR_HIGH, TWO_PAIR_SINGLE_LOW, "black wins - high card:Q"),
                Arguments.of(TWO_PAIR_SINGLE_LOW, TWO_PAIR_HIGH, "white wins - high card:Q"),
                Arguments.of(TWO_PAIR_HIGH, TWO_PAIR_SAME_HIGH, "tie"),
                Arguments.of(TWO_PAIR_SAME_HIGH, TWO_PAIR_HIGH, "tie")
        );
    }

    @DisplayName("The OnePair with higher two cards should win, or with higher simple card should win, or tie")
    @ParameterizedTest(name = "should return {2} when compare black: {0} and white: {1}")
    @MethodSource("stringBothOnePairProvider")
    void test_compare_both_OnePair(String black, String white, String expectResult) {
        String actualResult = new Poker().compairResult(black, white);

        assertThat(actualResult).isEqualTo(expectResult);
    }

    static Stream<Arguments> stringBothOnePairProvider() {
        return Stream.of(
                Arguments.of(ONE_PAIR_HIGH, ONE_PAIR_PAIR_LOW, "black wins - high card:A"),
                Arguments.of(ONE_PAIR_PAIR_LOW, ONE_PAIR_HIGH, "white wins - high card:A"),
                Arguments.of(ONE_PAIR_HIGH, ONE_PAIR_SINGLE_LOW, "black wins - high card:J"),
                Arguments.of(ONE_PAIR_SINGLE_LOW, ONE_PAIR_HIGH, "white wins - high card:J"),
                Arguments.of(ONE_PAIR_HIGH, ONE_PAIR_SAME_HIGH, "tie"),
                Arguments.of(ONE_PAIR_SAME_HIGH, ONE_PAIR_HIGH, "tie")
        );
    }

    @DisplayName("The HighCard with higher simple card should win, or tie")
    @ParameterizedTest(name = "should return {2} when compare black: {0} and white: {1}")
    @MethodSource("stringBothHighCardProvider")
    void test_compare_both_HighCard(String black, String white, String expectResult) {
        String actualResult = new Poker().compairResult(black, white);

        assertThat(actualResult).isEqualTo(expectResult);
    }

    static Stream<Arguments> stringBothHighCardProvider() {
        return Stream.of(
                Arguments.of(HIGH_CARD_HIGH, HIGH_CARD_LOW, "black wins - high card:J"),
                Arguments.of(HIGH_CARD_LOW, HIGH_CARD_HIGH, "white wins - high card:J"),
                Arguments.of(HIGH_CARD_HIGH, HIGH_CARD_SAME_HIGH, "tie"),
                Arguments.of(HIGH_CARD_SAME_HIGH, HIGH_CARD_HIGH, "tie")
        );
    }





}
