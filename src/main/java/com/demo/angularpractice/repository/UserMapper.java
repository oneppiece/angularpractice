package com.demo.angularpractice.repository;

import com.demo.angularpractice.account.param.UserParam;
import com.demo.angularpractice.entity.Resource;
import com.demo.angularpractice.entity.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
@CacheConfig(cacheNames = "account_")
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    /**
     * 新增用户
     *
     * @param record
     * @return
     */
    int insertUser(@Param("record") UserParam
                           record);

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
    @Cacheable(key = "'user_role_'+#userId", unless = "#result == null")
    Set<String> selectUserRoles(@Param("userId") String userId);

    /**
     * 查询角色权限
     *
     * @param roleId
     * @return
     */
    @Cacheable(key = "'role_resource_'+#roleId", unless = "#result == null")
    Set<String> selectRoleResource(@Param("roleId") int roleId);

    /**
     * 查询用户资源
     *
     * @param userId
     * @return
     */
    @Cacheable(key = "'user_resource_'+#userId", unless = "#result == null")
    Set<String> selectUserResource(@Param("userId") int userId);

    @CachePut(unless = "result == 0", key = "'user_'+#userParam.id")
    int updateByPrimaryKey(@Param("userParam") UserParam userParam);

    @Cacheable(key = "'user_'+#userParam.id", unless = "#result == null")
    UserParam selectByUser(@Param("userParam") UserParam userParam);

    @Cacheable(key = "'user_resources'", unless = "#result == null")
    Set<String> selectResourcesByUser(@Param("userParam") UserParam userParam);

    @Cacheable(key = "'user_'+#username", unless = "#result == null")
    UserParam selectByUserName(String username);

    @Cacheable(key = "'user_resourcde'+#id", unless = "#result == null")
    List<Resource> selectResourcesByUserId(@Param("userId") Integer id);

    List<Resource> selectAllResource();

    List<Role> selectRolesByCondition(@Param("role") Role param);

    List<Role> selectAllRoles(Role param);

    List<Resource> selectResourcesByRole(@Param("role") Role param);
}