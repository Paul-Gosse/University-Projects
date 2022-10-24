const MongoClient = require('mongodb').MongoClient;
var ObjectId = require('mongodb').ObjectID;
const assert = require('assert');

// Connection URL
const url = 'url_to_mongo_root';
//const url = 'url_to_mongo_root';
//const url = 'url_to_mongo_root';

// Database Name
const dbName = 'olympics';

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
    collection.findOne(query, function (err, docs) {
        assert.equal(err, null);
        callback(docs);
    });
}

client.connect(function (err) {
    assert.equal(err, null);
    console.log("Connected correctly to the MongoDB server");
});


// un résolveur simple pour la requête 'books' de type Query
// qui renvoie la variable 'books'
const resolvers = {
    Query: {
	olympics(root, args, context) {
	    return new Promise((resolve, reject) => {
                const db = client.db(dbName);
                findDocuments(db, 'sports', {}, resolve);
	    }).then(result => {
		return result
	    });
	},
	olympicsSeason(root, args, context){
	    return new Promise((resolve, reject) => {
		const db = client.db(dbName);
		const { season } = args;
		findDocuments(db, 'sports', {"season":season}, resolve);
	    }).then(result => {
		return result
	    });
	},
	olympicsSports(root, args, context){
	    return new Promise((resolve, reject) => {
		const db = client.db(dbName);
		const { sport } = args;
		findDocuments(db, 'sports', {"sport":sport}, resolve);
	    }).then(result => {
		return result
	    });
	},
	winterMedals(root, args, context){
	    return new Promise((resolve, reject) => {
		const db = client.db(dbName);
		findDocuments(db, 'winterMedals', {}, resolve);
	    }).then(result => {
		return result
	    });
	},
        summerMedals(root, args, context){
            return new Promise((resolve, reject) => {
                const db = client.db(dbName);
                findDocuments(db, 'summerMedals', {}, resolve);
            }).then(result => {
                return result
            });
        },
        winterPerYear(root, args, context){
            return new Promise((resolve, reject) => {
                const db = client.db(dbName);
		const { Year } = args;
                findDocuments(db, 'winterMedals', {"Year":Year}, resolve);
            }).then(result => {
                return result
            });
        },
        summerPerYear(root, args, context){
            return new Promise((resolve, reject) => {
                const db = client.db(dbName);
                const { Year } = args;
                findDocuments(db, 'summerMedals', {"Year":Year}, resolve);
            }).then(result => {
                return result
            });
        },
        winterPerCountry(root, args, context){
            return new Promise((resolve, reject) => {
                const db = client.db(dbName);
                const { Country_Name } = args;
                findDocuments(db, 'winterMedals', {"Country_Name":Country_Name}, resolve);
            }).then(result => {
                return result
            });
        },
        summerPerCountry(root, args, context){
            return new Promise((resolve, reject) => {
                const db = client.db(dbName);
                const { Country_Name } = args;
                findDocuments(db, 'summerMedals', {"Country_Name":Country_Name}, resolve);
            }).then(result => {
                return result
            });
        },
        hostCities(root, args, context){
            return new Promise((resolve, reject) => {
                const db = client.db(dbName);
                findDocuments(db, 'hostCities', {}, resolve);
            }).then(result => {
                return result
            });
        },
        hostCitiesSeason(root, args, context){
            return new Promise((resolve, reject) => {
                const db = client.db(dbName);
                const { season } = args;
                findDocuments(db, 'hostCities', {"season":season}, resolve);
            }).then(result => {
                return result
            });
        }
    }
};

// on exporte la définition de 'resolvers'
module.exports = resolvers;
