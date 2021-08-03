package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

public class NavigationHelper extends HelperBase {

    public NavigationHelper(FirefoxDriver wd) {
        super(wd);

    }

    public void gotoGroupePage() {
      click(By.linkText("groups"));
    }
    public void addNewContact() {
        click(By.linkText("add new"));
    }
    public void gotoHomePage() {
        click(By.linkText("home page"));
    }
}