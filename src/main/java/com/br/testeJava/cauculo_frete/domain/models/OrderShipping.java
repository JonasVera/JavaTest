package com.br.testeJava.cauculo_frete.domain.models;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder()
@Setter()
@Getter()
@AllArgsConstructor
@Entity(name = "ordersShipping")
@NoArgsConstructor
public class OrderShipping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true, updatable = true)
    private Long id;

    private String nameDestination;

    private Double weight;

    private String originZipCode;

    private String destinationZipCode;

    private Double valueShipping;

    @Column(nullable = true)
    private LocalDate deliveryDate =   LocalDate.now();

    @CreationTimestamp
    private LocalDateTime resultAt;

    public OrderShipping(Double weight, String destinationZipCode,String originZipCode,String nameDestination) {
        setWeight(weight);
        setDestinationZipCode(destinationZipCode);
        setOriginZipCode(originZipCode);
        setNameDestination(nameDestination);
    }
}
