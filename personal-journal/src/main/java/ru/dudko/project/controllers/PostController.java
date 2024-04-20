package ru.dudko.project.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.dudko.project.dto.PostDto;
import ru.dudko.project.service.PostService;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/post")
@RequiredArgsConstructor
@Tag(name = "post", description = "API for using posts")
public class PostController {

    private final PostService postService;

    @Operation(tags = "post", summary = "Get all posts")
    @GetMapping("/list")
    public List<PostDto> list() {
        return postService.getPosts();
    }

    @Operation(tags = "post", summary = "Create post")
    @PutMapping("/create")
    public PostDto createPost(PostDto postDto) {
        return postService.createPost(postDto);
    }

    @Operation(tags = "post", summary = "Get post")
    @GetMapping("/{id}")
    public PostDto getPost(@PathVariable Long id) {
        return postService.getPost(id);
    }

    @Operation(tags = "post", summary = "Update post")
    @PostMapping("/{id}")
    public PostDto updatePost(@PathVariable Long id, PostDto postDto) {
        return postService.updatePost(id, postDto);
    }

    @Operation(tags = "post", summary = "Delete post")
    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id) {
        postService.deletePost(id);
    }

}