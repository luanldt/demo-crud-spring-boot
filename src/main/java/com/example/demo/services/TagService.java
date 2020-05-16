package com.example.demo.services;

import com.example.demo.dtos.TagDto;
import com.example.demo.exceptions.ResourceNotFoundException;

import java.util.List;

public interface TagService {
  List<TagDto> findAll();

  TagDto findById(int id) throws ResourceNotFoundException;

  TagDto create(TagDto dto);

  TagDto update(int id, TagDto dto) throws ResourceNotFoundException;

  TagDto delete(int id) throws ResourceNotFoundException;
}
