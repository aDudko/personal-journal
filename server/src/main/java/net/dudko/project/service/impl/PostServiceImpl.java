package net.dudko.project.service.impl;

import lombok.AllArgsConstructor;
import net.dudko.project.domain.mapper.PostMapper;
import net.dudko.project.domain.entity.Post;
import net.dudko.project.domain.entity.PostStatus;
import net.dudko.project.domain.repository.PostRepository;
import net.dudko.project.model.dto.PostDto;
import net.dudko.project.model.exception.PostException;
import net.dudko.project.service.PostService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    public List<PostDto> getPosts() {
        var posts = postRepository.findAllActive();
        return posts.stream().map(PostMapper::mapToPostDto).toList();
    }

    public PostDto createPost(PostDto postDto) {
        var post = PostMapper.mapToPost(postDto);
        post.setCreateDate(LocalDateTime.now());
        post.setStatus(PostStatus.CREATED);
        post = postRepository.save(post);
        return PostMapper.mapToPostDto(post);
    }

    public PostDto getPost(Long id) {
        return PostMapper.mapToPostDto(getPostFromDB(id));
    }

    public PostDto updatePost(Long id, PostDto postDto) {
        var post = getPostFromDB(id);
        post.setTitle(postDto.title());
        post.setChangeDate(postDto.date());
        post.setTag(postDto.tag());
        post.setText(postDto.text());
        post = postRepository.save(post);
        return PostMapper.mapToPostDto(post);
    }

    public void deletePost(Long id) {
        Post post = postRepository.findById(id).orElseThrow();
        post.setStatus(PostStatus.DELETED);
        postRepository.save(post);
    }

    private Post getPostFromDB(Long id) {
        return postRepository.findById(id).orElseThrow(() -> new PostException("Post doesn't exist"));
    }

}
