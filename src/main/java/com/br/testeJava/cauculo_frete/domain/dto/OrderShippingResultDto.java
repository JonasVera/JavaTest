package com.br.testeJava.cauculo_frete.domain.dto;

import com.br.testeJava.cauculo_frete.domain.models.OrderShipping;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderShippingResultDto {

    private Double weight;

    private String originZipCode;

    private String destinationZipCode;

    private String nameDestination;

    private  Double totalShipping;

    private LocalDate deliveryDate;

    public OrderShippingResultDto convertTODto(OrderShipping orderShipping){
        OrderShippingResultDto orderShippingDto = new OrderShippingResultDto();
        orderShippingDto.setOriginZipCode(orderShipping.getOriginZipCode());
        orderShippingDto.setDestinationZipCode(orderShipping.getDestinationZipCode());
        orderShippingDto.setNameDestination(orderShipping.getNameDestination());
        orderShippingDto.setWeight(orderShipping.getWeight());
        orderShippingDto.setDeliveryDate(orderShipping.getDeliveryDate());

        return orderShippingDto;
    }

}
