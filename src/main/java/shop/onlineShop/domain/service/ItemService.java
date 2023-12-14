package shop.onlineShop.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.onlineShop.domain.entity.Item;
import shop.onlineShop.domain.repository.ItemRepository;
import shop.onlineShop.global.exception.CustomException;
import shop.onlineShop.global.uniformApi.ErrorStatus;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item){
        itemRepository.save(item);
    }

    public List<Item> findAllItems(){
        return itemRepository.findAll();
    }

    public Item findOneItem(Long itemId){
        Optional<Item> optionalItem = itemRepository.findById(itemId);
        if(optionalItem.isEmpty()){
            //TODO : error 추가 (해당 제품의 아이디는 존재하지 않습니다)
            throw new CustomException(ErrorStatus._INTERNAL_SERVER_ERROR);
        }else{
            return optionalItem.get();
        }
    }
}
