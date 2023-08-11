package com.eic.springbootmicroservices.departmentservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(
		description = "DepartmentDto Model Information"
		)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDto {
	
	private Long id;
	@Schema(
			description = "Department Name"
			)
	@NotEmpty(message = "Department Name should not be null or empty")
	private String departmentName;
	@Schema(description = "Department Description")
	@NotEmpty(message ="Department Description should not be null or empty")
	private String departmentDescription;
	@Schema(description = "Department Code")
	@NotEmpty(message = "Department Code should not be null or empty")
	private String departmentCode;
}
