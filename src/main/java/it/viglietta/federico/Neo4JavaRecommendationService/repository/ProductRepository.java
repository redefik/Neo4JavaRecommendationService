package it.viglietta.federico.Neo4JavaRecommendationService.repository;

import it.viglietta.federico.Neo4JavaRecommendationService.entity.Product;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends Neo4jRepository<Product, Long> {

    Optional<Product> findByExternalId(String productId);

    @Query(value = "MATCH (c:Customer)-[:PURCHASED]->(:Product)<-[:PURCHASED]-(:Customer)-[:PURCHASED]->(rp:Product) WHERE c.externalId = $0 RETURN rp")
    List<Product> getProductPurchasedByCustomersThatPurchasedTheSameProducts(String customerId);
}
