package com.example;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;

public class HelloService extends ActionSupport {
    private static final Logger logger = LogManager.getLogger(HelloService.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();
    
    // Struts 2のプロパティ
    private String name;
    private String message;
    private String jsonResponse;

    // デフォルトのexecuteメソッド
    @Override
    public String execute() throws Exception {
        logger.debug("execute() called with name='{}'", name);
        
        if (StringUtils.isBlank(name)) {
            logger.info("Name was blank. Using default greeting.");
            this.message = "Hello";
        } else {
            this.message = "Hello, " + name + "!";
            logger.info("Greeting generated: {}", message);
        }

        try {
            this.jsonResponse = objectMapper.writeValueAsString(Map.of("message", message, "name", name != null ? name : ""));
            logger.debug("Greeting JSON: {}", jsonResponse);
        } catch (JsonProcessingException e) {
            logger.warn("Failed to serialize greeting to JSON", e);
        }
        
        return SUCCESS;
    }
    
    // 従来のgreetメソッドも保持（後方互換性のため）
    public String greet(String name) {
        this.name = name;
        try {
            return execute();
        } catch (Exception e) {
            logger.error("Error in greet method", e);
            return ERROR;
        }
    }

    // Getters and Setters for Struts 2
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getJsonResponse() {
        return jsonResponse;
    }

    public void setJsonResponse(String jsonResponse) {
        this.jsonResponse = jsonResponse;
    }
}



