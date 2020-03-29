package it.viglietta.federico.Neo4JavaRecommendationService.repository;

import it.viglietta.federico.Neo4JavaRecommendationService.entity.Product;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

public interface ProductRepository extends Neo4jRepository<Product, Long> {
    @Query(value = "MATCH (c:Customer)-[:PURCHASED]->(:Product)<-[:PURCHASED]-(:Customer)-[:PURCHASED]->(rp:Product) WHERE ID(c)=$0 RETURN rp")
    List<Product> getProductPurchasedByCustomersThatPurchasedTheSameProducts(Long customerId);
}
