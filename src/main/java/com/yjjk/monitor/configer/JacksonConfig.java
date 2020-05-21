package com.yjjk.monitor.configer;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * null返回空字符串
 *
 * @author 戴旌旗
 */
@Configuration
public class JacksonConfig extends WebMvcConfigurerAdapter {
    @Bean
    @Primary
    @ConditionalOnMissingBean(ObjectMapper.class)
    public ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder) {
        ObjectMapper objectMapper = builder.createXmlMapper(false).build();
        objectMapper.setSerializerFactory(objectMapper.getSerializerFactory()
                .withSerializerModifier(new MyBeanSerializerModifier()));
        return objectMapper;
    }

    public static class MyNullArrayJsonSerializer extends JsonSerializer {
        @Override
        public void serialize(Object value, JsonGenerator generator, SerializerProvider provider) throws IOException {
            if (value == null) {
                generator.writeStartArray();
                generator.writeEndArray();
            }
        }
    }

    public static class MyNullJsonSerializer extends JsonSerializer {
        @Override
        public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
                throws IOException {
            jsonGenerator.writeString("");
        }
    }

    public static class MyBeanSerializerModifier extends BeanSerializerModifier {
        // 数组,集合类型 null -> []
        private JsonSerializer nullArrayJsonSerializer = new MyNullArrayJsonSerializer();
        // 字符串等类型 null -> ""
        private JsonSerializer nullJsonSerializer = new MyNullJsonSerializer();

        @Override
        public List<BeanPropertyWriter> changeProperties(SerializationConfig config, BeanDescription beanDesc,
                                                         List beanProperties) {
            for (Object beanProperty : beanProperties) {
                BeanPropertyWriter writer = (BeanPropertyWriter) beanProperty;
                //判断字段的类型，如果是array，list，set则注册nullSerializer
                if (isArrayType(writer)) {
                    writer.assignNullSerializer(this.nullArrayJsonSerializer);
                } else {
                    writer.assignNullSerializer(this.nullJsonSerializer);
                }
            }
            return beanProperties;
        }

        boolean isArrayType(BeanPropertyWriter writer) {
            Class clazz = writer.getPropertyType();
            return clazz.isArray() || clazz.equals(List.class) || clazz.equals(Set.class);
        }
    }
}