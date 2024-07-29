package org.itspooyan.jobportal.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "users_type")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UsersType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_type_id")
    private int userTypeId;

    @Column(name = "user_type_name")
    private String userTypeName;

    @OneToMany(targetEntity = Users.class, mappedBy = "userTypeId", cascade = CascadeType.ALL)
    private List<Users> users;

    @Override
    public String toString() {
        return "UsersType{" +
                "userTypeName='" + userTypeName + '\'' +
                ", userTypeId=" + userTypeId +
                '}';
    }
}