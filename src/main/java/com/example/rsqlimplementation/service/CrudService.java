package com.example.rsqlimplementation.service;

public interface CrudService<CreateDto,PatchDto,Dto,Id> extends GetService<Dto,Id>{
    Dto create(CreateDto createDto);

    Dto update(PatchDto patchDto,Id id);

    void delete(Id id);
}
