package com.ameda.kevin.data.onetone;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long addressId;
    private String locationName;
    private String zipCode;
    @OneToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
}
