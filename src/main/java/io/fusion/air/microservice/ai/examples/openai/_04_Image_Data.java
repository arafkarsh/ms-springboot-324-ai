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
package io.fusion.air.microservice.ai.examples.openai;

import io.fusion.air.microservice.ai.services.ImageBuilder;

/**
 * Create a Dynamic Image based on the User Input
 *
 * @author: Araf Karsh Hamid
 * @version:
 * @date:
 */
public class _04_Image_Data {

    public static void main(String[] args) {

        String kerala = """
                Kerala landscape, with backwaters, boats and on the one side of the river
                bank leading into the forest with elephants and deer and the other side with
                Dancers in Kathakali costumes along with chendamelam.
                """;

        String conf = """
                Create a Conference hall with speaker talking about Data Mesh technology.
                Screen contains an Architecture diagram of Data Mesh. 
                Around 20-30 participants curious about the Data Mesh Concept. 
                The Speaker is wearing a Red T-Shirt, completely bald with a thick beard. 
                """;

        ImageBuilder.downloadImage(ImageBuilder.createImage(kerala));
    }
}
