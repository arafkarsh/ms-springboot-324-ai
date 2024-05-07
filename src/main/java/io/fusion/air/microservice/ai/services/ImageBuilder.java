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
package io.fusion.air.microservice.ai.services;

import dev.langchain4j.data.image.Image;
import dev.langchain4j.model.image.ImageModel;
import dev.langchain4j.model.output.Response;
import io.fusion.air.microservice.ai.utils.AiBeans;
import io.fusion.air.microservice.ai.utils.AiConstants;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Image Builder
 * @author: Araf Karsh Hamid
 * @version:
 * @date:
 */
public class ImageBuilder {

    /**
     * Create Image
     * @param text
     * @return
     */
    public static Response<Image> createImage(String text) {
        ImageModel model = new AiBeans().createImageModel(AiConstants.DALL_E_3);
        Response<Image> response = model.generate(text);
        return response;
    }

    /**
     * Download Image
     * @param response
     */
    public static void downloadImage(Response<Image> response) {
        if (response.content().url() != null) {
            System.out.println("Image URL: " + response.content().url());
            try {
                downloadImageFromUrl(response.content().url().toString(), "downloaded_image.jpg");
            } catch (IOException e) {
                System.out.println("No image was returned.");
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("No image was returned.");
        }
    }

    /**
     * Download the image from the URL
     * @param imageUrl
     * @param fileName
     * @throws IOException
     */
    private static void downloadImageFromUrl(String imageUrl, String fileName) throws IOException {
        URL url = new URL(imageUrl);
        Path targetPath = Paths.get(fileName).toAbsolutePath();

        try (InputStream in = url.openStream();
             OutputStream out = new FileOutputStream(targetPath.toString())) {
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
            System.out.println("Image has been downloaded successfully to " + targetPath);
        }
    }
}
