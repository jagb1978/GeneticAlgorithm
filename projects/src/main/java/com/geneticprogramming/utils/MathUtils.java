package com.geneticprogramming.utils;

/**
 * Math Utils Class
 */
public class MathUtils {
    public static final double FLOATING_POINT_COMPARISON_EPSILON = 0.0000001;

    public static boolean almostZero(double number) {
        return MathUtils.floatsAlmostEqualWithEpsilonCheckSign(number, 0.0, FLOATING_POINT_COMPARISON_EPSILON, false);
    }

    /**
     * This static method is to be used to compare floating point numbers
     * This is due to how floating point numbers are represented in binary, the actual
     * number used isn't always exactly as you expect.
     * <p>
     * The epsilon value is the level of accuracy you are happy with
     * <p>
     * This method checks that number 1 == number 2 with a epsilon value off either direction
     * <p>
     * If the numbers are 0.0 and -0.1 they are shown as equals
     * If the numbers signs are different then they are not equal
     * Otherwise we check the absolute value difference between the 2 values against the epsilon value
     *
     * @param number1   Number 1 to compare
     * @param number2   Number 2 to compare
     * @param epsilon   Level of accuracy we are happy with
     * @param checkSign Will check the sign of the 2 numbers as a short circuit check
     * @return Boolean to whether the values are equal or not
     */
    public static boolean floatsAlmostEqualWithEpsilonCheckSign(double number1, double number2, double epsilon, boolean checkSign) {
        //handle 0.0 and -0.0
        if (Double.compare(Math.ulp(number1), Double.MIN_VALUE) == 0 &&
                Double.compare(Math.ulp(number2), Double.MIN_VALUE) == 0) {
            return true;
        }

        //if signs are different then they are not the same
        if (checkSign && (int) Math.signum(number1) != (int) Math.signum(number2)) {
            return false;
        }

        return Math.abs(number1 - number2) < epsilon;
    }

    /**
     *
     *  This static method is used to compare floating point numbers
     *  Using the default epsilon value relative to the numbers being compared
     *
     * @param number1 Number 1 to compare
     * @param number2 Number 2 to compare
     * @return Boolean to whether the values are equal or not
     */
    public static boolean floatsAlmostEqual(double number1, double number2) {
        //create relative epsilon based on the numbers being compared
        double relativeEpsilon = MathUtils.FLOATING_POINT_COMPARISON_EPSILON * Math.max(Math.abs(number1), Math.abs(number2));

        //return whether they are almost equals
        return MathUtils.floatsAlmostEqualWithEpsilonCheckSign(number1, number2, relativeEpsilon, true);
    }

}
