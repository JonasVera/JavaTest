package com.br.testeJava.cauculo_frete.domain.dto;

import com.br.testeJava.cauculo_frete.domain.models.OrderShipping;
import lombok.*;

import javax.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderShippingDto {

    @NotNull(message = "weight is mandatory")
    private Double weight;
    @NotBlank(message = "Origin zip code is mandatory")
    @Pattern(regexp="[0-9]{5}-[0-9]{3}",
            message="Must be formatted 00000-000")
    private String originZipCode;
    @NotBlank(message = "zip code destination is mandatory")
    @Pattern(regexp="[0-9]{5}-[0-9]{3}",
            message="Must be formatted 00000-000")
    private String destinationZipCode;
    @NotBlank(message = "Name is mandatory")
    private String nameDestination;

    public OrderShippingDto convertTODto(OrderShipping orderShipping){
        OrderShippingDto orderShippingDto = new OrderShippingDto();
        orderShippingDto.setOriginZipCode(orderShipping.getOriginZipCode());
        orderShippingDto.setDestinationZipCode(orderShipping.getDestinationZipCode());
        orderShippingDto.setNameDestination(orderShipping.getNameDestination());
        orderShippingDto.setWeight(orderShipping.getWeight());

    return orderShippingDto;
    }


}
