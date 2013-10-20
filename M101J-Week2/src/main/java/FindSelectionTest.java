import com.mongodb.*;

import java.net.UnknownHostException;
import java.util.Random;

public class FindSelectionTest {

    public static void main(String[] args) throws UnknownHostException {
        MongoClient client = new MongoClient();
        DB db = client.getDB("course");
        DBCollection collection = db.getCollection("findSelectionTest");
        collection.drop();

        Random rand = new Random();
        for (int i = 0; i < 10; i++) {
            collection.insert(
                    new BasicDBObject("x", rand.nextInt(2))
                            .append("y", rand.nextInt(100))
                            .append("z", rand.nextInt(1000))
            );
        }

        QueryBuilder query = new QueryBuilder().start("x").is(0)
                .and("y").greaterThan(10).lessThan(70);

        System.out.println("Count:");
        System.out.println(collection.count(query.get()));

        System.out.println("\nFind all:");
        // Get only the values for y in our results.
        DBCursor cursor = collection.find(query.get(),
                new BasicDBObject("y", true).append("_id", false));

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
