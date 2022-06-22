package com.IsaMrsTim19.projekat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.IsaMrsTim19.projekat.dto.DeletionRequestDTO;
import com.IsaMrsTim19.projekat.model.DeletionRequest;
import com.IsaMrsTim19.projekat.model.User;
import com.IsaMrsTim19.projekat.repository.DeletionRequestRepository;

@Service
public class DeletionRequestService {

	@Autowired
	DeletionRequestRepository delReqRepo;
	
	@Autowired
	UserService userService;
	
	
	public DeletionRequest createRequest(DeletionRequestDTO dto, User user) {
		DeletionRequest request = new DeletionRequest(dto.getRequestText(), user);
		return this.save(request);
	}
	
	public DeletionRequest save(DeletionRequest request) {
		return delReqRepo.save(request);
	}

	public List<DeletionRequest> findAll(){
		List<DeletionRequest> req = delReqRepo.findAll();
		if(req == null) {
			return null;
		}
		return req;
	}
	public void deleteUser(Long id) {
		DeletionRequest request = delReqRepo.findById(id).orElse(null);
		userService.delete(request.getUser());
		delReqRepo.delete(request);
		
		
	}
	
	
}
