package com.open.capacity.user.service.impl;

import com.open.capacity.common.exception.service.ServiceException;
import com.open.capacity.common.model.SysMenu;
import com.open.capacity.user.dao.SysMenuDao;
import com.open.capacity.user.dao.SysRoleMenuDao;
import com.open.capacity.user.service.SysMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class SysMenuServiceImpl implements SysMenuService {

	@Autowired
	private SysMenuDao menuDao;
 	@Autowired
	private SysRoleMenuDao roleMenuDao;

	/**
	 * 新增菜单
	 * @param menu
	 * @throws ServiceException
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void save(SysMenu menu)  throws ServiceException{
		menu.setCreateTime(new Date());
		menu.setUpdateTime(menu.getCreateTime());
		menu.setIsMenu(menu.getIsMenu() == null ? 1 : 2);
		menu.setHidden(menu.getHidden() == null ? false : true);
		menuDao.save(menu);
		if (menu.getParentId()==-1){
			SysMenu sysMenu = new SysMenu();
			sysMenu.setParentId(menu.getId());
			sysMenu.setName("默认子菜单");
			sysMenu.setCreateTime(new Date());
			sysMenu.setUpdateTime(menu.getCreateTime());
			sysMenu.setIsMenu(1);
			sysMenu.setHidden(false);
			sysMenu.setSort(menu.getSort()+1);
			menuDao.save(sysMenu);
		}
		log.info("新增菜单：{}", menu);
	}

	@Transactional
	@Override
	public void update(SysMenu menu)  throws ServiceException {
		menu.setUpdateTime(new Date());

		menuDao.updateByOps(menu);
		log.info("修改菜单：{}", menu);
	}

	@Transactional
	@Override
	public void delete(Long id)  throws ServiceException{
		try {
			SysMenu menu = menuDao.findById(id);

			menuDao.deleteByParentId(menu.getId());
			menuDao.delete(id);

			log.info("删除菜单：{}", menu);
		} catch (Exception e) {
			throw new ServiceException(e) ;
		}
	}

	@Transactional
	@Override
	public void setMenuToRole(Long roleId, Set<Long> menuIds)  throws ServiceException {
		try {
			roleMenuDao.delete(roleId, null);

			if (!CollectionUtils.isEmpty(menuIds)) {
				menuIds.forEach(menuId -> {
					roleMenuDao.save(roleId, menuId);
				});
			}
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<SysMenu> findByRoles(Set<Long> roleIds)  throws ServiceException{
		try {
			return roleMenuDao.findMenusByRoleIds(roleIds);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<SysMenu> findAdminMenus() {
		return roleMenuDao.findAdminMenus();
	}

	@Override
	public List<SysMenu> findAll()  throws ServiceException{
		try {
			return menuDao.findAll();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public SysMenu findMenuByMenuId(Long id) {
		return menuDao.findMenuByMenuId(id);
	}

	@Override
	public SysMenu findById(Long id)  throws ServiceException{
		try {
			return menuDao.findById(id);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Set<Long> findMenuIdsByRoleId(Long roleId)  throws ServiceException{
		try {
			return roleMenuDao.findMenuIdsByRoleId(roleId);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<SysMenu> findOnes()  throws ServiceException{
		try {
			return menuDao.findOnes();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Integer delRoleMenus(Integer roleId) {
		return menuDao.delRoleMenus(roleId);
	}

	@Override
	public Integer saveRoleMenus(Integer roleId, String[] resourceIds) {
		return menuDao.saveRoleMenus(roleId, resourceIds);
	}

	@Override
	public Integer delHalfResourceIds(Integer roleId, String[] halfResourceIds) {
		return menuDao.delHalfResourceIds(roleId, halfResourceIds);
	}

	@Override
	public Integer saveMenusByRoleId(Set<Long> roleIds, Long menuId) {
		return menuDao.saveMenusByRoleId(roleIds, menuId);
	}

	@Override
	public List<SysMenu> preMenus() {
		return menuDao.preMenus();
	}


}
