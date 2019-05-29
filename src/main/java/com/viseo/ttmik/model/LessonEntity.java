package com.viseo.ttmik.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="LESSON")
public class LessonEntity {
    @Id
    private int number;
    private String title;
    private String summary;
}
