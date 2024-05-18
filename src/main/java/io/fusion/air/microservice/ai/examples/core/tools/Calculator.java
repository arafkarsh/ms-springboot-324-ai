/**
 * (C) Copyright 2024 Araf Karsh Hamid
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.fusion.air.microservice.ai.examples.core.tools;

import dev.langchain4j.agent.tool.Tool;

/**
 * @author: Araf Karsh Hamid
 * @version:
 * @date:
 */
public class Calculator {

    @Tool("Calculates the length of a string")
    public int stringLength(String s) {
        System.out.println("Called stringLength() with s='" + s + "'");
        return s.length();
    }

    @Tool("Calculates the sum of two numbers")
    public int add(int a, int b) {
        System.out.println("Called add() with a=" + a + ", b=" + b);
        return a + b;
    }

    @Tool("Calculates the square root of a number")
    public double sqrt(int x) {
        System.out.println("Called sqrt() with x=" + x);
        return Math.sqrt(x);
    }
}
