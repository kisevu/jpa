package com.ameda.kevin.data.onetomany;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Teacher {
    @Id
    @GeneratedValue
    private Long teacherId;
    private String teacherName;
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
}
