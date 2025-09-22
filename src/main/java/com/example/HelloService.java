package com.example;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class HelloService extends Action {
    private static final Logger logger = LogManager.getLogger(HelloService.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    // Struts 1のActionメソッド
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, 
                               HttpServletRequest request, HttpServletResponse response) 
                               throws Exception {
        logger.debug("execute() called");
        
        // リクエストパラメータから名前を取得
        String name = request.getParameter("name");
        logger.debug("execute() called with name='{}'", name);
        
        String message;
        if (StringUtils.isBlank(name)) {
            logger.info("Name was blank. Using default greeting.");
            message = "Hello";
        } else {
            message = "Hello, " + name + "!";
            logger.info("Greeting generated: {}", message);
        }

        // リクエストに属性を設定
        request.setAttribute("message", message);
        request.setAttribute("name", name != null ? name : "");

        try {
            String jsonResponse = objectMapper.writeValueAsString(Map.of("message", message, "name", name != null ? name : ""));
            request.setAttribute("jsonResponse", jsonResponse);
            logger.debug("Greeting JSON: {}", jsonResponse);
        } catch (JsonProcessingException e) {
            logger.warn("Failed to serialize greeting to JSON", e);
        }
        
        return mapping.findForward("success");
    }
    
    // 従来のgreetメソッドも保持（後方互換性のため）
    public String greet(String name) {
        logger.debug("greet() called with name='{}'", name);
        
        if (StringUtils.isBlank(name)) {
            logger.info("Name was blank. Using default greeting.");
            return "Hello";
        }

        String message = "Hello, " + name + "!";
        logger.info("Greeting generated: {}", message);

        try {
            String json = objectMapper.writeValueAsString(Map.of("message", message, "name", name));
            logger.debug("Greeting JSON: {}", json);
        } catch (JsonProcessingException e) {
            logger.warn("Failed to serialize greeting to JSON", e);
        }
        return message;
    }
}



