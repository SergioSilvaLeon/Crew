package com.nearsoft.service.service;

import com.nearsoft.service.repository.Customer;
import com.nearsoft.service.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskMicroService {

    private final Logger mLogger = LoggerFactory.getLogger(TaskMicroService.class);
    private CustomerRepository mTaskRepository;

    public TaskMicroService(CustomerRepository mTaskRepository) {
        this.mTaskRepository = mTaskRepository;
    }

    @JmsListener(destination = "mailbox")
    public void createTask(String task) {
        mLogger.info("[x] Received task {}", task);

        // TODO: Insert task into repostory
        mTaskRepository.deleteAll();

        // save a couple of customers
        mTaskRepository.save(new Customer("Alice", "Smith"));
        mTaskRepository.save(new Customer("Bob", "Smith"));

        // fetch all customers
        System.out.println("Customers found with findAll():");
        System.out.println("-------------------------------");
        for (Customer customer : mTaskRepository.findAll()) {
            System.out.println(customer);
        }
        System.out.println();

        // fetch an individual customer
        System.out.println("Customer found with findByFirstName('Alice'):");
        System.out.println("--------------------------------");
        System.out.println(mTaskRepository.findByFirstName("Alice"));

        System.out.println("Customers found with findByLastName('Smith'):");
        System.out.println("--------------------------------");
        for (Customer customer : mTaskRepository.findByLastName("Smith")) {
            System.out.println(customer);
        }

    }

    // TODO: Add Listener
    public List<String> getTasks() {
        mLogger.info("[x] Received  Get Tasks Request");

        return null;
    }

}
