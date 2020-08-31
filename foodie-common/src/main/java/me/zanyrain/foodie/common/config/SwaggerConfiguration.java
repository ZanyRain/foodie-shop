package me.zanyrain.foodie.common.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Collection;

@Configuration
@EnableSwagger2
@EnableKnife4j
public class SwaggerConfiguration {
    @Bean(value = "docket")
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("me.zanyrain.foodie.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("天天吃货网站 Web 接口定义文档")
                .description("在线演示: http://shop.t.mukewang.com/")
                .version("Ver. 0.1")
                .license("MulanPSL-2.0")
                .licenseUrl("http://license.coscl.org.cn/MulanPSL2")
                .termsOfServiceUrl("https://www.imooc.com/help/detail/89")
                .contact(new Contact("zanyrain","https:/zanyrain.me","me@zanyrain.me"))
                .build();
    }
}


//    public ApiInfo(String title, String description, String version, String termsOfServiceUrl,
//    Contact contact, String license, String licenseUrl, Collection<VendorExtension> vendorExtensions) {
//        this.title = title;
//        this.description = description;
//        this.version = version;
//        this.termsOfServiceUrl = termsOfServiceUrl;
//        this.contact = contact;
//        this.license = license;
//        this.licenseUrl = licenseUrl;
//        this.vendorExtensions = new ArrayList(vendorExtensions);
//    }