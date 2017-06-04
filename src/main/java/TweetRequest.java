import twitter4j.*;

import java.util.ArrayList;
import java.util.List;

/**
 * This class will perform a request on Twitter and stored
 * as an ArrayList, the List of Tweets on the query.
 * @author Valentin Montmirail (http://valentin-montmirail.com/)
 */
public class TweetRequest {

    /** We will store all the tweets answering the query. */
    private ArrayList<List<Status>> tweets;

    /**
     * This constructor will initialize 'tweets' by answering the query
     * @param query_string the query that we perform on Twitter.
     */
    public TweetRequest(String query_string) {

        tweets = new ArrayList<List<Status>>();

        Query query = new Query(query_string);
        Twitter twitter = TwitterFactory.getSingleton();

        QueryResult result = null;
        do {

            try {

                /* We get the result from the query */
                result = twitter.search(query);

            } catch (TwitterException te) {

                System.err.println("\n");
                System.err.println("Failed to search tweets: " + te.getMessage());
                System.exit(-1);
            }

            /* We add this list to the result */
            tweets.add(result.getTweets());

        } while ((query = result.nextQuery()) != null);

    }


    /**
     * This function will export the Tweets just as its content in 140 characters.
     * @return the list of tweets as String of 140 characters.
     */
    public String[] getTweets() {

        ArrayList<String> texts = new ArrayList<String>();

        for(List<Status> statuses : tweets) {
            for(Status stat : statuses) {
                texts.add(stat.getText());
            }
        }

        /* We return all the Tweets as an array of String */
        return texts.toArray(new String[texts.size()]);
    }


}
