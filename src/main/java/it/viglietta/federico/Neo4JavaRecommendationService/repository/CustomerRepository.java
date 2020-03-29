package it.viglietta.federico.Neo4JavaRecommendationService.repository;

import it.viglietta.federico.Neo4JavaRecommendationService.entity.Customer;
import it.viglietta.federico.Neo4JavaRecommendationService.entity.Product;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

public interface CustomerRepository extends Neo4jRepository<Customer, Long> {



}
