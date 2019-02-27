package com.lambdaschool.sqliteorders.controllers;

import com.lambdaschool.sqliteorders.models.Order;
import com.lambdaschool.sqliteorders.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "orders", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderController {
  @Autowired
  OrderRepository repository;

  @GetMapping("")
  public List<Order> all() {
    return repository.findAll();
  }

  @GetMapping("{id}")
  public Order oneById(@PathVariable Long id) {
    var found = repository.findById(id);
    if (found.isPresent()) {
      return found.get();
    }
    return null;
  }

  @PostMapping("")
  public Order add(@RequestBody Order order) {
    return repository.save(order);
  }

  @PutMapping("{id}")
  public Order update(@RequestBody Order order, @PathVariable Long id) {
    var found = repository.findById(id);
    if (found.isPresent()) {
      order.setId(id);
      return repository.save(order);
    }
    return null;
  }
}
