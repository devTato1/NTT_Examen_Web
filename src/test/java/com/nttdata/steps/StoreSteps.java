package com.nttdata.steps;

import com.nttdata.page.StorePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class StoreSteps {

    private WebDriver driver;

    public StoreSteps(WebDriver driver){
        this.driver = driver;
    }

    // Navegación
    public void navegarA(String url){
        driver.get(url);
    }

    // Login
    public void irALogin(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(StorePage.linkIniciarSesion)).click();
    }

    public void login(String user, String password){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(StorePage.emailInput));
        emailField.clear();
        emailField.sendKeys(user);

        driver.findElement(StorePage.passwordInput).sendKeys(password);
        driver.findElement(StorePage.loginButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(StorePage.logoutLink));
    }

    // Navegación Categorías
    public void navegarCategorias(String categoria, String subcategoria){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By catLocator = By.xpath("//a[contains(@class, 'dropdown-item') and contains(., '" + categoria + "')]");
        WebElement catElement = wait.until(ExpectedConditions.visibilityOfElementLocated(catLocator));
        Actions action = new Actions(driver);
        action.moveToElement(catElement).perform();

        // Clic en subcategoría
        By subCatLocator = By.xpath("//a[contains(@class, 'dropdown-item') and contains(., '" + subcategoria + "')]");
        wait.until(ExpectedConditions.elementToBeClickable(subCatLocator)).click();
    }

    // Agregar al Carrito
    public void agregarAlCarrito(int cantidad){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(StorePage.firstProduct)).click();
        WebElement qty = wait.until(ExpectedConditions.visibilityOfElementLocated(StorePage.quantityInput));
        qty.clear();
        qty.sendKeys("\b\b");
        qty.sendKeys(String.valueOf(cantidad));
        driver.findElement(StorePage.addToCartButton).click();
    }

    public String getPopupTitle(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(StorePage.popupTitle)).getText();
    }

    public double getUnitPrice(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        String priceText = wait.until(ExpectedConditions.visibilityOfElementLocated(StorePage.popupUnitPrice)).getText();
        return Double.parseDouble(priceText.replace(",", ".").replaceAll("[^0-9.]", ""));
    }

    public double getPopupTotal(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        String totalText = wait.until(ExpectedConditions.visibilityOfElementLocated(StorePage.popupTotal)).getText();

        // Limpieza de moneda
        return Double.parseDouble(totalText.replace(",", ".").replaceAll("[^0-9.]", ""));
    }

    public void finalizarCompra(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(StorePage.popupCheckoutBtn)).click();
    }

    // Métodos del Carrito Final

    public String getCartTitle(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(StorePage.cartTitle)).getText();
    }

    public double getCartTotal(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        String totalText = wait.until(ExpectedConditions.visibilityOfElementLocated(StorePage.cartTotal)).getText();
        return Double.parseDouble(totalText.replace(",", ".").replaceAll("[^0-9.]", ""));
    }
}