package com.example.demo.repositories;

import com.example.demo.models.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

  @EntityGraph(attributePaths = {"categories", "tags", "backgrounds"})
  @Query("SELECT m FROM Movie m")
  Page<Movie> findAllFetchChild(Pageable pageable);

}
