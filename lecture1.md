% M101J: MongoDB for Java Developers
% MongoDB University
% Fall 2013

# What is MongoDB?

[Lecture Video](https://www.youtube.com/watch?v=q2iLKZ1LgjI)

## MongoDB is a non-relational JSON Document store.

This means that it does not support typical relational algebra or tables/columns/rows like your typical relational database. It stores documents in the JSON format.

Here is an example of JSON:

```json
{
     "first_name": "Casey",
     "last_name": "Scarborough",
     "hobbies": ["guitar", "photography", "programming"]
}
```

MongoDB, unlike relational databases, is able to store documents that do not have the same structure.

Features that MongoDB doesn't have:

* Joins
* SQL
* Transactions

## Quiz

Which of the following statements are true about MongoDB? Check all that apply.

1. MongoDB is document oriented.
2. MongoDB supports joins.
3. MongoDB has dynamic schema.
4. MongoDB supports SQL.

* [Answer Video](https://www.youtube.com/watch?v=CAR42sMkXEo)

# MongoDB Relative to Relational DBs

[Lecture Video](https://www.youtube.com/watch?v=-KIC1LXxcGM)

MongoDB gives you a large depth of functionality while keeping high scalability and performance.

Documents are hierarchical and MongoDB doesn't support atomic transactions.

## Quiz

Which features did MongoDB omit in order to retain scalability?

1. Joins
2. Indexes
3. Secondary Indexes
4. Transactions across multiple selections

* [Answer Video](https://www.youtube.com/watch?v=IAvnMgvHuLw)

# Building an App with MongoDB

[Lecture Video](https://www.youtube.com/watch?v=swhH4q_2Ttc)

## Application Architecture

The final application we are creating will be a blog using Java and MongoDB.

* MongoDB
  * Starts a mongod process
  * Java connects via TCP to MongoDB
* Java
  * SparkJava
  * FreeMarker
  * MongoJava driver

_SparkJava_ is a micro Java web framework inspired by Java that is inspired by Sinatra that easily allows you to creates routes and pages.

_FreeMarker_ is a templating language.

# Introduction to Mongo Shell

[Lecture Video](https://www.youtube.com/watch?v=f-lyGAMnNY4)

After installing MongoDB, you can start the mongo shell by issuing the `mongo` command.

The first command you'll want to issue is to select the database:

```
> use test
```

This creates the database if it isn't created yet. Here are some common commands:

```
> db.things.save({ a : 1, b : 2, c : 3})
> db.things.find()
{ "_id" : ObjectId("52535e464a33ac3bdbb08566"), "a" : 1, "b" : 2, "c" : 3 }
> db.things.save({ a : 3, b : 2, c : 3, d : 200})
> db.things.find()
{ "_id" : ObjectId("52535e464a33ac3bdbb08566"), "a" : 1, "b" : 2, "c" : 3 }
{ "_id" : ObjectId("52535e724a33ac3bdbb08567"), "a" : 3, "b" : 2, "c" : 3, "d" : 200 }
> db.things.find({ a : 1 })
{ "_id" : ObjectId("52535e464a33ac3bdbb08566"), "a" : 1, "b" : 2, "c" : 3 }
```

## Quiz

Which of the following expressions are valid JSON documents? Check all that apply.

1. `{a:1, b:2, c:3}`
2. `{a,1; b,4, c,6}`
3. `{a:1; b:1; c:4}`
4. `(A,1; b:2; c,4}`

* [Answer Video](https://www.youtube.com/watch?v=YQTqdLe3W4M)

# JSON Introduced

Issue the following commands in the Mongo Shell.

```
> db.test.save({a:1, b:1, fruits:['apple','orange','pear']})
> db.test.save({name:"casey", address:{street:"elm drive", city:"Morrow", zip:"30260", house_number: 6551}})
> db.test.find().pretty()
{ "_id" : ObjectId("52535e2e4a33ac3bdbb08565"), "a" : 1 }
{ "_id" : ObjectId("52535e464a33ac3bdbb08566"), "a" : 1, "b" : 2, "c" : 3 }
{
  "_id" : ObjectId("52535e724a33ac3bdbb08567"),
  "a" : 1,
  "b" : 2,
  "c" : 3,
  "d" : 200
}
{
  "_id" : ObjectId("52535fb94a33ac3bdbb08568"),
  "a" : 1,
  "b" : 1,
  "fruits" : [
    "apple",
    "orange",
    "pear"
  ]
}
{
  "_id" : ObjectId("525360134a33ac3bdbb08569"),
  "name" : "casey",
  "address" : {
    "street" : "elm drive",
    "city" : "Morrow",
    "zip" : "30260",
    "house_number" : 6551
  }
}
```

## Quiz

Which of the following expressions are valid JSON documents? Check all that apply.

1. `{a:1, b:2, c: 3}`
2. `{a:1, b:2, c:[1,2,3,4,5]}`
3. `{a:1, b:{}, c: [ { a:1, b:2}, 5, 6]}`
4. `{}`

* [Answer Video](https://www.youtube.com/watch?v=uWcOsdV4Iz4)

# System Requirements

## Operating Systems

* Mac OS X 10.8
* Windows 7
* Linux

## Java

* Version 1.6
* Version 1.7

> _Note: You can check this by issuing the `javac --version` command from your terminal._

# Installing MongoDB (Mac)

[Lecture Video](https://www.youtube.com/watch?v=6VFukRETCTg#t=69)

I installed MongoDB a little differently than the video does, and I'm listing that way. If you'd rather do it the way the video does, you can take a look there.

Issue the following commands from your home directory:

```bash
curl http://downloads.mongodb.org/osx/mongodb-osx-x86_64-2.4.6.tgz > mongodb.tgz
tar -zxvf mongodb.tgz
sudo mv mongodb-osx-x86_64-2.4.6 /usr/local/bin/mongodb
sudo mkdir -p /data/db
sudo chown `id -u` /data/db
export PATH=/usr/local/bin/mongodb/bin:$PATH
```

Or you can just install it through MacPorts.

```
port install mongodb
```

Then run the `mongod` command to start the server, and `mongo` to start the client.

## Installing and Using Maven

[Lecture Video](https://www.youtube.com/watch?v=72vejAmaypM)

# The MongoDB Java Driver

[Lecture Video](https://www.youtube.com/watch?v=FtyaK3pMHxw#t=229)

In order to use Mongo from Java we need the Mongo Java Driver. We're going to add the following to our Maven pom.xml file:

```xml
<dependencies>
    <dependency>
        <groupId>org.mongodb</groupId>
        <artifactId>mongo-java-driver</artifactId>
        <version>2.10.1</version>
    </dependency>
</dependencies>
```

Our MongoDB has a database called course and a collection called hello, with the value `{ name: "MongoDB" }`.

You can then create a new class and create a `public static void main()` function. Add the following to it:

```java
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
```

# Intro to the Spark Web Application Framework

[Lecture Video](https://www.youtube.com/watch?v=UH-VD_ypal8)

For our final project, we will be building a web application using the [Spark](http://www.sparkjava.com/) web framework. To install it in your project, add the following to your `pom.xml` file in the dependencies section.

```xml
<dependency>
    <groupId>com.sparkjava</groupId>
    <artifactId>spark-core</artifactId>
    <version>1.1</version>
</dependency>
```

After importing your dependency with Maven, create a new class called `HelloWorldSparkStyle` and add the following to it:

```java
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

public class HelloWorldSparkStyle {

    public static void main(String[] args) {

        Spark.get(new Route("/") {
            @Override
            public Object handle(final Request request, final Response response) {
                return "Hello World from Spark!";
            }
        });
    }
}
```

Run the application and then navigate in your browser to http://localhost:4567 to see the results.

# Intro to the Freemarker Templating Language

[Lecture Video](https://www.youtube.com/watch?v=_8-3K2Ds-Ok#t=86)

[FreeMarker](http://freemarker.org/) is a templating language for Java. We will be using it to give some styling to our webpages. To add it to your project, start by adding the following Maven dependency to `pom.xml`.

```xml
<dependency>
    <groupId>org.freemarker</groupId>
    <artifactId>freemarker</artifactId>
    <version>2.3.19</version>
</dependency>
```

Create a new file in the src/main/resources folder called `hello.ftl` and add the following template:

```html
<html>
<head>
    <title>Welcome!</title>
</head>
<body>
    <h1>Hello ${name}!</h1>
</body>
</html>
```

Then create a class called `HelloWorldFreemarkerStyle` and add the following code.

```java
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class HelloWorldFreemarkerStyle {
    public static void main(String[] args) {

        // Set the configuration for where to find the template
        Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(HelloWorldFreemarkerStyle.class, "/");

        try {
            // Create a new template and string writer
            Template helloTemplate = configuration.getTemplate("hello.ftl");
            StringWriter writer = new StringWriter();

            // Create a map to put the data bindings into
            Map<String, Object> helloMap = new HashMap<String, Object>();
            helloMap.put("name", "Freemarker");

            // Process the template with the map and put the data into writer
            helloTemplate.process(helloMap, writer);

            // Print out the results
            System.out.println(writer);
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }
}
```

If you run this application you should get the following printed out to the console, which is the HTML for our page.

```html
<html>
<head>
    <title>Welcome!</title>
</head>
<body>
    <h1>Hello Freemarker!</h1>
</body>
</html>
```

# Spark and Freemarker together

[Lecture Video](https://www.youtube.com/watch?v=7fdtf9aLc2w)

Create a new class called `HelloWorldSparkFreemarkerStyle` and add the following code:

```java
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
```
