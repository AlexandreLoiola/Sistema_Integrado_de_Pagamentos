package com.AlexandreLoiola.PaymentIntegrationSystem.rest.form;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PixPaymentForm {
    @JsonProperty("reference_id")
    private String referenceId;
    private Customer customer;
    private List<Item> items;
    @JsonProperty("qr_codes")
    private List<QrCode> qrCodes;
    private Shipping shipping;
    @JsonProperty("notification_urls")
    private List<String> notificationUrls;

    @Data
    @NoArgsConstructor
    public static class Customer {
        private String name;
        private String email;
        @JsonProperty("tax_id")
        private String taxId;
        private List<Phone> phones;
    }

    @Data
    @NoArgsConstructor
    public static class Phone {
        private String country;
        private String area;
        private String number;
        private String type;
    }

    @Data
    @NoArgsConstructor
    public static class Item {
        private String name;
        private int quantity;
        @JsonProperty("unit_amount")
        private int unitAmount;
    }

    @Data
    @NoArgsConstructor
    public static class QrCode {
        private Amount amount;
        @JsonProperty("expiration_date")
        private String expirationDate;
    }

    @Data
    @NoArgsConstructor
    public static class Amount {
        private int value;
    }

    @Data
    @NoArgsConstructor
    public static class Shipping {
        private Address address;
    }

    @Data
    @NoArgsConstructor
    public static class Address {
        private String street;
        private String number;
        private String complement;
        private String locality;
        private String city;
        @JsonProperty("region_code")
        private String regionCode;
        private String country;
        @JsonProperty("postal_code")
        private String postalCode;
    }
}