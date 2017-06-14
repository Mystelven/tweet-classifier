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

        /* Example data to show that it works. 3 strongly positives, 2 strongly negatives. */
        textToClassify = new String[] {
                "awesome stuff !",
                "very nice !!",
                "It is so sad",
                "it sucks",
                "pretty good"
        };

        if(args.length > 0) {

            /* The Twitter query must be the first argument of the method */
            TweetRequest tweetRequest = new TweetRequest(args[0]);

            /* We receive the tweets in a String way. */
            textToClassify = tweetRequest.getTweets();

            if(args.length > 1 && "-printTweets".compareTo(args[1]) == 0) {
                for(String tweet : textToClassify) {
                    System.out.println(tweet);
                }
            }
        }

        /* We then call our Classifier on those data */
        Classifier classifier = new Classifier(textToClassify);

        /*
         * It return then the classification.
         * - If > 0 then the string is classified as 'positive'
         * - If < 0 then the string is classified as 'negative'
         *
         * If in neutralPercentages, then the tweet was neutral
         */
        classifier.setClassification();
        ArrayList<Double> positivesPercentages = classifier.getPositivesPercentages();

        /* We then display the Array of probabilities to perform mathematical operations on it. */
        System.out.println("\n");
        System.out.println(positivesPercentages);
        System.out.println("\n");

        /* We then display a lot of mathematical informations about those data */

        MathematicalExploitation math = new MathematicalExploitation(classifier);

        System.out.println(math);
        System.out.println("\n");

        math.toFiles();

        return 0;
    }
}
