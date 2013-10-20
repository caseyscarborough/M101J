import com.mongodb.*;

import java.net.UnknownHostException;
import java.util.Arrays;

public class InsertTest {

    public static void main(String[] args) throws UnknownHostException {

        MongoClient client = new MongoClient();
        DB courseDB = client.getDB("course");

        DBCollection collection = courseDB.getCollection("insertTest");
        collection.drop();

        DBObject doc = new BasicDBObject("x", 1);
        DBObject doc2 = new BasicDBObject("x", 2);

        System.out.println(doc);
        collection.insert(Arrays.asList(doc, doc2));
        collection.insert(doc);
        System.out.println(doc);

    }

}
