

package com.zrn.assistant.common.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 公共字段，自动填充值

 */
@Component
public class FieldMetaObjectHandler implements MetaObjectHandler {
    private final static String CREATE_DATE = "createTime";
    private final static String UPDATE_DATE = "modifyTime";


    @Override
    public void insertFill(MetaObject metaObject) {
        Date date = new Date();
        //创建时间
        strictInsertFill(metaObject, CREATE_DATE, Date.class, date);
        //更新时间
        strictInsertFill(metaObject, UPDATE_DATE, Date.class, date);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        //更新时间
        strictUpdateFill(metaObject, UPDATE_DATE, Date.class, new Date());
    }
}