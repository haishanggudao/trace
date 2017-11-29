package cn.rfidcer.swagger;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

@EnableSwagger2
@EnableWebMvc
public class SwaggerConfig {

	
	@Bean
	  public Docket swaggerSpringMvcPlugin() {
	    return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
	            .select()
	              .paths(paths())
	              .build();
	  }

	private ApiInfo apiInfo() {  
        return new ApiInfoBuilder()  
                .title("IOS手机端接口").contact(new Contact("xzm", "", "xzm@rfidcer.cn"))  
                .version("1.0")  
                .build();  
    }
	
	private Predicate<String> paths() {
	    return Predicates.or(
	        regex("/yzIOS/.*?"),
	        regex("/mobileStock/.*?"),
	    	regex("/yzAndroid/.*?"));
	  }
}
