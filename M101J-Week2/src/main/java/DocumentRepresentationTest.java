import com.mongodb.BasicDBObject;

import java.util.Date;

public class DocumentRepresentationTest {
    public static void main(String[] args) {

        // Create and build our new document
        BasicDBObject doc = new BasicDBObject();

        doc.put("name", "Casey Scarborough");
        doc.put("age", 22);
        doc.put("birthdate", new Date(124184983));
        doc.put("programmer", true);
        doc.put("address", new BasicDBObject("street", "20 Main")
                    .append("town", "Atlanta")
                    .append("zip", 30303));

    }
}
