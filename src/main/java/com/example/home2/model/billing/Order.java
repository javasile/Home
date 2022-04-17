package com.example.home2.model.billing;

import com.example.home2.model.client.Customer;
import com.example.home2.model.house.Building;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(length = 30)
    private String orderNumber;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateAdded;

    @Enumerated(EnumType.STRING)
    private Contract contract;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Building building;


}
