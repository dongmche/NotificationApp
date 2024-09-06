package com.app.notification.entity.customer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "addresses")
@NoArgsConstructor
@Data
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    @JsonIgnore
    private Customer customer;

    @Column(nullable = false)
    private String type; // e.g., email, SMS, postal

    @Column(nullable = false)
    private String address;

    // Getters and setters


    public Address(Customer customer, String type, String address) {
        this.customer = customer;
        this.type = type;
        this.address = address;
    }
}
