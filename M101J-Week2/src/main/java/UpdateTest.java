import com.mongodb.*;

import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

public class UpdateTest {

    public static void main(String[] args) {
        DBCollection collection = createCollection();

        List<String> names = Arrays.asList("alice", "bobby", "cathy", "david", "ethan");
        for(String name : names) {
            collection.insert(new BasicDBObject("_id", name));
        }

        // Update alice with an age.
        collection.update(new BasicDBObject("_id", "alice"),
                    new BasicDBObject("age", 24));
        // Update alice with gender, but keep age too.
        collection.update(new BasicDBObject("_id", "alice"),
                new BasicDBObject("$set", new BasicDBObject("gender", "female")));

        // Add frank and update his gender at the same time.
        collection.update(new BasicDBObject("_id", "frank"),
                new BasicDBObject("$set", new BasicDBObject("gender", "male")), true, false);

        // Update everyone to have a title.
        collection.update(new BasicDBObject(),
                new BasicDBObject("$set", new BasicDBObject("title", "Dr.")), false, true);

        // Remove ethan
        collection.remove(new BasicDBObject("_id", "ethan"));

        printCollection(collection);
    }

    private static void printCollection(DBCollection collection) {
        DBCursor cursor = collection.find();
        try {
            while(cursor.hasNext()) {
                DBObject cur = cursor.next();
                System.out.println(cur);
            }
        } finally {
            cursor.close();
        }
    }

    private static DBCollection createCollection() {
        MongoClient client = null;
        try {
            client = new MongoClient();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        DB db = client.getDB("course");
        DBCollection collection = db.getCollection("updateTest");
        collection.drop();
        return collection;
    }
}
