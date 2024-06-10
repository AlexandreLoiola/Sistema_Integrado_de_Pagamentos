package com.AlexandreLoiola.PaymentIntegrationSystem.rest.controller;

import com.AlexandreLoiola.PaymentIntegrationSystem.rest.form.CardPaymentForm;
import com.AlexandreLoiola.PaymentIntegrationSystem.service.PagSeguroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/card")
public class CardController {
    private PagSeguroService pagSeguroService;

    public CardController(PagSeguroService pagSeguroService) {
        this.pagSeguroService = pagSeguroService;
    }


    @PostMapping("/generate")
    public ResponseEntity<String> chargeCard() {
        CardPaymentForm cardPaymentForm = createMockOrder();
        ResponseEntity<String> response = pagSeguroService.createCardOrder(cardPaymentForm);
        return response;
    }

    CardPaymentForm createMockOrder() {
        CardPaymentForm.Customer customer = new CardPaymentForm.Customer();
        customer.setName("Jose da Silva");
        customer.setEmail("email@test.com");
        customer.setTaxId("12345678909");

        CardPaymentForm.Phone phone = new CardPaymentForm.Phone();
        phone.setCountry("55");
        phone.setArea("11");
        phone.setNumber("999999999");
        phone.setType("MOBILE");
        customer.setPhones(List.of(phone));

        CardPaymentForm.Item item = new CardPaymentForm.Item();
        item.setReferenceId("referencia do item");
        item.setName("nome do item");
        item.setQuantity(1);
        item.setUnitAmount(500);

        CardPaymentForm.Amount qrCodeAmount = new CardPaymentForm.Amount();
        qrCodeAmount.setValue(500);

        CardPaymentForm.QrCode qrCode = new CardPaymentForm.QrCode();
        qrCode.setAmount(qrCodeAmount);

        CardPaymentForm.Address address = new CardPaymentForm.Address();
        address.setStreet("Avenida Brigadeiro Faria Lima");
        address.setNumber("1384");
        address.setComplement("apto 12");
        address.setLocality("Pinheiros");
        address.setCity("São Paulo");
        address.setRegionCode("SP");
        address.setCountry("BRA");
        address.setPostalCode("01452002");

        CardPaymentForm.Shipping shipping = new CardPaymentForm.Shipping();
        shipping.setAddress(address);

        List<String> notificationUrls = List.of("http://api.webhookinbox.com/i/7LMOcndm/in/");

        CardPaymentForm.Interest interest = new CardPaymentForm.Interest();
        interest.setTotal(887);
        interest.setInstallments(4);

        CardPaymentForm.Buyer buyer = new CardPaymentForm.Buyer();
        buyer.setInterest(interest);

        CardPaymentForm.Fees fees = new CardPaymentForm.Fees();
        fees.setBuyer(buyer);

        CardPaymentForm.Amount amount = new CardPaymentForm.Amount();
        amount.setValue(10887);
        amount.setFees(fees);
        amount.setCurrency("BRL");

        CardPaymentForm.Card card = new CardPaymentForm.Card();
        card.setNumber("4111111111111111");
        card.setExpMonth("03");
        card.setExpYear("2026");
        card.setSecurityCode("123");

        CardPaymentForm.Holder holder = new CardPaymentForm.Holder();
        holder.setName("Jose da Silva");
        holder.setTaxId("65544332211");

        card.setHolder(holder);
        card.setStore(false);

        CardPaymentForm.PaymentMethod paymentMethod = new CardPaymentForm.PaymentMethod();
        paymentMethod.setType("CREDIT_CARD");
        paymentMethod.setInstallments(8);
        paymentMethod.setCapture(false);
        paymentMethod.setCard(card);

        CardPaymentForm.Charge charge = new CardPaymentForm.Charge();
        charge.setReferenceId("referencia do pagamento");
        charge.setDescription("descricao do pagamento");
        charge.setAmount(amount);
        charge.setPaymentMethod(paymentMethod);
        charge.setNotificationUrls(notificationUrls);

        List<CardPaymentForm.Charge> charges = List.of(charge);

        CardPaymentForm cardPaymentForm = new CardPaymentForm();
        cardPaymentForm.setReferenceId("ex-00001");
        cardPaymentForm.setCustomer(customer);
        cardPaymentForm.setItems(List.of(item));
        cardPaymentForm.setQrCode(qrCode);
        cardPaymentForm.setShipping(shipping);
        cardPaymentForm.setNotificationUrls(notificationUrls);
        cardPaymentForm.setCharges(charges);

        return cardPaymentForm;
    }
}