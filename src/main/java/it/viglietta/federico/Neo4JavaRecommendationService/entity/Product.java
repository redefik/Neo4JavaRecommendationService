package it.viglietta.federico.Neo4JavaRecommendationService.entity;

import lombok.Data;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.Relationship;
import java.util.ArrayList;
import java.util.List;
import static org.neo4j.ogm.annotation.Relationship.INCOMING;

@Data
public class Product {
    @Id
    private Long Id;
    private String description;

    @Relationship(type = "PURCHASED_BY", direction = INCOMING)
    private List<Customer> customers = new ArrayList<>();
}
