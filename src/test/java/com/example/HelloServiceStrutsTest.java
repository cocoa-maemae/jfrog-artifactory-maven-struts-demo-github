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
    void testExecuteWithName() throws Exception {
        // Struts 2のプロパティを設定
        helloService.setName("World");
        
        // execute()メソッドを実行
        String result = helloService.execute();
        
        // 結果を検証
        assertEquals("success", result);
        assertEquals("Hello, World!", helloService.getMessage());
        assertNotNull(helloService.getJsonResponse());
        assertTrue(helloService.getJsonResponse().contains("Hello, World!"));
    }
    
    @Test
    void testExecuteWithBlankName() throws Exception {
        // 空の名前でテスト
        helloService.setName("");
        
        String result = helloService.execute();
        
        assertEquals("success", result);
        assertEquals("Hello", helloService.getMessage());
        assertNotNull(helloService.getJsonResponse());
    }
    
    @Test
    void testExecuteWithNullName() throws Exception {
        // nullの名前でテスト
        helloService.setName(null);
        
        String result = helloService.execute();
        
        assertEquals("success", result);
        assertEquals("Hello", helloService.getMessage());
        assertNotNull(helloService.getJsonResponse());
    }
    
    @Test
    void testGreetMethod() {
        // 従来のgreetメソッドをテスト
        String result = helloService.greet("Struts");
        
        assertEquals("success", result);
        assertEquals("Hello, Struts!", helloService.getMessage());
    }
}
