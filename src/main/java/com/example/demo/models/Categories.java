package com.example.demo.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
public class Categories {
  @Id
  @Access(AccessType.PROPERTY)
  private String id;

  private String type;

  private String title;

  private String description;

  private String thumbnail;

  private String background;

  private int cursor;

  private String slug;

  private String logo;

  @ManyToMany(mappedBy = "categories", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private Set<Movie> movies;

  public int hashCode() {
    return Objects.hashCode(id);
  }
}
