import com.mongodb.*;

import java.net.UnknownHostException;
import java.util.Random;

public class DotNotationTest {
    public static void main(String[] args) throws UnknownHostException {
        MongoClient client = new MongoClient();
        DB db = client.getDB("course");
        DBCollection collection = db.getCollection("dotNotationTest");
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

        //
        QueryBuilder query = new QueryBuilder().start("start.x").greaterThan(50);
        DBCursor cursor = collection.find(
            query.get(),
            new BasicDBObject("start.y", true).append("_id", false)
        );

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
