package com.ameda.kevin.data.onetone;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long employeeId;
    private String name;
    private String nationality;
//    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER,optional = true)
//    @JoinColumn(name = "address_id")
//    private Address address;
}
