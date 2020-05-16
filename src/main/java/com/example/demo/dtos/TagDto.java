package com.example.demo.dtos;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class TagDto {
  private int id;

  @NotNull
  private String title;
}
