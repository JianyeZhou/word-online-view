package net.marscore.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.marscore.mapper.IMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * @author Eden
 */
public class BaseService<T> {
    @Autowired
    private IMapper<T> mapper;

    public int insert(T pojo) {
        return mapper.insert(pojo);
    }

    public int update(T pojo) {
        return mapper.update(pojo);
    }

    public void delete(Long id) {
        if (id == null) {
            throw new RuntimeException("删除时id值必须指定");
        }
        mapper.delete(id);
    }

    public List<T> selectList() {
        return mapper.select(null);
    }

    public List<T> selectList(T pojo) {
        return mapper.select(pojo);
    }

    public List<T> selectList(T pojo, String orderBy) {
        //条件排序查询
        PageHelper.orderBy(orderBy);
        return mapper.select(pojo);
    }

    public T selectOne(T pojo) {
        //条件查询
        List<T> result = mapper.select(pojo);
        if (result==null || result.size()==0) {
            return null;
        }
        if (result.size()>1) {
            throw new RuntimeException("查找数据非唯一");
        }
        return result.get(0);
    }

    private T createInstanceAndSetId(Long id) {
        if (id==null) {
            throw new RuntimeException("根据id查询，id不能null");
        }
        ParameterizedType type = (ParameterizedType)this.getClass().getGenericSuperclass();
        Class tClass = (Class)type.getActualTypeArguments()[0];
        try {
            Object t = tClass.newInstance();
            tClass.getDeclaredMethod("setId", Long.class).invoke(t, id);
            return (T)t;
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
                | SecurityException e) {
            throw new RuntimeException("根据id查询，调用setId方法失败");
        }
    }
    public T selectOne(Long id) {
        //根据id进行查询
        List<T> result = mapper.select(createInstanceAndSetId(id));
        if (result==null || result.size()==0) {
            return null;
        }
        if (result.size()>1) {
            throw new RuntimeException("单一数据非唯一");
        }
        return result.get(0);
    }
    public PageInfo<T> page(int pageNum, int pageSize, T pojo) {
        //条件分页查询
        PageHelper.startPage(pageNum, pageSize);
        List<T> list = mapper.select(pojo);
        return new PageInfo<T>(list);
    }
    public PageInfo<T> page(int pageNum, int pageSize, T pojo,String orderBy) {
        //条件分页排序查询
        PageHelper.startPage(pageNum, pageSize, orderBy);
        List<T> list = mapper.select(pojo);
        return new PageInfo<T>(list);
    }
    public boolean isExisted(T pojo) {
        //判断是否存在
        List<T> result = mapper.select(pojo);
        return result!=null && result.size()>0;
    }
}
