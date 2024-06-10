package com.AlexandreLoiola.PaymentIntegrationSystem.rest.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoletoPaymentForm {
    @JsonProperty("reference_id")
    private String referenceId;
    private CustomerDto customer;
    private List<ItemDto> items;
    private ShippingDto shipping;
    @JsonProperty("notification_urls")
    private List<String> notificationUrls;
    private List<ChargeDto> charges;

    @Data
    @NoArgsConstructor
    public static class CustomerDto {
        private String name;
        private String email;
        @JsonProperty("tax_id")
        private String taxId;
        private List<PhoneDto> phones;
    }

    @Data
    @NoArgsConstructor
    public static class PhoneDto {
        private String country;
        private String area;
        private String number;
        private String type;
    }

    @Data
    @NoArgsConstructor
    public static class ItemDto {
        @JsonProperty("reference_id")
        private String referenceId;
        private String name;
        private int quantity;
        @JsonProperty("unit_amount")
        private int unitAmount;
    }

    @Data
    @NoArgsConstructor
    public static class ShippingDto {
        private AddressDto address;
    }

    @Data
    @NoArgsConstructor
    public static class AddressDto {
        private String street;
        private String number;
        private String complement;
        private String locality;
        private String city;
        private String region;
        @JsonProperty("region_code")
        private String regionCode;
        private String country;
        @JsonProperty("postal_code")
        private String postalCode;
    }

    @Data
    @NoArgsConstructor
    public static class ChargeDto {
        @JsonProperty("reference_id")
        private String referenceId;
        private String description;
        private AmountDto amount;
        @JsonProperty("payment_method")
        private PaymentMethodDto paymentMethod;

        @Data
        @NoArgsConstructor
        public static class AmountDto {
            private int value;
            private String currency;
        }

        @Data
        @NoArgsConstructor
        public static class PaymentMethodDto {
            private String type;
            private BoletoDto boleto;

            @Data
            @NoArgsConstructor
            public static class BoletoDto {
                @JsonProperty("due_date")
                @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
                private Date dueDate;
                @JsonProperty("instruction_lines")
                private InstructionLinesDto instructionLines;
                private HolderDto holder;

                @Data
                @NoArgsConstructor
                public static class InstructionLinesDto {
                    @JsonProperty("line_1")
                    private String line1;
                    @JsonProperty("line_2")
                    private String line2;
                }

                @Data
                @NoArgsConstructor
                public static class HolderDto {
                    private String name;
                    @JsonProperty("tax_id")
                    private String taxId;
                    private String email;
                    private AddressDto address;
                }
            }
        }
    }
}