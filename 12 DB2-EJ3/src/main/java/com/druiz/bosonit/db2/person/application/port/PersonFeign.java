package com.druiz.bosonit.db2.person.application.port;

import com.druiz.bosonit.db2.teacher.infrastucture.controller.dto.output.TeacherOutputDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "simpleFeign", url = "http://localhost:8080")
public interface PersonFeign {
    @GetMapping("/teacher/id/{httpCode}")
    ResponseEntity<TeacherOutputDto> callServer(@PathVariable("httpCode") String httpCode);
}
