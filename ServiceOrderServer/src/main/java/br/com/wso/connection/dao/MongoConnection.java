package br.com.wso.connection.dao;

import com.mongodb.DB;
import com.mongodb.MongoClient;

public class MongoConnection {

    private static final String HOST = "localhost";
    private static final int PORT = 27017;
    private static final String DB_NAME = "serviceorder";

    private static MongoConnection instance;
    private static int mongoInstance = 1;

    private MongoClient mongo;
    private DB db;

    private MongoConnection() {
    }

    //garante sempre uma única instância da classe
    public static synchronized MongoConnection getInstance() {
        if (instance == null) {
            instance = new MongoConnection();
        }
        return instance;
    }

    //garante a existência de um único objeto mongo
    public DB getDB() {
        if (mongo == null) {
            mongo = new MongoClient(HOST, PORT);
            db = mongo.getDB(DB_NAME);
            System.out.println("Mongo instance equals :> " + mongoInstance++);
        }
        return db;
    }

}
