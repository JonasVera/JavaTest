package com.br.testeJava.cauculo_frete.domain.repositories;

import com.br.testeJava.cauculo_frete.domain.models.OrderShipping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderShippingRepository extends JpaRepository<OrderShipping, Long> {
}
