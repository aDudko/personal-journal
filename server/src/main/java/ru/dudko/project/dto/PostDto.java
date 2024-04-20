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

    private String title;

    @JsonFormat(pattern = "dd.MM.yyyy HH:mm")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime date;

    private String tag;

    private String text;

    public static PostDto mapToPostDto(Post post) {
        return PostDto.builder()
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
                .changeDate(LocalDateTime.now())
                .tag(postDto.getTag())
                .text(postDto.getText())
                .status(PostStatus.CREATED)
                .build();
    }

}
