package com.onboarding.assign.mapper;

import com.onboarding.assign.model.Employee;
import com.onboarding.assign.vo.EmployeeVO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-04-19T17:53:05+0530",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.4.1.jar, environment: Java 11.0.12 (Eclipse Foundation)"
)
@Component
public class EmployeeMapperImpl implements EmployeeMapper {

    @Override
    public EmployeeVO modelToVO(Employee employee) {
        if ( employee == null ) {
            return null;
        }

        EmployeeVO employeeVO = new EmployeeVO();

        employeeVO.setEmpCode( employee.getEmpCode() );
        employeeVO.setId( employee.getId() );
        employeeVO.setName( employee.getName() );
        employeeVO.setDesignation( employee.getDesignation() );
        employeeVO.setDepartment( employee.getDepartment() );

        return employeeVO;
    }

    @Override
    public List<EmployeeVO> modelsToVos(List<Employee> employee) {
        if ( employee == null ) {
            return null;
        }

        List<EmployeeVO> list = new ArrayList<EmployeeVO>( employee.size() );
        for ( Employee employee1 : employee ) {
            list.add( modelToVO( employee1 ) );
        }

        return list;
    }

    @Override
    public Employee voToModel(EmployeeVO employeeVO) {
        if ( employeeVO == null ) {
            return null;
        }

        Employee employee = new Employee();

        employee.setEmpCode( employeeVO.getEmpCode() );
        employee.setId( employeeVO.getId() );
        employee.setName( employeeVO.getName() );
        employee.setDesignation( employeeVO.getDesignation() );
        employee.setDepartment( employeeVO.getDepartment() );

        return employee;
    }
}
