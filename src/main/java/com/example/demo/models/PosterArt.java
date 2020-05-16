package com.example.demo.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "posterarts")
public class PosterArt {
  @Id
  @Access(AccessType.PROPERTY)
  private int id;

  @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
  @JoinColumn(name = "id_movie")
  private Movie movie;

  private String url;
}
