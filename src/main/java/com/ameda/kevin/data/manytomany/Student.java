package com.ameda.kevin.data.manytomany;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {
    @Id
    @GeneratedValue
    private Long studentId;
    private String name;
    private String school;
    @ManyToMany
            @JoinTable(name = "students_missions",joinColumns =
            @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "mission_id"))
    List<Mission> missions = new ArrayList<>();
}
