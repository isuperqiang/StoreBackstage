package com.richie.backstage.controller;

import com.richie.backstage.config.Constant;
import com.richie.backstage.domain.Member;
import com.richie.backstage.handler.ListResult;
import com.richie.backstage.handler.Result;
import com.richie.backstage.service.MemberService;
import com.richie.backstage.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author richie on 2018.06.29
 */
@RestController
@RequestMapping("/member")
public class MemberController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private MemberService memberService;

    @Autowired
    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }

    // {"nickname":"落英坠露","gender":1,"phone":"15868825645","cardNo":"417988475789283"}
    @PostMapping("/create")
    public Result createMember(@RequestBody Member member, @CookieValue(Constant.USER_TOKEN) String token,
                               HttpServletRequest request) {
        int userId = (int) WebUtils.getSessionAttribute(request, token);
        boolean ret = memberService.createMember(userId, member);
        if (ret) {
            return Result.createYesResult();
        } else {
            return Result.createNoResult(Result.ErrorCode.CREATE_MEMBER_FAILED);
        }
    }

    // {"memberId":1, "nickname":"落英坠露","gender":0,"phone":"15868825645","cardNo":"417988475789283"}
    @PostMapping("/update")
    public Result updateMember(@RequestBody Member member) {
        boolean ret = memberService.updateMember(member);
        if (ret) {
            return Result.createYesResult();
        } else {
            return Result.createNoResult(Result.ErrorCode.UPDATE_MEMBER_FAILED);
        }
    }

    @PostMapping("/delete")
    public Result deleteMember(@RequestBody Member member) {
        boolean ret = memberService.deleteMember(member.getMemberId());
        if (ret) {
            return Result.createYesResult();
        } else {
            return Result.createNoResult(Result.ErrorCode.DELETE_MEMBER_FAILED);
        }
    }

    @PostMapping("/delete_bundle")
    public Result deleteMembers(@RequestBody List<Member> members) {
        boolean ret = true;
        for (Member member : members) {
            boolean del = memberService.deleteMember(member.getMemberId());
            ret &= del;
        }
        if (ret) {
            return Result.createYesResult();
        } else {
            return Result.createNoResult(Result.ErrorCode.DELETE_MEMBER_FAILED);
        }
    }

    @PostMapping("/query")
    public ListResult queryAllCategories(@CookieValue(Constant.USER_TOKEN) String token, @RequestParam(value = "page_index",
            defaultValue = "1") int pageIndex, @RequestParam(value = "page_size", defaultValue = "10") int pageSize,
                                         @RequestParam(value = "nickname", required = false) String nickname, HttpServletRequest request) {
        int userId = (int) WebUtils.getSessionAttribute(request, token);
        logger.info("pageIndex:{}, pageSize:{}, nickname:{}", pageIndex, pageSize, nickname);
        List<Member> members = memberService.queryMemberByPage(userId, pageIndex, pageSize, nickname);
        if (members != null) {
            int count;
            if (StringUtils.isEmpty(nickname)) {
                count = memberService.queryMemberCount(userId);
            } else {
                count = members.size();
            }
            return ListResult.createOk(members, count);
        } else {
            return ListResult.createNo(Result.ErrorCode.QUERY_MEMBER_FAILED.getMessage());
        }
    }

}
