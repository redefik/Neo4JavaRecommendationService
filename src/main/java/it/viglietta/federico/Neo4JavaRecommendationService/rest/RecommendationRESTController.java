package it.viglietta.federico.Neo4JavaRecommendationService.rest;

import it.viglietta.federico.Neo4JavaRecommendationService.controller.CustomerNotFoundException;
import it.viglietta.federico.Neo4JavaRecommendationService.controller.ProductNotFoundException;
import it.viglietta.federico.Neo4JavaRecommendationService.controller.RecommendationController;
import it.viglietta.federico.Neo4JavaRecommendationService.dto.CustomerDTO;
import it.viglietta.federico.Neo4JavaRecommendationService.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<?> getCustomerById(@PathVariable String customerId) {
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
    public ResponseEntity<?> getProductById(@PathVariable String productId) {
        try {
            ProductDTO productDTO = recommendationController.getProductById(productId);
            return new ResponseEntity<>(productDTO, HttpStatus.OK);
        } catch (ProductNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.PUT, path="customer/{customerId}/product/{productId}")
    public ResponseEntity<?> addPurchase(@PathVariable String customerId, @PathVariable String productId) {
        try {
            recommendationController.addPurchase(customerId, productId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (CustomerNotFoundException | ProductNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.GET, path="customer/{customerId}/product")
    public ResponseEntity<?> getCustomerPurchases(@PathVariable String customerId) {
        try {
            List<ProductDTO> purchasedProducts = recommendationController.getCustomerPurchases(customerId);
            return new ResponseEntity<>(purchasedProducts, HttpStatus.OK);
        } catch (CustomerNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "customer/{customerId}/recommended")
    public ResponseEntity<?> getRecommendedProducts(@PathVariable String customerId) {
        try {
            List<ProductDTO> recommendedProducts = recommendationController.getRecommendedProducts(customerId);
            return new ResponseEntity<>(recommendedProducts, HttpStatus.OK);
        } catch (CustomerNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
