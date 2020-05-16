package com.example.demo.services;

import com.example.demo.dtos.MovieDto;
import com.example.demo.exceptions.ResourceNotFoundException;

import java.util.List;

public interface MovieService {
  List<MovieDto> findAll();

  MovieDto findById(int id) throws ResourceNotFoundException;

  MovieDto create(MovieDto dto);

  MovieDto update(int id, MovieDto dto) throws ResourceNotFoundException;

  MovieDto delete(int id) throws ResourceNotFoundException;
}
