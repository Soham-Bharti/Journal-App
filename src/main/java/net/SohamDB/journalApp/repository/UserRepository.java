package net.SohamDB.journalApp.repository;

import net.SohamDB.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {
    User findByUserName(String user);

    void deleteByUserName(String name);
}
