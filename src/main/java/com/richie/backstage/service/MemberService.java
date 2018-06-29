package com.richie.backstage.service;

import com.richie.backstage.dao.MemberMapper;
import com.richie.backstage.domain.Member;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * @author richie on 2018.06.29
 */
@Service
public class MemberService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private MemberMapper memberMapper;

    @Autowired
    public void setMemberMapper(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    @CacheEvict(value = "deleteMember", key = "'member_count'")
    public boolean createMember(int userId, Member member) {
        int key = 0;
        try {
            key = memberMapper.createMember(userId, member.getNickname(), member.getGender(), member.getPhone(), member.getCardNo());
        } catch (SQLException e) {
            logger.error("create member failed", e);
        }
        return key > 0;
    }

    @CacheEvict(value = "deleteMember", key = "'member_count'")
    public boolean updateMember(Member member) {
        int key = 0;
        try {
            key = memberMapper.updateMember(member.getMemberId(), member.getNickname(), member.getGender(), member.getPhone(), member.getCardNo());
        } catch (SQLException e) {
            logger.error("create member failed", e);
        }
        return key > 0;
    }

    @CacheEvict(value = "deleteMember", key = "'member_count'")
    public boolean deleteMember(int memberId) {
        int affected = 0;
        try {
            affected = memberMapper.deleteMember(memberId);
        } catch (SQLException e) {
            logger.error("delete member failed", e);
        }
        return affected > 0;
    }

    public List<Member> queryMemberByPage(int userId, int pageIndex, int pageSize, String nickname) {
        if (--pageIndex < 0) {
            pageIndex = 0;
        }
        if (nickname == null) {
            nickname = "";
        }
        return memberMapper.queryMemberByPage(nickname, pageIndex * pageSize, pageSize, userId);
    }

    @Cacheable(value = "queryMemberCount", key = "'member_count'")
    public int queryMemberCount(int userId) {
        return memberMapper.queryMemberCount(userId);
    }
}
