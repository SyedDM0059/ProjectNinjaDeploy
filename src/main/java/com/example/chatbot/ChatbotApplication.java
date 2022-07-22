package com.example.chatbot;

import com.example.chatbot.DBstuff.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;


//@ComponentScan({"com"})
//@EntityScan("com.example.chatbot")
//@EnableMongoRepositories()
@SpringBootApplication
public class ChatbotApplication {

	@Autowired
	private QuestionRepository questionRepository;
	public static void main(String[] args) {
		SpringApplication.run(ChatbotApplication.class, args);
	}

}
