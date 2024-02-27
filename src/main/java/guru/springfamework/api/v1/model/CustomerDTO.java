package guru.springfamework.api.v1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
    private String firstName;
    private String lastName;
    private Long id;
    private String customerUrl;
}
