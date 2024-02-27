package guru.springfamework.bootstrap;


import guru.springfamework.domain.Category;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CategoryRepository;
import guru.springfamework.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class Bootstrap implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final CustomerRepository customerRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadCategories();

        loadCustomers();

    }

    private void loadCategories() {
        Category fruits = new Category();
        fruits.setName("Fruits");

        Category dried = new Category();
        dried.setName("dried");

        Category exotic = new Category();
        exotic.setName("exotic");

        categoryRepository.saveAll(Arrays.asList(fruits, dried, exotic));
        System.out.println("Category DataLoaded: " + categoryRepository.count());
    }

    private void loadCustomers() {
        customerRepository.save(Customer.builder().firstName("Antoine").lastName("Gordon").build());
        customerRepository.save(Customer.builder().firstName("Andrea").lastName("Bobadilla").build());
        customerRepository.save(Customer.builder().firstName("Mignon").lastName("Gordon").build());
        customerRepository.save(Customer.builder().firstName("Estret").lastName("Gordon").build());
        System.out.println("Customer Data Loaded: " + customerRepository.count());
    }
}
