package com.example.rsqlimplementation.model.domain;

import jakarta.persistence.*;
import lombok.*;
import org.apache.commons.lang3.builder.ToStringExclude;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "app_user")
public class User implements UserDetails {

    @Id
    @SequenceGenerator(name = "userIdSeq",sequenceName = "user_id_seq",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "userIdSeq")
    private Long id;

    @Column(length = 64,unique = true)
    private String username;

    @Column(length = 64)
    private String email;

    @Column(length = 64)
    private String firstName;

    @Column(length = 64)
    private String lastName;

    @Column(length = 64)
    private String middleName;

    @Column(length = 128)
    private String fullName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate registrationDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate removalDate;

    @Column(length = 64)
    private String mobilePhoneNumber;

    @Column(length = 64)
    private String password;


    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> authorities = new HashSet<>();

    public User(String email, String username, String password, Set<Role> authorities) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.authorities = authorities;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
