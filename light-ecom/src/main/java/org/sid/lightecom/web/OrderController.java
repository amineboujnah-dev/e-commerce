package org.sid.lightecom.web;

import org.sid.lightecom.dao.ClientRepository;
import org.sid.lightecom.dao.OrderItemRepository;
import org.sid.lightecom.dao.OrderRepository;
import org.sid.lightecom.dao.ProduitRepository;
import org.sid.lightecom.entities.Client;
import org.sid.lightecom.entities.Order;
import org.sid.lightecom.entities.OrderItem;
import org.sid.lightecom.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@CrossOrigin("*")
@RestController
public class OrderController {
    @Autowired
    private ProduitRepository produitRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @PostMapping("/orders")
    public Order saveOrder(@RequestBody OrderForm orderForm){
        Client client=new Client();
        client.setName(orderForm.getClient().getName());
        client.setEmail(orderForm.getClient().getEmail());
        client.setAddress(orderForm.getClient().getAddress());
        client.setPhoneNumber(orderForm.getClient().getPhoneNumber());
        client.setUserName(orderForm.getClient().getUserName());
        client=clientRepository.save(client); /*enregistrer le client dans la base de données*/
        System.out.println(client.getId());

        Order order=new Order();
        order.setClient(client);
        order.setDate(new Date());
        order=orderRepository.save(order); /*enregistrer la commande dans la base de données*/
        double total=0;
        for(OrderProduct p:orderForm.getProducts()){
            OrderItem orderItem=new OrderItem();
            orderItem.setOrder(order);
            Product product=produitRepository.findById(p.getId()).get();
            orderItem.setProduct(product);
            orderItem.setPrice(product.getCurrentPrice());
            orderItem.setQuantity(p.getQuantity());
            orderItemRepository.save(orderItem);
            total+=p.getQuantity()*product.getCurrentPrice();
        }
        order.setTotalAmount(total);
        return orderRepository.save(order);
    }

}
