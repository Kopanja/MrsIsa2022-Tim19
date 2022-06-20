package com.IsaMrsTim19.projekat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.IsaMrsTim19.projekat.model.Owner;
import com.IsaMrsTim19.projekat.model.OwnerApplication;
import com.IsaMrsTim19.projekat.repository.OwnerApplicationRepository;

@Service
public class OwnerApplicationService {

	@Autowired
	OwnerApplicationRepository ownerAppRepo;

	@Autowired
	OwnerService ownerService;

	@Autowired
	EmailSenderService emailService;

	public OwnerApplication save(OwnerApplication application) {
		return ownerAppRepo.save(application);
	}

	public List<OwnerApplication> findAll() {
		return ownerAppRepo.findAll();
	}

	public OwnerApplication findById(Long applicationId) {
		return ownerAppRepo.getById(applicationId);
	}

	public void acceptApplication(Long applicationId) {
		Owner owner = ownerService.findOwnerByApplicationId(applicationId);
		OwnerApplication oa = ownerAppRepo.getById(applicationId);
		if (oa != null) {
			ownerAppRepo.delete(oa);
		}
		owner.setActive(true);
		System.out.println(owner.getRole().getRole());
		ownerService.save(owner);
		emailService.sendEmail(owner.getEmail(), "Hook&Travel Application", "Your Application Has Been Accepted");
	}

	public void declineApplication(Long applicationId, String rejectionReason) {
		
		OwnerApplication oa = ownerAppRepo.getById(applicationId);
		Owner owner = ownerService.findOwnerByApplicationId(applicationId);
		ownerService.delete(owner);
		
		if (oa != null) {
			ownerAppRepo.delete(oa);
		}
		String body = "Your Application Has Been Declined" + "\n";
		body += "Reason: " + rejectionReason;
		emailService.sendEmail(owner.getEmail(), "Hook&Travel Application", body);

	}

}
