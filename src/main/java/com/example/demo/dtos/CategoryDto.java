package com.example.demo.dtos;

import lombok.Data;

@Data
public class CategoryDto {
  private String id;

  private String type;

  private String title;

  private String description;

  private String thumbnail;

  private String background;

  private int cursor;

  private String slug;

  private String logo;
}
