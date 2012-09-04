package edu.drexel.cs451.hangman;

import java.util.HashMap;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import pubnub.api.*;

public class ChannelHost {
	
	private final static String PUBLISHER_KEY = "pub-654dd108-e91b-4dd7-b730-547b8e108e7e";
    private final static String SUBSCRIBE_KEY = "sub-2d525f38-ec96-11e1-b035-4b53f42f5189";
    private final static String SECRET_KEY = "sec-ZGM3MTQyYjUtMjg2OC00ZGIwLThlMjctNWM1MGVlZThjNzI2";
    private final static String channel = "lobby";
    
    private static WordAccessor wordAccessor = WordAccessor.getInstance();
    private static Pubnub pubnub = new Pubnub(PUBLISHER_KEY, SUBSCRIBE_KEY, SECRET_KEY, "", true);
    private static int numPlayers= 0;
    private static HashMap<String, Integer> scoreboard = new HashMap<String, Integer>();
    private static String word;
    
    
    public static void main(String[]args) {
    	
    	class Receiver implements Callback {
    		
    		@Override
    		public boolean presenceCallback(String channel, Object message) {
    			return false;
            }

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
                
            	if (message instanceof JSONObject) {
            		//if Message is a JSONObject, parse
                    JSONObject inMessage = (JSONObject) message;
                    Iterator keys = inMessage.keys();
                    while (keys.hasNext()) {
                        try {
                        	String key = keys.next().toString();
                        	String sMessage = (String) inMessage.get(key);
                        	if (key.compareToIgnoreCase("Type") == 0 && sMessage.compareToIgnoreCase("Hi") == 0) {
                        		
                        		if (numPlayers == 0) {
                        			//Add first player
                        			numPlayers += 1;
                        			chooseWord();
                        		}
                        		else if (numPlayers >= 1) {
                        			//Add Subsequent Players
                        			numPlayers += 1;
                        		}
                        		
                        		//Add player to score board
                        		key = keys.next().toString();
                        		sMessage = (String) inMessage.get(key);
                        		if (key.compareToIgnoreCase("msg") == 0) {
                        			scoreboard.put(sMessage, 0);
                        		}	
                        	}
                        	else if (key.compareToIgnoreCase("Type") == 0 && sMessage.compareToIgnoreCase("Win") == 0) {
                        		key = keys.next().toString();
                        		sMessage = (String) inMessage.get(key);
                            	
                        		//Increment Score
                        		int newScore = scoreboard.get(sMessage) + 1;
                            	scoreboard.put(sMessage, newScore);
                            	System.out.println(scoreboard);
                        		
                        		//Choose New Word
                        		chooseWord();
                        	}
                        	else if (key.compareToIgnoreCase("Type") == 0 && sMessage.compareToIgnoreCase("bye") == 0) {
                        		//Player Disconnect
                        		numPlayers-= 1;
                        		
                        		System.out.println("Number of Players: "+numPlayers);
                        		key = keys.next().toString();
                        		sMessage = (String) inMessage.get(key);
                            	
                        		scoreboard.remove(sMessage);
                        	}
                        	//System.out.print(sMessage + " ");
						} catch (JSONException e) {
							e.printStackTrace();
						}
                    }
                    System.out.println();
                    
                } 
                return true;
            }
        }
    	
    	Receiver messageReceiver = new Receiver();
    	pubnub.subscribe(channel, messageReceiver);
    }
    
    private static void chooseWord() {
    	if (numPlayers >= 1) {
    		word = wordAccessor.getRandomWord();
    		JSONObject message = new JSONObject();
    		try {
    			message.put("type", "new");
    			message.put("msg", word);
        		pubnub.publish(channel, message);
        		System.out.println(message);
    		} catch (org.json.JSONException jsonError) {
    			jsonError.printStackTrace();
    		}
    	}
    }

}
