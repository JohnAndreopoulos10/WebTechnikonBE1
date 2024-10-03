package gr.ed.ch.webtechnikon.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor 
@NoArgsConstructor
public class OwnerDTO {

    @NotNull(message = "Owner ID must not be null")
    @Positive(message = "Owner ID must be positive")
    private Long ownerId; 

    @NotNull(message = "VAT Number must not be null")
    @Positive(message = "VAT Number must be positive")
    private Long vatNumber; 

    @NotBlank(message = "Name must not be blank")
    private String name; 

    @NotBlank(message = "Surname must not be blank")
    private String surName; 

    @NotBlank(message = "Address must not be blank")
    private String address; 

    @NotBlank(message = "Phone Number must not be blank")
    private String phoneNumber; 

    @NotBlank(message = "Email must not be blank")
    @Email(message = "Email must be a valid email address")
    private String email; 

    @NotBlank(message = "Username must not be blank")
    private String username; 

    @NotBlank(message = "Password must not be blank")
    private String password; 

    private List<Long> propertyList; 

    private boolean deletedOwner; 
}


