package com.example.demo.dtos;

import lombok.Data;

import java.util.List;

@Data
public class MovieDto {
  private int id;

  private String type;

  private String category;

  private String title;

  private String url;

  private int duration;

  private String description;

  private int availabilityDuration;

  private boolean hasTrailer;

  private boolean hasSubtitle;

  private int year;

  private String importId;

  private List<CategoryDto> categories;

  private List<TagDto> tags;
}
