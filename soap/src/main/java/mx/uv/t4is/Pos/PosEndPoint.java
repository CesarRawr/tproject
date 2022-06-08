package mx.uv.t4is.Pos;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import https.t4is_uv_mx.pos.VentaRequest;
import https.t4is_uv_mx.pos.VentaResponse;
import https.t4is_uv_mx.pos.ModificarStockRequest;
import https.t4is_uv_mx.pos.ModificarStockResponse;

// Inventario
import https.t4is_uv_mx.pos.AgregarProductoRequest;
import https.t4is_uv_mx.pos.AgregarProductoResponse;
import https.t4is_uv_mx.pos.BuscarProductosResponse;
import https.t4is_uv_mx.pos.ModificarInventarioRequest;
import https.t4is_uv_mx.pos.ModificarInventarioResponse;
import https.t4is_uv_mx.pos.BorrarProductoResponse;
import https.t4is_uv_mx.pos.BorrarProductoRequest;

@Endpoint
public class PosEndPoint {
    @Autowired
    private IPOS ipos;

    @Autowired
    private Iinventory iinventory;

    @Autowired
    private IUsuario iusuario;

    @Autowired
    private IStock istock;

    // hacer venta
    @PayloadRoot(namespace = "https://t4is.uv.mx/pos", localPart = "VentaRequest")
    @ResponsePayload
    public VentaResponse vender(@RequestPayload VentaRequest peticion) {
        VentaResponse respuesta = new VentaResponse();
        Venta venta = new Venta();

        try {
            // Verificar si están vacios los parametros
            if (peticion.getVendedor().isEmpty() || peticion.getArticulo() == 0 || peticion.getCantidad() == 0) {
                respuesta.setMsg("No dejes campos vacios");
                return respuesta;
            }

            // Obtener item (Se usa optional porque no sabemos si va a estar)
            Optional<Inventario> item = iinventory.findById(peticion.getArticulo());
            // En caso de no estar el articulo en la base
            if (!item.isPresent()) {
                respuesta.setMsg("Producto no encontrado");
                return respuesta;
            }

            System.out.println(item.get().getNombre());
            System.out.println(item.get().getCantidad());
            
            // Si no hay stock no se registra la venta
            if (item.get().getCantidad() == 0) {
                respuesta.setMsg("No hay stock, no se realizó la venta");
                return respuesta;
            }

            // Si la cantidad de producto a vender supera la que hay en stock
            if (peticion.getCantidad() > item.get().getCantidad()) {
                respuesta.setMsg("La venta supera el limite de stock");
                return respuesta;
            }

            // Actualizar stock restandole a la cantidad del item en stock, lo que se va a vender
            item.get().setCantidad(item.get().getCantidad() - peticion.getCantidad());
            iinventory.save(item.get());

            // Guardar parametros en objeto
            venta.setArticulo(item.get());
            venta.setCantidad(peticion.getCantidad());
            venta.setVendedor(peticion.getVendedor());
            
            // Guardar venta
            ipos.save(venta);
            // setteando mensaje en respuesta
            respuesta.setMsg("guardados correctamente");
        } catch (Exception e) {
            respuesta.setMsg("Algo salió muy mal :(");
            return respuesta;
        }

        return respuesta;
    }

    // Actualizar stock
    @PayloadRoot(namespace = "https://t4is.uv.mx/pos", localPart = "ModificarStockRequest")
    @ResponsePayload
    public ModificarStockResponse actualizarStock(@RequestPayload ModificarStockRequest peticion) {
        ModificarStockResponse respuesta = new ModificarStockResponse();

        try {
            // Verificar si están vacios los parametros o si ambos stock están en 0
            if (peticion.getNickname().isEmpty() || (peticion.getTortillas() == 0 && peticion.getTotopos() == 0)) {
                respuesta.setMsg("Error: No nickname o ambos stocks están vacios");
                return respuesta;
            }

            // Obtener el usuario (Se usa optional porque no sabemos si va a estar)
            Optional<Usuario> usuario = iusuario.findByNickname(peticion.getNickname());
            // En caso de no estar el usuario en la base de datos
            if (!usuario.isPresent()) {
                respuesta.setMsg("Usuario no encontrado");
                return respuesta;
            }

            // Crear stock
            Stock newStock = new Stock();
            newStock.setId(usuario.get().getId());
            newStock.setUsuario(usuario.get());
            newStock.setTortillas(peticion.getTortillas());
            newStock.setTotopos(peticion.getTotopos());

            // Actualizar stock
            istock.save(newStock);
        } catch (Exception e) {
            System.out.println(e);
            respuesta.setMsg("Algo salió muy mal :(");
            return respuesta;
        }

        respuesta.setMsg("Se actualizó correctamente el stock");
        return respuesta;
    }

    /* 
        INVENTARIO
    */

