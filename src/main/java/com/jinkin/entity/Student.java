package com.jinkin.entity;

import lombok.*;

@Data
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@AllArgsConstructor
public class Student {
    private Long id;
    private String name;
    private int age;


}
