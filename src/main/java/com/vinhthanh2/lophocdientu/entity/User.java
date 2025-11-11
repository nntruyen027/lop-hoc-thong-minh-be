package com.vinhthanh2.lophocdientu.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 120)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column
    private String fullName;

    @Column
    private Date birthday;

    @Column
    private String avatar;

    @Column
    private String role;

    @OneToMany(mappedBy = "giaoVien", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Lop> dsLop;
}
