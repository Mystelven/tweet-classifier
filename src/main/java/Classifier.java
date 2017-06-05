import com.google.gson.JsonParser;
import com.monkeylearn.MonkeyLearn;
import com.monkeylearn.MonkeyLearnException;
import com.monkeylearn.MonkeyLearnResponse;

import java.util.ArrayList;

/**
 * This class will call a Classifier of Tweet already trained
 * (https://app.monkeylearn.com/main/classifiers/cl_qkjxv9Ly/tab/tree-sandbox/)
 *
 * So basically this classifier will be run on some textual data and will classify them with probabilities
 * We get those probabilities as (> 0) if positive, (< 0) if negative.
 *
 * And then finally, the whole array is returned
 * @author Valentin Montmirail (http://valentin-montmirail.com/)
 */
public class Classifier {

    private static final String token    = "eb05a0294231995a27e8ca4f07c0a9da9da918b5";
    private static final String moduleId = "cl_qkjxv9Ly";

    private MonkeyLearnResponse response;

    private ArrayList<Double> positivesPercentages;
    private ArrayList<Double> neutralPercentages;

    /**
     * We call the classifier on an array of String
     * @param textList the array of data that will be classified.
     */
    public Classifier(String[] textList) {

        try {

            /* We use the key of the already trained classifier */
            MonkeyLearn ml = new MonkeyLearn(token);

            /* Then we classifiy the data and retuned then as JSON array */
            response = ml.classifiers.classify(moduleId, textList, true);

        } catch(MonkeyLearnException e) {

            System.err.println(e.getMessage());
            System.exit(-2);
        }
    }

    /**
     * We will extract from the JSON array, all the probabilities and on which label they are classified.
     */
    public void setClassification() {

        ArrayList<String> values = new ArrayList<String>();

        positivesPercentages = new ArrayList<Double>();
        neutralPercentages   = new ArrayList<Double>();

        /* We extract all the JSONElement as just String easily parsed. */
        for (int i = 0; i < response.arrayResult.size(); ++i) {

            values.add(response.arrayResult.get(i).toString());
        }

        for(String str : values) {

            JsonParser jsonParser = new JsonParser();
            String element = jsonParser.parse(str).getAsJsonArray().get(0).toString();

            String[] splitted = element.split(",");

            String[] probabilities = splitted[1].split(":");

            /* We extract the 'probability' in the JSON element */
            Double proba = Double.valueOf(probabilities[1]);

            String[] labels = splitted[2].split(":");

            /* We extract the 'label' in the JSON element */
            String label = labels[1].replace("\"","").replace("}","");

            /* According to the 'label', or it is negative, or positive, or neutral */
            switch(label) {

                /* When it is positive, we add positively the probability */
                case "positive":
                    positivesPercentages.add(proba);
                    break;

                /* When it is negative, we add negatively the probability */
                case "negative":
                    positivesPercentages.add(-proba);
                    break;

                /* We don't consider the neutral here, but it could be used. */
                default:
                    neutralPercentages.add(proba);
                    break;
            }

        }

        /* We sort all the probabilites */
        positivesPercentages.sort((a, b) -> Double.compare(b, a));
        neutralPercentages.sort((a,b) -> Double.compare(b,a));
    }

    /**
     * Return the list of positives percentages ('>0') and the list of negatives percentages ('<0')
     * @return the list of positives percentages ('>0') and the list of negatives percentages ('<0')
     */
    public ArrayList<Double> getPositivesPercentages() {
        return positivesPercentages;
    }

    /**
     * Return the list of neutral percentages
     * @return the list of neutral percentages
     */
    public ArrayList<Double> getNeutralPercentages() {
        return neutralPercentages;
    }

}
