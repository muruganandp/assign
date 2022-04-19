package com.onboarding.assign.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.onboarding.assign.model.Employee;
import com.onboarding.assign.vo.EmployeeVO;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
	Logger logger = LoggerFactory.getLogger("EmployeeMapper");
	EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);
	@Mapping(source="employee.empCode",target="empCode")
    EmployeeVO modelToVO(Employee employee);
	List<EmployeeVO> modelsToVos(List<Employee> employee);
    @InheritInverseConfiguration
    Employee voToModel(EmployeeVO employeeVO);
}
