package com.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class HelloServiceStrutsTest {
    
    private HelloService helloService;
    
    @BeforeEach
    void setUp() {
        helloService = new HelloService();
    }
    
    @Test
    void testGreetWithName() {
        // 名前ありのテスト
        String result = helloService.greet("World");
        assertEquals("Hello, World!", result);
    }
    
    @Test
    void testGreetWithBlankName() {
        // 空の名前でテスト
        String result = helloService.greet("");
        assertEquals("Hello", result);
    }
    
    @Test
    void testGreetWithNullName() {
        // nullの名前でテスト
        String result = helloService.greet(null);
        assertEquals("Hello", result);
    }
    
    @Test
    void testGreetWithWhitespaceName() {
        // 空白のみの名前でテスト
        String result = helloService.greet("   ");
        assertEquals("Hello", result);
    }
}
