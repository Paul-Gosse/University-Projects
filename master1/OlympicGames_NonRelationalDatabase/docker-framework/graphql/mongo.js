const MongoClient = require('mongodb').MongoClient;
const assert = require('assert');

// Connection URL
//const url = 'url_to_mongo_root'; 
//const url = 'url_to_mongo_root';
//const url = 'url_to_mongo_root';
const url = 'url_to_mongo_root';

// Database Name
const dbName = 'database_name';

// Create a new MongoClient
const client = new MongoClient(url, {useUnifiedTopology: true});

const findDocuments = function (db, col, query, callback) {
    // Get the documents collection
    const collection = db.collection(col);
    // Find some documents
    collection.find(query).toArray(function (err, docs) {
        assert.equal(err, null);
        callback(docs);
    });
}

const findOneDocument = function (db, col, query, callback) {
    // Get the documents collection
    const collection = db.collection(col);
    // Find some documents
//    collection.find(query).toArray(function (err, docs) {
//        assert.equal(err, null);
//        callback(docs);
//    });
    collection.findOne(query, function (err, docs) {
        assert.equal(err, null);
        callback(docs);
    });
}

// Use connect method to connect to the Server
client.connect(function (err) {
    assert.equal(err, null);
    console.log("Connected correctly to server");
    const db = client.db(dbName);
    findOneDocument(db, 'pop', {id: "10"}, function (data) {
        console.log("Found the following records");
        console.log('data', data);
        client.close();
    });
});



