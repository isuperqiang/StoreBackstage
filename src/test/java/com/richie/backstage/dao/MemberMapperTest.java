package com.richie.backstage.dao;

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
import static org.hamcrest.Matchers.greaterThan;

/**
 * @author richie on 2018.07.09
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MemberMapperTest {
    private MemberMapper memberMapper;

    @Autowired
    public void setMemberMapper(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    @Test
    @Transactional
    public void createMember() throws SQLException {
        Member member = new Member();
        member.setUser(new User(1));
        member.setCardNo("23");
        member.setGender("boy");
        member.setPhone("1324534");
        member.setNickname("richie");
        int i = memberMapper.createMember(member);
        assertThat(i, greaterThan(0));
    }

    @Test
    @Transactional
    public void updateMember() throws SQLException {
        Member member = new Member();
        member.setCardNo("23");
        member.setMemberId(4);
        member.setGender("boy");
        member.setPhone("1324534");
        member.setNickname("richie");
        int i = memberMapper.updateMember(member);
        assertThat(i, greaterThan(0));
    }

    @Test
    @Transactional
    public void deleteMember() throws SQLException {
        int i = memberMapper.deleteMember(4);
        assertThat(i, greaterThan(0));
    }

    @Test
    public void queryMemberByPage() {
        List<Member> members = memberMapper.queryMemberByPage("", 1, 10, 1);
        assertThat(members, Matchers.notNullValue());
    }

    @Test
    public void queryMemberById() {
        Member member = memberMapper.queryMemberById(4);
        assertThat(member, Matchers.notNullValue());
    }

    @Test
    public void queryMemberCount() {
        int i = memberMapper.queryMemberCount(1);
        assertThat(i, greaterThan(0));
    }
}