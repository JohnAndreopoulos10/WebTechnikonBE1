package gr.ed.ch.webtechnikon.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor

public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ownerId;

    @Column(unique = true)
    private long vatNumber;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surName;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "owner")
    private List<Property> propertyList;

    @Column(nullable = false)
    private boolean deletedOwner = false;

    @Override
    public String toString() {
        return "Owner{" + "OwnerId=" + ownerId + ", VatNumber=" + vatNumber + 
                ","+ " Name=" + name + ", SurName=" + surName + ", Address=" + address + 
                ", PhoneNumber=" + phoneNumber + ", Email=" + email + 
                ", Username=" + username + ", password=" + password + ", propertyList=" + propertyList + ", deletedOwner=" + deletedOwner + '}';
    }

}
