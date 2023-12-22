package com.ameda.kevin.data.manytomany;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Mission {
    @Id
    @GeneratedValue
    private Long missionId;
    private String message;
    @ManyToMany(mappedBy = "missions")
    private List<Student> students = new ArrayList<>();
}
