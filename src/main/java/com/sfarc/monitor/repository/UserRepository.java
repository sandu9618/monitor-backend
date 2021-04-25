package com.sfarc.monitor.repository;

import com.sfarc.monitor.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository< User , String >
{
	List<User> findUsersByUserSensors( String userSensor );
}
