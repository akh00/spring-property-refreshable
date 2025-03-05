package com.example.service;

import com.example.service.config.SomeProperties;

public class SomeService
{
    private final SomeProperties someProperties;

    public SomeService(SomeProperties someProperties)
    {
        this.someProperties = someProperties;
    }

    public void doSomething() {
        if(someProperties.isSomeProperty()) {
            System.out.println("Doing something");
        } else {
            System.out.println("Do nothing");
        }
    }
}
