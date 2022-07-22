package com.azure.employee.seleniumscripts;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeTest {

    @BeforeAll
    public static void setUpAll() {
        Configuration.browserSize = "1280x800";
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    public void setUp() {
        open("https://employee-details.azurewebsites.net/");
    }

    @Test
    @Order(1)
    public void addEmployee() {
        $("a[href = '/add']").click();

        $("input[id='firstName']").sendKeys("Selenium");
        $("input[id='lastName']").sendKeys("Test");
        $("input[id='emailId']").sendKeys("seleniumTest@gmail.com");
        $("button[type='submit']").click();

    }

    @Test
    @Order(2)
    public void lookUpDetails() {
        $(By.xpath("//td[contains(., 'seleniumTest@gmail.com')]//parent::tr//td[4]//button[contains(., 'Details')]")).click();
        $(By.xpath("//button[contains(., 'Back to Employee List')]")).click();
    }

    @Test
    @Order(3)
    public void updateEmployee() {
        $(By.xpath("//td[contains(., 'seleniumTest@gmail.com')]//parent::tr//td[4]//button[contains(., 'Details')]")).click();

        $(By.xpath("//button[contains(., 'Update Employee')]")).click();

        $("input[id='firstName']").clear();
        $("input[id='firstName']").sendKeys("Selenium");
        $("input[id='lastName']").clear();
        $("input[id='lastName']").sendKeys("Test1");
        $("input[id='emailId']").clear();
        $("input[id='emailId']").sendKeys("seleniumTest@gmail.com");

        $(By.xpath("//button[@type='submit']")).click();

    }

    @Test
    @Order(4)
    public void deleteEmployee(){
        $(By.xpath("//td[contains(., 'seleniumTest@gmail.com')]//parent::tr//td[4]//button[contains(., 'Delete')]")).click();
    }
}
