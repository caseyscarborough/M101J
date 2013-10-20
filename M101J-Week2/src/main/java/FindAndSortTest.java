import com.mongodb.*;

import java.net.UnknownHostException;

public class FindAndSortTest {

    public static void main(String[] args) {
        DBCollection collection = getCollection();

        DBObject query = new BasicDBObject("type", "homework");
        DBCursor cursor = collection.find(query).sort(new BasicDBObject("student_id", 1).append("score", 1));

        try {
            int studentId = -1;
            int dbStudentId;
            while(cursor.hasNext()) {
                DBObject cur = cursor.next();
                dbStudentId = (Integer) cur.get("student_id");
                if(studentId != dbStudentId) {
                        collection.remove(cur);
                        studentId = dbStudentId;
                }
            }
        } finally {
            cursor.close();
        }
    }

    private static DBCollection getCollection() {
        MongoClient client = null;
        try {
            client = new MongoClient();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        DB db = client.getDB("students");
        DBCollection collection = db.getCollection("grades");
        return collection;
    }
}
