package com.br.testeJava.cauculo_frete.domain.services.impl;

import com.br.testeJava.cauculo_frete.domain.dto.OrderShippingDto;
import com.br.testeJava.cauculo_frete.domain.dto.OrderShippingResultDto;
import com.br.testeJava.cauculo_frete.domain.exceptions.AllException;
import com.br.testeJava.cauculo_frete.domain.http.ZipCodeService;
import com.br.testeJava.cauculo_frete.domain.models.Adress;
import com.br.testeJava.cauculo_frete.domain.models.OrderShipping;
import com.br.testeJava.cauculo_frete.domain.repositories.OrderShippingRepository;
import com.br.testeJava.cauculo_frete.domain.services.OrderShippingService;
import com.br.testeJava.cauculo_frete.domain.ults.Utils;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Data
@Builder()
@Service
@Slf4j
public class OrderShippingServiceImpl implements OrderShippingService {

    @Autowired
    private final OrderShippingRepository orderShippingRepository;

    private final ZipCodeService zipCodeService;
    @Autowired
    private final Utils utils;
    @Override
    public OrderShippingResultDto calculateShipping(OrderShippingDto orderShippingDto) {
        OrderShippingResultDto result = new OrderShippingResultDto();
        try {
            if(orderShippingDto.getNameDestination() == null){
                throw new AllException("Nome do destinatário é obrigatório");
            }
            if(orderShippingDto.getWeight() == null){
                throw new AllException("Peso da mercadoria é obrigatório");
            }

            if(orderShippingDto.getDestinationZipCode() == null){
                throw new AllException("Cep do destinatario é obrigatório");
            }

            if(orderShippingDto.getOriginZipCode() == null){
                throw new AllException("Cep da origem é obrigatório");
            }

             OrderShipping newOrderShipping = new OrderShipping(
                     orderShippingDto.getWeight(),
                     utils.convertZipCodeFormat(orderShippingDto.getDestinationZipCode()),
                     utils.convertZipCodeFormat(orderShippingDto.getOriginZipCode()),
                     orderShippingDto.getNameDestination()
            );
             ArrayList<String> ceps = new ArrayList<String>();
             ceps.add(String.valueOf(orderShippingDto.getOriginZipCode()));
             ceps.add(String.valueOf(orderShippingDto.getDestinationZipCode()));

            ArrayList<Adress> adresses = new ArrayList<Adress>();
            //ADD RESULT SERVICE IN LIST
            for (String cep : ceps){
                adresses.add(zipCodeService.buscaEnderecoPorCep(cep));
            }
            //CALCULATE PRICE TOTAL SHIPPING
            calculateShipping(adresses.get(0),adresses.get(1),newOrderShipping);

            orderShippingRepository.save(newOrderShipping);
            //PREPARE RESULT
            result.setTotalShipping(newOrderShipping.getValueShipping());
            result.setNameDestination(newOrderShipping.getNameDestination());
            result.setWeight(newOrderShipping.getWeight());
            result.setDestinationZipCode(utils.tranformZipCode(newOrderShipping.getDestinationZipCode()));
            result.setOriginZipCode(utils.tranformZipCode( newOrderShipping.getOriginZipCode()));
            result.setDeliveryDate(newOrderShipping.getDeliveryDate());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private void calculateShipping(Adress origin, Adress destination, OrderShipping orderShipping){
        double valueTotal = 0;

        if(origin.getDdd().equals(destination.getDdd())){
            valueTotal = orderShipping.getWeight() / 2;
            orderShipping.setDeliveryDate(orderShipping.getDeliveryDate().plusDays(1));
            orderShipping.setValueShipping(valueTotal);
        }
       else if(origin.getUf().equals(destination.getUf())){
            valueTotal =  orderShipping.getWeight() - (orderShipping.getWeight() * 0.75);
            orderShipping.setDeliveryDate(orderShipping.getDeliveryDate().plusDays(3));
            orderShipping.setValueShipping(valueTotal);
        }else{
            valueTotal =  orderShipping.getWeight();
            orderShipping.setDeliveryDate(orderShipping.getDeliveryDate().plusDays(10));
            orderShipping.setValueShipping(valueTotal);
        }
    }
    @Override
    public Page<OrderShipping> findAllOrderShipping(int page, int qtd) {
        Pageable pageable = PageRequest.of(page, qtd);
        Page<OrderShipping> pages = orderShippingRepository.findAll(pageable);
        return pages;
    }
}
