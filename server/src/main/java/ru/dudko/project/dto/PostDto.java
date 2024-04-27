package ru.dudko.project.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Builder;
import lombok.Data;
import ru.dudko.project.domain.model.Post;
import ru.dudko.project.domain.model.PostStatus;

import java.time.LocalDateTime;

@Data
@Builder
public class PostDto {

    private Long id;

    private String title;

    private String date;

    private String tag;

    private String text;

    public static PostDto mapToPostDto(Post post) {
        return PostDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .date(post.getChangeDate())
                .tag(post.getTag())
                .text(post.getText())
                .build();
    }

    public static Post mapToPost(PostDto postDto) {
        return Post.builder()
                .title(postDto.getTitle())
                .createDate(LocalDateTime.now())
                .changeDate(postDto.getDate())
                .tag(postDto.getTag())
                .text(postDto.getText())
                .status(PostStatus.CREATED)
                .build();
    }

}
