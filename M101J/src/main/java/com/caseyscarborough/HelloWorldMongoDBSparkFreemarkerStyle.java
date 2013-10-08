package com.caseyscarborough;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

import java.io.StringWriter;
import java.net.UnknownHostException;

public class HelloWorldMongoDBSparkFreemarkerStyle {
    public static void main(String[] args) {

        // Set the configuration for where to find the template
        final Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(HelloWorldFreemarkerStyle.class, "/");

        MongoClient client = null;
        try {
            // Defaults to localhost, 27017
            client = new MongoClient("localhost", 27017);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        DB database = client.getDB("course");
        final DBCollection collection = database.getCollection("hello");

        // Define a path for the root path
        Spark.get(new Route("/") {

            @Override
            public Object handle(final Request request, final Response response) {
                StringWriter writer = new StringWriter();
                try {
                    // Create a new template and string writer
                    Template helloTemplate = configuration.getTemplate("hello.ftl");

                    DBObject document = collection.findOne();
                    helloTemplate.process(document, writer);

                } catch (Exception e) {
                    halt(500);
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }

                // Return the HTML to be served by spark
                return writer;
            }
        });
    }
}
