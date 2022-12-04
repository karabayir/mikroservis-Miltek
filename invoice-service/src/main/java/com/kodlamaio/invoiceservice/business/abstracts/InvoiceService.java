package com.kodlamaio.invoiceservice.business.abstracts;

import java.util.List;

import com.kodlamaio.invoiceservice.business.request.CreateInvoiceRequest;
import com.kodlamaio.invoiceservice.business.response.CreateInvoiceResponse;
import com.kodlamaio.invoiceservice.business.response.GetAllInvoicesResponse;

public interface InvoiceService {

	List<GetAllInvoicesResponse> getAll();
	
	CreateInvoiceResponse add(CreateInvoiceRequest request);
}
