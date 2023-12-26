package com.mdp.user.service.UserService.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "micro_users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    @Column(name = "ID")
    String userId;
    @Column(name = "NAME", length = 20)
    String name;
    @Column(name = "EMAIL")
    String email;
    @Column(name = "ADDRESS")
    String address;
    @Column(name = "ABOUT")
    String about;

    @Transient
    List<Rating> ratings;
}
