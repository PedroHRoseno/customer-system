package com.customersystem.poc.consumers;

import com.customersystem.poc.dtos.CustomerDto;
import com.customersystem.poc.services.CustomerService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class CustomerConsumer {

    @Autowired
    CustomerService customerService;

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void listen (@Payload CustomerDto customerDto){
        customerService.validateToPost(customerDto);
        customerService.save(customerService.fillValuesInModel(customerDto, "POST"));
        System.out.println("Customer register received: " + customerDto.getName() );
    }
}
