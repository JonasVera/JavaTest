package com.br.testeJava.cauculo_frete.domain.services;

import com.br.testeJava.cauculo_frete.domain.dto.OrderShippingDto;
import com.br.testeJava.cauculo_frete.domain.dto.OrderShippingResultDto;
import com.br.testeJava.cauculo_frete.domain.models.OrderShipping;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface OrderShippingService {

    OrderShippingResultDto calculateShipping(OrderShippingDto orderShippings);


    Page<OrderShipping> findAllOrderShipping(int page, int qtd);
}
