package com.example.crud.repositories;

import com.example.crud.entities.AuthorEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends CrudRepository<AuthorEntity, Long> {

    @Query(value = "select * from authors where age > ?1", nativeQuery = true)
    List<AuthorEntity> findAuthorsWithAgeGreaterThan(Integer age);

    @Query(value = "select id, name from authors where age < ?1", nativeQuery = true)
    List<Object[]> findAuthorsWithAgeLessThan(Integer age);
}
