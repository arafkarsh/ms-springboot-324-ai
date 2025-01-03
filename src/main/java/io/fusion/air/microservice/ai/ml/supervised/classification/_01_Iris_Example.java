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
package io.fusion.air.microservice.ai.ml.supervised.classification;

import io.fusion.air.microservice.ai.ml.utils.DownloaderUtility;
// Deep Learning 4 J
import io.fusion.air.microservice.utils.Std;
import org.datavec.api.records.reader.RecordReader;
import org.datavec.api.records.reader.impl.csv.CSVRecordReader;
import org.datavec.api.split.FileSplit;
import org.deeplearning4j.datasets.datavec.RecordReaderDataSetIterator;
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.layers.DenseLayer;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.nn.weights.WeightInit;
import org.deeplearning4j.optimize.listeners.ScoreIterationListener;
import org.nd4j.evaluation.classification.Evaluation;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.dataset.SplitTestAndTrain;
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;
import org.nd4j.linalg.dataset.api.preprocessor.DataNormalization;
import org.nd4j.linalg.dataset.api.preprocessor.NormalizerStandardize;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.learning.config.Sgd;
import org.nd4j.linalg.lossfunctions.LossFunctions;

import java.io.File;
import java.util.Arrays;

/**
 * From Deep Learning 4 Java Examples
 * Reference Code: @author Adam Gibson
 *
 * @author: Araf Karsh Hamid
 * @version:
 * @date:
 */
public class _01_Iris_Example {

    /**
     * This code is a comprehensive example of using DL4J to create a neural network for
     * classification tasks, showcasing data handling, model configuration, training, and
     * evaluation processes.
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws  Exception {
        // Step 1: Loading the Dataset:
        RecordReader dataReader = loadData();
        // Step 2: Convert & Split Train & Test Data. 65% Training Data
        SplitTestAndTrain  allData = getAllData(dataReader, 0.65);
        DataSet trainingData = allData.getTrain();
        DataSet testData = allData.getTest();
        // Step 3: Data Normalization
        DataNormalization normalizer = normalizeData(trainingData,  testData);
        // Step 4: Create Multi Model Configuration
        // Defines the neural network architecture:
        // Input layer with 4 inputs.
        // Two hidden layers with 3 neurons each.
        // Output layer with 3 neurons (one for each class) and softmax activation function for classification.
        MultiLayerConfiguration config =  createMultiModelConfig();
        // Step 5: Create Multi-Layer-Network and Train data with 2000 iterations, with a iterationListener score of 200
        MultiLayerNetwork model = createAndRunMultiLayerNetworkModel(config,  trainingData,  1000, 200);
        // Step 6: Evaluate the Model
        evaluateModel( model,  testData);
        // Test with New Data, New iris flower measurements
        double[] newFlowerFeatures1 = {5.1, 3.5, 1.4, 0.2};
        predictFlowerClass( newFlowerFeatures1, normalizer,  model); // Expected Setosa
        double[] newFlowerFeatures2 = {7.1, 3.3, 1.9, 1.4};
        predictFlowerClass( newFlowerFeatures2, normalizer,  model); // Expected Versicolor
        double[] newFlowerFeatures3 = {5.9, 3.1, 5.2, 1.8};
        predictFlowerClass( newFlowerFeatures3, normalizer,  model); // Expected Virginica
    }

    /**
     * Step 1:
     * Load the Data for Training and Testing
     * @return
     * @throws Exception
     */
    private static RecordReader loadData() throws Exception {
        // Step 1: Loading the Dataset:
        Std.println("Step 1: Load Data >> iris.txt .....  AND Return RecordReader.");
        int numLinesToSkip = 0;
        char delimiter = ',';
        RecordReader recordReader = new CSVRecordReader(numLinesToSkip, delimiter);
        recordReader.initialize(new FileSplit(new File(DownloaderUtility.IRISDATA.download(), "iris.txt")));
        return recordReader;
    }

