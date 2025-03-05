package controller.shop;

import dal.CategoryDAO;
import dal.ProductDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Cart;
import model.WishList;

@WebServlet(name = "CartServlet", urlPatterns = {"/cart"})
public class CartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        CategoryDAO cd = new CategoryDAO();
        ProductDAO pd = new ProductDAO();

        String txt = "";
        String txt2 = "";
        Cookie[] cookies = request.getCookies();
        for (Cookie c : cookies) {
            if (c.getName().equals("cart")) {
                txt = c.getValue();
            }
            if (c.getName().equals("wishlist")) {
                txt2 = c.getValue();
            }
        }
        Cart cart = new Cart(txt, pd.getAllProductByCid(0));
        WishList wishlist = new WishList(txt2, pd.getAllProductByCid(0));

        request.setAttribute("sizeCart", cart.getSizeCart());
        request.setAttribute("sizeWishlist", wishlist.getSizeWishList());
        request.setAttribute("cart", cart.getListItems());
        request.setAttribute("totalAmount", cart.getTotalAmount());
        request.setAttribute("categoryList", cd.getAllCategory());
        request.getRequestDispatcher("cart.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pid = request.getParameter("productId");
        String quantity = request.getParameter("quantity");

        // add to cart
        if (pid != null && quantity != null) {
            String txt = "";
            // lay cookie 
            Cookie[] cookies = request.getCookies();
            for (Cookie x : cookies) {
                if (x.getName().equals("cart")) {
                    txt += x.getValue();
                    x.setMaxAge(0);
                    response.addCookie(x);
                }
            }

            if (txt.isEmpty()) {
                txt += pid + ":" + quantity;
            } else {
                txt += "/" + pid + ":" + quantity;
            }

            Cookie cart = new Cookie("cart", txt);
            cart.setMaxAge(60 * 60 * 24 * 60);
            response.addCookie(cart);
        }
        // remove items into cart 
        if (pid != null && quantity == null) {
            try {
                int id = Integer.parseInt(pid);

                // get cart on cookie
                ProductDAO pd = new ProductDAO();
                String txt = "";
                Cookie[] cookies = request.getCookies();
                for (Cookie c : cookies) {
                    if (c.getName().equals("cart")) {
                        txt = c.getValue();
                    }
                }
                Cart cart = new Cart(txt, pd.getAllProductByCid(0));

                //remove item
                cart.removeItemByProductId(id);

                //set cookie after delete
                for (Cookie c : cookies) {
                    if (c.getName().equals("cart")) {
                        txt = c.getValue();
                        c.setMaxAge(0);
                    }
                }
                Cookie cartCookie = new Cookie("cart", cart.listCartToString());
                cartCookie.setMaxAge(60 * 60 * 24 * 60);
                response.addCookie(cartCookie);

                // call doGet to reload page
                response.sendRedirect("cart");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
