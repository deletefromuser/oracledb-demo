package com.example.oracledbdemo;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.time.LocalDateTime;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import com.example.oracledbdemo.dao.model.Countries;
import com.google.zxing.WriterException;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SpelTest {

    // https://www.baeldung.com/spring-expression-language
    // https://stackoverflow.com/questions/67414561/spring-expression-language-referencing-variables
    // https://docs.spring.io/spring-framework/docs/3.2.x/spring-framework-reference/html/expressions.html
    @Test
    void test1() throws WriterException, IOException {
        ExpressionParser expressionParser = new SpelExpressionParser();
        Expression expression = expressionParser.parseExpression("'Any string'");
        String result = (String) expression.getValue();
        System.out.println(result);

        expression = expressionParser.parseExpression("'Any string'.length()");
        Integer result2 = (Integer) expression.getValue();
        System.out.println(result2);

        Countries countries = new Countries("CC", "Chile", Short.valueOf("2"));
        expression = expressionParser.parseExpression("countryName");
        EvaluationContext context = new StandardEvaluationContext(countries);
        String result3 = (String) expression.getValue(context);
        System.out.println(result3);

        context = new StandardEvaluationContext();
        context.setVariable("country", countries);
        context.setVariable("todos",
                Lists.list(new Todo("a", "hello"), new Todo("b", "123"), new Todo("c", "opc")));
        expression = expressionParser.parseExpression("#country.countryName == 'Chile' and #todos.size > 0");
        boolean result4 = (Boolean) expression.getValue(context);
        System.out.println(result4);

        // expression = expressionParser.parseExpression("#todos.size > 0 and
        // #todos.?[id == 'c2'].get(0)?.name == 'opc'");
        expression = expressionParser.parseExpression(
                "#todos.size > 0 and #todos.?[id == 'c2'].size > 0 and #todos.?[id == 'c2'][0].name == 'opc'");
        boolean result5 = (Boolean) expression.getValue(context);
        System.out.println(result5);

        // filter
        expression = expressionParser.parseExpression(
                "#todos.size > 0 ? (#todos.?[id == 'c2'].size > 0 and #todos.?[id == 'c'][0].name == 'opc' ? 1 : (#todos.?[id == 'b2'].size > 0 and #todos.?[id == 'b'][0].name == '123' ? 2 : 3)) : 0");
        Integer result6 = (Integer) expression.getValue(context);
        System.out.println(result6);
        // first
        expression = expressionParser.parseExpression(
                "#todos.size > 0 ? (#todos.?[id == 'c2'].size > 0 and #todos.?[id == 'c'][0].name == 'opc' ? 1 : (#todos.?[id == 'b2'].size > 0 and #todos.^[id == 'b'][0].name == '123' ? 2 : 3)) : 0");
        result6 = (Integer) expression.getValue(context);
        System.out.println(result6);
        // last
        expression = expressionParser.parseExpression(
                "#todos.size > 0 ? (#todos.?[id == 'c2'].size > 0 and #todos.?[id == 'c'][0].name == 'opc' ? 1 : (#todos.?[id == 'b2'].size > 0 and #todos.$[id == 'b'][0].name == '123' ? 2 : 3)) : 0");
        result6 = (Integer) expression.getValue(context);
        System.out.println(result6);

        context.setVariable("dateTime", LocalDateTime.now());
        log.info(LocalDateTime.now().getClass().getCanonicalName());
        expression = expressionParser.parseExpression(
                "#dateTime instanceof T(java.time.LocalDateTime)");
        Boolean result7 = (Boolean) expression.getValue(context);
        System.out.println(result7);

        expression = expressionParser.parseExpression(
                "#systemEnvironment");
        System.out.println(expression.getValue(java.util.Properties.class));

        expression = expressionParser.parseExpression(
                "#todos.![name]");
        System.out.println(expression.getValue(context, java.util.List.class));
        expression = expressionParser.parseExpression(
                "#todos.![name matches '\\d+']");
        System.out.println(expression.getValue(context, java.util.List.class));

        assertTrue(true);
    }

    @Data
    @AllArgsConstructor
    public static class Todo {
        String id;
        String name;
    }
}
