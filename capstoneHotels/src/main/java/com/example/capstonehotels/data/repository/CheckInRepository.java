package com.example.capstonehotels.data.repository;

import com.example.capstonehotels.data.model.CheckIn;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;

public interface CheckInRepository extends MongoRepository<CheckIn, String> {

}
