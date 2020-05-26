package com.example.demo.configs;

import com.example.demo.dtos.MovieDto;
import com.example.demo.dtos.PageDto;
import com.example.demo.services.MovieService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Component
@RabbitListener(queues = "movie.req")
public class MovieReceiver {

  @Autowired
  private MovieService movieService;

  @RabbitHandler
  public List<MovieDto> receive(Map<String, Object> params) {
    try {
      int page = (int) params.get("page");
      int size = (int) params.get("size");
      Pageable pageable = PageRequest.of(page, size);
      return movieService.findAll(pageable);
    } catch(Exception e) {
      return Collections.emptyList();
    }
  }
}
