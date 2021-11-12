package es.uam.eps.irg.rs.trec.eval.test;

import es.uam.eps.irg.rs.trec.eval.EvalUtils;
import es.uam.eps.irg.rs.trec.eval.TrecRecommenderEvaluator;
import es.uam.eps.irg.rs.trec.eval.TrecRecommenderEvaluatorFactory;
import es.uam.eps.irg.rs.trec.eval.file.persistent.RecommenderResultFromFileInMemory;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.recommender.ItemUserAverageRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

/**
 *
 * Main class with examples of how to invoke the different evaluators
 *
 * @author Alejandro
 */
public class Main {

    private Recommender[] recommenders;
    private String[] names;
    private Map<Long, Map<Long, Float>> trainMap;
    private Map<Long, Map<Long, Float>> testMap;

    private void evaluateFromMahout() throws IOException, TasteException {
        recommenders = new Recommender[2];
        names = new String[2];
        trainMap = new HashMap<Long, Map<Long, Float>>();
        testMap = new HashMap<Long, Map<Long, Float>>();
        initForEvaluateFromMahout(recommenders, names, trainMap, testMap);
        evaluate();
    }

    private void initForEvaluateFromMahout(Recommender[] recs, String[] names,
            Map<Long, Map<Long, Float>> trainMap, Map<Long, Map<Long, Float>> testMap) throws IOException, TasteException {
        // open the files
        DataModel train = new FileDataModel(new File("res/train.txt"));
        DataModel test = new FileDataModel(new File("res/test.txt"));
        // choose a recommender
        ItemUserAverageRecommender iuAvg = new ItemUserAverageRecommender(train);
        UserSimilarity similarity = new PearsonCorrelationSimilarity(train);
        UserNeighborhood neighborhood = new NearestNUserNeighborhood(50, similarity, train);
        UserBasedRecommender ub = new GenericUserBasedRecommender(train, neighborhood, similarity);
        recs[0] = iuAvg;
        recs[1] = ub;
        names[0] = "iuAvg";
        names[1] = "ub";
        // generate different evaluation files
        trainMap.putAll(EvalUtils.fromDatamodelToMap(train));
        testMap.putAll(EvalUtils.fromDatamodelToMap(test));
    }

    private void evaluateFromMyMediaLite(String recFile) throws IOException, TasteException {
        recommenders = new Recommender[1];
        names = new String[1];
        trainMap = new HashMap<Long, Map<Long, Float>>();
        testMap = new HashMap<Long, Map<Long, Float>>();
        initForEvaluateFromMyMediaLite(recFile, recommenders, names, trainMap, testMap);
        evaluate();
    }

    private static void initForEvaluateFromMyMediaLite(String recFile,
            Recommender[] recs, String[] names,
            Map<Long, Map<Long, Float>> trainMap, Map<Long, Map<Long, Float>> testMap) throws IOException, TasteException {
        // open the files
        DataModel train = new FileDataModel(new File("res/train.txt"));
        DataModel test = new FileDataModel(new File("res/test.txt"));
        // load the recommender from file
        Recommender recommender = EvalUtils.getRecommenderFromResult(new RecommenderResultFromFileInMemory(recFile));
        recs[0] = recommender;
        names[0] = new File(recFile).getName();
        // generate different evaluation files
        trainMap.putAll(EvalUtils.fromDatamodelToMap(train));
        testMap.putAll(EvalUtils.fromDatamodelToMap(test));
    }

    private void evaluateFromGeneratedFiles(String recFile) throws IOException, TasteException {
        recommenders = new Recommender[1];
        names = new String[1];
        trainMap = new HashMap<Long, Map<Long, Float>>();
        testMap = new HashMap<Long, Map<Long, Float>>();
        initForEvaluateFromGeneratedFiles(recFile, recommenders, names, trainMap, testMap);
        evaluate();
    }

