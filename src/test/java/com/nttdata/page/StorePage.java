package com.nttdata.page;

import org.openqa.selenium.By;

public class StorePage {

    // Login
    public static By linkIniciarSesion = By.xpath("//span[text()='Iniciar sesión']");
    public static By emailInput = By.id("field-email");
    public static By passwordInput = By.id("field-password");
    public static By loginButton = By.id("submit-login");
    public static By logoutLink = By.xpath("//a[contains(@class, 'logout')]");

    //Pagina de los productos
    public static By firstProduct = By.xpath("//article[contains(@class, 'product-miniature')][1]//h2/a");

    // Obtener precio
    public static By popupUnitPrice = By.xpath("//p[@class='product-price']");

    // Detalle
    public static By quantityInput = By.id("quantity_wanted");
    public static By addToCartButton = By.xpath("//button[contains(@class, 'add-to-cart')]");

    // Popup
    public static By popupTitle = By.id("myModalLabel");
    public static By popupTotal = By.cssSelector(".cart-content .product-total .value");
    public static By popupCheckoutBtn = By.xpath("//div[@class='cart-content']//a[contains(@class, 'btn-primary')]");

    // Carrito
    public static By cartTitle = By.xpath("//div[@class='card-block']//h1");
    public static By cartTotal = By.xpath("//div[@class='cart-summary-line cart-total']//span[@class='value']");
}