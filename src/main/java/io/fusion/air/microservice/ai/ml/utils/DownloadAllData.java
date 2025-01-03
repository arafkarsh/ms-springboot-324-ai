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
package io.fusion.air.microservice.ai.ml.utils;

import io.fusion.air.microservice.utils.Std;
import org.apache.commons.io.FilenameUtils;
import org.nd4j.common.resources.Downloader;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Download All Training / Test Data for Machine Learning
 * URL: https://dl4jdata.blob.core.windows.net/dl4j-examples
 *
 *
 * @author: Araf Karsh Hamid
 * @version:
 * @date:
 */
public class DownloadAllData {

    public static String wordVectorsPath = "";

    public static final String DATA_PATH = FilenameUtils.concat(System.getProperty("java.io.tmpdir"), "dl4j_w2vSentiment/");


    /**
     * Download Google News Word 2 Vector Data (1.5 GB)
     * @throws IOException
     */
    public static void checkAndDownloadGoogleNewsWord2VectorData() throws IOException {
        String defaultwordVectorsPath = FilenameUtils.concat(System.getProperty("user.home"), "dl4j-examples-data/w2vec300");
        String md5w2vec = "1c892c4707a8a1a508b01a01735c0339";
        if (!new File(defaultwordVectorsPath).exists()) {
            Std.println("Creating Directory >> "+defaultwordVectorsPath);
            new File(defaultwordVectorsPath).mkdirs();
        }
        wordVectorsPath = new File(defaultwordVectorsPath, "GoogleNews-vectors-negative300.bin.gz").getAbsolutePath();
        if (new File(wordVectorsPath).exists()) {
            Std.println("\n\tGoogleNews-vectors-negative300.bin.gz file found at path: " + defaultwordVectorsPath);
            Std.println("\tChecking md5 of existing file..");
            if (Downloader.checkMD5OfFile(md5w2vec, new File(wordVectorsPath))) {
                Std.println("\tExisting file hash matches.");
                return;
            } else {
                Std.println("\tExisting file hash doesn't match. Retrying download...");
            }
        } else {
            Std.println("\n\tNo previous download of GoogleNews-vectors-negative300.bin.gz found at path: " + defaultwordVectorsPath);
        }
        Std.println("\tWARNING: GoogleNews-vectors-negative300.bin.gz is a 1.5GB file.");
        Std.println("Starting model download (1.5GB!)...");
        Downloader.download("Word2Vec",
                new URL("https://dl4jdata.blob.core.windows.net/resources/wordvectors/GoogleNews-vectors-negative300.bin.gz"),
                new File(wordVectorsPath), md5w2vec, 5);
        Std.println("Successfully downloaded word2vec model to " + wordVectorsPath);
    }

    /**
     * Download All DL4J Sample Data
     */
    public static void  downloadSampleData() throws Exception {
        Std.println("IRISDATA > "+DownloaderUtility.IRISDATA.download());
        Std.println("ANIMALS > "+DownloaderUtility.ANIMALS.download());
        Std.println("ANOMALYSEQUENCEDATA > "+DownloaderUtility.ANOMALYSEQUENCEDATA.download());
        Std.println("CAPTCHAIMAGE > "+DownloaderUtility.CAPTCHAIMAGE.download());
        Std.println("CLASSIFICATIONDATA > "+DownloaderUtility.CLASSIFICATIONDATA.download());
        Std.println("DATAEXAMPLES > "+DownloaderUtility.DATAEXAMPLES.download());
        Std.println("LOTTERYDATA > "+DownloaderUtility.LOTTERYDATA.download());
        Std.println("NEWSDATA > "+DownloaderUtility.NEWSDATA.download());
        Std.println("NLPDATA > "+DownloaderUtility.NLPDATA.download());
        Std.println("PREDICTGENDERDATA > "+DownloaderUtility.PREDICTGENDERDATA.download());
        Std.println("STYLETRANSFER > "+DownloaderUtility.STYLETRANSFER.download());
        Std.println("VIDEOEXAMPLE > "+DownloaderUtility.VIDEOEXAMPLE.download());
    }

    /**
     * Download all DL4J Sample Data for testing
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        // Download All DL4J Sample Data to your Home Directory
        // ~/dl4j-examples-data/
        // downloadSampleData();

        // Download Google News Word 2 Vector Sample Data - 1.5 GB File
        checkAndDownloadGoogleNewsWord2VectorData();

    }
}
