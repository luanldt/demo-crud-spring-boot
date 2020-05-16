package com.example.demo.services;

import com.example.demo.dtos.CategoryDto;
import com.example.demo.exceptions.ResourceNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CategoryService {
  List<CategoryDto> findAll();

  CategoryDto findById(String id) throws ResourceNotFoundException;

  CategoryDto create(CategoryDto dto);

  CategoryDto update(String id, CategoryDto dto) throws ResourceNotFoundException;

  CategoryDto delete(String id) throws ResourceNotFoundException;
}
