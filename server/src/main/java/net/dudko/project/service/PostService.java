package net.dudko.project.service;

import net.dudko.project.model.dto.PostDto;

import java.util.List;

public interface PostService {

    List<PostDto> getPosts();

    PostDto createPost(PostDto postDto);

    PostDto getPost(Long id);

    PostDto updatePost(Long id, PostDto postDto);

    void deletePost(Long id);

}
