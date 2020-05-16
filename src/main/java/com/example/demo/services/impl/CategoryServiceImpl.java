package com.example.demo.services.impl;

import com.example.demo.dtos.CategoryDto;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.models.Categories;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.services.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

  private final CategoryRepository categoryRepository;

  @Override
  @Transactional(readOnly = true)
  public List<CategoryDto> findAll() {
    List<Categories> entities = categoryRepository.findAll();

    return entities.stream().map(entity -> {
      CategoryDto dto = new CategoryDto();
      BeanUtils.copyProperties(entity, dto);
      return dto;
    }).collect(Collectors.toList());
  }

  @Override
  @Transactional(readOnly = true)
  public CategoryDto findById(String id) throws ResourceNotFoundException {
    return categoryRepository.findById(id).map(entity -> {
      CategoryDto dto = new CategoryDto();
      BeanUtils.copyProperties(entity, dto);
      return dto;
    }).orElseThrow(() ->
        new ResourceNotFoundException("Category " + id + " not found!")
    );
  }

  @Override
  @Transactional
  public CategoryDto create(CategoryDto dto) {
    Categories entity = new Categories();
    BeanUtils.copyProperties(dto, entity);
    categoryRepository.save(entity);
    return dto;
  }

  @Override
  @Transactional
  public CategoryDto update(String id, CategoryDto dto) throws ResourceNotFoundException {
    return categoryRepository.findById(id).map(entity -> {
      BeanUtils.copyProperties(dto, entity, "id");
      return dto;
    }).orElseThrow(() ->
        new ResourceNotFoundException("Category " + id + " not found!")
    );
  }

  @Override
  @Transactional
  public CategoryDto delete(String id) throws ResourceNotFoundException {
    return categoryRepository.findById(id).map(entity -> {
      CategoryDto dto = new CategoryDto();
      BeanUtils.copyProperties(entity, dto);

      categoryRepository.delete(entity);

      return dto;
    }).orElseThrow(() ->
        new ResourceNotFoundException("Category " + id + " not found!")
    );
  }
}