    // Agregar producto
    @PayloadRoot(namespace = "https://t4is.uv.mx/pos", localPart = "AgregarProductoRequest")
    @ResponsePayload
    public AgregarProductoResponse agregarProducto(@RequestPayload AgregarProductoRequest peticion) {
        AgregarProductoResponse respuesta = new AgregarProductoResponse();

        try {
            // Verificar si están vacios los parametros
            if (peticion.getNombre().isEmpty() || (peticion.getCantidad() == 0 && peticion.getGramaje() == 0 && peticion.getPrecio() == 0)) {
                respuesta.setMsg("Error: Parametro/s vacio/s");
                return respuesta;
            }

            // Obtener el producto (Se usa optional porque no sabemos si va a estar)
            Optional<Inventario> producto = iinventory.findByNombre(peticion.getNombre());
            // En caso de estar el producto en la base de datos
            if (producto.isPresent()) {
                respuesta.setMsg("El producto ya se encuentra en la base de datos");
                return respuesta;
            }

            // Crear nuevo producto
            Inventario newProducto = new Inventario();
            newProducto.setNombre(peticion.getNombre());
            newProducto.setCantidad(peticion.getCantidad());
            newProducto.setPrecio(peticion.getPrecio());
            newProducto.setGramaje(peticion.getGramaje());

            // Crear producto
            iinventory.save(newProducto);
        } catch (Exception e) {
            System.out.println(e);
            respuesta.setMsg("Algo salió muy mal :(");
            return respuesta;
        }

        respuesta.setMsg("Se insertó correctamente el producto");
        return respuesta;
    }

    // Mirar todos los productos de la base de datos
    @PayloadRoot(namespace = "https://t4is.uv.mx/pos", localPart = "BuscarProductosRequest")
    @ResponsePayload
    public BuscarProductosResponse buscar() {
        BuscarProductosResponse respuesta = new BuscarProductosResponse();

        Iterable<Inventario> lista = iinventory.findAll();
        for (Inventario producto : lista) {
            BuscarProductosResponse.Inventario i = new BuscarProductosResponse.Inventario();
            i.setId(producto.getId());
            i.setNombre(producto.getNombre());
            i.setCantidad(producto.getCantidad());
            i.setGramaje(producto.getGramaje());
            i.setPrecio(producto.getPrecio());

            respuesta.getInventario().add(i);
        }
        return respuesta;
    }

    // Modificar inventario
    @PayloadRoot(namespace = "https://t4is.uv.mx/pos", localPart = "ModificarInventarioRequest")
    @ResponsePayload
    public ModificarInventarioResponse modificarInventario(@RequestPayload ModificarInventarioRequest peticion) {
        ModificarInventarioResponse respuesta = new ModificarInventarioResponse();

        try {
            // Verificar si están vacios los parametros
            if (peticion.getNombre().isEmpty()) {
                respuesta.setMsg("Error: No dejes el nombre vacio");
                return respuesta;
            }

            // Obtener el producto(Se usa optional porque no sabemos si va a estar)
            Optional<Inventario> producto = iinventory.findByNombre(peticion.getNombre());
            // En caso de no estar el producto en la base de datos
            if (!producto.isPresent()) {
                respuesta.setMsg("Producto no encontrado");
                return respuesta;
            }

            // Crear producto
            Inventario newProducto = new Inventario();
            newProducto.setId(producto.get().getId());
            newProducto.setNombre(peticion.getNombre());
            newProducto.setCantidad(peticion.getCantidad());
            newProducto.setGramaje(peticion.getGramaje());
            newProducto.setPrecio(peticion.getPrecio());

            // Actualizar stock
            iinventory.save(newProducto);
        } catch (Exception e) {
            System.out.println(e);
            respuesta.setMsg("Algo salió muy mal :(");
            return respuesta;
        }

        respuesta.setMsg("Se actualizó correctamente el producto");
        return respuesta;
    }

  // Borrar por nombre
    @PayloadRoot(namespace = "https://t4is.uv.mx/pos", localPart = "BorrarProductoRequest")
    @ResponsePayload
    public BorrarProductoResponse borrarInventario(@RequestPayload BorrarProductoRequest peticion) {
        BorrarProductoResponse respuesta = new BorrarProductoResponse();

        try {
            // Verificar si están vacios los parametros
            if (peticion.getNombre().isEmpty()) {
                respuesta.setMsg("Error: No dejes el nombre vacio");
                return respuesta;
            }

            // Obtener el producto(Se usa optional porque no sabemos si va a estar)
            Optional<Inventario> producto = iinventory.findByNombre(peticion.getNombre());
            // En caso de no estar el producto en la base de datos
            if (!producto.isPresent()) {
                respuesta.setMsg("Producto no encontrado");
                return respuesta;
            }

            // borrar
            iinventory.deleteById(producto.get().getId());
        } catch (Exception e) {
            System.out.println(e);
            respuesta.setMsg("Algo salió muy mal :(");
            return respuesta;
        }

        respuesta.setMsg("Se borró correctamente el producto");
        return respuesta;
    }
}