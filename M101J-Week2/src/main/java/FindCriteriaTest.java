import com.mongodb.*;

import java.net.UnknownHostException;
import java.util.Random;

public class FindCriteriaTest {
    public static void main(String[] args) throws UnknownHostException {

        MongoClient client = new MongoClient();
        DB db = client.getDB("course");
        DBCollection collection = db.getCollection("findCriteriaTest");
        collection.drop();

        for (int i = 0; i < 10; i++) {
            collection.insert(
                    new BasicDBObject("x", new Random().nextInt(2))
                        .append("y", new Random().nextInt(100))
            );
        }

        // Limit our results to only those that have x with a value of 0
        // and y is greater than 10 and less than 90.
        DBObject query = new BasicDBObject("x", 0)
                .append("y", new BasicDBObject("$gt", 10).append("$lt", 90));

        // You can also create this using QueryBuilder.
        QueryBuilder builder = new QueryBuilder().start("x").is(0)
            .and("y").greaterThan(10).lessThan(90);

        System.out.println("Count:");
        // System.out.println(collection.count(query));
        System.out.println(collection.count(builder.get()));

        System.out.println("\nFind all:");
        // DBCursor cursor = collection.find(query);
        DBCursor cursor = collection.find(builder.get());

        try {
            while(cursor.hasNext()) {
                DBObject cur = cursor.next();
                System.out.println(cur);
            }
        } finally {
            cursor.close();
        }

    }
}
