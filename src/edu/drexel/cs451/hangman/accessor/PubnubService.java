package edu.drexel.cs451.hangman.accessor;

import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import pubnub.Callback;
import pubnub.Pubnub;
import edu.drexel.cs451.hangman.MultiPlayerGame;

public class PubnubService {

    private final static String PUBLISHER_KEY = "pub-654dd108-e91b-4dd7-b730-547b8e108e7e";
    private final static String SUBSCRIBE_KEY = "sub-2d525f38-ec96-11e1-b035-4b53f42f5189";
    private final static String SECRET_KEY = "sec-ZGM3MTQyYjUtMjg2OC00ZGIwLThlMjctNWM1MGVlZThjNzI2";
    private final static String LOBBY = "lobby";
    private static String UNIQUEID;
    private Pubnub pubnub;
    private MultiPlayerGame game;

    public final class MSGTYPE {
        public final static String HI = "hi";
        public final static String MSG = "msg";
        public final static String WIN = "win";
        public final static String NEW = "new";
        public final static String BYE = "bye";
    }

    private PubnubService() {
        pubnub = new Pubnub(PUBLISHER_KEY, SUBSCRIBE_KEY);
        UNIQUEID = UUID.randomUUID().toString();
    }

    public void setGame(MultiPlayerGame game) {
        this.game = game;
    }

    private class Receiver implements Callback {

        private PubnubService service;
        public Receiver(PubnubService service) {
            this.service = service;
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
                JSONObject msg = (JSONObject) message;
                try {
                    String type = msg.getString("type");
                    if (type.equalsIgnoreCase(MSGTYPE.HI)) {
                        service.respondToHi(msg.getString("msg"));
                    } else if (type.equalsIgnoreCase(MSGTYPE.MSG)) {
                        service.respondToMsg(msg.getString("msg"));
                    } else if (type.equalsIgnoreCase(MSGTYPE.WIN)) {
                        service.respondToWin(msg.getString("msg"));
                    } else if (type.equalsIgnoreCase(MSGTYPE.NEW)) {
                        service.respondToNew(msg.getString("msg"));
                    }
                } catch (JSONException e) {
                }
            }
            return false;
        }
    }

    private static class Holder {
        public static final PubnubService instance = new PubnubService();
    }

    public static PubnubService getInstance() {
        return Holder.instance;
    }

    // ******************************************************
    // methods to send msg to others


    // sending a message to request everyone in the channel answer
    public void hi() {
        try {
            JSONObject msg = new JSONObject();
            msg.put("type", MSGTYPE.HI);
            msg.put("msg", game.getPlayerName());
            JSONArray response = pubnub.publish(LOBBY, msg);
        } catch (JSONException e) {
        }
    }

    public void win() {
        try {
            JSONObject msg = new JSONObject();
            msg.put("type", MSGTYPE.WIN);
            msg.put("msg", game.getPlayerName());
            JSONArray response = pubnub.publish(LOBBY, msg);
        } catch (JSONException e) {
        }
    }
    
    public void bye() {
        try {
            JSONObject msg = new JSONObject();
            msg.put("type", MSGTYPE.BYE);
            msg.put("msg", game.getPlayerName());
            JSONArray response = pubnub.publish(LOBBY, msg);
        } catch (JSONException e) {            
        }
    }

    public void sendMsg(String string) {
        try {
            JSONObject msg = new JSONObject();
            msg.put("type", MSGTYPE.MSG);
            msg.put("msg", string);
            pubnub.publish(LOBBY, msg);
        } catch (JSONException e) {}
    }

    // ******************************************************
    // methods to process msg from other players

    public void respondToHi(String playerName) {
        game.addMsg(playerName + " just joined the game!");
    }
    
    public void respondToMsg(String msg) {
        game.addMsg(msg);
    }

    public void respondToWin(String winner) {
        game.someoneWon(winner);
    }
    
    public void respondToNew(String word) {
        game.start(word);
    }
    
    public void respondToBye(String leaver) {
        game.addMsg(leaver + "left the room");
    }
    
    public void subscribe() {
        new Thread(new Runnable(){
            @Override
            public void run() {
                pubnub.subscribe(LOBBY, new Receiver(Holder.instance));
            }
        }).start();
    }
}
