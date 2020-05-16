package com.example.demo.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

@Data
@Entity
@Table(name = "tags")
public class Tag {
  @Id
  @Access(AccessType.PROPERTY)
  private int id;

  @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
  @JoinColumn(name = "id_movie")
  private Movie movie;

  private String title;

  public int hashCode() {
    return Objects.hashCode(id);
  }
}
