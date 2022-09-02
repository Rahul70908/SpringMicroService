package com.user.vo;

import com.user.dto.DepartmentDto;
import com.user.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseVo {
	
	private User user;
	
	private DepartmentDto departmentDto;
}