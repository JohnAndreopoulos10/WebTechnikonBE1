package gr.ed.ch.webtechnikon.dtos;

import gr.ed.ch.webtechnikon.enums.PropertyType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor 
@NoArgsConstructor
public class PropertyDTO {

    @NotNull
    @Positive
    private Long propertyId; 

    @NotNull
    @Positive
    private Long E9; 

    @NotNull
    @Positive
    private Long propertyCode; 

    @NotBlank
    private String address; 

    @NotNull
    @Positive
    private Integer yearOfConstruction; 

    @NotNull
    private PropertyType propertyType; 

    private Long ownerId; 

    private List<Long> repairIds; 

    private boolean deletedProperty; 

}

