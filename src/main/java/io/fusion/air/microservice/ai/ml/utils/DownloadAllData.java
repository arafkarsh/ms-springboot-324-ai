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

    public static void main(String[] args) throws Exception {

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
}
