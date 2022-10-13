package com.example.home2.model;

import com.example.home2.model.Seller;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(length = 60)
    private String country;

    @Column(length = 60)
    private String county;

    @Column(length = 60)
    private String city;

    @Column(length = 60)
    private String street;

    @Column(length = 60)
    private String number;

    @Column(length = 10)
    private String postalCode;

    @Column(length = 256)
    private String additionalInformation;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "address_seller",
            joinColumns = @JoinColumn(name = "address_id"),
            inverseJoinColumns = @JoinColumn(name = "seller_id")
    )
    private List<Seller> sellerList;

}
