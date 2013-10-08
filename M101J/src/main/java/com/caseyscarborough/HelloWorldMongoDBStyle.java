package com.caseyscarborough;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

import java.net.UnknownHostException;

public class HelloWorldMongoDBStyle {
    public static void main(String[] args) {

        MongoClient client = null;
        try {
            // Defaults to localhost, 27017
            client = new MongoClient("localhost", 27017);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        // Get the database
        DB database = client.getDB("course");

        // Get the hello collection
        DBCollection collection = database.getCollection("hello");

        // Retrieve our document
        DBObject document = collection.findOne();
        System.out.println(document);
    }
}
