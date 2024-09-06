package com.app.notification.entity.customer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "notification_preferences")
@Data
@NoArgsConstructor
@AllArgsConstructor
//@EqualsAndHashCode(of = {"name", "email", "phone"})
public class Preference {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    @JsonIgnore
    private Customer customer;

    @Column(nullable = false)
    private String preferenceType; // e.g., SMS, email

    @Column(nullable = false)
    private Boolean optIn; // true for opt-in, false for opt-out

    // Getters and setters


    public Preference(Customer customer, String preferenceType, Boolean optIn) {
        this.customer = customer;
        this.preferenceType = preferenceType;
        this.optIn = optIn;
    }
}