package it.viglietta.federico.Neo4JavaRecommendationService.rest;

import it.viglietta.federico.Neo4JavaRecommendationService.controller.CustomerNotFoundException;
import it.viglietta.federico.Neo4JavaRecommendationService.controller.ProductNotFoundException;
import it.viglietta.federico.Neo4JavaRecommendationService.controller.RecommendationController;
import it.viglietta.federico.Neo4JavaRecommendationService.dto.CustomerDTO;
import it.viglietta.federico.Neo4JavaRecommendationService.dto.ProductDTO;
import it.viglietta.federico.Neo4JavaRecommendationService.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recommendation/")
public class RecommendationRESTController {

    @Autowired
    private RecommendationController recommendationController;

    @RequestMapping(method = RequestMethod.POST, path="customer")
    public ResponseEntity<?> createCustomer(@RequestBody CustomerDTO customerDTO) {
        CustomerDTO createdCustomer = recommendationController.createCustomer(customerDTO);
        return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, path="customer/{customerId}")
    public ResponseEntity<?> getCustomerById(@PathVariable Long customerId) {
        try {
            CustomerDTO customerDTO = recommendationController.getCustomerById(customerId);
            return new ResponseEntity<>(customerDTO, HttpStatus.OK);
        } catch (CustomerNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.POST, path="product")
    public ResponseEntity<?> createProduct(@RequestBody ProductDTO productDTO) {
        ProductDTO createdProduct = recommendationController.createProduct(productDTO);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, path="product/{productId}")
    public ResponseEntity<?> getProductById(@PathVariable Long productId) {
        try {
            ProductDTO productDTO = recommendationController.getProductById(productId);
            return new ResponseEntity<>(productDTO, HttpStatus.OK);
        } catch (ProductNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
