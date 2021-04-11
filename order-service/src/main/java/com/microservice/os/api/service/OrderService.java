package com.microservice.os.api.service;

import com.microservice.os.api.common.Payment;
import com.microservice.os.api.common.TransactionRequest;
import com.microservice.os.api.common.TransactionResponse;
import com.microservice.os.api.entity.Order;
import com.microservice.os.api.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {
  @Autowired
  private OrderRepository repository;
  @Autowired
  private RestTemplate template;

  public TransactionResponse saveOrder(TransactionRequest request){
      String response="";
      Order order=request.getOrder();
      Payment payment=request.getPayment();
      payment.setOrderId(order.getId());
      payment.setAmount(order.getPrice());
      //rest call
      Payment paymentResponse= template.postForObject("http://PAYMENT-SERVICE/payment/doPayment",payment,Payment.class);
      response=paymentResponse.getPaymentStatus().equals("success")?"payment processing successful and order placed":"there is a failure in payment api, order to card";
      repository.save(order);
      return new TransactionResponse(order,paymentResponse.getAmount(),paymentResponse.getTransactionId(),response);
  }

}
