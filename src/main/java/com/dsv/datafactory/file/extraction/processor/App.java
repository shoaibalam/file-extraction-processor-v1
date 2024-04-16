
package com.dsv.datafactory.file.extraction.processor;

import com.dsv.datafactory.file.extraction.processor.util.StreamConstants;

@SpringBootApplication(scanBasePackages = StreamConstants.DSV_BASE_PAKAGE)
public class App {
    public static void main(String[] args) {
       SpringApplication.run(App.class, args);
    }
}