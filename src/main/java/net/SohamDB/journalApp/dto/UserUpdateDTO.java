package net.SohamDB.journalApp.dto;

//import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserUpdateDTO {

//    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
    private String userName;

//    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

    // You can add more validations if needed
}