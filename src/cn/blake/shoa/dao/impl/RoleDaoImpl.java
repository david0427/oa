package cn.blake.shoa.dao.impl;

import java.util.Collection;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.blake.shoa.dao.RoleDao;
import cn.blake.shoa.dao.base.BaseDaoImpl;
import cn.blake.shoa.domain.Role;

@Repository("roleDao")
public class RoleDaoImpl extends BaseDaoImpl<Role> implements RoleDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public Collection<Role> getRoles(Integer uid) {
		/**
		 * 1�������еĽ�ɫȫ����ȡ���� 2�����û��ܹ����ʵ��Ľ�ɫ��ȡ���� 3������ѭ�����еĽ�ɫ �ٱ����û��ܹ����ʵ��Ľ�ɫ
		 * ������ڱ��������еĽ�ɫ���е�ĳһ����ɫ�������û��ܹ����ʵ��ģ���ô����checkedΪtrue
		 */
		Collection<Role> allRoles = getAllEntry(Role.class);
		String hql = "from Role r inner join fetch r.users u where u.uid=?";
		Query query = getSession().createQuery(hql);
		query.setInteger(0,uid);
		List<Role> userRoles = query.list();
//		Collection<Role> userRoles = (Collection<Role>) hibernateTemplate.find(
//				"from Role r inner join fetch r.users u where u.uid=?", uid);
		for (Role role : allRoles) {// �������е�role
			for (Role role2 : userRoles) {// �����û��ܹ����ʵ���role
				if (role.getRid().longValue() == role2.getRid().longValue()) {
					role.setChecked(true);
					break;
				}
			}
		}
		return allRoles;
	}
	public Session getSession() {
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
		} catch (Exception e) {
			session = sessionFactory.openSession();//��������,�������Ա���Could not obtain transaction-synchronized Session for current thread at org.
		}
		return session;
	}

}
