//tag::all[]
//tag::allButValidation[]
package tacos.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.CreditCardNumber;

import lombok.Data;

@Data
public class Order {

  Long id;
  //end::allButValidation[]
  @NotBlank(message="Name is required")
  //tag::allButValidation[]
  private String orderName;
  //end::allButValidation[]

  @NotBlank(message="Street is required")
  //tag::allButValidation[]
  private String street;
  //end::allButValidation[]

  @NotBlank(message="City is required")
  //tag::allButValidation[]
  private String city;
  //end::allButValidation[]

  @NotBlank(message="State is required")
  //tag::allButValidation[]
  private String state;
  //end::allButValidation[]

  @NotBlank(message="Zip code is required")
  @Size(min = 6, max = 6, message = "Zip code should be 6 charecters.")
  //tag::allButValidation[]
  private String zip;
  //end::allButValidation[]

//  @CreditCardNumber(message="Not a valid credit card number")
  //tag::allButValidation[]
  @Size(min = 3, max = 3, message = "CCN should be 3 charecters.")
  private String ccNumber;
  //end::allButValidation[]

  @Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$",
           message="Must be formatted MM/YY")
  //tag::allButValidation[]
  private String ccExpiration;
  //end::allButValidation[]

  @Digits(integer=3, fraction=0, message="Invalid CVV")
  //tag::allButValidation[]
  private Integer ccCVV;
  
  private String createdAt;
  
  private List<Taco> tacos = new ArrayList<Taco>();
  
  public void addTaco(Taco taco) {
	  tacos.add(taco);
  }

}
//end::allButValidation[]
//end::all[]
