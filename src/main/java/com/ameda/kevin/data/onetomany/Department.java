package com.ameda.kevin.data.onetomany;

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
public class Department {
    @Id
    @GeneratedValue
    private Long departmentId;
    private String departmentName;
    private String headPerson;
    @OneToMany(mappedBy = "department")
    private List<Teacher> teachers = new ArrayList<>();
}
