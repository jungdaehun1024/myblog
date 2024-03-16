package com.course.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardLikeDTO {
  private int currentLike;
  private int boardId;
  private int userId;
}
