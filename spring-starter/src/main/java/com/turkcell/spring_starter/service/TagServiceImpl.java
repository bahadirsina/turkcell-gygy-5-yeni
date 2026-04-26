package com.turkcell.spring_starter.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.turkcell.spring_starter.dto.CreateTagRequest;
import com.turkcell.spring_starter.dto.CreatedTagResponse;
import com.turkcell.spring_starter.dto.ListTagResponse;
import com.turkcell.spring_starter.entity.Tag;
import com.turkcell.spring_starter.repository.TagRepository;

@Service
public class TagServiceImpl {

    private final TagRepository tagRepository;

    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public CreatedTagResponse create(CreateTagRequest createTagRequest) {
        Tag tag = new Tag();
        tag.setName(createTagRequest.getName());

        tag = this.tagRepository.save(tag);

        CreatedTagResponse response = new CreatedTagResponse();
        response.setId(tag.getId());
        response.setName(tag.getName());

        return response;
    }

    public List<ListTagResponse> getAll() {
        List<Tag> tags = this.tagRepository.findAll();
        List<ListTagResponse> responseList = new ArrayList<>();

        for (Tag tag : tags) {
            ListTagResponse response = new ListTagResponse();
            response.setId(tag.getId());
            response.setName(tag.getName());
            responseList.add(response);
        }

        return responseList;
    }

    public CreatedTagResponse update(String id, CreateTagRequest entity) {
        UUID tagId = UUID.fromString(id);
        Tag existingTag = this.tagRepository.findById(tagId)
            .orElseThrow(() -> new RuntimeException("Tag not found with id: " + id));

        existingTag.setName(entity.getName());
        Tag updatedTag = this.tagRepository.save(existingTag);

        CreatedTagResponse response = new CreatedTagResponse();
        response.setId(updatedTag.getId());
        response.setName(updatedTag.getName());

        return response;
    }

    public CreatedTagResponse delete(String id) {
        UUID tagId = UUID.fromString(id);
        Tag tag = this.tagRepository.findById(tagId)
            .orElseThrow(() -> new RuntimeException("Tag not found with id: " + id));

        CreatedTagResponse response = new CreatedTagResponse();
        response.setId(tag.getId());
        response.setName(tag.getName());

        this.tagRepository.delete(tag);

        return response;
    }

    public CreatedTagResponse getById(String id) {
        UUID tagId = UUID.fromString(id);
        Tag tag = this.tagRepository.findById(tagId)
            .orElseThrow(() -> new RuntimeException("Tag not found with id: " + id));

        CreatedTagResponse response = new CreatedTagResponse();
        response.setId(tag.getId());
        response.setName(tag.getName());

        return response;
    }
}
