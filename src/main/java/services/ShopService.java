package services;

import config.RestTemplateConfig;
import dtos.TotalCountDto;
import dtos.VehicleDto;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.ws.rs.core.Request;
import java.util.ArrayList;
import java.util.List;

@Component
public class ShopService {
    public TotalCountDto getByWheelsNumber(int from, int to){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<TotalCountDto> responseEntity =
                restTemplate.exchange(
                        RestTemplateConfig.VEHICLES_URL+"/vehicles",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<TotalCountDto>() {}
                );

        List<VehicleDto> allVehicles = responseEntity.getBody().getVehicles();
        List<VehicleDto> result = new ArrayList<>();

        allVehicles.forEach(v->{
            if((v.getWheelsNumber()<=to) && (v.getWheelsNumber()>=from))
                result.add(v);
        });
        TotalCountDto resultTotalCount = new TotalCountDto();
        resultTotalCount.setVehicles(result);
        resultTotalCount.setTotalCount(responseEntity.getBody().getTotalCount());
        return resultTotalCount;
    }

    public HttpStatus fixDistance(Long id){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<VehicleDto> responseEntity =
                restTemplate.exchange(
                        RestTemplateConfig.VEHICLES_URL+"/vehicles/"+id,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<VehicleDto>() {}
                );
        VehicleDto fixedVehicle = responseEntity.getBody();
        fixedVehicle.setMileage(0);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<VehicleDto> request = new HttpEntity<VehicleDto>(fixedVehicle, headers);

        ResponseEntity<VehicleDto> responseEntityResult =
                restTemplate.exchange(
                        RestTemplateConfig.VEHICLES_URL+"/vehicles/"+id,
                        HttpMethod.PUT,
                        request,
                        new ParameterizedTypeReference<VehicleDto>() {}
                );
        return responseEntityResult.getStatusCode();
    }
}
