package com.kodlamaio.filterservice.business.abstracts;

import java.util.List;

import com.kodlamaio.filterservice.business.responses.GetAllFiltersResponse;
import com.kodlamaio.filterservice.entities.Filter;

public interface FilterService {

	List<GetAllFiltersResponse> getAll();
    List<GetAllFiltersResponse> getByBrandName(String brandName);
    List<GetAllFiltersResponse> getByModelName(String modelName);
    List<GetAllFiltersResponse> getByPlate(String plate);
    List<GetAllFiltersResponse> searchByPlate(String plate);
    List<GetAllFiltersResponse> searchByBrandName(String brandName);
    List<GetAllFiltersResponse> searchByModelName(String modelName);
    List<GetAllFiltersResponse> getByModelYear(int modelYear);
    List<GetAllFiltersResponse> getByState(String state);
    
    
    Filter getByCarId(String id);
    List<Filter> getByModelId(String modelId);
    List<Filter> getByBrandId(String brandId);
    void save(Filter filter);
    void deleteById(String id);
    void deleteAllByBrandId(String brandId);
    void deleteAllByModelId(String modelId);
}
