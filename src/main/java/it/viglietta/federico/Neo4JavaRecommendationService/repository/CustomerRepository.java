package it.viglietta.federico.Neo4JavaRecommendationService.repository;

import it.viglietta.federico.Neo4JavaRecommendationService.entity.Customer;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.Optional;


public interface CustomerRepository extends Neo4jRepository<Customer, Long> {

    Optional<Customer> findByExternalId(String customerId);

}
