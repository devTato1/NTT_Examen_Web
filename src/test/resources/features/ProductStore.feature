# language: es
@Tienda
Característica: Producto - Tienda
  Como automatizador principal de NTT DATA
  Quiero automatizar las pruebas de regresión de la tienda
  Para validar la compra de productos y manejo de errores

  Esquema del escenario: Testing Tienda - <caso>
    Dado estoy en la página de la tienda
    Y me logueo con mi usuario "<usuario>" y clave "<clave>"
    Cuando navego a la categoria "<categoria>" y subcategoria "<subcategoria>"
    Y agrego 2 unidades del primer producto al carrito
    Entonces valido en el popup la confirmación del producto agregado
    Y valido en el popup que el monto total sea calculado correctamente
    Cuando finalizo la compra
    Entonces valido el titulo de la pagina del carrito
    Y vuelvo a validar el calculo de precios en el carrito

    Ejemplos:
      | caso               | usuario                 | clave       | categoria | subcategoria |
      | Login Exitoso      | iaopta2@gmail.com       | @C0nt0$0MF  | Clothes   | Men          |
      | Usuario Invalido   | usuario_invalido@qa.com | password123 | Clothes   | Men          |
      | Categoria Invalida | iaopta2@gmail.com       | @C0nt0$0MF  | Autos     | Men          |