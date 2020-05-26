package com.example.demo.resources;

import com.example.demo.dtos.MovieDto;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.services.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/movies")
public class MovieResource {

  private final MovieService movieService;

  @GetMapping("")
  public List<MovieDto> findAll(Pageable pageable) {
    return movieService.findAll(pageable);
  }

  @GetMapping("/{id}")
  public MovieDto findById(@PathVariable("id") int id) throws ResourceNotFoundException {
    return movieService.findById(id);
  }

  @PostMapping("")
  public MovieDto create(@Valid @RequestBody MovieDto dto) {
    return movieService.create(dto);
  }

  @PutMapping("/{id}")
  public MovieDto update(@PathVariable("id") int id,
                            @Valid @RequestBody MovieDto dto) throws ResourceNotFoundException {
    return movieService.update(id, dto);
  }

  @DeleteMapping("/{id}")
  public MovieDto delete(@PathVariable("id") int id) throws ResourceNotFoundException {
    return movieService.delete(id);
  }
}
