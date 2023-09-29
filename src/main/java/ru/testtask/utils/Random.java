package ru.testtask.utils;

/**
 * The type Random. Generate a random number, using {@link java.util.Random}
 */
public class Random {
    /**
     * Generate random num int in range from minNumber to maxNumber using {@linkplain java.util.Random#nextInt(int)}.
     *
     * @param minNumber the min number
     * @param maxNumber the max number
     * @return the int - random generating number
     */
    public static int generateRandomNum(int minNumber, int maxNumber) {
        java.util.Random random = new java.util.Random();
        return random.nextInt(maxNumber) + minNumber;
    }
}
