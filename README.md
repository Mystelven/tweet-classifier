# Tweet Classifier

Learning-ception: I wanted to learn how to use Machine Learning and Natural Language Processing in practice.

This program can classify tweets on a topic (passed in argument) on 3 levels 'positive', 'negative' and 'neutral'.

# How to use the program

You must add a file `twitter4j.properties` at the root of the project.
In this file you must specify some informations from your app credential (https://apps.twitter.com/)
The file is looking as follow:

    debug                   = false
    oauth.consumerKey       = ********
    oauth.consumerSecret    = ********
    oauth.accessToken       = ********
    oauth.accessTokenSecret = ********

Then from the command `mvn compile install exec:java -Dexec.mainClass=Main` you can execute the program.

# Credits

- [Twitter4J](http://twitter4j.org/en/index.html)
- [Gson](https://github.com/google/gson)
- [Twitter Apps](https://apps.twitter.com)
- [MonkeyLearn](https://monkeylearn.com/)
- [Maven](https://maven.apache.org/)


# Contact

Feel free to contact me on valentin.montmirail@gmail.com for more information

and/or to fork this program to add more features ;).

