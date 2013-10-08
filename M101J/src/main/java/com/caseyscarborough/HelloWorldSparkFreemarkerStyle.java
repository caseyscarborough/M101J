package com.caseyscarborough;

import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class HelloWorldSparkFreemarkerStyle {
    public static void main(String[] args) {

        // Set the configuration for where to find the template
        final Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(HelloWorldFreemarkerStyle.class, "/");

        // Define a path for the root path
        Spark.get(new Route("/") {

            @Override
            public Object handle(final Request request, final Response response) {
                StringWriter writer = new StringWriter();
                try {
                    // Create a new template and string writer
                    Template helloTemplate = configuration.getTemplate("hello.ftl");

                    // Create a map to put the data bindings into
                    Map<String, Object> helloMap = new HashMap<String, Object>();
                    helloMap.put("name", "Freemarker");

                    // Process the template with the map and put the data into writer
                    helloTemplate.process(helloMap, writer);
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
