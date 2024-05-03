package net.dudko.project.model.dto;

import lombok.Builder;

@Builder
public record PostDto(Long id,
                      String title,
                      String date,
                      String tag,
                      String text) { }
