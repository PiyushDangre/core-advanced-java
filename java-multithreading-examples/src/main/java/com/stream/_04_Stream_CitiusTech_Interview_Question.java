package com.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * find all orders that are already Shipped and have a
 * total order value greater than 2500
 */
public class _04_Stream_CitiusTech_Interview_Question {

    public static void main(String[] args) {

        /**
         * Sample Data
         *
         */

        // Sample Order Details
        List<OrderDetail> orderDetails = Arrays.asList(
                new OrderDetail(10100, "S18_1749", 30, 136.00),
                new OrderDetail(10100, "S18_2248", 50, 55.09),
                new OrderDetail(10100, "S18_4409", 22, 75.46),
                new OrderDetail(10100, "S24_3969", 49, 35.29),
                new OrderDetail(10101, "S18_2325", 25, 108.06),
                new OrderDetail(10102, "S18_2325", 25, 108.06)
        );

        // Sample Order Status
        List<OrderStatus> orderStatuses = Arrays.asList(
                new OrderStatus(10100, "Shipped"),
                new OrderStatus(10101, "Shipped"),
                new OrderStatus(10102, "Not Shipped")
        );



        /**
         * This will generate the following Map
         *
         * 10100 - OrderStatus{orderNumber=10100, status='Shipped'}
         * 10101 - OrderStatus{orderNumber=10101, status='Shipped'}
         *
         * In collectors.ToMap() - The first arg is keyMapper, and second is valueMapper
         *
         */
        Map<Integer, OrderStatus> map = orderStatuses.stream().filter(s -> s.status.equals("Shipped"))
                .collect(Collectors.toMap((s) -> s.orderNumber, (s) -> s));

        /**
         * The below will generate the correct result as fdollows -
         *
         * 10100 - 10223.83
         * 10101 - 2701.5
         *
         */

        orderDetails.stream().filter(s-> map.containsKey(s.orderNumber))
                .collect(Collectors.groupingBy(s-> s.orderNumber, Collectors.summingDouble(s-> s.priceEach * s.quantityOrdered))).entrySet().forEach(s-> System.out.println(s.getKey()+ " - "+s.getValue()));

    }

    static class OrderDetail {
        int orderNumber;
        String productCode;
        int quantityOrdered;
        double priceEach;

        @Override
        public String toString() {
            return "OrderDetail{" +
                    "orderNumber=" + orderNumber +
                    ", productCode='" + productCode + '\'' +
                    ", quantityOrdered=" + quantityOrdered +
                    ", priceEach=" + priceEach +
                    '}';
        }

        public OrderDetail(int orderNumber, String productCode, int quantityOrdered, double priceEach) {
            this.orderNumber = orderNumber;
            this.productCode = productCode;
            this.quantityOrdered = quantityOrdered;
            this.priceEach = priceEach;
        }

        public double getTotalPrice() {
            return quantityOrdered * priceEach;
        }
    }

    static class OrderStatus {
        int orderNumber;
        String status;

        public OrderStatus(int orderNumber, String status) {
            this.orderNumber = orderNumber;
            this.status = status;
        }

        @Override
        public String toString() {
            return "OrderStatus{" +
                    "orderNumber=" + orderNumber +
                    ", status='" + status + '\'' +
                    '}';
        }
    }
}