package com.company.catalogservice.jpa;
 
import java.util.Date;

import javax.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import lombok.Data;

@Data
@Entity
@Table(name = "catalog")
public class CatalogEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120, unique = true)
    private String productId;
    @Column(nullable = false)
    private String productName;
    @Column(nullable = false)
    private Integer stock;
    @Column(nullable = false)
    private Integer unitPrice;

    @Column(nullable = false, updatable = false, insertable = false)
    @ColumnDefault(value = "CURRENT_TIMESTAMP")
    private Date createdAt;
}