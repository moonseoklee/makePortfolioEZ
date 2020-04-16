package com.example.demo.domain;

import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional

public interface ProjectsRepository  extends CrudRepository<Project,Long> {
    List<Project> findAllByUserId(String userId);
}
