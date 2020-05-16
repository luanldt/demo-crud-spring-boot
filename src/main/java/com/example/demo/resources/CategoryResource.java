package com.example.demo.resources;

import com.example.demo.dtos.CategoryDto;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/categories")
public class CategoryResource {

  private final CategoryService categoryService;

  @GetMapping("")
  public List<CategoryDto> findAll() {
    return categoryService.findAll();
  }

  @GetMapping("/{id}")
  public CategoryDto findById(@PathVariable("id") String id) throws ResourceNotFoundException {
    return categoryService.findById(id);
  }

  @PostMapping("")
  public CategoryDto create(@Valid @RequestBody CategoryDto dto) {
    return categoryService.create(dto);
  }

  @PutMapping("/{id}")
  public CategoryDto update(@PathVariable("id") String id,
                            @Valid @RequestBody CategoryDto dto) throws ResourceNotFoundException {
    return categoryService.update(id, dto);
  }

  @DeleteMapping("/{id}")
  public CategoryDto delete(@PathVariable("id") String id) throws ResourceNotFoundException {
    return categoryService.delete(id);
  }
}
