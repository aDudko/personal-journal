package net.dudko.project.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import net.dudko.project.model.dto.PostDto;
import net.dudko.project.service.PostService;

import java.util.List;

@RestController
@RequestMapping("/post")
@Tag(name = "post", description = "API for using posts")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @Operation(tags = "post", summary = "Get all posts")
    @GetMapping(value = "/list")
    public ResponseEntity<List<PostDto>> list() {
        return ResponseEntity.ok(postService.getPosts());
    }

    @Operation(tags = "post", summary = "Create post")
    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    @Operation(tags = "post", summary = "Get post")
    @GetMapping(value = "/{id}")
    public ResponseEntity<PostDto> getPost(@PathVariable Long id) {
        return ResponseEntity.ok(postService.getPost(id));
    }

    @Operation(tags = "post", summary = "Update post")
    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<PostDto> updatePost(@PathVariable Long id, @RequestBody PostDto postDto) {
        return ResponseEntity.ok(postService.updatePost(id, postDto));
    }

    @Operation(tags = "post", summary = "Delete post")
    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<String> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.ok("Post is deleted successfully");
    }

}

