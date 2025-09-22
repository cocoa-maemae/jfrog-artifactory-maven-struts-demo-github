package com.example;

public class HelloServiceRunner {
    public static void main(String[] args) {
        System.out.println("=== HelloService Struts 2.5 CUI Demo ===");
        
        HelloService helloService = new HelloService();
        
        // テスト1: 名前あり
        System.out.println("\n1. 名前ありのテスト:");
        helloService.setName("Struts");
        try {
            String result = helloService.execute();
            System.out.println("結果: " + result);
            System.out.println("メッセージ: " + helloService.getMessage());
            System.out.println("JSON: " + helloService.getJsonResponse());
        } catch (Exception e) {
            System.err.println("エラー: " + e.getMessage());
        }
        
        // テスト2: 空の名前
        System.out.println("\n2. 空の名前のテスト:");
        helloService.setName("");
        try {
            String result = helloService.execute();
            System.out.println("結果: " + result);
            System.out.println("メッセージ: " + helloService.getMessage());
            System.out.println("JSON: " + helloService.getJsonResponse());
        } catch (Exception e) {
            System.err.println("エラー: " + e.getMessage());
        }
        
        // テスト3: 従来のgreetメソッド
        System.out.println("\n3. 従来のgreetメソッドのテスト:");
        String greetResult = helloService.greet("World");
        System.out.println("結果: " + greetResult);
        System.out.println("メッセージ: " + helloService.getMessage());
        System.out.println("JSON: " + helloService.getJsonResponse());
        
        // テスト4: コマンドライン引数がある場合
        if (args.length > 0) {
            System.out.println("\n4. コマンドライン引数のテスト:");
            String name = args[0];
            helloService.setName(name);
            try {
                String result = helloService.execute();
                System.out.println("引数: " + name);
                System.out.println("結果: " + result);
                System.out.println("メッセージ: " + helloService.getMessage());
                System.out.println("JSON: " + helloService.getJsonResponse());
            } catch (Exception e) {
                System.err.println("エラー: " + e.getMessage());
            }
        }
        
        System.out.println("\n=== デモ完了 ===");
    }
}
