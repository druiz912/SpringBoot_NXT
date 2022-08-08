package com.druiz.bs9.persona.application.port;
import com.druiz.bs9.profesor.infrastucture.dto.output.ProfesorOutputDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "simpleFeign", url = "http://localhost:8080")
public interface FeignPort {
    @GetMapping("/profesor/id/{id}")
    ResponseEntity<ProfesorOutputDto> callServer(@PathVariable("id") String id);
}