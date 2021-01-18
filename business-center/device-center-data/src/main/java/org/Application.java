package org;

import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
public class Application {

    //static final String defaultContextName = "config/pipedevice.xml";
    static final String defaultContextName = "config/airdevice.xml";

    @SuppressWarnings("resource")
    public static void main(String[] args) {

        String applicationContextName;
        if (args.length == 1){
            applicationContextName = args[0];
        } else {
            applicationContextName = defaultContextName;
        }

        new FileSystemXmlApplicationContext( applicationContextName );
    }

}
