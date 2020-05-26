package com.example.demo.services;

import com.example.demo.dtos.MovieDto;
import com.example.demo.exceptions.ResourceNotFoundException;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MovieService {
  List<MovieDto> findAll(Pageable pageable);

  MovieDto findById(int id) throws ResourceNotFoundException;

  MovieDto create(MovieDto dto);

  MovieDto update(int id, MovieDto dto) throws ResourceNotFoundException;

  MovieDto delete(int id) throws ResourceNotFoundException;
}
