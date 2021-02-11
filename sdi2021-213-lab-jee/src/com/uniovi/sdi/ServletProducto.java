package com.uniovi.sdi;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



/**
 * Servlet implementation class ServletProducto
 */
@WebServlet("/productos")
public class ServletProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletProducto() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		ProductosService productos = new ProductosService();
		List<Producto> productosTienda = productos.getProductos();
		
		//HashMap<String, Integer> productosTienda = new HashMap<String, Integer>();
		
		//int numeroProductos = 1;
		
//		for(Producto producto: productos.getProductos()) {
//			productosTienda.put(producto.getNombre(), numeroProductos);
//			numeroProductos++;
//		}
		
		request.setAttribute("productosTienda", productosTienda);
		getServletContext().getRequestDispatcher("/vista-productos.jsp").forward(request, response);

	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	
	
	
	
	
}
