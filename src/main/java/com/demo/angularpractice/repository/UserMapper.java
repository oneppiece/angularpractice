package com.demo.angularpractice.repository;

import com.demo.angularpractice.account.SysUserParam;
import com.demo.angularpractice.entity.Resource;
import com.demo.angularpractice.entity.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    /**
     * 新增用户
     *
     * @param record
     * @return
     */
    int insertUser(@Param("record") SysUserParam record);

    /**
     * 新增权限
     *
     * @param role
     * @return
     */
    int insertRole(@Param("role") Role role);

    /**
     * 新增资源
     *
     * @param resource
     * @return
     */
    int insertResource(@Param("resource") Resource resource);

    /**
     * 给用户分配角色
     *
     * @param userId
     * @param RoleId
     * @return
     */
    int updateUserRole(@Param("userId") int userId, @Param("roleId") int RoleId);

    /**
     * 删除用户角色
     *
     * @param userId
     * @param RoleId
     * @return
     */
    int deleteUserRole(@Param("userId") int userId, @Param("roleId") int RoleId);

    /**
     * 给角色分配资源
     *
     * @param roleId
     * @param resourceId
     * @return
     */
    int updateRoleResource(@Param("roleId") int roleId, @Param("resourceId") int resourceId);

    /**
     * 删除角色资源
     *
     * @param roleId
     * @param resourceId
     * @return
     */
    int deleteRoleResource(@Param("roleId") int roleId, @Param("resourceId") int resourceId);

    /**
     * 查看用户角色
     *
     * @param userId
     * @return
     */
    Set<String> selectUserRoles(@Param("userId") String userId);

    /**
     * 查询角色权限
     *
     * @param roleId
     * @return
     */
    Set<String> selectRoleResource(@Param("roleId") int roleId);

    /**
     * 查询用户资源
     *
     * @param userId
     * @return
     */
    Set<String> selectUserResource(@Param("userId") int userId);


    int updateByPrimaryKey(@Param("userParam") SysUserParam userParam);

    SysUserParam selectByUser(@Param("userParam") SysUserParam userParam);

    Set<String> selectResourcesByUser(@Param("userParam") SysUserParam userParam);


    SysUserParam selectByUserName(String username);

    List<Resource> selectResourcesByUserId(@Param("userId") Integer id);

    List<Resource> selectAllResource();
}