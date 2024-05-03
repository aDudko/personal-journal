package net.dudko.project.domain.mapper;

import net.dudko.project.domain.entity.Post;
import net.dudko.project.model.dto.PostDto;

public class PostMapper {

    public static Post mapToPost(PostDto postDto) {
        return Post.builder()
                .id(postDto.id())
                .title(postDto.title())
                .changeDate(postDto.date())
                .tag(postDto.tag())
                .text(postDto.text())
                .build();

    }

    public static PostDto mapToPostDto(Post post) {
        return PostDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .date(post.getChangeDate())
                .tag(post.getTag())
                .text(post.getText())
                .build();
    }

}
