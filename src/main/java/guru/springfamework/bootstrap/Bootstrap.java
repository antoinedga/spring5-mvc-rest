package guru.springfamework.bootstrap;


import guru.springfamework.domain.Category;
import guru.springfamework.domain.Customer;
import guru.springfamework.domain.Vendor;
import guru.springfamework.repositories.CategoryRepository;
import guru.springfamework.repositories.CustomerRepository;
import guru.springfamework.repositories.VendorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class Bootstrap implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final CustomerRepository customerRepository;
    private final VendorRepository vendorRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository, VendorRepository vendorRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadCategories();

        loadCustomers();
        vendorRepository.save(Vendor.builder().name("Pepsi").build());
        vendorRepository.save(Vendor.builder().name("Coke").build());
        vendorRepository.save(Vendor.builder().name("Kava").build());
        vendorRepository.save(Vendor.builder().name("HomeDepot").build());

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
