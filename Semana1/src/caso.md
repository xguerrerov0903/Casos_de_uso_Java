# Pasos a realizar

## 1. Modelo de datos
- Crear `ArrayList<String>` para nombres de productos.
- Crear `double[]` para precios, sincronizado con nombres.
- Crear `HashMap<String, Integer>` para stock.
- Implementar métodos utilitarios: `addProducto`, `expandPrecios`, `indexOfNombre`.

## 2. Menú principal con JOptionPane
- Mostrar un menú repetitivo con las opciones:
    1. Agregar producto.
    2. Listar inventario.
    3. Comprar producto.
    4. Mostrar estadísticas (más barato y más caro).
    5. Buscar producto por nombre.
    6. Salir con ticket final.

## 3. Flujo de cada opción
- **Agregar producto:** pedir nombre, precio, stock. Validar entradas, no duplicados.
- **Listar inventario:** recorrer estructuras y mostrar todos los productos con nombre, precio y stock.
- **Comprar:** solicitar producto y cantidad. Validar existencia y stock, confirmar compra y actualizar.
- **Estadísticas:** encontrar precio mínimo y máximo recorriendo el array precios.
- **Buscar producto:** permitir coincidencias parciales en el nombre.
- **Salir:** mostrar total acumulado de compras en la sesión.

## 4. Validaciones y mensajes
- Manejo de excepciones (`NumberFormatException`).
- Mensajes de error y confirmación con `showMessageDialog`.
- Formatear salidas para claridad.  
