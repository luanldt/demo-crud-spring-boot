package com.example.demo.resources;

import com.example.demo.dtos.TagDto;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.services.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/tags")
public class TagResource {

  private final TagService tagService;

  @GetMapping("")
  public List<TagDto> findAll() {
    return tagService.findAll();
  }

  @GetMapping("/{id}")
  public TagDto findById(@PathVariable("id") int id) throws ResourceNotFoundException {
    return tagService.findById(id);
  }

  @PostMapping("")
  public TagDto create(@Valid @RequestBody TagDto dto) {
    return tagService.create(dto);
  }

  @PutMapping("/{id}")
  public TagDto update(@PathVariable("id") int id,
                            @Valid @RequestBody TagDto dto) throws ResourceNotFoundException {
    return tagService.update(id, dto);
  }

  @DeleteMapping("/{id}")
  public TagDto delete(@PathVariable("id") int id) throws ResourceNotFoundException {
    return tagService.delete(id);
  }
}
