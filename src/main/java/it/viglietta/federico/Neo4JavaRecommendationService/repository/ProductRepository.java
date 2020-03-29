package it.viglietta.federico.Neo4JavaRecommendationService.repository;

import it.viglietta.federico.Neo4JavaRecommendationService.entity.Product;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface ProductRepository extends Neo4jRepository<Product, Long> {
}
