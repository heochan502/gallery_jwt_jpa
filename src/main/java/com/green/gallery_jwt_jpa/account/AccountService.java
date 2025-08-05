package com.green.gallery_jwt_jpa.account;

import com.green.gallery_jwt_jpa.account.model.AccountJoinReq;
import com.green.gallery_jwt_jpa.account.model.AccountLoginReq;
import com.green.gallery_jwt_jpa.account.model.AccountLoginRes;
import com.green.gallery_jwt_jpa.config.model.JwtUser;
import com.green.gallery_jwt_jpa.entity.Members;
import com.green.gallery_jwt_jpa.entity.MembersRoles;
import com.green.gallery_jwt_jpa.entity.MembersRolesIds;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountMapper accountMapper;
    private final AccountRepository accountRepository;

//    public int join(AccountJoinReq req) {
    public void join(AccountJoinReq req) {
        String hashedPw = BCrypt.hashpw(req.getLoginPw(), BCrypt.gensalt());
        //암호화가 된 비밀번호를 갖는 AccountJoinReq 객체를 만들어주세요. (아이디, 이름 갖고 있고)
        AccountJoinReq changedReq = new AccountJoinReq(req.getName(), req.getLoginId(), hashedPw);


        Members members = new Members();
        members.setLoginId(req.getLoginId());
        members.setLoginPw(hashedPw);
        members.setName(req.getName());



//        MembersRolesIds membersRolesIds = new MembersRolesIds();
//        membersRolesIds.setRoleName("ROLE_USER_1");
//
//        MembersRoles membersRoles = new MembersRoles();
//        membersRoles.setMembersRolesIds(membersRolesIds);
//        membersRoles.setMembers(members);
//
//        List<MembersRoles> membersRolesList = new ArrayList<>();
//        membersRolesList.add(membersRoles);//
//        members.setRoles(membersRolesList);


        members.addRole("ROLE_USER_1");

        accountRepository.save(members);

        // return accountMapper.save(changedReq);
    //        return 1;

    }

    public AccountLoginRes login(AccountLoginReq req) {
        AccountLoginRes res = accountMapper.findByLoginId(req);

        //아이디가 없거나 비밀번호가 다르다면
        if(res == null || !BCrypt.checkpw(req.getLoginPw(), res.getLoginPw())) {
            return null; //return null; 처리
        }
        //로그인 성공!!! 로그인 한 사용자의 role가져오기~
        //호출해주세요
        List<String> roles = accountMapper.findAllRolesByMemberId(res.getId());
        JwtUser jwtuser = new JwtUser(res.getId(), roles);
        res.setJwtUser(jwtuser);



        return res;
    }

}
