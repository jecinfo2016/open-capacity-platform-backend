package com.open.capacity.uaa.service;

import com.open.capacity.common.web.Result;
import com.open.capacity.uaa.dto.SysClientDto;
import com.open.capacity.uaa.model.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface DevUserService {
    Result saveOrUpdate(DevUser devUser);
    Long checkUser(String username, String password);
    DevUser getSelf();
    Result clientSaveOrUpdate(SysClientDto clientDto, Integer groupId);
    List<SysService> findServiceAll();
    PageResult<SysClient> listRoles(Map<String, Object> params);
    Result deleteClient(Long id);
    Result updatePassword(Map params);
    Result saveGroup(DevGroup devGroup);
    Result updateGroup(DevGroup devGroup);
    PageResult<DevGroup> grouplist(Map<String, Object> params);
    Result deleteGroup(Integer id);
}
