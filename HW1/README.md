# Homework 1

## 1.1

Install MongoDB on your computer and run it on the standard port.
Download the HW1 tarball (mac) or zipfile (windows), expand it as follows:

### Mac Users

```bash
tar xvf hw1.tar
```

### Windows Users

> You probably don't have tar installed so right click on the hw1.zip file and choose "extract all"

Use `mongorestore` to restore the dump into your running mongod. Do this by opening a terminal window (mac) or cmd window (windows) and navigating to the directory so that the dump directory is directly beneath you. Now type

```bash
mongorestore dump
```

Note you will need to have your path setup correctly to find mongorestore.

Now, using the Mongo shell, perform a findone on the collection called hw1 in the database m101. That will return one document. Please provide the value corresponding to the "answer" key from the document returned.

## 1.2

Which of the following are valid JSON documents. Please choose all that apply.

1. `{'name':'Fred Flintstone';'occupation':'Miner';'wife':'Wilma'}`
2. `{'title':'Star Wars', 'quotes':['Use The Force','These are not the Droids you are looking for'],'director':'George Lucas'}`
3. `{}`
4. `{'city':'New York', 'population', 7999034, boros:{'queens', 'manhattan', 'staten island', 'the bronx', 'brooklyn'}}`
5. `{'a':1, 'b':{'b':1, 'c':'foo', 'd':'bar', 'e':[1,2,4]}}`


## 1.3

Before starting this problem, you should have run mongorestore to set up your MongoDB database as described in HW1.1. 

We want to test that you have a working JDK, that maven is installed and that you can run maven-based projects. Please install JDK 1.6 or above and maven if they are not already installed. This week's video lessons show how to install maven. 

Download hw1-3.zip or hw1-3.tar, uncompress it, cd into the hw1-3 directory (there should be a pom.xml file in there), and run Maven as follows:

```bash
mvn compile exec:java -Dexec.mainClass=com.tengen.Week1Homework3
```

It requires Maven to be installed correctly, your mongod server to be running, and that you have run mongorestore properly.
If it's working correctly, there should be two lines towards the bottom that say:

```
[INFO] -------------------
[INFO] BUILD SUCCESS
```

Right before those lines there should be a line that starts with:

```
THE ANSWER IS: 
```

Type the number that appears after the colon into the box below.

## 1.4

We are now going to test that you can run a Maven-based project that depends on the MongoDB Java driver, Spark, and Freemarker. Download hw1-4.zip or hw1-4.tar, uncompress it, cd to the hw-1.4 directory, and run Maven as follows:

```bash
mvn compile exec:java -Dexec.mainClass=com.tengen.Week1Homework4
```

Like the previous homework, it requires Maven to be installed correctly, your mongod server to be running, and that you have run mongorestore properly from HW 1.1.

If all is working correctly, there should be two lines towards the bottom that say:

```
== Spark has ignited ...
>> Listening on 0.0.0.0:4567
```

Next, open a tab in your web browser and navigate to http://localhost:4567. There should be a single line of text that starts with:

```
THE ANSWER IS: 
```

Type the number that appears after the colon into the box below, no spaces or punctuation.