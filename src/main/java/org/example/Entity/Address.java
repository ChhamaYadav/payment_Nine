package org.example.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "addresses")
public class Address {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long userId;

        private String street;
        private String city;
         private String pincode;
        private String state;
        private String country;
    }


