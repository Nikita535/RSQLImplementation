package com.example.rsqlimplementation.service;

import com.example.rsqlimplementation.model.param.QuerySearchParams;
import org.springframework.data.domain.Page;

public interface GetService<DTO,ID> {
    Page<DTO> get(QuerySearchParams params);
    DTO getById(ID id);
}
