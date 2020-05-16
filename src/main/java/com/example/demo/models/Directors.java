package com.example.demo.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Directors {
  @Id
  @Access(AccessType.PROPERTY)
  private int id;

  @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
  @JoinColumn(name = "id_movie")
  private Movie movie;

  private String title;
}
