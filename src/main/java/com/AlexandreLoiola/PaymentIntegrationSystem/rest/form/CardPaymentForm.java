package com.AlexandreLoiola.PaymentIntegrationSystem.rest.form;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardPaymentForm {
    @JsonProperty("reference_id")
    private String referenceId;
    private Customer customer;
    private List<Item> items;
    @JsonProperty("qr_code")
    private QrCode qrCode;
    private Shipping shipping;
    @JsonProperty("notification_urls")
    private List<String> notificationUrls;
    private List<Charge> charges;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Customer {
        private String name;
        private String email;
        @JsonProperty("tax_id")
        private String taxId;
        private List<Phone> phones;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Phone {
        private String country;
        private String area;
        private String number;
        private String type;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Item {
        @JsonProperty("reference_id")
        private String referenceId;
        private String name;
        private int quantity;
        @JsonProperty("unit_amount")
        private int unitAmount;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class QrCode {
        private Amount amount;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Amount {
        private int value;
        private Fees fees;
        private String currency;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Fees {
        private Buyer buyer;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Buyer {
        private Interest interest;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Interest {
        private int total;
        private int installments;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Shipping {
        private Address address;
    }

    @Data
    @AllArgsConstructor
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

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Charge {
        @JsonProperty("reference_id")
        private String referenceId;
        private String description;
        private Amount amount;
        @JsonProperty("payment_method")
        private PaymentMethod paymentMethod;
        @JsonProperty("notification_urls")
        private List<String> notificationUrls;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PaymentMethod {
        private String type;
        private int installments;
        private boolean capture;
        private Card card;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Card {
        private String number;
        @JsonProperty("exp_month")
        private String expMonth;
        @JsonProperty("exp_year")
        private String expYear;
        @JsonProperty("security_code")
        private String securityCode;
        private Holder holder;
        private boolean store;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Holder {
        private String name;
        @JsonProperty("tax_id")
        private String taxId;
    }
}