    private static void initForEvaluateFromGeneratedFiles(String recFile,
            Recommender[] recs, String[] names,
            Map<Long, Map<Long, Float>> trainMap, Map<Long, Map<Long, Float>> testMap) throws IOException, TasteException {
        // open the files
        DataModel train = new FileDataModel(new File("res/train.txt"));
        DataModel test = new FileDataModel(new File("res/test.txt"));
        // load the recommender from file
        Recommender recommender = EvalUtils.getRecommenderFromResult(new RecommenderResultFromFileInMemory(recFile, 0, 2, 4));
        recs[0] = recommender;
        names[0] = new File(recFile).getName();
        // generate different evaluation files
        trainMap.putAll(EvalUtils.fromDatamodelToMap(train));
        testMap.putAll(EvalUtils.fromDatamodelToMap(test));
    }

    private void evaluate() {
        TrecRecommenderEvaluator eval = null;
        long time = 0;

        eval = TrecRecommenderEvaluatorFactory.getTrecRecommenderEvaluator(TrecRecommenderEvaluatorFactory.TREC_REC_EVALUATOR_TYPE.USING_ALL_ITEMS);
        TrecRecommenderEvaluatorFactory.initEvaluator(eval, trainMap, testMap, "res/urel_" + eval + ".txt", null, recommenders, names, "res/");
        time = System.currentTimeMillis();
        eval.evaluate();
        System.out.println(eval + " --> " + (System.currentTimeMillis() - time) + " ms");
        eval = TrecRecommenderEvaluatorFactory.getTrecRecommenderEvaluator(TrecRecommenderEvaluatorFactory.TREC_REC_EVALUATOR_TYPE.USING_TEST_ITEMS);
        TrecRecommenderEvaluatorFactory.initEvaluator(eval, trainMap, testMap, "res/urel_" + eval + ".txt", null, recommenders, names, "res/");
        time = System.currentTimeMillis();
        eval.evaluate();
        System.out.println(eval + " --> " + (System.currentTimeMillis() - time) + " ms");
        eval = TrecRecommenderEvaluatorFactory.getTrecRecommenderEvaluator(TrecRecommenderEvaluatorFactory.TREC_REC_EVALUATOR_TYPE.USING_TRAIN_ITEMS);
        TrecRecommenderEvaluatorFactory.initEvaluator(eval, trainMap, testMap, "res/urel_" + eval + ".txt", null, recommenders, names, "res/");
        time = System.currentTimeMillis();
        eval.evaluate();
        System.out.println(eval + " --> " + (System.currentTimeMillis() - time) + " ms");

        eval = TrecRecommenderEvaluatorFactory.getTrecRecommenderEvaluator(TrecRecommenderEvaluatorFactory.TREC_REC_EVALUATOR_TYPE.USING_TEST_RATINGS, 4.0f, 500, null);
        TrecRecommenderEvaluatorFactory.initEvaluator(eval, trainMap, testMap, "res/urel_" + eval + ".txt", null, recommenders, names, "res/");
        time = System.currentTimeMillis();
        eval.evaluate();
        System.out.println(eval + " --> " + (System.currentTimeMillis() - time) + " ms");
        eval = TrecRecommenderEvaluatorFactory.getTrecRecommenderEvaluator(TrecRecommenderEvaluatorFactory.TREC_REC_EVALUATOR_TYPE.USING_ONE_AND_N_ITEMS, 4.0f, 500, null);
        TrecRecommenderEvaluatorFactory.initEvaluator(eval, trainMap, testMap, "res/urel_" + eval + ".txt", null, recommenders, names, "res/");
        time = System.currentTimeMillis();
        eval.evaluate();
        System.out.println(eval + " --> " + (System.currentTimeMillis() - time) + " ms");
        eval = TrecRecommenderEvaluatorFactory.getTrecRecommenderEvaluator(TrecRecommenderEvaluatorFactory.TREC_REC_EVALUATOR_TYPE.USING_TEST_AND_N_ITEMS, 4.0f, 500, null);
        TrecRecommenderEvaluatorFactory.initEvaluator(eval, trainMap, testMap, "res/urel_" + eval + ".txt", null, recommenders, names, "res/");
        time = System.currentTimeMillis();
        eval.evaluate();
        System.out.println(eval + " --> " + (System.currentTimeMillis() - time) + " ms");
    }

    public static void main(String[] args) throws Exception {
        new Main().evaluateFromMahout();
        new Main().evaluateFromMyMediaLite(args[0]);
        new Main().evaluateFromGeneratedFiles(args[1]);
    }
}
