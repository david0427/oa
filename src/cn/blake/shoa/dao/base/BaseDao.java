package cn.blake.shoa.dao.base;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

/**
 * 
 * @author Blake.Zhou
 * @see Dao interface(common)
 * @param <T>
 */
public interface BaseDao<T> {
	/**
	 * @see �õ�T��������е�ʵ�����
	 * @return
	 */
	public  Collection<T> getAllEntry(Class<T> entityClass);

	/**
	 * @see Serializable�����Ϳ��Խ������еĻ������ͺ�String����
	 * @param id
	 * @return
	 */
	public T getEntryById(Serializable id,Class<T> entityClass);

	public void saveEntry(T t);

	public void deleteEntry(Serializable id,Class<T> entityClass);

	public void updateEntry(T t);
	
	/**idType example:uid,rid...*/
	public Set<T> getEntrysByIDS(String idType,String ids,Class<T> entityClass);
	
	/**idType example:uid,rid...*/
	public Set<T> getEntrysByIDS(String idType,Object[] ids,Class<T> entityClass);

}
