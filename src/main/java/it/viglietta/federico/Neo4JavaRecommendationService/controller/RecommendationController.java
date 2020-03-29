package it.viglietta.federico.Neo4JavaRecommendationService.controller;

import it.viglietta.federico.Neo4JavaRecommendationService.dto.CustomerDTO;
import it.viglietta.federico.Neo4JavaRecommendationService.dto.ProductDTO;
import it.viglietta.federico.Neo4JavaRecommendationService.entity.Customer;
import it.viglietta.federico.Neo4JavaRecommendationService.entity.Product;
import it.viglietta.federico.Neo4JavaRecommendationService.repository.CustomerRepository;
import it.viglietta.federico.Neo4JavaRecommendationService.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class RecommendationController {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ProductRepository productRepository;


    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Customer customer = modelMapper.map(customerDTO, Customer.class);
        Customer createdCustomer = customerRepository.save(customer);
        return modelMapper.map(createdCustomer, CustomerDTO.class);
    }

    public CustomerDTO getCustomerById(Long id) throws CustomerNotFoundException {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            Customer foundCustomer = customer.get();
            ModelMapper modelMapper = new ModelMapper();
            return modelMapper.map(foundCustomer, CustomerDTO.class);
        } else {
            throw new CustomerNotFoundException();
        }
    }

    public ProductDTO createProduct(ProductDTO productDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Product product = modelMapper.map(productDTO, Product.class);
        Product createdProduct = productRepository.save(product);
        return modelMapper.map(createdProduct, ProductDTO.class);
    }

    public ProductDTO getProductById(Long id) throws ProductNotFoundException {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            Product foundProduct = product.get();
            ModelMapper modelMapper = new ModelMapper();
            return modelMapper.map(foundProduct, ProductDTO.class);
        } else {
            throw new ProductNotFoundException();
        }
    }


}
