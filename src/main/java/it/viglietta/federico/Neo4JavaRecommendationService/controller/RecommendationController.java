package it.viglietta.federico.Neo4JavaRecommendationService.controller;

import it.viglietta.federico.Neo4JavaRecommendationService.dto.CustomerDTO;
import it.viglietta.federico.Neo4JavaRecommendationService.dto.ProductDTO;
import it.viglietta.federico.Neo4JavaRecommendationService.entity.Customer;
import it.viglietta.federico.Neo4JavaRecommendationService.entity.Product;
import it.viglietta.federico.Neo4JavaRecommendationService.repository.CustomerRepository;
import it.viglietta.federico.Neo4JavaRecommendationService.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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

    public CustomerDTO getCustomerById(String id) throws CustomerNotFoundException {
        Optional<Customer> customer = customerRepository.findByExternalId(id);
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

    public ProductDTO getProductById(String id) throws ProductNotFoundException {
        Optional<Product> product = productRepository.findByExternalId(id);
        if (product.isPresent()) {
            Product foundProduct = product.get();
            ModelMapper modelMapper = new ModelMapper();
            return modelMapper.map(foundProduct, ProductDTO.class);
        } else {
            throw new ProductNotFoundException();
        }
    }

    /* Associate a product to a customer that bought it*/
    public void addPurchase(String customerId, String productId) throws CustomerNotFoundException, ProductNotFoundException {
        Optional<Customer> customer = customerRepository.findByExternalId(customerId);
        if (!customer.isPresent()) {
            throw new CustomerNotFoundException();
        }
        Optional<Product> product = productRepository.findByExternalId(productId);
        if (!product.isPresent()) {
            throw new ProductNotFoundException();
        }
        Customer foundCustomer = customer.get();
        Product foundProduct = product.get();
        foundCustomer.addProduct(foundProduct);
        customerRepository.save(foundCustomer);
    }

    public List<ProductDTO> getCustomerPurchases(String customerId) throws CustomerNotFoundException {
        Optional<Customer> customer = customerRepository.findByExternalId(customerId);
        if (!customer.isPresent()) {
            throw new CustomerNotFoundException();
        }
        Customer foundCustomer = customer.get();
        ModelMapper modelMapper = new ModelMapper();
        List<Product> products = foundCustomer.getProducts();
        return modelMapper.map(products, new TypeToken<List<ProductDTO>>(){}.getType());

    }

    /* Retrieves the products purchased by the customers that purchased the same products */
    public List<ProductDTO> getRecommendedProducts(String customerId) throws CustomerNotFoundException {
        Optional<Customer> customer = customerRepository.findByExternalId(customerId);
        if (!customer.isPresent()) {
            throw new CustomerNotFoundException();
        }
        List<Product> recommendedProducts = productRepository.getProductPurchasedByCustomersThatPurchasedTheSameProducts(customerId);
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(recommendedProducts, new TypeToken<List<ProductDTO>>(){}.getType());
    }
}
