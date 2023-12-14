package shop.onlineShop.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.onlineShop.domain.entity.*;
import shop.onlineShop.domain.repository.ItemRepository;
import shop.onlineShop.domain.repository.MemberRepository;
import shop.onlineShop.domain.repository.OrderRepository;
import shop.onlineShop.global.exception.CustomException;
import shop.onlineShop.global.uniformApi.ErrorStatus;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    //1) 주문 생성
    @Transactional
    public Long createOrder(Long memberId, Long itemId, int count){
        //엔티티 조회
        Member member;
        Item item;
        Optional<Member> optionalMember = memberRepository.findById(memberId);
        if(optionalMember.isEmpty()){
            throw new CustomException(ErrorStatus.MEMBER_NOT_FOUND);
        }else{
            member = optionalMember.get();
        }

        Optional<Item> optionalItem = itemRepository.findById(itemId);
        if(optionalItem.isEmpty()){
            //TODO : error 추가
            throw new CustomException(ErrorStatus._INTERNAL_SERVER_ERROR);
        } else{
            item = optionalItem.get();
        }

        //배송 정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());
        delivery.setStatus(DeliveryStatus.READY);

        //주문 상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        //주문 생성
        Order order = Order.createOrder(member, delivery, orderItem);

        //주문 저장
        orderRepository.save(order);
        return order.getId();
    }

    //2) 주문 취소
    @Transactional
    public void cancelOrder(Long orderId){
        Order order = new Order();
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if(optionalOrder.isEmpty()){
            //TODO
            throw new CustomException(ErrorStatus._INTERNAL_SERVER_ERROR);
        }
        order.cancel();
    }

    //TODO 3) 주문 검색 : Query 날려서 해보자


}
