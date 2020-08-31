package me.zanyrain.foodie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("me.zanyrain.foodie.mapper")
public class FoodieShopApplication {
    public static void main(String[] args) {
        SpringApplication.run(FoodieShopApplication.class);
    }
}
