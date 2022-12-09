package com.kodlamaio.paymentservice.adapters;

import java.util.Random;

import org.springframework.stereotype.Service;

import com.kodlamaio.common.utilities.exception.BusinessException;
import com.kodlamaio.paymentservice.business.abstracts.FakePosService;

@Service
public class FakePosAdapter implements FakePosService{

	@Override
	public void payment() {
		boolean randomValue = new Random().nextBoolean();
		if(randomValue == false)
			throw new BusinessException("Payment Failed");
	}

}
