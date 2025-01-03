/**
 * Copyright (c) 2025 Araf Karsh Hamid
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * <p>
 * This program and the accompanying materials are dual-licensed under
 * either the terms of the Eclipse Public License v1.0 as published by
 * the Eclipse Foundation
 * <p>
 * or (per the licensee's choosing)
 * <p>
 * under the terms of the Apache 2 License version 2.0
 * as published by the Apache Software Foundation.
 */
package io.fusion.air.microservice.ai.genai.utils;

import io.fusion.air.microservice.utils.Std;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * ms-springboot-324-ai / AiUtils
 *
 * @author: Araf Karsh Hamid
 * @version: 0.1
 * @date: 2025-01-03T12:50 PM
 */
public class AiUtils {

    private AiUtils() {
    }

    public static final String ERROR_MESSAGE = "Error: ";

    /**
     * Validate the Hours Calculation
     */
    public static void validateCalc() {
        LocalDateTime startTime = LocalDateTime.of(1970, 2, 7, 6, 0);
        LocalDateTime endTime = LocalDateTime.of(1980, 6, 2, 11, 0);
        LocalDateTime endTime2 = LocalDateTime.of(2024, 3, 11, 12, 0);
        // Calculate the difference in hours
        long hoursBetween = ChronoUnit.HOURS.between(startTime, endTime);
        long hoursBetween2 = ChronoUnit.HOURS.between(endTime, endTime2);
        long sumTotal = sumOfDigits(hoursBetween);
        long sumTotal2 = sumOfDigits(hoursBetween2);

        // Output the result
        Std.println(
                "Actual Hours between: 1970-02-07T06:00 & 1980-06-02T11:00 => " + hoursBetween
                        + "  Sum = "+sumTotal+ " Is prime = "+isPrime(sumTotal) + ", Days = "+hoursBetween / 24);
        Std.println(
                "Actual Hours between: 1980-06-02T11:00 & 2024-03-11T12:00 => " + hoursBetween2
                        + " Sum = "+sumTotal2+ " Is prime = "+isPrime(sumTotal2) + ", Days = "+hoursBetween2 / 24);
    }

    /**
     * Sum Digits
     * @param number
     * @return
     */
    public static long sumOfDigits(long number) {
        long sum = 0;
        while (number > 0) {
            sum += number % 10;
            number /= 10;
        }
        return sum;
    }

    /**
     * Find Prime
     * @param number
     * @return
     */
    public static boolean isPrime(long number) {
        if (number <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
