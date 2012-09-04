package pubnub.simple.example;

import java.util.Iterator;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

import pubnub.api.Callback;
import pubnub.api.Pubnub;

public class PubNubExample {
    private final static String PUBLISHER_KEY = "pub-654dd108-e91b-4dd7-b730-547b8e108e7e";
    private final static String SUBSCRIBE_KEY = "sub-2d525f38-ec96-11e1-b035-4b53f42f5189";
    private final static String SECRET_KEY = "sec-ZGM3MTQyYjUtMjg2OC00ZGIwLThlMjctNWM1MGVlZThjNzI2";
    private final static String CIPHER_KEY = "";

    public static void main(String[] args) {
        /*
         * What we can do is ask for the user for the name of a room. We create
         * a channel using that string have both players subscribe to this
         * channel and send their letter choice as messages through the channel.
         * We can also use these messages to indicate whether if the other
         * person has won or lost I've also noticed, since pubnub uses a channel
         * system, we could actually do more than 2 players
         */
        Scanner scan = new Scanner(System.in);

        System.out.print("Enter the name of a channel to connect to: ");
        String channel = scan.nextLine();
        Pubnub pubnub = new Pubnub(PUBLISHER_KEY, SUBSCRIBE_KEY, SECRET_KEY,
                CIPHER_KEY, true);

        // How to Send a Message
        // *****************************************************
        // Construct Message
        JSONObject message = new JSONObject();
        try {
            message.put("Player1", "Hello World!");
        } catch (org.json.JSONException jsonError) {
        }

        // Send Message
        JSONArray response = pubnub.publish(channel, message);
        // ******************************************************

        // How to listen
        // Implement the listener
        class Receiver implements Callback {

            @Override
            public void connectCallback(String arg0) {
            }

            @Override
            public void disconnectCallback(String arg0) {
            }

            @Override
            public void errorCallback(String arg0, Object arg1) {
            }

            @Override
            public void reconnectCallback(String arg0) {
            }

            @Override
            public boolean subscribeCallback(String channel, Object message) {
                try {
                    // If we got JSON step through all the entries and print the
                    // message
                    if (message instanceof JSONObject) {
                        JSONObject inMessage = (JSONObject) message;
                        Iterator keys = inMessage.keys();
                        while (keys.hasNext()) {
                            System.out.print(inMessage.get(keys.next()
                                    .toString()) + " ");
                        }
                        System.out.println();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                return false;
            }

			@Override
			public boolean presenceCallback(String arg0, Object arg1) {
				// TODO Auto-generated method stub
				return false;
			}
        }

        // Create a Receiver
        Receiver messageReceiver = new Receiver();
        // Listen
        pubnub.subscribe(channel, messageReceiver);
    }
}
