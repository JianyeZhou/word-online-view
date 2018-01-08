package net.marscore.mapper;

import java.util.List;

/**
 * @author Eden
 */
public interface IMapper<T> {
    /**
     * 插入数据
     * @return
     */
    int insert(T pojo);

    /**
     * 根据id删除
     * @param id
     * @return
     */
    int delete(Long id);

    /**
     * 更新数据
     * @param pojo
     * @return
     */
    int update(T pojo);

    /**
     * 查询数据
     * @param pojo
     * @return
     */
    List<T> select(T pojo);
}
