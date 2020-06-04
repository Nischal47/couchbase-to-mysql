package com.example.couchbasemysql;

import com.example.couchbasemysql.database.implementation.CouchbaseToMysqlImplementation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class CouchbaseMysqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(CouchbaseMysqlApplication.class, args);
	}

	@GetMapping("/")
	public String index() {
		return "Welcome to MySQL - Couchbase Sync";
	}

	@GetMapping("/sync-ms")
	public String syncCBToMS() {
		new CouchbaseToMysqlImplementation().couchbaseToMysql();
		return "Couch Base to MySQL\n\nCheck MySQL Database";
	}
}
