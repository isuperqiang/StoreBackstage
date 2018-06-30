package com.richie.backstage.dao;

import com.richie.backstage.domain.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * @author richie on 2018.06.29
 */
@Repository
@Mapper
public interface MemberMapper {
    /**
     * 创建会员
     *
     * @param userId
     * @param nickname
     * @param gender
     * @param phone
     * @param cardNo
     * @return
     * @throws SQLException
     */
    int createMember(@Param("user_id") int userId, @Param("nickname") String nickname, @Param("gender") String gender,
                     @Param("phone") String phone, @Param("card_no") String cardNo) throws SQLException;

    /**
     * 修改会员
     *
     * @param memberId
     * @param nickname
     * @param gender
     * @param phone
     * @param cardNo
     * @return
     * @throws SQLException
     */
    int updateMember(@Param("member_id") int memberId, @Param("nickname") String nickname, @Param("gender") String gender,
                     @Param("phone") String phone, @Param("card_no") String cardNo) throws SQLException;

    /**
     * 删除会员
     *
     * @param memberId
     * @return
     * @throws SQLException
     */
    int deleteMember(@Param("member_id") int memberId) throws SQLException;

    /**
     * 分页查询
     *
     * @param nickname
     * @param pageIndex
     * @param pageSize
     * @param userId
     * @return
     */
    List<Member> queryMemberByPage(@Param("nickname") String nickname, @Param("page_index") int pageIndex, @Param("page_size") int pageSize, @Param("user_id") int userId);

    /**
     * 根据 ID 查询
     *
     * @param memberId
     * @return
     */
    Member queryById(@Param("member_id") int memberId);

    /**
     * 查询会员数量
     *
     * @param userId
     * @return
     */
    int queryMemberCount(@Param("user_id") int userId);
}
