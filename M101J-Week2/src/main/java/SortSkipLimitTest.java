import com.mongodb.*;

import java.net.UnknownHostException;
import java.util.Random;

public class SortSkipLimitTest {
    public static void main(String[] args) throws UnknownHostException {
        MongoClient client = new MongoClient();
        DB db = client.getDB("course");
        DBCollection collection = db.getCollection("sortSkipLimitTest");
        collection.drop();

        Random rand = new Random();

        for (int i = 0; i < 10; i++) {
            collection.insert(
                new BasicDBObject("_id", i)
                    .append("start",
                            new BasicDBObject("x", rand.nextInt(90) + 10)
                                    .append("y", rand.nextInt(90) + 10))
                    .append("end",
                            new BasicDBObject("x", rand.nextInt(90) + 10)
                                    .append("y", rand.nextInt(90) + 10)
                    )
            );
        }

        DBCursor cursor = collection.find()
            .sort(new BasicDBObject("start.x", 1).append("start.y", -1)).skip(2).limit(10);
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
