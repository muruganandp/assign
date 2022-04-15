package com.onboarding.assign.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.onboarding.assign.model.Employee;
import com.onboarding.assign.vo.EmployeeVO;

import org.slf4j.*;

@Mapper(componentModel = MappingConstants.ComponentModel.CDI)
public interface EmployeeMapper {
	Logger logger = LoggerFactory.getLogger("EmployeeMapper");
	@Mapping(source="id",target="id")
    EmployeeVO modelToVO(Employee employee);
	List<EmployeeVO> modelsToVos(List<Employee> employee);
    @InheritInverseConfiguration
    Employee voToModel(EmployeeVO employeeVO);
}
