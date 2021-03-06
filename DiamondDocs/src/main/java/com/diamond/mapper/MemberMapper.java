package com.diamond.mapper;

import com.diamond.dto.MemberPreview;
import com.diamond.pojo.Doc;
import com.diamond.pojo.DocUser;
import com.diamond.pojo.Member;
import com.diamond.pojo.Team;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface MemberMapper {

    /*
    添加一个成员
     */
    int addMember(Member member);

    /*
    返回和userID对应用户同组的总人数
     */
    int getCollaboratorNum(@Param("userID") String userID);

    int getTeamNum(@Param("userID") String userID);

    /*
    修改用户身份
    用map进行传参，key分别为userID和userIdentity
     */
    int updateDocUserIdentity(Map<String, Object> map);

    /*
    修改用户最近访问该组文档的时间
     */
    int updateLastVisitTime(@Param("userID") String userID, @Param("teamID") String teamID);

    /*
    查询用户身份
     */
    int getDocUserIdentity(String userID, String teamID);

    Member checkIsInGroup(String userID, String teamID);

    /*
    通过用户ID获得一个该用户参加的团队列表
     */
    List<Team> getTeamByUserID(@Param("userID") String userID);

    /*
    通过团队ID获得一个该团队内的用户列表
     */
    List<DocUser> getTeamMemberByTeamID(@Param("teamID") String teamID);

    /*
    通过团队ID获得一个该团队内的团队成员列表
    多表联合查询
     */
    List<MemberPreview> getTeamMemberPreviewByTeamID(@Param("teamID") String teamID);

    /*
    通过团队ID获得一个该团队内的团队成员列表
    多表联合查询
    搜索关键词
     */
    List<MemberPreview> getTeamMemberPreviewByTeamIDWithSearch(@Param("teamID") String teamID, @Param("searchText") String searchText);

    /*
    通过团队ID获得该团队的文档列表
     */
    List<Doc> getDocByTeamID(@Param("teamID") String teamID);

    /*
    删除团队成员
    返回结果为删除行数，结果为0时更新失败
    使用map传参，key分别为userID和teamID
     */
    int deleteMember(Map<String, Object> map);

    /*
    设置管理员
    返回结果为删除行数，结果为0时更新失败
    使用map传参，key分别为userID和teamID
     */
    int setAdmin(Map<String, Object> map);
}
