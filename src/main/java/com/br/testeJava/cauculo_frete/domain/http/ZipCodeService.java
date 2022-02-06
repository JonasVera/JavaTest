package com.br.testeJava.cauculo_frete.domain.http;

import com.br.testeJava.cauculo_frete.domain.models.Adress;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url= "https://viacep.com.br/ws/", name = "viacep")
public interface ZipCodeService {
        @GetMapping("{cep}/json")
        public abstract Adress buscaEnderecoPorCep(@PathVariable("cep") String cep);

}
