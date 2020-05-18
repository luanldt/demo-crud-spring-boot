package com.example.demo.services.impl;

import com.example.demo.dtos.CategoryDto;
import com.example.demo.dtos.MovieDto;
import com.example.demo.dtos.TagDto;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.models.Movie;
import com.example.demo.repositories.MovieRepository;
import com.example.demo.services.MovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

  private final MovieRepository movieRepository;

  @Override
  @Transactional(readOnly = true)
  public List<MovieDto> findAll() {
    List<Movie> entities = movieRepository.findAllFetchChild();

    return entities.parallelStream().map(entity -> {
      MovieDto dto = new MovieDto();
      BeanUtils.copyProperties(entity, dto);
      dto.setCategories(new LinkedList<>());
      entity.getCategories().forEach(category -> {
        CategoryDto categoryDto = new CategoryDto();
        BeanUtils.copyProperties(category, categoryDto);
        dto.getCategories().add(categoryDto);
      });

      dto.setTags(new LinkedList<>());
      entity.getTags().forEach(tag -> {
        TagDto tagDto = new TagDto();
        tagDto.setTitle(tag.getTitle());
        tagDto.setId(tag.getId());
        dto.getTags().add(tagDto);
      });

      dto.setBackgrounds(new LinkedList<>());
      entity.getBackgrounds().forEach(background -> {
        dto.getBackgrounds().add(background.getUrl());
      });


      return dto;
    }).collect(Collectors.toList());
  }

  @Override
  @Transactional(readOnly = true)
  public MovieDto findById(int id) throws ResourceNotFoundException {
    return movieRepository.findById(id).map(entity -> {
      MovieDto dto = new MovieDto();
      BeanUtils.copyProperties(entity, dto);
      return dto;
    }).orElseThrow(() ->
        new ResourceNotFoundException("Movie " + id + " not found!")
    );
  }

  @Override
  @Transactional
  public MovieDto create(MovieDto dto) {
    Movie entity = new Movie();
    BeanUtils.copyProperties(dto, entity);
    movieRepository.save(entity);
    return dto;
  }

  @Override
  @Transactional
  public MovieDto update(int id, MovieDto dto) throws ResourceNotFoundException {
    return movieRepository.findById(id).map(entity -> {
      BeanUtils.copyProperties(dto, entity, "id");
      return dto;
    }).orElseThrow(() ->
        new ResourceNotFoundException("Movie " + id + " not found!")
    );
  }

  @Override
  @Transactional
  public MovieDto delete(int id) throws ResourceNotFoundException {
    return movieRepository.findById(id).map(entity -> {
      MovieDto dto = new MovieDto();
      BeanUtils.copyProperties(entity, dto);

      movieRepository.delete(entity);

      return dto;
    }).orElseThrow(() ->
        new ResourceNotFoundException("Movie " + id + " not found!")
    );
  }
}
