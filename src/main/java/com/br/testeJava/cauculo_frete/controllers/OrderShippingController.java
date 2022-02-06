package com.br.testeJava.cauculo_frete.controllers;

import com.br.testeJava.cauculo_frete.domain.dto.OrderShippingDto;
import com.br.testeJava.cauculo_frete.domain.dto.OrderShippingResultDto;
import com.br.testeJava.cauculo_frete.domain.models.OrderShipping;
import com.br.testeJava.cauculo_frete.domain.services.OrderShippingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/orderShipping")
@RequiredArgsConstructor
public class OrderShippingController {

    @Autowired
    private OrderShippingService orderShippingService;

    @PostMapping()
    public OrderShippingResultDto saveOrderShipping(@Valid @RequestBody OrderShippingDto orderShippingDto) {
        return this.orderShippingService.calculateShipping(orderShippingDto);
    }
    @GetMapping()
    public Page<OrderShipping> getAllResults(@RequestParam int page, @RequestParam int qtd){
        return this.orderShippingService.findAllOrderShipping(page,qtd);
    }
}
