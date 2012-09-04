package pubnub.simple.example;

import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

import pubnub.api.Callback;
import pubnub.api.Pubnub;

public class PubNubInputClient {
    private final static String PUBLISHER_KEY = "pub-654dd108-e91b-4dd7-b730-547b8e108e7e";
    private final static String SUBSCRIBE_KEY = "sub-2d525f38-ec96-11e1-b035-4b53f42f5189";

    public static void main(String[] args) {
        /*
         * What we can do is ask for the user for the name of a room. We create
         * a channel using that string have both players subscribe to this
         * channel and send their letter choice as messages through the channel.
         * We can also use these messages to indicate whether if the other
         * person has won or lost I've also noticed, since pubnub uses a channel
         * system, we could actually do more than 2 players
         */
        Pubnub pubnub = new Pubnub(PUBLISHER_KEY, SUBSCRIBE_KEY);
        Scanner scan = new Scanner(System.in);

        System.out.print("Enter the name of a channel to connect to: ");
        String channel = "CS451_Hangman_" + scan.nextLine();

        // How to Send a Message
        // *****************************************************
        // Construct Message
        while (true) {
            System.out.print("Enter messageo: ");
            String input = scan.nextLine();

            JSONObject message = new JSONObject();
            try {
                message.put("Player2", input);
            } catch (org.json.JSONException jsonError) {
            }

            JSONArray response = pubnub.publish(channel, message);
            System.out.println(response);
        }
    }
}