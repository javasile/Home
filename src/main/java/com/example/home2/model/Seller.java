package com.example.home2.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Seller {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

//    @Column(columnDefinition = "field default false")
//    private boolean isCompany;

    @Column(length = 20)
    @NotNull
    @NotBlank
    private String firstName;

    @Column(length = 20)
    @NotNull
    @NotBlank
    private String lastName;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "seller_contact",
            joinColumns = @JoinColumn(name = "seller_id"),
            inverseJoinColumns = @JoinColumn(name = "contact_id")
    )
    private List<Contact> contactList;
}
