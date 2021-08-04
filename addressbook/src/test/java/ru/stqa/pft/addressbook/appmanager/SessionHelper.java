package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SessionHelper extends  HelperBase {

    public SessionHelper(WebDriver wd) {
        super(wd);
    }
    public void login(String username, String userpassword) {
        type(By.name("user"),username);
        type(By.name("pass"), userpassword);
        click(By.xpath("//input[@value='Login']"));
    }
}
