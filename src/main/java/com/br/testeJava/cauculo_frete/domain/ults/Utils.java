package com.br.testeJava.cauculo_frete.domain.ults;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@AllArgsConstructor

@Configuration
public class Utils {

    public String tranformZipCode(String zipCode){

            if (String.valueOf(zipCode).length() == 8){
                String zipCodeStr = String.valueOf(zipCode);
                String partOne =  zipCodeStr.substring(0,5);
                String partTwo =  zipCodeStr.substring(5);
                zipCodeStr = partOne + "-"+ partTwo;
                System.out.println("<======= COVERT ========>" +zipCodeStr);
                return zipCodeStr;
            }else{
                return zipCode;
            }
         }

    public String convertZipCodeFormat(String zipCode){
        zipCode.replace("-","");
        return zipCode;
    }
}
