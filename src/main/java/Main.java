import java.util.ArrayList;

/**
 * @brief
 * The program will read texts that need to be classified
 *    - or from Tweets thanks to a query.
 *    - or from just an array of Strings.
 *
 *  Then it will associate probabilities to each datum.
 *    - If > 0 then the string is classified as 'positive'
 *    - If < 0 then the string is classified as 'negative'
 *    - Classification of 'neutral' are performed also but not stored (displayed on stderr)
 *  The value itself is a probability
 *
 * @return 0 to symbolize that everything went OK.
 * @author Valentin Montmirail (http://valentin-montmirail.com/)
 */
public class Main {

    /**
     * The entrance point of the program
     * @param args the query performed on Twitter.
     * @return 0 when everything went ok, something else otherwise.
     */
    public static int main(String[] args) {

        /* The data that we will classify */
        String[] textToClassify;

        /* Example data to show that it works. 2 strongly positives, 1 strongly negative. */
        textToClassify = new String[] {"awesome stuff !", "very nice !!", "I'm so sad"};

        if(args.length > 0) {
            TweetRequest tweetRequest = new TweetRequest(args[1]);
            textToClassify = tweetRequest.getTweets();
        }

        /* We then call our Classifier on those data */
        Classifier classifier = new Classifier(textToClassify);

        /*
         * It return then the classification.
         * - If > 0 then the string is classified as 'positive'
         * - If < 0 then the string is classified as 'negative'
         * - Classification of 'neutral' are performed also but not stored (displayed on stderr)
         * The value itself is a probability
         */
        ArrayList<Double> positivesPercentages = classifier.getClassification();

        /* We then display the Array of probabilities to perform mathematical operations on it. */
        System.out.println("\n");
        System.out.println(positivesPercentages);
        System.out.println("\n");

        /* We then display a lot of mathematical informations about those data */
        System.out.println(new MathematicalExploitation(positivesPercentages));

        return 0;
    }
}