    /**
     * Step 2:
     * Convert and Split the Training Data
     * Default Percentage is 65% (0.65)
     *
     * @param recordReader
     * @param splitPercentage
     * @return
     */
    private static SplitTestAndTrain getAllData(RecordReader recordReader, double splitPercentage) {
        // Step 2: Split and Train Data
        //  Converts the parsed data into DataSet objects and splits the dataset into training and testing sets.
        Std.println("Step 2: Convert Data >> iris .....  AND Split into Training ("+(splitPercentage*100)+"%) and Test Data");
        // 5 values in each row of the iris.txt CSV: 4 input features followed by an integer label (class) index.
        // Labels are the 5th value (index 4) in each row
        int labelIndex = 4;
        // 3 classes (types of iris flowers) in the iris data set. Classes have integer values 0, 1 or 2
        int numClasses = 3;
        // Iris data set: 150 examples total. We are loading all of them into one DataSet (not recommended for large data sets)
        int batchSize = 150;

        DataSetIterator iterator = new RecordReaderDataSetIterator(recordReader, batchSize, labelIndex, numClasses);
        DataSet allData = iterator.next();
        // Shuffles the data to ensure that it is randomly ordered, which helps in training by avoiding any bias that might be
        // present in the order of the data.
        allData.shuffle();
        // splitTestAndTrain(splitPercentage): Splits the dataset into training and testing sets based on the splitPercentage.
        // For example, if splitPercentage is 0.65, 65% of the data will be used for training, and 35% for testing.
        SplitTestAndTrain testAndTrain = allData.splitTestAndTrain(splitPercentage);
        return testAndTrain;
    }

    /**
     * Step 3:
     * Normalize the training and test data
     * Data Normalization:
     * 	•	The training data is used to calculate normalization parameters (mean and standard deviation).
     * 	•	Both the training and testing sets are normalized using these parameters to ensure that the
     * 	    data fed into the neural network has zero mean and unit variance, which helps in faster and
     * 	    more stable training.
     *
     * By following these steps, the code ensures that the data is properly prepared and normalized before being
     * fed into the neural network for training and evaluation. This process helps in achieving better performance
     * and more reliable results from the neural network model.
     *
     * @param trainingData
     * @param testData
     */
    private static DataNormalization normalizeData(DataSet trainingData, DataSet testData) {
        // Step 3: Data Normalization
        Std.println("Step 3: Normalize Data >> iris .....  Training and Test Data");
        // Creates an instance of a standard normalizer which standardizes the data (zero mean and unit variance).
        DataNormalization normalizer = new NormalizerStandardize();
        // Computes the mean and standard deviation from the training data. This step collects the
        // statistics necessary for normalization but does not alter the data.
        normalizer.fit(trainingData);
        //  Applies normalization to the training data using the computed statistics.
        normalizer.transform(trainingData);
        // Applies the same normalization to the testing data using the statistics from the training
        // data. This ensures that the test data is normalized in the same way as the training data.
        normalizer.transform(testData);
        return normalizer;
    }

    /**
     * Step 4:
     * Model Configuration
     * •	Input Layer: 4 neurons (input features).
     * •	Hidden Layer 1: Dense layer with 3 neurons, tanh activation.
     * •	Hidden Layer 2: Dense layer with 3 neurons, tanh activation.
     * •	Output Layer: Dense layer with 3 neurons, softmax activation, and Negative Log-Likelihood loss function.
     *
     *  This configuration sets up a simple neural network designed to classify iris flowers into one of three classes based on four input features.
     *  The architecture, along with chosen activation functions, weight initialization, and optimizer, aims to provide a balanced and effective training process.
     *
     * Returns the MultiLayerConfiguration
     * @return
     */
    private static MultiLayerConfiguration createMultiModelConfig() {
        Std.println("Step 4: Setup Model Configuration >> iris .....  Input Layer, 2 Hidden Layer (3 Neurons), Output Layer (3 Neurons)");
        final int numInputs = 4; // The number of input features for each sample in the Iris dataset (sepal length, sepal width, petal length, petal width).
        int outputNum = 3;       // The number of output classes in the Iris dataset (Setosa, Versicolor, Virginica).
        long seed = 6;             // A seed value for random number generation, ensuring reproducibility of the results.
        // Creates a new model for configuring the neural network.
        MultiLayerConfiguration conf = new NeuralNetConfiguration.Builder()
                // Sets the random seed to ensure the initialization and training process can be reproduced.
                .seed(seed)
                // Sets the activation function for neurons globally to hyperbolic tangent (tanh). This function helps introduce non-linearity into the model.
                .activation(Activation.TANH)
                // Uses Xavier initialization for weights. This technique helps in maintaining the variance of activations through layers, aiding in effective training.
                .weightInit(WeightInit.XAVIER)
                // Sets the optimizer to Stochastic Gradient Descent (SGD) with a learning rate of 0.1. SGD is an optimization algorithm used to minimize the loss function.
                .updater(new Sgd(0.1))
                // Applies L2 regularization with a coefficient of 0.0001. Regularization helps to prevent overfitting by penalizing large weights.
                .l2(1e-4)
                // Starts the configuration of the layers in the network.
                .list()
                // •	Adds a dense (fully connected) layer with:
                //	•	nIn(numInputs): 4 input neurons (corresponding to the 4 features of the Iris dataset).
                //	•	nOut(3): 3 output neurons (arbitrary choice for the first layer’s output size).
                //	•	Uses the global activation function (tanh).
                .layer(new DenseLayer.Builder().nIn(numInputs).nOut(3).build())
                // •	Adds another dense layer with:
                //	•	nIn(3): 3 input neurons (from the previous layer’s output).
                //	•	nOut(3): 3 output neurons (arbitrary choice for the second layer’s output size).
                //	•	Also uses the global activation function (tanh).
                .layer(new DenseLayer.Builder().nIn(3).nOut(3).build())
                // Output Layer
                // •	Adds an output layer with:
                //	•	LossFunctions.LossFunction.NEGATIVELOGLIKELIHOOD: Sets the loss function to Negative Log-Likelihood, suitable for classification tasks.
                //	•	activation(Activation.SOFTMAX): Overrides the global tanh activation with softmax for this layer. Softmax is used to output a
                //  	probability distribution across the classes.
                //	•	nIn(3): 3 input neurons (from the previous layer’s output).
                //	•	nOut(outputNum): 3 output neurons (one for each class in the Iris dataset).
                .layer(new OutputLayer.Builder(LossFunctions.LossFunction.NEGATIVELOGLIKELIHOOD)
                        .activation(Activation.SOFTMAX)
                        .nIn(3).nOut(outputNum).build())
                .build();
        return conf;
    }

