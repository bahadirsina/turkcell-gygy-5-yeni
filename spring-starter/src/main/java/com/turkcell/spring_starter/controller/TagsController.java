package com.turkcell.spring_starter.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.spring_starter.dto.CreateTagRequest;
import com.turkcell.spring_starter.dto.CreatedTagResponse;
import com.turkcell.spring_starter.dto.ListTagResponse;
import com.turkcell.spring_starter.service.TagServiceImpl;

@RestController
@RequestMapping("/api/tags")
public class TagsController {

    private final TagServiceImpl tagServiceImpl;

    public TagsController(TagServiceImpl tagServiceImpl) {
        this.tagServiceImpl = tagServiceImpl;
    }

    @PostMapping()
    public CreatedTagResponse create(@RequestBody CreateTagRequest createTagRequest) {
        return this.tagServiceImpl.create(createTagRequest);
    }

    @GetMapping
    public List<ListTagResponse> getAll() {
        return this.tagServiceImpl.getAll();
    }

    @PutMapping("{id}")
    public CreatedTagResponse update(@PathVariable String id, @RequestBody CreateTagRequest entity) {
        return this.tagServiceImpl.update(id, entity);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id) {
        this.tagServiceImpl.delete(id);
    }

    @GetMapping("{id}")
    public CreatedTagResponse getById(@PathVariable String id) {
        return this.tagServiceImpl.getById(id);
    }
}
