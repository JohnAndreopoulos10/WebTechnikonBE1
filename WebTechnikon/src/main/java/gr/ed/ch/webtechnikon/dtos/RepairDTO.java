package gr.ed.ch.webtechnikon.dtos;

import gr.ed.ch.webtechnikon.enums.RepairStatus;
import gr.ed.ch.webtechnikon.enums.RepairType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor 
@NoArgsConstructor
public class RepairDTO {

    @NotNull
    @Positive
    private Long repairId;  

    private RepairType repairType;  

    @NotBlank
    private String shortDescription;  

    private Date dateOfSubmission;  

    private String descriptionOfWork;  

    private Date proposedDateOfStart;  

    private Date proposedDateOfEnd;  

    private BigDecimal proposedCost;  

    private boolean acceptance;  

    private RepairStatus repairStatus;  

    private Date dateOfStart;  

    private Date dateOfEnd;  

    private boolean deletedRepair;  

   
    @NotNull
    @Positive
    private Long propertyId; 

}

