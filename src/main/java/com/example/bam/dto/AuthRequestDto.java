package com.example.bam.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AuthRequestDto {

  // validations (@Valid, @NotBlank)

  private String username;
  private String password;
}
