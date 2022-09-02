package com.user.service;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.user.dto.DepartmentDto;
import com.user.entity.User;
import com.user.repository.UserRepository;
import com.user.vo.ResponseVo;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	DiscoveryClient discoveryClient;

	public User saveUser(User user) {
		return userRepository.save(user);
	}

	public ResponseVo getUserByDepartmentId(Integer id) {
		ResponseVo vo = new ResponseVo();
		Optional<User> user = userRepository.findById(Long.valueOf(id));
		User u = null;
		DepartmentDto department = null;
		List<ServiceInstance> instances = discoveryClient.getInstances("DEPARTMENT-SERVICE");
		String url = null;
		if (Optional.ofNullable(instances).isPresent()) {
			Optional<URI> findFirst = instances.stream().map(ServiceInstance::getUri).findFirst();
			if (findFirst.isPresent()) {
				url = findFirst.get().toString();
			}
		}
		if (user.isPresent()) {
			u = user.get();
		}
		if (!(StringUtils.isEmpty(url)) && Optional.ofNullable(u).isPresent()) {
			department = new RestTemplate().getForObject(
					url.concat("/departments/getDepartment?id=" + u.getDepartmentId()), DepartmentDto.class);
		}
		vo.setUser(u);
		vo.setDepartmentDto(department);
		return vo;
	}
}