package guru.springfamework.service;

import guru.springfamework.api.v1.mappers.CustomerMapper;
import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.controllers.v1.CustomerController;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CustomerRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class CustomerServiceImpl implements  CustomerService{

    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerMapper customerMapper, CustomerRepository customerRepository) {
        this.customerMapper = customerMapper;
        this.customerRepository = customerRepository;
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll().stream()
                .map(customer -> {
                    CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
                    customerDTO.setCustomerUrl(getCustomerUri(customer.getId()));
                    return customerDTO;
                }).collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {
        return customerRepository
                .findById(id)
                .map(customerMapper::customerToCustomerDTO)
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public CustomerDTO createNewCustomer(CustomerDTO customerDTO) {
        Customer customer = customerMapper.customerDtoToCustomer(customerDTO);
        Customer saved = customerRepository.save(customer);
        CustomerDTO returnedDTO = customerMapper.customerToCustomerDTO(saved);
        returnedDTO.setCustomerUrl(getCustomerUri(saved.getId()));
        return returnedDTO;
    }

    @Override
    public CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO) {
        Customer customer = customerMapper.customerDtoToCustomer(customerDTO);
        customer.setId(id);
        Customer saved = customerRepository.save(customer);
        return customerMapper.customerToCustomerDTO(saved);
    }

    @Override
    public CustomerDTO updatePatchCustomer(Long id, CustomerDTO customerDTO) {

        return customerRepository.findById(id).map(customer -> {
            if (customerDTO.getFirstName() != null) {
                customer.setFirstName(customerDTO.getFirstName());
            }
            if (customerDTO.getLastName() != null) {
                customer.setLastName(customerDTO.getLastName());
            }
            CustomerDTO returnDTO = customerMapper.customerToCustomerDTO(customerRepository.save(customer));
            returnDTO.setCustomerUrl(getCustomerUri(customer.getId()));
            return returnDTO;
        }).orElseThrow(RuntimeException::new);
    }

    @Override
    public void deleteCustomerById(Long id) {
        customerRepository.deleteById(id);
    }

    public String getCustomerUri(Long id) {
       return CustomerController.BASE_URL + id;
    }
}
