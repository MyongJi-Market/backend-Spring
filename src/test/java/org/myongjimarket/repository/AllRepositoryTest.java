package org.myongjimarket.repository;

import org.junit.jupiter.api.*;
import org.myongjimarket.domain.Item;
import org.myongjimarket.domain.Likes;
import org.myongjimarket.domain.Member;
import org.myongjimarket.domain.constant.Campus;
import org.myongjimarket.domain.constant.ItemStatus;
import org.myongjimarket.domain.constant.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Transactional
class AllRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    LikesRepository likesRepository;

    @Test
    @DisplayName("회원가입")
    public void memberSave() {
        Member member = saveMember();
        Member savedMember = memberRepository.save(member);
        assertThat(savedMember.getId()).isEqualTo(member.getId());
    }


    @Test
    @DisplayName("아이템 등록")
    public void itemSave() {
        Member member = saveMember();
        Item item = saveItem(member);
        Item savedItem = itemRepository.save(item);
        assertThat(savedItem.getId()).isEqualTo(item.getId());
    }

    @Test
    @DisplayName("좋아요 추가")
    public void likesSave() {
        Member member = saveMember();
        Item item = saveItem(member);
        Likes likes = Likes.builder()
                .member(member)
                .item(item)
                .build();

        Likes savedLikes = likesRepository.save(likes);
        assertThat(savedLikes.getMember()).isEqualTo(member);
        assertThat(savedLikes.getItem()).isEqualTo(item);
    }

    private static Item saveItem(Member member) {
        Item item = Item.builder()
                .member(member)
                .title("아이패드 프로")
                .detail("11인치입니다.")
                .price(10000)
                .itemStatus(ItemStatus.SELL)
                .build();
        return item;
    }

    private static Member saveMember() {
        Member member = Member.builder()
                .email("member@naver.com")
                .password("12345")
                .username("username")
                .campus(Campus.YONGIN)
                .rating(Rating.GOLD)
                .build();
        return member;
    }

}