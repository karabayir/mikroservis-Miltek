package com.kodlamaio.invoiceservice.business.concretes;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kodlamaio.common.utilities.mapper.ModelMapperService;
import com.kodlamaio.invoiceservice.business.abstracts.InvoiceService;
import com.kodlamaio.invoiceservice.business.request.CreateInvoiceRequest;
import com.kodlamaio.invoiceservice.business.response.CreateInvoiceResponse;
import com.kodlamaio.invoiceservice.business.response.GetAllInvoicesResponse;
import com.kodlamaio.invoiceservice.entities.Invoice;
import com.kodlamaio.invoiceservice.repository.InvoiceRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class InvoiceManager implements InvoiceService{

	private final InvoiceRepository invoiceRepository;
	private final ModelMapperService mapperService;

	@Override
	public List<GetAllInvoicesResponse> getAll() {
		return invoiceRepository.findAll()
				.stream()
				.map(i-> mapperService.forResponse().map(i, GetAllInvoicesResponse.class))
				.collect(Collectors.toList());
	}

	@Override
	public CreateInvoiceResponse add(CreateInvoiceRequest request) {
		Invoice invoice = mapperService.forRequest().map(request, Invoice.class);
		invoice.setId(UUID.randomUUID().toString());
		invoiceRepository.save(invoice);
		return mapperService.forResponse().map(invoice, CreateInvoiceResponse.class);
	}
}
