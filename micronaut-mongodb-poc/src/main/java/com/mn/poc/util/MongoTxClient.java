package com.mn.poc.util;

import com.mn.poc.model.Customer;
import com.mn.poc.model.User;
import com.mongodb.client.model.ReplaceOptions;
import com.mongodb.reactivestreams.client.MongoClient;
import io.micronaut.context.annotation.Property;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.bson.Document;

import javax.inject.Singleton;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

/**
 * MongoTxClient class used is generic class to handle CRUD transaction into mongodb.
 *
 * @author Ravi
 * @CopyRight (C) All rights reserved to Ravi POC Inc. It's Illegal to reproduce this code.
 */
@Slf4j
@Singleton
public class MongoTxClient {

    private MongoClient mongoClient;

    @Property(name = "mongo.database")
    public String database;

    @Property(name = "mongo.collection")
    public String collection;


    public MongoTxClient(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    /**
     * Method to save or update for customer and user.
     *
     * @param dataEntity
     * @param <T>
     */
    public <T> void saveOrUpdate(T dataEntity) {
        try {

            /** Setting the option to upsert to insert or update based on the IHRID  */
            ReplaceOptions replaceOptions = new ReplaceOptions();
            replaceOptions.upsert(true);

            /** Translate data entity into Document.*/
            String dataNode = JsonUtils.serializeJson(dataEntity);
            Document data = Document.parse(dataNode);

            /** Generating the unique key based on entity type. */
            String uniqueKey = dataEntity.getClass().getSimpleName().equalsIgnoreCase("Customer") ? "CUST".concat(String.valueOf(RandomUtils.nextInt())) :
                    "USR".concat(String.valueOf(RandomUtils.nextInt()));

            /** Saving data into mongo database  */
            Single<Document> result = Single.fromPublisher(mongoClient.getDatabase(database)
                    .getCollection(collection)
                    .replaceOne(eq("_id", uniqueKey), (data), replaceOptions)).map(success -> data);

            Document savedUpdatedDoc = result.blockingGet();
            log.debug("Document inserted into mongo with ID: {}", savedUpdatedDoc.get("_id"));
        } catch (Exception ex) {
            log.error("unable to insert or update document into mongoDB exception: {}", ex.getMessage());
        }
    }

    /**
     * Method to find users by id.
     *
     * @param userId
     * @return Maybe
     */
    public User findByUserId(String userId) throws Exception {
        User user = new User();
        User searchedUser = (User) findById(user, "userId", userId);
        return searchedUser;
    }


    /**
     * Method to find users by id.
     *
     * @param customerId
     * @return Maybe
     */
    public Customer findByCustomerId(String customerId) throws Exception {
        Customer customer = new Customer();
        Customer searchedCustomer = (Customer) findById(customer, "customerId", customerId);
        return searchedCustomer;
    }


    /**
     * Method to get all users by id prefix.
     *
     * @return Single
     * @throws Exception
     */
    public Single<List<Document>> findAllUsers() throws Exception {
        Single<List<Document>> allUsers = findAll("/USR/");
        return allUsers;
    }

    /**
     * Method to get all customers by id prefix.
     *
     * @return Single
     * @throws Exception
     */
    public Single<List<Document>> findAllCustomers() throws Exception {
        Single<List<Document>> allCustomers = findAll("/CUST/");
        return allCustomers;
    }


    /**
     * Method to query all documents by id prefix.
     *
     * @param idPrefix
     * @return Single
     */
    private Single<List<Document>> findAll(String idPrefix) {
        Single<List<Document>> allDocuments = Flowable.fromPublisher(mongoClient.getDatabase(database).getCollection(collection).find(eq("_id", idPrefix))).toList();
        return allDocuments;
    }

    /**
     * Method to find by given search key and value.
     *
     * @param entity
     * @param searchKey
     * @param searchValue
     * @return Object
     * @throws Exception
     */
    private Object findById(Object entity, String searchKey, String searchValue) throws Exception {
        Maybe<Document> filteredCustomer = Flowable.fromPublisher(mongoClient.getDatabase(database).getCollection(collection)
                .find(eq(searchKey, searchValue)).limit(1)).firstElement();

        /** Deserialize search document to user object. */
        String searchedJson = JsonUtils.serializeJson(filteredCustomer.blockingGet());
        Object deserializeObj = JsonUtils.deserializeJson(entity.getClass(), searchedJson);

        Object searchedCustomer = deserializeObj;
        return searchedCustomer;
    }
}
