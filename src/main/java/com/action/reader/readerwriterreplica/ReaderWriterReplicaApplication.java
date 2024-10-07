package com.action.reader.readerwriterreplica;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableEncryptableProperties
public class ReaderWriterReplicaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReaderWriterReplicaApplication.class, args);
    }

}
