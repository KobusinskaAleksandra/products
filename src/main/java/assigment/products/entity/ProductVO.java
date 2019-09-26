package assigment.products.entity;

import java.time.LocalDateTime;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
@Table(name = "Product")
public class ProductVO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Product_id")
    private Long id;
    
    private String name;
    
    private double currentPrice;
    
    private LocalDateTime lastUpdated;

}