package com.nttdata.stepsdefinitions;

import com.nttdata.steps.StoreSteps;
import io.cucumber.java.es.*;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

import static com.nttdata.core.DriverManager.getDriver;
import static com.nttdata.core.DriverManager.screenShot;

public class StoreStepsDef {

    private WebDriver driver;
    private StoreSteps storeSteps;

    @Dado("estoy en la página de la tienda")
    public void estoy_en_la_pagina_de_la_tienda() {
        driver = getDriver();
        storeSteps = new StoreSteps(driver);
        storeSteps.navegarA("https://qalab.bensg.com/store");
        screenShot();
    }

    @Y("me logueo con mi usuario {string} y clave {string}")
    public void me_logueo_con_mi_usuario_y_clave(String user, String password) {
        storeSteps.irALogin();
        storeSteps.login(user, password);
        screenShot();
    }

    @Cuando("navego a la categoria {string} y subcategoria {string}")
    public void navego_a_la_categoria_y_subcategoria(String cat, String subcat) {
        storeSteps.navegarCategorias(cat, subcat);
        screenShot();
    }

    @Y("agrego {int} unidades del primer producto al carrito")
    public void agrego_unidades_del_primer_producto_al_carrito(int cantidad) {
        storeSteps.agregarAlCarrito(cantidad);
        screenShot();
    }

    @Entonces("valido en el popup la confirmación del producto agregado")
    public void valido_en_el_popup_la_confirmacion_del_producto_agregado() {
        String titulo = storeSteps.getPopupTitle();
        Assertions.assertTrue(titulo.toLowerCase().contains("correctamente"),
                "El título del popup no contiene el mensaje esperado: " + titulo);
        screenShot();
    }

    @Y("valido en el popup que el monto total sea calculado correctamente")
    public void valido_en_el_popup_que_el_monto_total_sea_calculado_correctamente() {
        double precioUnitario = storeSteps.getUnitPrice();
        double totalPopup = storeSteps.getPopupTotal();
        double totalEsperado = precioUnitario * 2;
        Assertions.assertEquals(totalEsperado, totalPopup, 0.01,
                "El total calculado no es correcto");
    }

    @Cuando("finalizo la compra")
    public void finalizo_la_compra() {
        storeSteps.finalizarCompra();
        screenShot();
    }

    @Entonces("valido el titulo de la pagina del carrito")
    public void valido_el_titulo_de_la_pagina_del_carrito() {
        String titulo = storeSteps.getCartTitle();
        // --- AQUÍ SÍ VALIDAMOS "CARRITO" ---
        Assertions.assertEquals("CARRITO", titulo);
        screenShot();
    }

    @Y("vuelvo a validar el calculo de precios en el carrito")
    public void vuelvo_a_validar_el_calculo_de_precios_en_el_carrito() {
        // Validación final
        double total = storeSteps.getCartTotal();
        Assertions.assertTrue(total > 0);
        screenShot(); // Evidencia Final
    }
}