package com.example.demo.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
public class Movie {
  @Id
  @Access(AccessType.PROPERTY)
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

  @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinTable(name = "category_movie",
      joinColumns = @JoinColumn(name = "id_movie"),
      inverseJoinColumns = @JoinColumn(name = "id_category")
  )
  private Set<Categories> categories;

  @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private Set<Tag> tags;

  @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private Set<Background> backgrounds;

}
