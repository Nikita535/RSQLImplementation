package com.example.rsqlimplementation.model.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.core.SpringVersion;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskCreateDto {
    String code;
    boolean successful;
}

//public record TaskCreateDto(
//        String code,
//        boolean successful
//) {
//    @JsonCreator
//    public TaskCreateDto(
//            @JsonProperty("code") String code,
//            @JsonProperty(value = "successful", required = true) boolean successful
//    ) {
//        this.code = code;
//        this.successful = successful;
//    }
//}