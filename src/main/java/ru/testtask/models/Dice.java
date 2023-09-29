package ru.testtask.models;

/**
 * The type Dice, which is described in the condition, has a minimum number {@linkplain Dice#MIN_NUMBER} (conditionally 1) and a maximum number {@linkplain Dice#MAX_NUMBER} (conditionally 6) that the die throws.
 * The use of its throwing is utilized using a special method {@link ru.testtask.utils.Random#generateRandomNum(int, int)} in the class {@link ru.testtask.utils.Random}.
 */
public class Dice {
    /**
     * The constant MIN_NUMBER, conditionally 1.
     */
    public static final int MIN_NUMBER = 1;
    /**
     * The constant MAX_NUMBER, conditionally 6.
     */
    public static final int MAX_NUMBER = 6;

}
