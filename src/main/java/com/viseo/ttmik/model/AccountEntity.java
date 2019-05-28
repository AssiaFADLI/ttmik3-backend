package com.viseo.ttmik.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "ACCOUNT")
public class AccountEntity {
    @Id
    private String login;
    private String email;
    private String password;
}
