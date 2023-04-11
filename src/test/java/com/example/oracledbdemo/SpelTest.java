package com.example.oracledbdemo;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

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

public class SpelTest {

        // https://www.baeldung.com/spring-expression-language
        // https://stackoverflow.com/questions/67414561/spring-expression-language-referencing-variables
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

                // Lists.list().stream().filter(item -> return true).
                // expression = expressionParser.parseExpression("#todos.size > 0 and
                // #todos.?[id == 'c2'].size > 0 and #todos.?[id == 'c2'][0].name == 'opc'");
                expression = expressionParser.parseExpression(
                                "#todos.size > 0 and #todos.?[id == 'c2'].size > 0 and #todos.?[id == 'c2'][0].name == 'opc'");
                boolean result5 = (Boolean) expression.getValue(context);
                System.out.println(result5);

                expression = expressionParser.parseExpression(
                                "#todos.size > 0 ? (#todos.?[id == 'c2'].size > 0 and #todos.?[id == 'c'][0].name == 'opc' ? 1 : (#todos.?[id == 'b2'].size > 0 and #todos.?[id == 'b'][0].name == '123' ? 2 : 3)) : 0");
                Integer result6 = (Integer) expression.getValue(context);
                System.out.println(result6);

                assertTrue(true);
        }

        @Data
        @AllArgsConstructor
        public static class Todo {
                String id;
                String name;
        }
}
