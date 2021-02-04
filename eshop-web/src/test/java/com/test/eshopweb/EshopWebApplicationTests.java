package com.test.eshopweb;

import com.test.eshopweb.dto.ItemDto;
import com.test.eshopweb.service.ItemService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@Transactional // Diky teto anotaci se po kazdem testu automaticky zavola ROLLBACK
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EshopWebApplicationTests {

    // Muzeme Autowirovat jakoukoli Spring bean!!
    @Autowired
    private ItemService itemService;

    // Port, na kterem bezi Tomcat
    @LocalServerPort
    private int port;

    // Kdekoli v aplikaci muzeme pouzit RestTemplate,
    // v testech muzeme pouzivat TestRestTemplate
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void integrationTest() {
        // Super knihovna pro asserty: https://assertj.github.io/doc/
        List<ItemDto> list = itemService.findAll(Sort.by(Sort.Direction.ASC, "id"));
        assertThat(list)
                .isNotNull()
                .isNotEmpty()
                .extracting(ItemDto::getId)
                .contains(1, 2, 3, 4, 5);
    }

    @Test
    void saveTest() {
        ItemDto dto = new ItemDto();
        dto.setName("Aaa");
        dto.setPrice(123);
        ItemDto savedDto = itemService.save(dto);
        assertThat(savedDto.getId())
                .isNotZero();
        Optional<ItemDto> optionalItemDto = itemService.findById(savedDto.getId());
        assertThat(optionalItemDto)
                .isPresent()
                .get()
                .extracting(ItemDto::getId)
                .isEqualTo(savedDto.getId());
    }

    @Test
    void endToEndTest() {
        ItemDto itemDto = testRestTemplate.getForObject("/item/1", ItemDto.class);
        assertThat(itemDto)
                .isNotNull();
        assertThat(itemDto.getId())
                .isEqualTo(1);
    }

    // Super projekt pro spousteni serveru pro ad-hoc testovani:
    // https://www.testcontainers.org/
    // Je mozne pomoci nej spustit JAKYKOLI server!!!

}
