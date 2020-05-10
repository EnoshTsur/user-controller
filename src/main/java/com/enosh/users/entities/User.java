package com.enosh.users.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Length(min = 2, max = 30)
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Length(min = 2, max = 30)
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Length(min = 5, max = 50)
    @Column(name = "email", nullable = false)
    private String email;

    public User(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

}
