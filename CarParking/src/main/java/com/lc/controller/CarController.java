package com.lc.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lc.entity.CarParking;
import com.lc.entity.Slot;
import com.lc.repo.CarRepo;
import com.lc.repo.SlotRepo;

@RestController
public class CarController {
	@Autowired
	CarRepo carrepo;
	@Autowired
	SlotRepo slotrepo;
	
	@PostMapping("/parkcar/{carnumber}")
	public Slot parkcar(@PathVariable String carnumber) {
		
		Integer slotid = carrepo.availableslot();
		if(slotid!=null) {
			CarParking cp1= new CarParking();
			cp1.setSlotid(slotid);
			cp1.setCarnumber(carnumber);
			cp1.setAllotmentdate(new Date());
			carrepo.save(cp1);
			Optional<Slot> s= slotrepo.findById(slotid);
			if(s.isPresent()) {
				Slot s1= s.get();
				s1.setStatus("NA");
				slotrepo.save(s1);
				return s1;
				
			}else {
				return null;
			}	
		}
		return null;
		
	}

	@PostMapping("/unparkcar/{slotid}")
	public String unparkacar(@PathVariable Integer slotid) {
		String message="";
		Optional<Slot> s = slotrepo.findById(slotid);
		if(s.isPresent()) {
			Slot s1=s.get();
			if(s1.getStatus().equals("NA")) {
				s1.setStatus("AVAILABLE");
				slotrepo.save(s1);
				message="Car Unparked";
			}else {
				message="There is no car parked for this slot";
			}
		}else {
			message="Wrong slot";
		}
		return message;
		
	}
	
	@GetMapping("/getinfobyslotid/{slotid}")
	public Map<String,String> getInfoBySlotId(@PathVariable Integer slotid){
		CarParking cp1 = carrepo.getParking(slotid,1);
		Map<String,String> details=new HashMap<String,String>();
		if(cp1!=null) {
			details.put("Slot ID", slotid.toString());
			details.put("Car Number", cp1.getCarnumber());
			details.put("Last Alloted Date", cp1.getAllotmentdate().toString());
			return details;
		}else {
			details.put("Error", "No Slot Found");
			return details; 
		}
		
	}
	@GetMapping(value="/getinfobycarnumber/{carnumber}")
	public Map<String,String> getInfoByCarNumber(@PathVariable("carnumber") String carnumber){
		CarParking cp1 = carrepo.getParkingByCarNumber(carnumber,1);
		Map<String,String> details=new HashMap<String,String>();
		if(cp1!=null) {
			details.put("Slot ID", cp1.getSlotid().toString());
			details.put("Car Number", carnumber);
			details.put("Last Alloted Date", cp1.getAllotmentdate().toString());
			return details;
		}else {
			details.put("Error", "No Car Found");
			return details; 
		}
		
	}

}
