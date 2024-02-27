package guru.springfamework.controllers.v1;


import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.api.v1.model.CustomerListDTO;
import guru.springfamework.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1/customers/")
public class CustomerController {
    private final CustomerService customerService;
    public static final String BASE_URL = "/api/v1/customers/";

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<CustomerListDTO> getListOfCustomers() {
        return new ResponseEntity<>(new CustomerListDTO(customerService.getAllCustomers()),HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable String id) {
        return new ResponseEntity<>(customerService.getCustomerById(Long.parseLong(id)),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> createNewCustomer(@RequestBody CustomerDTO customerDTO) {
        return new ResponseEntity<>(customerService.createNewCustomer(customerDTO),HttpStatus.CREATED);
    }


    @PutMapping("{id}")
    public ResponseEntity<CustomerDTO> updateCustomers(@PathVariable String id, @RequestBody CustomerDTO customerDTO) {
        return new ResponseEntity<>(
                customerService.updateCustomer(Long.parseLong(id), customerDTO),HttpStatus.CREATED);

    }

    @PatchMapping("{id}")
    public ResponseEntity<CustomerDTO> updatePatchCustomers(@PathVariable String id, @RequestBody CustomerDTO customerDTO) {
        return new ResponseEntity<>(
                customerService.updatePatchCustomer(Long.parseLong(id), customerDTO),HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCustomerById(@PathVariable String id) {
        customerService.deleteCustomerById(Long.parseLong(id));
        return ResponseEntity.ok().build();
    }

}
