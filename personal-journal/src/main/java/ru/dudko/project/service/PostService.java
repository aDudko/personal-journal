package ru.dudko.project.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.dudko.project.domain.model.Post;
import ru.dudko.project.domain.model.PostStatus;
import ru.dudko.project.domain.repository.PostRepository;
import ru.dudko.project.dto.PostDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public List<PostDto> getPosts() {
        return postRepository.findAllActive()
                .stream()
                .map(PostDto::mapToPostDto)
                .collect(Collectors.toList());
    }

    public PostDto createPost(PostDto postDto) {
        Post post = PostDto.mapToPost(postDto);
        return PostDto.mapToPostDto(postRepository.save(post));
    }

    public PostDto getPost(Long id) {
        return PostDto.mapToPostDto(postRepository.findById(id).orElseThrow());
    }

    public PostDto updatePost(Long id, PostDto postDto) {
        Post oldPost = postRepository.findById(id).orElseThrow();
        oldPost.setTitle(postDto.getTitle());
        oldPost.setChangeDate(LocalDateTime.now());
        oldPost.setTag(postDto.getTag());
        oldPost.setText(postDto.getText());
        return PostDto.mapToPostDto(postRepository.save(oldPost));
    }

    public void deletePost(Long id) {
        Post post = postRepository.findById(id).orElseThrow();
        post.setStatus(PostStatus.DELETED);
        postRepository.save(post);
    }

}
