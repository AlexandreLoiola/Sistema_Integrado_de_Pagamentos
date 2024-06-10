package com.AlexandreLoiola.PaymentIntegrationSystem.rest.controller;

import com.AlexandreLoiola.PaymentIntegrationSystem.rest.form.BoletoPaymentForm;
import com.AlexandreLoiola.PaymentIntegrationSystem.service.PagSeguroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;

import java.util.List;

@RestController
@RequestMapping("/boleto")
public class BoletoController {

    private PagSeguroService pagSeguroService;

    public BoletoController(PagSeguroService pagSeguroService) {
        this.pagSeguroService = pagSeguroService;
    }

    @PostMapping("/generate")
    public ResponseEntity<String> generateBoleto() {
        BoletoPaymentForm boletoPaymentForm = createMockOrder();
        var response = pagSeguroService.createBoletoOrder(boletoPaymentForm);
        return response;
    }

    private BoletoPaymentForm createMockOrder() {
        BoletoPaymentForm order = new BoletoPaymentForm();
        order.setReferenceId("ex-00001");

        BoletoPaymentForm.CustomerDto customer = new BoletoPaymentForm.CustomerDto();
        customer.setName("Jose da Silva");
        customer.setEmail("email@test.com");
        customer.setTaxId("12345679891");

        BoletoPaymentForm.PhoneDto phone = new BoletoPaymentForm.PhoneDto();
        phone.setCountry("55");
        phone.setArea("11");
        phone.setNumber("999999999");
        phone.setType("MOBILE");
        customer.setPhones(List.of(phone));

        order.setCustomer(customer);

        BoletoPaymentForm.ItemDto item = new BoletoPaymentForm.ItemDto();
        item.setReferenceId("referencia do item");
        item.setName("nome do item");
        item.setQuantity(1);
        item.setUnitAmount(510);

        order.setItems(List.of(item));

        BoletoPaymentForm.ShippingDto shipping = new BoletoPaymentForm.ShippingDto();
        BoletoPaymentForm.AddressDto address = new BoletoPaymentForm.AddressDto();
        address.setStreet("Avenida Brigadeiro Faria Lima");
        address.setNumber("1384");
        address.setComplement("apto 12");
        address.setLocality("Pinheiros");
        address.setCity("São Paulo");
        address.setRegion("Sao Paulo");
        address.setRegionCode("SP");
        address.setCountry("BRA");
        address.setPostalCode("01452002");
        shipping.setAddress(address);

        order.setShipping(shipping);

        order.setNotificationUrls(List.of("https://meusite.com/notificacoes"));

        BoletoPaymentForm.ChargeDto charge = new BoletoPaymentForm.ChargeDto();
        charge.setReferenceId("referencia da cobranca");
        charge.setDescription("descricao da cobranca");

        BoletoPaymentForm.ChargeDto.AmountDto amount = new BoletoPaymentForm.ChargeDto.AmountDto();
        amount.setValue(500);
        amount.setCurrency("BRL");
        charge.setAmount(amount);

        BoletoPaymentForm.ChargeDto.PaymentMethodDto paymentMethod = new BoletoPaymentForm.ChargeDto.PaymentMethodDto();
        paymentMethod.setType("BOLETO");

        BoletoPaymentForm.ChargeDto.PaymentMethodDto.BoletoDto boleto = new BoletoPaymentForm.ChargeDto.PaymentMethodDto.BoletoDto();
        boleto.setDueDate(Date.valueOf("2025-07-29"));

        BoletoPaymentForm.ChargeDto.PaymentMethodDto.BoletoDto.InstructionLinesDto instructionLines = new BoletoPaymentForm.ChargeDto.PaymentMethodDto.BoletoDto.InstructionLinesDto();
        instructionLines.setLine1("Pagamento processado para DESC Fatura");
        instructionLines.setLine2("Via PagSeguro");
        boleto.setInstructionLines(instructionLines);

        BoletoPaymentForm.ChargeDto.PaymentMethodDto.BoletoDto.HolderDto holder = new BoletoPaymentForm.ChargeDto.PaymentMethodDto.BoletoDto.HolderDto();
        holder.setName("Jose da Silva");
        holder.setTaxId("12345679891");
        holder.setEmail("jose@email.com");

        BoletoPaymentForm.AddressDto holderAddress = new BoletoPaymentForm.AddressDto();
        holderAddress.setCountry("Brasil");
        holderAddress.setRegion("São Paulo");
        holderAddress.setRegionCode("SP");
        holderAddress.setCity("Sao Paulo");
        holderAddress.setPostalCode("01452002");
        holderAddress.setStreet("Avenida Brigadeiro Faria Lima");
        holderAddress.setNumber("1384");
        holderAddress.setLocality("Pinheiros");

        holder.setAddress(holderAddress);
        boleto.setHolder(holder);
        paymentMethod.setBoleto(boleto);

        charge.setPaymentMethod(paymentMethod);
        order.setCharges(List.of(charge));

        return order;
    }
}