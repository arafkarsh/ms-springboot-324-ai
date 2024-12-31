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
            System.out.println("Creating Directory >> "+defaultwordVectorsPath);
            new File(defaultwordVectorsPath).mkdirs();
        }
        wordVectorsPath = new File(defaultwordVectorsPath, "GoogleNews-vectors-negative300.bin.gz").getAbsolutePath();
        if (new File(wordVectorsPath).exists()) {
            System.out.println("\n\tGoogleNews-vectors-negative300.bin.gz file found at path: " + defaultwordVectorsPath);
            System.out.println("\tChecking md5 of existing file..");
            if (Downloader.checkMD5OfFile(md5w2vec, new File(wordVectorsPath))) {
                System.out.println("\tExisting file hash matches.");
                return;
            } else {
                System.out.println("\tExisting file hash doesn't match. Retrying download...");
            }
        } else {
            System.out.println("\n\tNo previous download of GoogleNews-vectors-negative300.bin.gz found at path: " + defaultwordVectorsPath);
        }
        System.out.println("\tWARNING: GoogleNews-vectors-negative300.bin.gz is a 1.5GB file.");
        System.out.println("Starting model download (1.5GB!)...");
        Downloader.download("Word2Vec",
                new URL("https://dl4jdata.blob.core.windows.net/resources/wordvectors/GoogleNews-vectors-negative300.bin.gz"),
                new File(wordVectorsPath), md5w2vec, 5);
        System.out.println("Successfully downloaded word2vec model to " + wordVectorsPath);
    }

    /**
     * Download All DL4J Sample Data
     */
    public static void  downloadSampleData() throws Exception {
        System.out.println("IRISDATA > "+DownloaderUtility.IRISDATA.download());
        System.out.println("ANIMALS > "+DownloaderUtility.ANIMALS.download());
        System.out.println("ANOMALYSEQUENCEDATA > "+DownloaderUtility.ANOMALYSEQUENCEDATA.download());
        System.out.println("CAPTCHAIMAGE > "+DownloaderUtility.CAPTCHAIMAGE.download());
        System.out.println("CLASSIFICATIONDATA > "+DownloaderUtility.CLASSIFICATIONDATA.download());
        System.out.println("DATAEXAMPLES > "+DownloaderUtility.DATAEXAMPLES.download());
        System.out.println("LOTTERYDATA > "+DownloaderUtility.LOTTERYDATA.download());
        System.out.println("NEWSDATA > "+DownloaderUtility.NEWSDATA.download());
        System.out.println("NLPDATA > "+DownloaderUtility.NLPDATA.download());
        System.out.println("PREDICTGENDERDATA > "+DownloaderUtility.PREDICTGENDERDATA.download());
        System.out.println("STYLETRANSFER > "+DownloaderUtility.STYLETRANSFER.download());
        System.out.println("VIDEOEXAMPLE > "+DownloaderUtility.VIDEOEXAMPLE.download());
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
