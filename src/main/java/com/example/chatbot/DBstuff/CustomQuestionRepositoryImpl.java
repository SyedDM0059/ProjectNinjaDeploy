package com.example.chatbot.DBstuff;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.BasicUpdate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

// just in case we need partial updates
@Repository
public class CustomQuestionRepositoryImpl implements CustomQuestionRepository{
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void partialUpdate(String questionId, String fieldName, Object fieldValue) {
        mongoTemplate.findAndModify(BasicQuery.query(Criteria.where("id").is(questionId)),
        BasicUpdate.update(fieldName, fieldValue), FindAndModifyOptions.none(), Question.class);
    }
}
