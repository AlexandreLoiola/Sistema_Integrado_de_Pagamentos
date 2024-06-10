package com.AlexandreLoiola.PaymentIntegrationSystem.rest.controller;

import com.AlexandreLoiola.PaymentIntegrationSystem.rest.form.PixPaymentForm;
import com.AlexandreLoiola.PaymentIntegrationSystem.service.PagSeguroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pix")
public class PixController {

    private PagSeguroService pagSeguroService;

    public PixController(PagSeguroService pagSeguroService) {
        this.pagSeguroService = pagSeguroService;
    }

    @PostMapping("/generate")
    public ResponseEntity<String> generateBoleto() {
        PixPaymentForm pixPaymentForm = createMockOrder();
        ResponseEntity<String> response = pagSeguroService.createPixOrder(pixPaymentForm);
        return response;
    }

    PixPaymentForm createMockOrder() {
        PixPaymentForm.Customer customer = new PixPaymentForm.Customer();
        customer.setName("Jose da Silva");
        customer.setEmail("email@test.com");
        customer.setTaxId("12345679891");

        PixPaymentForm.Phone phone = new PixPaymentForm.Phone();
        phone.setCountry("55");
        phone.setArea("11");
        phone.setNumber("999999999");
        phone.setType("MOBILE");
        customer.setPhones(List.of(phone));

        PixPaymentForm.Item item = new PixPaymentForm.Item();
        item.setName("nome do item");
        item.setQuantity(1);
        item.setUnitAmount(500);

        PixPaymentForm.Shipping shipping = new PixPaymentForm.Shipping();
        PixPaymentForm.Address address = new PixPaymentForm.Address();
        address.setStreet("Avenida Brigadeiro Faria Lima");
        address.setNumber("1384");
        address.setComplement("apto 12");
        address.setLocality("Pinheiros");
        address.setCity("São Paulo");
        address.setRegionCode("SP");
        address.setCountry("BRA");
        address.setPostalCode("01452002");
        shipping.setAddress(address);

        List<String> notificationUrls = List.of("https://meusite.com/notificacoes");

        PixPaymentForm.Amount amount = new PixPaymentForm.Amount();
        amount.setValue(500);

        PixPaymentForm.QrCode qrCode = new PixPaymentForm.QrCode();
        qrCode.setAmount(amount);
        qrCode.setExpirationDate("2024-06-29T20:15:59-03:00");

        List<PixPaymentForm.QrCode> qrCodes = List.of(qrCode);

        PixPaymentForm pixPaymentForm = new PixPaymentForm();
        pixPaymentForm.setReferenceId("ex-00001");
        pixPaymentForm.setCustomer(customer);
        pixPaymentForm.setItems(List.of(item));
        pixPaymentForm.setQrCodes(qrCodes);
        pixPaymentForm.setShipping(shipping);
        pixPaymentForm.setNotificationUrls(notificationUrls);

        return pixPaymentForm;
    }
}