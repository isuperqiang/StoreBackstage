package com.richie.backstage.service;

import com.richie.backstage.domain.Member;
import com.richie.backstage.domain.User;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author richie on 2018.07.09
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MemberServiceTest {

    private MemberService memberService;

    @Autowired
    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }

    @Test
    @Transactional
    public void createMember() throws SQLException {
        Member member = new Member();
        User user = new User();
        user.setUserId(1);
        member.setUser(user);
        member.setCardNo("23");
        member.setGender("boy");
        member.setPhone("1324534");
        member.setNickname("richie");
        boolean b = memberService.createMember(member);
        assertThat(b, Matchers.equalTo(true));
    }

    @Test
    @Transactional
    public void updateMember() {
        Member member = new Member();
        member.setMemberId(4);
        member.setCardNo("23");
        member.setGender("boy");
        member.setPhone("1324534");
        member.setNickname("richie");
        boolean b = memberService.updateMember(member);
        assertThat(b, Matchers.equalTo(true));
    }

    @Test
    @Transactional
    public void deleteMember() {
        boolean b = memberService.deleteMember(4);
        assertThat(b, Matchers.equalTo(true));
    }

    @Test
    public void queryMemberByPage() {
        List<Member> members = memberService.queryMemberByPage(1, 1, 10, "");
        assertThat(members, Matchers.notNullValue());
    }

    @Test
    public void queryMemberCount() {
        int i = memberService.queryMemberCount(1);
        assertThat(i, Matchers.greaterThan(0));
    }

    @Test
    public void queryMemberById() {
        Member member = memberService.queryMemberById(4);
        assertThat(member, Matchers.notNullValue());
    }
}