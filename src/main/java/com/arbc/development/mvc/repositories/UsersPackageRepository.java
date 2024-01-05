package com.arbc.development.mvc.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.arbc.development.mvc.models.entities.TravelPackage;
import com.arbc.development.mvc.models.entities.User;
import com.arbc.development.mvc.models.entities.UsersPackage;

public interface UsersPackageRepository extends CrudRepository<UsersPackage, Long> {
    Optional<UsersPackage> findByUserAndTraPackage(User user, TravelPackage tPackage);

    List<UsersPackage> findByTraPackage(TravelPackage tPackage);
}