    /**
     * Step 5:
     * Create the Model
     * Run the Model with the specified set of iterations (Default 1000) on the training data
     * Returns the Model
     *
     * @param conf
     * @param trainingData
     * @param iterations
     * @param iterationListenerScore
     * @return
     */
    private static MultiLayerNetwork createAndRunMultiLayerNetworkModel(
            MultiLayerConfiguration conf,  DataSet trainingData, int iterations, int iterationListenerScore) {
        // Step 5: Run the model
        // Model Initialization and Training:
        Std.println("Step 5: Run Model >> iris .....  "+iterations+" iterations with the training data set");
        // This line creates a new instance of a MultiLayerNetwork using the configuration conf which was previously
        // defined (this config would specify the architecture, activation functions, loss functions, etc. of the neural network).
        MultiLayerNetwork model = new MultiLayerNetwork(conf);
        model.init();
        // This line sets up a listener to monitor and log the model’s performance. The ScoreIterationListener
        // logs the score (or loss) of the model every iterationListenerScore iterations (in this case, every 100 iterations).
        // This helps in tracking the training progress and diagnosing potential issues.
        model.setListeners(new ScoreIterationListener(iterationListenerScore));
        // Initializes the model and trains it for (1000) iterations using the training data.
        for (int i = 0; i < iterations; i++) {
            // This line trains the model using the trainingData. During each iteration, the model updates its weights
            // and biases based on the training data to minimize the loss function. The fit method processes the entire
            // training dataset once in each iteration.
            model.fit(trainingData);
        }
        // This line returns the trained MultiLayerNetwork model. After training is complete, the model is ready
        // for evaluation or inference.
        return model;
    }

    /**
     * Step 6:
     * Evaluate the Model
     * Print the Evaluation and Confusion Matrix
     *
     * @param model
     * @param testData
     */
    private static void evaluateModel(MultiLayerNetwork model, DataSet testData) {
        // Evaluates the model’s performance on the test data and logs the evaluation statistics.
        // Evaluate the model on the test set
        Std.println("Step 6: Evaluate Model >> iris .....  with Test data and print stats");
        Evaluation eval = new Evaluation(3);
        INDArray output = model.output(testData.getFeatures());
        eval.eval(testData.getLabels(), output);
        Std.println("Step 6: Results:\n"+eval.stats());
        Std.println("Training Process Completed...... >>>>>>> ------------------------------");
    }

    /**
     * Predict Flower Class
     *
     * @param normalizer
     * @param model
     */
    private static void predictFlowerClass(
            double[] newFlowerFeatures, DataNormalization normalizer, MultiLayerNetwork model) {
        // Convert the features to an INDArray (required by DL4J)
        INDArray input = Nd4j.create(newFlowerFeatures).reshape(1, -1);  // Reshape to [1, 4]
        // Assume `normalizer` was previously fitted on the training data
        normalizer.transform(input);
        // Use the trained model to predict the class probabilities
        INDArray output = model.output(input);
        // Get the predicted class (the index of the highest probability)
        int predictedClass = Nd4j.argMax(output, 1).getInt(0);
        // Map the predicted class index to the actual class label
        String[] classLabels = {"Setosa", "Versicolor", "Virginica"};
        String predictedLabel = classLabels[predictedClass];
        // Print the predicted label
        Std.println("Input = "+Arrays.toString(newFlowerFeatures)+" >> Predicted Label: " + predictedLabel);
    }
}
