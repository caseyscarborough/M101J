# Week 2

## Crud and the Mongo Shell

[Lecture Video](https://www.youtube.com/watch?v=4KtdCMSKx84)

In MongoDB, the CRUD operations are:

* Create: `insert`
* Read: `find`
* Update: `update`
* Delete: `remove`

MongoDB's CRUD operations exist as methods/functions in programming language APIs, not as a separate language. You should manipulate documents in the database using objects in your programming language.

### Quiz

By the end of this week, you'll know which of the following?

* MongoDB's basic document creation, retrieval, modification, and removal operations
* Some features of the MongoDB shell, mongo
* How to measure performance of MongoDB operations
* How to manipulate MongoDB documents from Java
* How to analyze data in MongoDB collections

[Answer Video](https://www.youtube.com/watch?v=lqhzCtKuJ7c)

## Secrets of the Mongo Shell

[Lecture Video](https://www.youtube.com/watch?v=hJy10xgZXzc)

Mongo shell is an interactive Javascript interpreter that allows you to connect to MongoDB and manipulate its data. This means you can execute Javascript inside the shell:

```js
> for (i = 0; i < 3; i++) print("Hello!");
Hello!
Hello!
Hello!
```

The shell also has a lot of builtin helpers as well.

```
> help
  db.help()                    help on db methods
  db.mycoll.help()             help on collection methods
  sh.help()                    sharding helpers
  rs.help()                    replica set helpers
  help admin                   administrative help
  help connect                 connecting to a db help
  help keys                    key shortcuts
  help misc                    misc things to know
  help mr                      mapreduce

  show dbs                     show database names
  show collections             show collections in current database
  show users                   show users in current database
  show profile                 show most recent system.profile entries with time >= 1ms
  show logs                    show the accessible logger names
  show log [name]              prints out the last segment of log in memory, 'global' is default
  use <db_name>                set current database
  db.foo.find()                list objects in collection foo
  db.foo.find( { a : 1 } )     list objects in foo where a == 1
  it                           result of the last line evaluated; use to further iterate
  DBQuery.shellBatchSize = x   set default number of items to display on shell
  exit                         quit the mongo shell
```

It also has tab completions for Javascript and other commands. For example, typing `pri` and pressing the Tab key, it will complete the word `print`, a common Javascript function.

### Quiz

What does the following fragment of JavaScript output?

```js
x = { "a" : 1 };
y = "a";
x[y]++;
print(x.a);
```

[Answer Video](https://www.youtube.com/watch?v=a6TFwpCoLoY)

## BSON Introduced

[Lecture Video](https://www.youtube.com/watch?v=K3J6WvDW-Hc)

MongoDB uses BSON to represent data in the database. The specification for BSON is give at [bsonspec.org](http://bsonspec.org).

### Quiz

Which of the following are types available in BSON?

* Strings
* Floating-point numbers
* Complex numbers
* Arrays
* Objects
* Timestamps

[Answer Video](https://www.youtube.com/watch?v=4hsTQrMs-xY)

## Mongo shell, inserting docs

[Lecture Video](https://www.youtube.com/watch?v=RbRRvBLl7Qo)

```bash
> doc = { "name" : "Smith", "age" : 30, "profession" : "hacker" }
{ "name" : "Smith", "age" : 30, "profession" : "hacker" }
> db
test
> db.people.insert(doc)
> db.people.find()
{ "_id" : ObjectId("525db09800fa8276d3d083d4"), "name" : "Smith", "age" : 30, "profession" : "hacker" }
> db.people.insert({ "name" : "Jones", "age" : 35, "profession" : "baker" })
> db.people.find()
{ "_id" : ObjectId("525db09800fa8276d3d083d4"), "name" : "Smith", "age" : 30, "profession" : "hacker" }
{ "_id" : ObjectId("525db17400fa8276d3d083d5"), "name" : "Jones", "age" : 35, "profession" : "baker" }
```

### Quiz

Insert a document into the "fruit" collection with the attributes of "name" being "apple", "color" being "red", and "shape" being round. Assume that we have already issued the use command to get into the right database. Use the "insert" method.

[Answer Video](https://www.youtube.com/watch?v=4eqpL7ETQZQ)

## Mongo shell, introduction to findOne

[Lecture Video](https://www.youtube.com/watch?v=w9V0fJsDwbQ)

```bash
> db.people.findOne()
{
  "_id" : ObjectId("525db09800fa8276d3d083d4"),
  "name" : "Smith",
  "age" : 30,
  "profession" : "hacker"
}
> db.people.findOne({ "name" : "Jones" })
{
  "_id" : ObjectId("525db17400fa8276d3d083d5"),
  "name" : "Jones",
  "age" : 35,
  "profession" : "baker"
}
> db.people.findOne({ "name" : "Jones" }, { "name" : true, "_id" : false })
{ "name" : "Jones" }
> db.people.findOne({ "name" : "Jones" }, { "name" : true })
{ "_id" : ObjectId("525db17400fa8276d3d083d5"), "name" : "Jones" }
```

### Quiz

Use `findOne` on the collection `users` to find one document where the key "username" is "dwight", and retrieve only the key named "email".

[Answer Video](https://www.youtube.com/watch?v=uN_wf5a3BE4)

## Mongo shell, introduction to find

[Lecture Video](https://www.youtube.com/watch?v=8kKfFK6a0Ak)

## Mongo shell, querying using field selection

[Lecture Video](https://www.youtube.com/watch?v=UIg86QjSoyY)

You can use the find method in different ways. See below:

```bash
> db.scores.find( { student: 19 } );
> db.scores.find( { student: 19, type: "essay"} );

// Limit the type returned from the find method
> db.scores.find({ student: 19, type: "essay" }, { score: true, _id: false });
```

### Quiz

Supposing a scores collection similar to the one presented, how would you find all documents with an essay score equal to 50 and only retrieve the student field?

```bash
db.scores.find({ "type": "essay", "score" : 50 }, { "student" : true, "_id" : false });
```

## Querying using $gt and $lt

[Lecture Video](https://www.youtube.com/watch?v=FHLrz4VGzkg)

```
// Retrieve documents that have a score greater than 95.
> db.scores.find({ score : { $gt : 95 } });

// Retrieve documents that have a score greater than
// 40 and less or equal to 70, with the type essay
> db.scores.find({ score : { $gt: 40, $lte : 70 }, type: "essay" });
```

### Quiz

Which of these finds documents with a score between 50 and 60, inclusive?

* db.scores.find({ score : { $gt : 50 , $lt : 60 } } );
* db.scores.find({ score : { $gte : 50 , $lte : 60 } } );
* db.scores.find({ score : { $gt : 50 , $lte : 60 } } );
* db.scores.find({ score : { $gte : 50 , $lt : 60 } } );
* db.scores.find({ score : { $gt : 50 } } );

## Inequalities on strings

[Lecture Video](https://www.youtube.com/watch?v=imCCKOevU3c)