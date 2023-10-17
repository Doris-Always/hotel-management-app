package com.example.capstonehotels.data.repository;

import com.example.capstonehotels.data.model.Guest;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface GuestRepository extends MongoRepository<Guest, String> {
   Optional<Guest> findGuestByTelephoneNumber(String telephoneNumber);
}
