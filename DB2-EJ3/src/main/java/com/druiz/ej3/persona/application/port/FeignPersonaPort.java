package com.druiz.ej3.persona.application.port;

import com.druiz.ej3.profesor.infrastucture.dto.output.ProfessorOutputDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "simpleFeign", url = "http://localhost:8080")
public interface FeignPersonaPort {
    @GetMapping("/profesor/id/{httpCode}")
    ResponseEntity<ProfessorOutputDto> callServer(@PathVariable("httpCode") String httpCode);
}
