package br.com.wso.connection.dao;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class MongoConnection {

    private static final String DBUSER = "serviceorder";
    private static final String DBPASSWORD = "order123123";
    private static final String DB_NAME = "orderservice";

    private static MongoConnection instance;
    private static int mongoInstance = 1;

    private MongoClientURI uri;
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
            uri = new MongoClientURI("mongodb://"+DBUSER+":"+DBPASSWORD+"@ds017231.mlab.com:17231/orderservice");
            mongo = new MongoClient(uri);
            db = mongo.getDB(DB_NAME);
            System.out.println("Mongo instance equals :> " + mongoInstance++);
        }
        return db;
    }

}
