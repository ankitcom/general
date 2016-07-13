package com.crossover.data;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class MongoAccess implements DataAccess {
	
	private static Logger log = LoggerFactory.getLogger(MongoAccess.class);
	
	private static MongoClient getMongoClient() throws Exception {
		MongoClient mongoClient = new MongoClient();
		return mongoClient;
	}

	public void storeLog(String userId, String userLog) throws Exception {
		
		log.trace("in storeLog");
		MongoClient mongoClient = getMongoClient();
		MongoDatabase db = mongoClient.getDatabase("test");
		MongoCollection<Document> mc=db.getCollection("userLogs");
		mc.updateOne(new Document("userId",userId), new Document("$push",new Document("entries",Document.parse(userLog))));
		Document doc=new Document(userId, Document.parse(userLog));
		mc.insertOne(doc);
		mongoClient.close();
	}

	public List<String> getUserLogs() throws Exception{
		
		log.trace("in getUserLogs");
		List<String> userlogColl=new ArrayList<String>();
		MongoClient mongoClient = getMongoClient();
		MongoDatabase db = mongoClient.getDatabase("test");
		MongoCollection<Document> mc=db.getCollection("userLogs");
		MongoCursor<Document> cursor = mc.find().iterator();
		try {
		    while (cursor.hasNext()) {
		        userlogColl.add(cursor.next().toJson());
		    }
		} finally {
		    cursor.close();
		}
		mongoClient.close();
		return userlogColl;
	}

}
