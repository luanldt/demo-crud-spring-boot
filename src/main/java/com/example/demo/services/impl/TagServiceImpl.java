package com.example.demo.services.impl;

import com.example.demo.dtos.TagDto;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.models.Tag;
import com.example.demo.repositories.TagRepository;
import com.example.demo.services.TagService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

  private final TagRepository tagRepository;

  @Override
  @Transactional(readOnly = true)
  public List<TagDto> findAll() {
    List<Tag> entities = tagRepository.findAll();

    return entities.stream().map(entity -> {
      TagDto dto = new TagDto();
      dto.setId(entity.getId());
      dto.setTitle(entity.getTitle());
      return dto;
    }).collect(Collectors.toList());
  }

  @Override
  @Transactional(readOnly = true)
  public TagDto findById(int id) throws ResourceNotFoundException {
    return tagRepository.findById(id).map(entity -> {
      TagDto dto = new TagDto();
      BeanUtils.copyProperties(entity, dto);
      return dto;
    }).orElseThrow(() ->
        new ResourceNotFoundException("Tag " + id + " not found!")
    );
  }

  @Override
  @Transactional
  public TagDto create(TagDto dto) {
    Tag entity = new Tag();
    BeanUtils.copyProperties(dto, entity);
    tagRepository.save(entity);
    return dto;
  }

  @Override
  @Transactional
  public TagDto update(int id, TagDto dto) throws ResourceNotFoundException {
    return tagRepository.findById(id).map(entity -> {
      BeanUtils.copyProperties(dto, entity, "id");
      return dto;
    }).orElseThrow(() ->
        new ResourceNotFoundException("Tag " + id + " not found!")
    );
  }

  @Override
  @Transactional
  public TagDto delete(int id) throws ResourceNotFoundException {
    return tagRepository.findById(id).map(entity -> {
      TagDto dto = new TagDto();
      BeanUtils.copyProperties(entity, dto);

      tagRepository.delete(entity);

      return dto;
    }).orElseThrow(() ->
        new ResourceNotFoundException("Tag " + id + " not found!")
    );
  }
}
