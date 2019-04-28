package com.packtpublishing.tddjava.ch06tictactoemongo.mongo;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.WriteResult;
import org.jongo.Jongo;
import org.jongo.MongoCollection;

import java.rmi.UnknownHostException;

public class TicTacToeCollection {

    private MongoCollection mongoCollection;

    protected MongoCollection getMongoCollection() {
        return mongoCollection;
    }

    public TicTacToeCollection() throws UnknownHostException {
        MongoCredential credential = MongoCredential.createCredential("root", "localhost", "password".toCharArray());
        DB db = new MongoClient(new ServerAddress("localhost"), credential, new MongoClientOptions.Builder().build()).getDB("tic-tac-toe");
        mongoCollection = new Jongo(db).getCollection("game");
    }

    public boolean saveMove(TicTacToeBean bean) {
        try {
            getMongoCollection().save(bean);
            return true;
        }catch(Exception ex) {
            return false;
        }
    }

    public boolean drop() {
        try {
            getMongoCollection().drop();
            return true;
        }catch (Exception ex) {
            return false;
        }
    }
}
